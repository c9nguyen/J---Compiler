// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a binary expression. A binary expression has an operator and
 * two operands: a lhs and a rhs.
 */

abstract class JConditionExpression extends JExpression {

    /** The lhs operand. */
    protected JExpression lhs;

    /** The ms operand. */
    protected JExpression ms;
    
    /** The rhs operand. */
    protected JExpression rhs;

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
        this.lhs = lhs;
        this.rhs = rhs;
        this.ms = ms;
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
        lhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Condition>\n");
        p.printf("<TrueExpr>\n");
        p.indentRight();
        ms.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TrueExpr>\n");
        p.printf("<FalseExpr>\n");
        p.indentRight();
        rhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</FalseExpr>\n");
        p.indentLeft();
        p.printf("</JBinaryExpression>\n");
    }

}

/**
 * The AST node for a plus (+) expression. In j--, as in Java, + is overloaded
 * to denote addition for numbers and concatenation for Strings.
 */

class JPlusOp extends JConditionExpression {

    /**
     * Construct an AST node for an addition expression given its line number,
     * and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the addition expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JPlusOp(int line, JExpression lhs, JExpression ms, JExpression rhs) {
        super(line, lhs, ms, rhs);
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
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        if (lhs.type() == Type.STRING || rhs.type() == Type.STRING) {
            return (new JStringConcatenationOp(line, lhs, rhs))
                    .analyze(context);
        } else if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
            type = Type.INT;
        } else {
            type = Type.ANY;
            JAST.compilationUnit.reportSemanticError(line(),
                    "Invalid operand types for +");
        }
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
        if (type == Type.INT) {
            lhs.codegen(output);
            rhs.codegen(output);
            output.addNoArgInstruction(IADD);
        }
    }

}