package org.benf.cfr.reader.bytecode.analysis.parse.lvalue;

import org.benf.cfr.reader.bytecode.analysis.parse.Expression;
import org.benf.cfr.reader.bytecode.analysis.parse.LValue;
import org.benf.cfr.reader.bytecode.analysis.parse.StatementContainer;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.LValueAssigmentCollector;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifierFactory;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifiers;
import org.benf.cfr.reader.util.ConfusedCFRException;

/**
 * Created by IntelliJ IDEA.
 * User: lee
 * Date: 22/03/2012
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class ArrayVariable implements LValue {

    private Expression arrayIndex;

    public ArrayVariable(Expression arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public int getNumberOfCreators() {
        throw new ConfusedCFRException("NYI");
    }


    @Override
    public String toString() {
        return arrayIndex.toString();
    }

    @Override
    public void determineLValueEquivalence(Expression assignedTo, StatementContainer statementContainer, LValueAssigmentCollector lValueAssigmentCollector) {
    }

    @Override
    public LValue replaceSingleUsageLValues(LValueAssigmentCollector lValueAssigmentCollector, SSAIdentifiers ssaIdentifiers) {
        arrayIndex = arrayIndex.replaceSingleUsageLValues(lValueAssigmentCollector, ssaIdentifiers);
        return this;
    }

    @Override
    public SSAIdentifiers collectVariableMutation(SSAIdentifierFactory ssaIdentifierFactory) {
        return new SSAIdentifiers();
    }
}
