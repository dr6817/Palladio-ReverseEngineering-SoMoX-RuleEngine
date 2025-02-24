package org.palladiosimulator.somox.analyzer.rules.impl


import static org.palladiosimulator.somox.analyzer.rules.engine.RuleHelper.*
import org.palladiosimulator.somox.analyzer.rules.engine.IRule
import org.palladiosimulator.somox.analyzer.rules.blackboard.RuleEngineBlackboard
import java.nio.file.Path
import org.eclipse.jdt.core.dom.CompilationUnit
import java.util.Map;
import org.jdom2.Document
import java.util.stream.Collectors
import org.apache.log4j.Logger
import org.eclipse.jdt.core.dom.MethodDeclaration
import org.eclipse.jdt.core.dom.ITypeBinding
import java.util.HashMap
import java.util.List
import java.util.Properties
import org.palladiosimulator.somox.analyzer.rules.model.RESTName
import org.palladiosimulator.somox.analyzer.rules.model.HTTPMethod
import java.util.Optional

class SpringRules extends IRule {
    static final Logger LOG = Logger.getLogger(SpringRules)

    public static final String YAML_DISCOVERER_ID = "org.palladiosimulator.somox.discoverer.yaml"
    public static final String XML_DISCOVERER_ID = "org.palladiosimulator.somox.discoverer.xml"
    public static final String PROPERTIES_DISCOVERER_ID = "org.palladiosimulator.somox.discoverer.properties"

	new(RuleEngineBlackboard blackboard) {
		super(blackboard)
	}

	override boolean processRules(Path path) {
		val yamlObjects = blackboard.getPartition(YAML_DISCOVERER_ID)
		val yamls = yamlObjects as Map<String, Iterable<Map<String, Object>>>

		val pomObjects = blackboard.getPartition(XML_DISCOVERER_ID)
		val poms = pomObjects as Map<String, Document>

		val propertiesObjects = blackboard.getPartition(PROPERTIES_DISCOVERER_ID)
		val properties = propertiesObjects as Map<String, Properties>

		val projectRoot = getProjectRoot(path, poms)
		val configRoot = getConfigRoot(poms)
		val bootstrapYaml = getBootstrapYaml(projectRoot, yamls)
		val applicationProperties = getApplicationProperties(projectRoot, properties)

		var applicationName = getApplicationName(bootstrapYaml)
		if (applicationName === null) applicationName = getApplicationName(applicationProperties)

		val projectConfigYaml = getProjectConfigYaml(configRoot, yamls, applicationName)
		
		val applicationYaml = getApplicationYaml(projectRoot, yamls);
		var contextPath = getContextPath(projectConfigYaml)
		
		if (contextPath === null) {
			contextPath = "/"
			if (applicationName !== null) {
				contextPath += applicationName + "/"
			}
		}

		val contextVariables = getContextVariables(applicationYaml);

		val units = blackboard.getCompilationUnitAt(path)

		var containedSuccessful = false
		for (unit : units) {
			containedSuccessful = processRuleForCompUnit(unit, contextPath, contextVariables) || containedSuccessful
		}

		return containedSuccessful
	}

	def getProjectRoot(Path currentPath, Map<String, Document> poms) {
		if (currentPath === null || poms === null) {
			return null
		}
		val closestPom = poms.entrySet.stream
			.map([ entry | Path.of(entry.key) ])
			// Only keep poms above the current compilation unit.
			.filter([ path | currentPath.startsWith(path.parent) ])
			// Take the longest path, which is the pom.xml closest to the compilation unit
			.max([a, b | a.size.compareTo(b.size)])

		if (closestPom.present) {
			return closestPom.get.parent
		} else {
			return null
		}
	}

	def getConfigRoot(Map<String, Document> poms) {
		if (poms === null) {
			return null
		}
		val configRoots = poms.entrySet.stream
			.map[ entry | Path.of(entry.key) -> entry.value ]
			.map[ entry | entry.key -> entry.value.rootElement.getChild("dependencies", entry.value.rootElement.namespace) ]
			.filter[ entry | entry.value !== null ]
			.map[ entry | entry.key -> entry.value.getChildren("dependency", entry.value.namespace) ]
			.filter[ entry | !entry.value.empty ]
			.filter[ entry | entry.value
				.filter[ dependency | dependency.getChildTextTrim("groupId", dependency.namespace) == "org.springframework.cloud" ]
				.exists[ dependency | dependency.getChildTextTrim("artifactId", dependency.namespace) == "spring-cloud-config-server" ]
			]
			.collect(Collectors.toList)
		
		if (configRoots.size > 1) {
			LOG.warn("Multiple Spring config servers, choosing \"" + configRoots.get(0).key.parent + "\" arbitrarily")
		} else if (configRoots.empty) {
			return null
		}
		return configRoots.get(0).key.parent
	}

	def getBootstrapYaml(Path projectRoot, Map<String, Iterable<Map<String, Object>>> yamls) {
		if (projectRoot === null || yamls === null) {
			return null
		}
		val bootstrapYamls =  yamls.entrySet.stream
			.map[ entry | Path.of(entry.key) -> entry.value ]
			.filter[ entry | entry.key.parent == projectRoot.resolve("src/main/resources") ]
			.filter[ entry | val fileName = entry.key.fileName.toString; fileName == "bootstrap.yaml" || fileName == "bootstrap.yml" ]
			.collect(Collectors.toList)

		if (bootstrapYamls.size > 1) {
			LOG.warn("Multiple bootstrap.y[a]mls in " + projectRoot + ", choosing " + projectRoot.relativize(bootstrapYamls.get(0).key) + " arbitrarily")
		} else if (bootstrapYamls.empty) {
			return null
		}
		return bootstrapYamls.get(0).value
	}

	def getApplicationName(Iterable<Map<String, Object>> bootstrapYaml) {
		if (bootstrapYaml === null) {
			return null
		}
		for (yaml : bootstrapYaml) {
			val applicationName = getApplicationName(yaml)
			if (applicationName != "/") {
				return applicationName
			}
		}
		return "/"
	}

	def getApplicationName(Map<String, Object> bootstrapYaml) {
		val spring = bootstrapYaml.get("spring") as Map<String, Object>
		if (spring === null) {
			return null
		}
		val application = spring.get("application") as Map<String, Object>
		if (application === null) {
			return null
		}
		val name = application.get("name") as String
		return name
	}

	def getApplicationName(Properties applicationProperties) {
		if (applicationProperties === null) {
			return null
		}
		return applicationProperties.getProperty("spring.application.name")
	}

	def getProjectConfigYaml(Path configRoot, Map<String, Iterable<Map<String, Object>>> yamls, String projectName) {
		if (configRoot === null || yamls === null || projectName === null) {
			return null
		}
		val projectYamls = yamls.entrySet.stream
			.map[ entry | Path.of(entry.key) -> entry.value ]
			.filter[ entry | entry.key.startsWith(configRoot) ]
			.filter[ entry | val fileName = entry.key.fileName.toString; fileName == projectName + ".yaml" || fileName == projectName + ".yml" ]
			.collect(Collectors.toList)

		if (projectYamls.size > 1) {
			LOG.warn("Multiple " + projectName + ".y[a]mls in config server, choosing " + configRoot.relativize(projectYamls.get(0).key) + " arbitrarily")
		}
		if (projectYamls.empty) {
			return null
		}
		return projectYamls.get(0).value
	}

	def getContextPath(Iterable<Map<String, Object>> projectConfigYaml) {
		if (projectConfigYaml === null) {
			return null
		}
		for (yaml : projectConfigYaml) {
			val contextPath = getContextPath(yaml)
			if (contextPath != "/") {
				return contextPath
			}
		}
		return null
	}

	def getContextPath(Map<String, Object> projectConfigYaml) {
		val server = projectConfigYaml.get("server") as Map<String, Object>
		if (server === null) {
			return null
		}
		val servlet = server.get("servlet") as Map<String, Object>
		if (servlet === null) {
			return null
		}
		val contextPath = servlet.get("context-path") as String
		if (contextPath === null) {
			return null
		}
		return contextPath
	}
	
	def getApplicationProperties(Path projectRoot, Map<String, Properties> properties) {
		if (projectRoot === null || properties === null) {
			return null
		}
		val applicationProperties =  properties.entrySet.stream
			.map[ entry | Path.of(entry.key) -> entry.value ]
			.filter[ entry | entry.key.parent == projectRoot.resolve("src/main/resources") ]
			.filter[ entry | val fileName = entry.key.fileName.toString; fileName == "application.properties" ]
			.collect(Collectors.toList)

		if (applicationProperties.size > 1) {
			LOG.warn("Multiple application.properties in " + projectRoot + ", choosing " + projectRoot.relativize(applicationProperties.get(0).key) + " arbitrarily")
		} else if (applicationProperties.empty) {
			return null
		}
		return applicationProperties.get(0).value
	}

	def getApplicationYaml(Path projectRoot, Map<String, Iterable<Map<String, Object>>> yamls) {
		if (projectRoot === null || yamls === null) {
			return null
		}
		val applicationYamls =  yamls.entrySet.stream
			.map[ entry | Path.of(entry.key) -> entry.value ]
			.filter[ entry | entry.key.parent == projectRoot.resolve("src/main/resources") ]
			.filter[ entry | val fileName = entry.key.fileName.toString; fileName == "application.yaml" || fileName == "application.yml" ]
			.collect(Collectors.toList)

		if (applicationYamls.size > 1) {
			LOG.warn("Multiple application.y[a]mls in " + projectRoot + ", choosing " + projectRoot.relativize(applicationYamls.get(0).key) + " arbitrarily")
		} else if (applicationYamls.empty) {
			return null
		}
		return applicationYamls.get(0).value
	}

	def Map<String, String> getContextVariables(Iterable<Map<String, Object>> applicationYaml) {
		val result = new HashMap<String, String>();
		if (applicationYaml === null || applicationYaml.empty) {
			return result;
		}
		
		return getContextVariables(applicationYaml.get(0));
	}
	
	def Map<String, String> getContextVariables(Map<String, Object> applicationYaml) {
		val result = new HashMap<String, String>();
		if (applicationYaml === null) {
			return result;
		}

		for (entry : applicationYaml.entrySet) {
			if (entry.value instanceof Map) {
				val mapValue = entry.value as Map<String, Object>;
				for (mapEntry : getContextVariables(mapValue).entrySet) {
					result.put(entry.key + "." + mapEntry.key, mapEntry.value);
				}
			} else if (entry.value instanceof List) {
				val extendedMapValue = entry.value as List<Map<String, Object>>;
				for (extendedEntry : extendedMapValue) {
					val extendedKey = extendedEntry.get("key") as String;
					var extendedValue = extendedEntry.get("value") as String;
					if (extendedKey !== null && extendedValue !== null) {
						if (extendedValue.startsWith("${")) {
							val startIndex = extendedValue.indexOf(":");
							val endIndex = extendedValue.indexOf("}", startIndex);
							extendedValue = extendedValue.substring(startIndex + 1, endIndex);
						}
						result.put(entry.key + "." + extendedKey, extendedValue);
					}
				}
			} else if (entry.value instanceof String) {
				var stringValue = entry.value as String;
				if (stringValue.startsWith("${")) {
					val startIndex = stringValue.indexOf(":");
					val endIndex = stringValue.indexOf("}", startIndex);
					stringValue = stringValue.substring(startIndex + 1, endIndex);
				}
				result.put(entry.key, stringValue);
			}
		}

		return result;
	}

	def boolean processRuleForCompUnit(CompilationUnit unit, String contextPath, Map<String, String> contextVariables) {
		val pcmDetector = blackboard.getPCMDetector
		if (pcmDetector === null) {
			return false
		}

		// Abort if there is no CompilationUnit at the specified path
		if (unit === null) {
			return false
		}

		val isService = isUnitAnnotatedWithName(unit, "Service")
		val isController = isUnitAnnotatedWithName(unit, "RestController") || isUnitAnnotatedWithName(unit, "Controller")
		val isClient = isUnitAnnotatedWithName(unit, "FeignClient")
		val isRepository = isRepository(unit)
		val isComponent = isService || isController || isClient || isRepository || isUnitAnnotatedWithName(unit, "Component")

		if (isComponent) {
			pcmDetector.detectComponent(unit)

			getConstructors(unit)
				.stream
				.filter[c | isMethodAnnotatedWithName(c, "Autowired")]
				.flatMap[c | getParameters(c).stream]
				.filter[p | !isParameterAnnotatedWith(p, "Value")]
				.forEach[p | pcmDetector.detectRequiredInterface(unit, p)]
		}

		if (isService || isController) {
			for (f : getFields(unit)) {
				val annotated = isFieldAnnotatedWithName(f, "Autowired")
				if (annotated || isRepository(f.type.resolveBinding)) {
					pcmDetector.detectRequiredInterface(unit, f)
				}
			}
		}

		if (isService || isController) {
			pcmDetector.detectPartOfComposite(unit, getUnitName(unit));
		}

		if (isController) {
			val requestedUnitMapping = getUnitAnnotationStringValue(unit, "RequestMapping");
			var ifaceName = contextPath;
			if (requestedUnitMapping !== null) {
				ifaceName += requestedUnitMapping;
			}
			for (m : getMethods(unit)) {
				val annotated = hasMapping(m);
				if (annotated) {
					var requestedMapping = getMapping(m);
					if (requestedMapping !== null) {
						requestedMapping = substituteVariables(requestedMapping, contextVariables);
						var methodName = ifaceName + "/" + requestedMapping;
						methodName = replaceArgumentsWithWildcards(methodName);
						val httpMethod = getHTTPMethod(m);
						pcmDetector.detectCompositeProvidedOperation(unit, m.resolveBinding, new RESTName(methodName, httpMethod));
					}
				}
			}
		}

		if (isClient) {
			val requestedUnitMapping = getUnitAnnotationStringValue(unit, "RequestMapping");
			// Do not include the context path since client requests are expressed as uniquely identifiable paths.
			var ifaceName = "";
			if (requestedUnitMapping !== null) {
				ifaceName += requestedUnitMapping;
			}
			for (m : getMethods(unit)) {
				val annotated = hasMapping(m);
				if (annotated) {
					var requestedMapping = getMapping(m);
					if (requestedMapping !== null) {
						requestedMapping = substituteVariables(requestedMapping, contextVariables);
						var methodName = ifaceName + "/" + requestedMapping;
						methodName = replaceArgumentsWithWildcards(methodName);
						val httpMethod = getHTTPMethod(m);
						pcmDetector.detectCompositeRequiredInterface(unit, new RESTName(methodName, httpMethod));
					}
				}
			}
		}

		// TODO: This can be solved differently now, for all implemented interfaces instead of just one.
		var inFs = getAllInterfaces(unit)
		val isImplementingOne = inFs.size == 1

		if (isComponent && isImplementingOne) {
			var firstIn = inFs.get(0)
			pcmDetector.detectProvidedInterface(unit, firstIn.resolveBinding)
			for (m : getMethods(firstIn)) {
				pcmDetector.detectProvidedOperation(unit, firstIn.resolveBinding, m)
			}
		}

		return true;
	}
	
	def substituteVariables(String string, Map<String, String> contextVariables) {
		var result = string;

		while (result.contains("${")) {
			val startIndex = result.indexOf("${");
			val endIndex = result.indexOf("}", startIndex);
			val key = result.substring(startIndex + 2, endIndex);
			val value = contextVariables.get(key);
			if (value !== null) {
				result = result.substring(0, startIndex) + value + result.substring(endIndex + 1);
			} else {
				result = result.substring(0, startIndex) + "ERROR_COULD_NOT_RESOLVE" + result.substring(endIndex + 1);
				LOG.error("Could not resolve key " + key);
			}
		}

		return result;
	}
	
	def replaceArgumentsWithWildcards(String methodName) {
		var newName = methodName.replaceAll("\\{.*\\}", "*")
						 .replaceAll("[\\*\\/]*$", "")
						 .replaceAll("[\\*\\/]*\\[", "[")
		newName = "/" + newName
		newName = newName.replaceAll("/+", "/")
		return newName
	}

	def hasMapping(MethodDeclaration m) {
		return isMethodAnnotatedWithName(m, "RequestMapping")
		    ||isMethodAnnotatedWithName(m, "GetMapping")
    		||isMethodAnnotatedWithName(m, "PostMapping")
	    	||isMethodAnnotatedWithName(m, "PutMapping")
		    ||isMethodAnnotatedWithName(m, "DeleteMapping")
    		||isMethodAnnotatedWithName(m, "PatchMapping");
	}

	def getMapping(MethodDeclaration m) {
		val requestMapping = getMappingString(m, "RequestMapping");
		if (requestMapping !== null) {
			return requestMapping;
		}

		val getMapping = getMappingString(m, "GetMapping");
		if (getMapping !== null) {
			return getMapping;
		}

		val postMapping = getMappingString(m, "PostMapping");
		if (postMapping !== null) {
			return postMapping;
		}

		val putMapping = getMappingString(m, "PutMapping");
		if (putMapping !== null) {
			return putMapping;
		}

		val deleteMapping = getMappingString(m, "DeleteMapping");
		if (deleteMapping !== null) {
			return deleteMapping;
		}

		val patchMapping = getMappingString(m, "PatchMapping");
		if (patchMapping !== null) {
			return patchMapping;
		}
		
		return null;
	}

	def getHTTPMethod(MethodDeclaration m) {
		val requestMapping = getMappingString(m, "RequestMapping");
		if (requestMapping !== null) {
			return Optional.empty;
		}

		val getMapping = getMappingString(m, "GetMapping");
		if (getMapping !== null) {
			return Optional.of(HTTPMethod.GET);
		}

		val postMapping = getMappingString(m, "PostMapping");
		if (postMapping !== null) {
			return Optional.of(HTTPMethod.POST);
		}

		val putMapping = getMappingString(m, "PutMapping");
		if (putMapping !== null) {
			return Optional.of(HTTPMethod.PUT);
		}

		val deleteMapping = getMappingString(m, "DeleteMapping");
		if (deleteMapping !== null) {
			return Optional.of(HTTPMethod.DELETE);
		}

		val patchMapping = getMappingString(m, "PatchMapping");
		if (patchMapping !== null) {
			return Optional.of(HTTPMethod.PATCH);
		}
		
		return null;
	}

	def getMappingString(MethodDeclaration m, String annotationName) {
		val value = getMethodAnnotationStringValue(m, annotationName);
		if (value !== null) {
			return value;
		}

		val path = getMethodAnnotationStringValue(m, annotationName, "path");
		return path;
	}
	
	def isRepository(ITypeBinding binding) {
		return isImplementingOrExtending(binding, "Repository")
			|| isImplementingOrExtending(binding, "CrudRepository")
			|| isImplementingOrExtending(binding, "JpaRepository")
			|| isImplementingOrExtending(binding, "PagingAndSortingRepository")
			|| isImplementingOrExtending(binding, "MongoRepository")
	}

	def isRepository(CompilationUnit unit) {
		return isUnitAnnotatedWithName(unit, "Repository") 
			|| isImplementingOrExtending(unit, "Repository")
			|| isImplementingOrExtending(unit, "CrudRepository")
			|| isImplementingOrExtending(unit, "JpaRepository")
			|| isImplementingOrExtending(unit, "PagingAndSortingRepository")
			|| isImplementingOrExtending(unit, "MongoRepository")
	}
}
