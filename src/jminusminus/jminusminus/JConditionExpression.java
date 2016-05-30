
package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a conditional expression.
 */

public class JConditionExpression extends JExpression {

    /** The condition operand. */
    protected JExpression condition;

    /** The ms operand. */
    protected JExpression then;
    
    /** The rhs operand. */
    protected JExpression alternate;

    /**
     * Construct an AST node for a binary expression given its line number, the
     * binary operator, and lhs and rhs operands.
     * 
     * @param line
     *            line in which the binary expression occurs in the source file.
     * @param operator
     *            the binary operator.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    protected JConditionExpression(int line, JExpression lhs, JExpression ms, JExpression rhs) {
        super(line);
        this.condition = lhs;
        this.alternate = rhs;
        this.then = ms;
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JConditionalExpression line=\"%d\" type=\"%s\" "
                + "operator=\"%s\">\n", line(), ((type == null) ? "" : type
                .toString()), "? :");
        p.indentRight();
        p.printf("<Condition>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Condition>\n");
        p.printf("<TrueExpr>\n");
        p.indentRight();
        then.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TrueExpr>\n");
        p.printf("<FalseExpr>\n");
        p.indentRight();
        alternate.writeToStdOut(p);
        p.indentLeft();
        p.printf("</FalseExpr>\n");
        p.indentLeft();
        p.printf("</JBinaryExpression>\n");
    }
    

    /**
     * Analyzing the conditional expression means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
    	 condition = condition.analyze(context);
         condition.type().mustMatchExpected(line(), Type.BOOLEAN);
         then = then.analyze(context);
         alternate = alternate.analyze(context);
         
         return this;

    }

    /**
     * Any string concatenation has been rewritten as a JStringConcatenationOp
     * (in analyze()), so code generation here involves simply generating code
     * for loading the operands onto the stack and then generating the
     * appropriate add instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        String elseLabel = output.createLabel();
        String endLabel = output.createLabel();
        
        condition.codegen(output, elseLabel, false);
        then.codegen(output);
        alternate.codegen(output);
        output.addLabel(endLabel);
    }


}

///**
// * The AST node for a plus (+) expression. In j--, as in Java, + is overloaded
// * to denote addition for numbers and concatenation for Strings.
// */
//
//class JPlusOp extends JConditionExpression {
//
//    /**
//     * Construct an AST node for an addition expression given its line number,
//     * and the lhs and rhs operands.
//     * 
//     * @param line
//     *            line in which the addition expression occurs in the source
//     *            file.
//     * @param lhs
//     *            the lhs operand.
//     * @param rhs
//     *            the rhs operand.
//     */
//
//    public JPlusOp(int line, JExpression lhs, JExpression ms, JExpression rhs) {
//        super(line, lhs, ms, rhs);
//    }
//
//}