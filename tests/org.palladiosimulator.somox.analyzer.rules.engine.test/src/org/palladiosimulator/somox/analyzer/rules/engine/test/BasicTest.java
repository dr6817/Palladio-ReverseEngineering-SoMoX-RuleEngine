package org.palladiosimulator.somox.analyzer.rules.engine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.PrimitiveTypeEnum;
import org.palladiosimulator.pcm.repository.impl.RepositoryImpl;
import org.palladiosimulator.somox.analyzer.rules.all.DefaultRule;
import org.palladiosimulator.somox.analyzer.rules.blackboard.RuleEngineBlackboard;
import org.palladiosimulator.somox.analyzer.rules.main.RuleEngineAnalyzer;
import org.palladiosimulator.somox.analyzer.rules.main.RuleEngineException;
import org.palladiosimulator.somox.discoverer.Discoverer;
import org.palladiosimulator.somox.discoverer.EmfTextDiscoverer;
import org.palladiosimulator.somox.discoverer.JavaDiscoverer;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class BasicTest extends RuleEngineTest {

    private static final String PROJECT_NAME = "BasicProject";
    private static final DefaultRule[] RULES = { DefaultRule.JAX_RS, DefaultRule.JAX_RS_EMFTEXT };

    protected BasicTest() {
        super(PROJECT_NAME, RULES);
    }

    /**
     * Tests the basic functionality of the RuleEngineAnalyzer. Requires it to execute without an
     * exception and produce an output file.
     */
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void test(boolean emfText) {
        assertTrue(new File(OUT_DIR.appendSegment("pcm.repository")
            .devicePath()).exists());
    }

    @Disabled("This bug is inherited from Palladio, this can only be fixed after it is fixed there.")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void testShort(boolean emfText) {
        OperationInterface conflictingMethods = getConflictingMethods(getInterfaces(emfText));
        for (OperationSignature sig : conflictingMethods.getSignatures__OperationInterface()) {
            for (Parameter param : sig.getParameters__OperationSignature()) {
                if (param.getParameterName()
                    .equals("shortArg")) {
                    assertTrue(param.getDataType__Parameter() instanceof PrimitiveDataType);
                    PrimitiveDataType primDT = (PrimitiveDataType) param.getDataType__Parameter();
                    assertNotEquals(PrimitiveTypeEnum.INT, primDT.getType());
                }
            }
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void testArray(boolean emfText) {
        OperationInterface conflictingMethods = getConflictingMethods(getInterfaces(emfText));
        for (OperationSignature sig : conflictingMethods.getSignatures__OperationInterface()) {
            for (Parameter param : sig.getParameters__OperationSignature()) {
                if (param.getParameterName()
                    .equals("intArray")) {
                    assertTrue(param.getDataType__Parameter() instanceof CollectionDataType);
                    CollectionDataType collDT = (CollectionDataType) param.getDataType__Parameter();
                    assertTrue(collDT.getInnerType_CollectionDataType() instanceof PrimitiveDataType);
                    PrimitiveDataType primDT = (PrimitiveDataType) collDT.getInnerType_CollectionDataType();
                    assertEquals(PrimitiveTypeEnum.INT, primDT.getType());
                }
            }
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void testVararg(boolean emfText) {
        OperationInterface conflictingMethods = getConflictingMethods(getInterfaces(emfText));
        for (OperationSignature sig : conflictingMethods.getSignatures__OperationInterface()) {
            for (Parameter param : sig.getParameters__OperationSignature()) {
                if (param.getParameterName()
                    .equals("longVararg")) {
                    assertTrue(param.getDataType__Parameter() instanceof CollectionDataType);
                    CollectionDataType collDT = (CollectionDataType) param.getDataType__Parameter();
                    assertTrue(collDT.getInnerType_CollectionDataType() instanceof PrimitiveDataType);
                    PrimitiveDataType primDT = (PrimitiveDataType) collDT.getInnerType_CollectionDataType();
                    assertEquals(PrimitiveTypeEnum.LONG, primDT.getType());
                }
            }
        }
    }

    /**
     * The RuleEngine produced inconsistent results if executed multiple times. Arguments and
     * methods appear multiple times. This probably has something to do with (discouraged) static
     * states somewhere in the stack.
     * 
     * @throws ModelAnalyzerException
     *             forwarded from RuleEngineAnalyzer. Should cause the test to fail.
     * @throws UserCanceledException
     *             should not happen since no user is in the loop.
     * @throws JobFailedException
     *             forwarded from JavaDiscoverer. Should cause the test to fail.
     */
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void testRepeatability(boolean emfText) throws RuleEngineException, JobFailedException, UserCanceledException {
        OperationInterface conflictingMethods = getConflictingMethods(getInterfaces(emfText));
        int firstIntArgCount = 0;
        for (OperationSignature sig : conflictingMethods.getSignatures__OperationInterface()) {
            for (Parameter param : sig.getParameters__OperationSignature()) {
                if (param.getParameterName()
                    .equals("intArg")) {
                    firstIntArgCount++;
                }
            }
        }

        // Run the RuleEngine again on the same project
        RuleEngineBlackboard blackboard = new RuleEngineBlackboard();
        RuleEngineAnalyzer analyzer = new RuleEngineAnalyzer(blackboard);
        Discoverer discoverer = emfText ? new EmfTextDiscoverer() : new JavaDiscoverer();
        discoverer.create(getConfig(emfText), blackboard)
            .execute(null);

        analyzer.analyze(getConfig(emfText), null);

        String discovererSegment = emfText ? "emfText" : "jdt";
        RepositoryImpl repo = loadRepository(OUT_DIR.appendSegment(discovererSegment).appendSegment("pcm.repository"));
        conflictingMethods = getConflictingMethods(repo.getInterfaces__Repository());

        int secondIntArgCount = 0;
        for (OperationSignature sig : conflictingMethods.getSignatures__OperationInterface()) {
            for (Parameter param : sig.getParameters__OperationSignature()) {
                if (param.getParameterName()
                    .equals("intArg")) {
                    secondIntArgCount++;
                }
            }
        }

        assertEquals(firstIntArgCount, secondIntArgCount);
    }

    private OperationInterface getConflictingMethods(List<Interface> interfaces) {
        OperationInterface conflictingMethods = null;
        for (Interface iface : interfaces) {
            if (iface.getEntityName()
                .equals("basic_ConflictingMethods")) {
                assertTrue(iface instanceof OperationInterface);
                conflictingMethods = (OperationInterface) iface;
            }
        }
        assertNotNull(conflictingMethods);
        return conflictingMethods;
    }
}
