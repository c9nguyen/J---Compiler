
package jminusminus;

import static jminusminus.CLConstants.*;

/**
<<<<<<< HEAD
 * The AST node for a conditional expression.
=======
 * The AST node for a condition statement. A condition statement has a condition and
 * two expressions: for true and false.
>>>>>>> Chinh
 */

public class JConditionExpression extends JExpression {

<<<<<<< HEAD
    /** The condition operand. */
    protected JExpression condition;

    /** The ms operand. */
    protected JExpression then;
    
    /** The rhs operand. */
    protected JExpression alternate;
=======
    /** The condition expression. */
    protected JExpression condition;

    /** The true expression. */
    protected JExpression trueExpression;
    
    /** The false expression. */
    protected JExpression falseExpression;
>>>>>>> Chinh

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
<<<<<<< HEAD
        this.alternate = rhs;
        this.then = ms;
=======
        this.falseExpression = rhs;
        this.trueExpression = ms;
>>>>>>> Chinh
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
<<<<<<< HEAD
        then.writeToStdOut(p);
=======
        trueExpression.writeToStdOut(p);
>>>>>>> Chinh
        p.indentLeft();
        p.printf("</TrueExpr>\n");
        p.printf("<FalseExpr>\n");
        p.indentRight();
<<<<<<< HEAD
        alternate.writeToStdOut(p);
=======
        falseExpression.writeToStdOut(p);
>>>>>>> Chinh
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
<<<<<<< HEAD
    	 condition = condition.analyze(context);
         condition.type().mustMatchExpected(line(), Type.BOOLEAN);
         then = then.analyze(context);
         alternate = alternate.analyze(context);
         
         return this;

=======
    	condition = (JExpression) condition.analyze(context);
    	condition.type.mustMatchExpected(line(), Type.BOOLEAN);
    	trueExpression = (JExpression) trueExpression.analyze(context);
    	type = trueExpression.type();
    	falseExpression = (JExpression) falseExpression.analyze(context);
    	falseExpression.type().mustMatchExpected(line(), type);

    	return this;
>>>>>>> Chinh
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
<<<<<<< HEAD
        String elseLabel = output.createLabel();
        String endLabel = output.createLabel();
        
        condition.codegen(output, elseLabel, false);
        then.codegen(output);
        alternate.codegen(output);
        output.addLabel(endLabel);
=======
    		String falseLabel = output.createLabel();
    		String endLabel = output.createLabel();
            condition.codegen(output, falseLabel, false);
            trueExpression.codegen(output);
            output.addBranchInstruction(GOTO, endLabel);
            output.addLabel(falseLabel);
            falseExpression.codegen(output);
            output.addLabel(endLabel);
>>>>>>> Chinh
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