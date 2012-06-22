package org.benf.cfr.reader.bytecode.analysis.parse.statement;

import org.benf.cfr.reader.bytecode.analysis.parse.Expression;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.LValueAssigmentCollector;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.SSAIdentifiers;
import org.benf.cfr.reader.bytecode.analysis.structured.StructuredStatement;
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredThrow;
import org.benf.cfr.reader.util.output.Dumper;

/**
 * Created by IntelliJ IDEA.
 * User: lee
 * Date: 16/03/2012
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class ThrowStatement extends ReturnStatement {
    private Expression rvalue;

    public ThrowStatement(Expression rvalue) {
        this.rvalue = rvalue;
    }

    @Override
    public void dump(Dumper dumper) {
        dumper.print("throw " + rvalue.toString() + ";\n");
    }

    @Override
    public void replaceSingleUsageLValues(LValueAssigmentCollector lValueAssigmentCollector, SSAIdentifiers ssaIdentifiers) {
        this.rvalue = rvalue.replaceSingleUsageLValues(lValueAssigmentCollector, ssaIdentifiers);
    }

    @Override
    public StructuredStatement getStructuredStatement() {
        return new StructuredThrow(rvalue);
    }
}
