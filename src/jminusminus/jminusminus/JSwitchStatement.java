// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * The AST node for an switch-statement.
 */

class JSwitchStatement extends JStatement {

    /** Initial test. */
    private JExpression test;

    /** condition literals. */
    ArrayList<JExpression> cases;

    /** Statements. */
    ArrayList<JBlock> statements;

    /**
     * Construct an AST node for an switch-statement given its line number, the test
     * expression, the consequent, and the alternate.
     * 
     * @param line
     *            line in which the if-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param thenPart
     *            then clause.
     * @param elsePart
     *            else clause.
     */

    public JSwitchStatement(int line, JExpression variable, ArrayList<JExpression> cases,
    		ArrayList<JBlock> statements) {
        super(line);
        this.test = variable;
        this.cases = cases;
        this.statements = statements;
    }

    /**
     * Analyzing the switch-statement means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
        //TO DO analyze it
        return this;
    }

    /**
     * Code generation for an switch-statement. We generate code to branch over the
     * consequent if !test; the consequent is followed by an unconditonal branch
     * over (any) alternate.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
//        String elseLabel = output.createLabel();
//        String endLabel = output.createLabel();
//        condition.codegen(output, elseLabel, false);
//        thenPart.codegen(output);
//        if (elsePart != null) {
//            output.addBranchInstruction(GOTO, endLabel);
//        }
//        output.addLabel(elseLabel);
//        if (elsePart != null) {
//            elsePart.codegen(output);
//            output.addLabel(endLabel);
//        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JSwitchStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        test.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        
        for (int i = 0; i < cases.size(); i++) {
        	 p.printf("<CasesBlock>\n");
             p.indentRight();
             cases.get(i).writeToStdOut(p);
             p.indentRight();
             statements.get(i).writeToStdOut(p);
             p.indentLeft();
             p.indentLeft();
             p.printf("</CasesBlock>\n");
        }
        
      
        if (statements.size() > cases.size()) {
        	 p.printf("<CasesBlock>\n");
             p.indentRight();
             p.printf("<Default>\n");
             p.indentRight();
             statements.get(statements.size() - 1).writeToStdOut(p);
             p.indentLeft();
             p.indentLeft();
             p.printf("</CasesBlock>\n");
        }
        p.indentLeft();
        p.printf("</JIfStatement>\n");
    }

}
