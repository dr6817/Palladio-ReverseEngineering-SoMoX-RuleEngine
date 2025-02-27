package org.palladiosimulator.somox.analyzer.rules.mocore.surrogate.relation;

import org.palladiosimulator.somox.analyzer.rules.mocore.surrogate.element.Interface;
import org.palladiosimulator.somox.analyzer.rules.mocore.surrogate.element.Signature;

import tools.mdsd.mocore.framework.surrogate.RelationTest;

public class SignatureProvisionRelationTest extends RelationTest<SignatureProvisionRelation, Signature, Interface> {
    @Override
    protected SignatureProvisionRelation createRelation(Signature source, Interface destination,
            boolean isPlaceholder) {
        return new SignatureProvisionRelation(source, destination, isPlaceholder);
    }

    @Override
    protected Signature getUniqueSourceEntity() {
        return Signature.getUniquePlaceholder();
    }

    @Override
    protected Interface getUniqueDestinationEntity() {
        return Interface.getUniquePlaceholder();
    }
}
