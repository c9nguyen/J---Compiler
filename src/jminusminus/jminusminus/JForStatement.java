// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a while-statement.
 */

abstract class JForStatement extends JStatement {
	
	  /**
     * Construct an AST node for a for-statement.
     * 
     * @param line
     *            line in which the for-statement occurs in the source file.
     */

    public JForStatement(int line) {
        super(line);
    }

	@Override
	public JAST analyze(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void codegen(CLEmitter output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToStdOut(PrettyPrinter p) {
		// TODO Auto-generated method stub
		
	}
	
}


/**
 * The AST node for a for-statement.
 */

class JTraditionForStatement extends JForStatement {

    /** Initial variable. */

    private JVariableDeclaration init;

	
    /** Test expression. */
    private JExpression condition;
    
    /** Test expression. */
    private JExpression after;

    /** The body. */
    private JStatement body;
    
    /**
     * Construct an AST node for a for-statement given its line number, the
     * init expression, conidtion expression, after expression, and the body.
     * 
     * @param line
     *            line in which the for-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body.
     */
    public JTraditionForStatement(int line, JVariableDeclaration init, JExpression condition,
    		JExpression after, JStatement body) {
        super(line);
        this.init = init;
        this.condition = condition;
        this.after = after;
        this.body = body;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForStatement analyze(Context context) {
        condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        body = (JStatement) body.analyze(context);
        return this;
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        // Branch out of the loop on the test condition
        // being false
        output.addLabel(test);
        condition.codegen(output, out, false);

        // Codegen body
        body.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<InitExpression>\n");
        p.indentRight();
        init.writeToStdOut(p);
        p.indentLeft();
        p.printf("</InitExpression>\n");
        p.printf("<ConditionExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ConditionExpression>\n");
        if (after != null) {
        	p.printf("<AfterExpression>\n");
        	p.indentRight();
        	after.writeToStdOut(p);
        	p.indentLeft();
        	p.printf("</AfterExpression>\n");
        }
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JForStatement>\n");
    }

}

/**
 * The AST node for a enhanced for-statement.
 */

class JEnhancedForStatement extends JForStatement {

    /** Test expression. */
    private JVariableDeclaration decla;

	
    /** Test expression. */
    private JExpression collection;

    /** The body. */
    private JStatement body;
    
    /**
     * Construct an AST node for a enhanced for-statement given its line number, the
     * declarator expression, collection expression, and the body.
     * 
     * @param line
     *            line in which the for-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body.
     */


    public JEnhancedForStatement(int line, JVariableDeclaration decla, JExpression collection, JStatement body) {
        super(line);
        this.decla = decla;
        this.collection = collection;
        this.body = body;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForStatement analyze(Context context) {
    	collection = collection.analyze(context);
    	collection.type().mustMatchExpected(line(), Type.BOOLEAN);
        body = (JStatement) body.analyze(context);
        return this;
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        // Branch out of the loop on the test condition
        // being false
        output.addLabel(test);
        collection.codegen(output, out, false);

        // Codegen body
        body.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<Declarator>\n");
        p.indentRight();
        decla.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Declarator>\n");
        p.printf("<CollectionExpression>\n");
        p.indentRight();
        collection.writeToStdOut(p);
        p.indentLeft();
        p.printf("</CollectionExpression>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JForStatement>\n");
    }

}
