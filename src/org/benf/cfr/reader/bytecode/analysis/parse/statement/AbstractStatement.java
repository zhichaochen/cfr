package org.benf.cfr.reader.bytecode.analysis.parse.statement;

import org.benf.cfr.reader.bytecode.analysis.parse.Expression;
import org.benf.cfr.reader.bytecode.analysis.parse.LValue;
import org.benf.cfr.reader.bytecode.analysis.parse.Statement;
import org.benf.cfr.reader.bytecode.analysis.parse.StatementContainer;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.CreationCollector;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.LValueAssigmentCollector;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifierFactory;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifiers;
import org.benf.cfr.reader.util.ConfusedCFRException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lee
 * Date: 15/03/2012
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStatement implements Statement {
    private StatementContainer container;

    @Override
    public void setContainer(StatementContainer container) {
        if (container == null) throw new ConfusedCFRException("Trying to setContainer null!");
        this.container = container;
    }

    @Override
    public LValue getCreatedLValue() {
        return null;
    }

    @Override
    public void getLValueEquivalences(LValueAssigmentCollector lValueAssigmentCollector) {
    }

    @Override
    public void collectObjectCreation(CreationCollector creationCollector) {
    }

    @Override
    public SSAIdentifiers collectLocallyMutatedVariables(SSAIdentifierFactory ssaIdentifierFactory) {
        return new SSAIdentifiers();
    }

    @Override
    public StatementContainer getContainer() {
        if (container == null) throw new ConfusedCFRException("Null container!");
        return container;
    }

    @Override
    public Expression getRValue() {
        throw new ConfusedCFRException("Not appropriate here.");
    }

    protected Statement getTargetStatement(int idx) {
        return container.getTargetStatement(idx);
    }

    @Override
    public boolean condenseWithNextConditional() {
        return false;
    }

    @Override
    public boolean condenseWithPriorIfStatement(IfStatement ifStatement) {
        return false;
    }

    @Override
    public boolean isCompound() {
        return false;
    }

    @Override
    public List<Statement> getCompoundParts() {
        throw new ConfusedCFRException("Should not be calling getCompoundParts on this statement");
    }


}
