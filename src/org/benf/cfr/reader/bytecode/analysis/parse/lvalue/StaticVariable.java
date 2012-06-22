package org.benf.cfr.reader.bytecode.analysis.parse.lvalue;

import org.benf.cfr.reader.bytecode.analysis.parse.Expression;
import org.benf.cfr.reader.bytecode.analysis.parse.LValue;
import org.benf.cfr.reader.bytecode.analysis.parse.StatementContainer;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.LValueAssigmentCollector;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifierFactory;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifiers;
import org.benf.cfr.reader.entities.ConstantPool;
import org.benf.cfr.reader.entities.ConstantPoolEntry;
import org.benf.cfr.reader.entities.ConstantPoolEntryFieldRef;
import org.benf.cfr.reader.util.ConfusedCFRException;

/**
 * Created by IntelliJ IDEA.
 * User: lee
 * Date: 22/03/2012
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class StaticVariable implements LValue {

    private final ConstantPool cp;
    private final ConstantPoolEntryFieldRef field;
    private final String className;
    private final String varName;

    public StaticVariable(ConstantPool cp, ConstantPoolEntry field) {
        this.field = (ConstantPoolEntryFieldRef) field;
        this.cp = cp;
        this.className = cp.getUTF8Entry(cp.getClassEntry(this.field.getClassIndex()).getNameIndex()).getValue();
        this.varName = this.field.getLocalName(cp);
    }

    @Override
    public int getNumberOfCreators() {
        throw new ConfusedCFRException("NYI");
    }


    @Override
    public String toString() {
        return className + "." + varName;
    }

    @Override
    public void determineLValueEquivalence(Expression assignedTo, StatementContainer statementContainer, LValueAssigmentCollector lValueAssigmentCollector) {
    }

    @Override
    public LValue replaceSingleUsageLValues(LValueAssigmentCollector lValueAssigmentCollector, SSAIdentifiers ssaIdentifiers) {
        return this;
    }

    @Override
    public SSAIdentifiers collectVariableMutation(SSAIdentifierFactory ssaIdentifierFactory) {
        return new SSAIdentifiers(this, ssaIdentifierFactory);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StaticVariable)) return false;
        StaticVariable other = (StaticVariable) o;
        if (!other.className.equals(className)) return false;
        return other.varName.equals(varName);
    }

    @Override
    public int hashCode() {
        int hashcode = className.hashCode();
        hashcode = (13 * hashcode) + varName.hashCode();
        return hashcode;
    }
}
