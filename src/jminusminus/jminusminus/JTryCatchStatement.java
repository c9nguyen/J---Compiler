// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * The AST node for an if-statement.
 */

class JTryCatchStatement extends JStatement {

    /** Try statement. */
    private JStatement tryStatements;
	
    /** Exceptions to catch. */
    private ArrayList<JVariableDeclarator> exceptions;

    /** Exceptions statements. */
    private ArrayList<JStatement> catchStatements;

    /** Final Statement. */
    private JStatement finalStatement;
    
    /**
     * Construct an AST node for an try-catch-statement given its line number, try statement,
     * excepts, statements.
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

    public JTryCatchStatement(int line, JStatement tryStatements, ArrayList<JVariableDeclarator> exceptions,
    							ArrayList<JStatement> catchStatements, JStatement finalStatement) {
        super(line);
        this.tryStatements = tryStatements;
        this.exceptions = exceptions;
        this.catchStatements = catchStatements;
        this.finalStatement = finalStatement;
    }

    /**
     * Analyzing the if-statement means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
//        condition = (JExpression) condition.analyze(context);
//        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
//        thenPart = (JStatement) thenPart.analyze(context);
//        if (elsePart != null) {
//            elsePart = (JStatement) elsePart.analyze(context);
//        }
    	//TO DO analyze try-catch
        return this;
    }

    /**
     * Code generation for an if-statement. We generate code to branch over the
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
    	//TO DO emit code for try-catch
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTryCatchStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TryStatement>\n");
        p.indentRight();
        tryStatements.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TryStatement>\n");
        
        for (int i = 0; i < exceptions.size(); i++) {
        	p.printf("<CatchException>\n");
            p.indentRight();
        	exceptions.get(i).writeToStdOut(p);
            p.indentLeft();
            p.printf("</CatchException>\n");
        	p.printf("<CatchStatement>\n");
            p.indentRight();
            catchStatements.get(i).writeToStdOut(p);
            p.indentLeft();
            p.printf("</CatchStatement>\n");
        }
        
        if (finalStatement != null) {
            p.printf("<FinalStatement>\n");
            p.indentRight();
            finalStatement.writeToStdOut(p);
            p.indentLeft();
            p.printf("</FinalStatement>\n");
        }
        p.indentLeft();
        p.printf("</JTryCatchStatement>\n");
    }

}
