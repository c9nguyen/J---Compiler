// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a condition statement. A condition statement has a condition and
 * two expressions: for true and false.
 */

public class JConditionExpression extends JExpression {

    /** The condition expression. */
    protected JExpression condition;

    /** The true expression. */
    protected JExpression trueExpression;
    
    /** The false expression. */
    protected JExpression falseExpression;

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
        this.falseExpression = rhs;
        this.trueExpression = ms;
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
        trueExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TrueExpr>\n");
        p.printf("<FalseExpr>\n");
        p.indentRight();
        falseExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</FalseExpr>\n");
        p.indentLeft();
        p.printf("</JBinaryExpression>\n");
    }
    

    /**
     * Analysis involves first analyzing the operands. If this is a string
     * concatenation, we rewrite the subtree to make that explicit (and analyze
     * that). Otherwise we check the types of the addition operands and compute
     * the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
    	condition = (JExpression) condition.analyze(context);
    	condition.type.mustMatchExpected(line(), Type.BOOLEAN);
    	trueExpression = (JExpression) trueExpression.analyze(context);
    	type = trueExpression.type();
    	falseExpression = (JExpression) falseExpression.analyze(context);
    	falseExpression.type().mustMatchExpected(line(), type);

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
    		String falseLabel = output.createLabel();
    		String endLabel = output.createLabel();
            condition.codegen(output, falseLabel, false);
            trueExpression.codegen(output);
            output.addBranchInstruction(GOTO, endLabel);
            output.addLabel(falseLabel);
            falseExpression.codegen(output);
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