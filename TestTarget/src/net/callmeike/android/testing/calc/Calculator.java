/* $Id: $
 */
package net.callmeike.android.testing.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class Calculator {
    public static enum Op { ADD, SUB, MUL, DIV; }

    /** StackListener */
    public static interface StackChangeListener { void stackChanged(List<Integer> contents); }

    /**
     * CalculationException
     */
    public static class CalculationException extends Exception {
        /** @param detailMessage */
        public CalculationException(String detailMessage) { super(detailMessage); }
    }

    private final Stack<Integer> stack = new Stack<Integer>();

    private StackChangeListener listener;

    /**
     * @param listener
     */
    public void setStackChangeListener(StackChangeListener listener) { this.listener = listener; }

    /**
     * @param n
     */
    public void push(Integer n) {
        if (null != n) {
            stack.push(n);
            notifyListener();
        }
    }

    /**
     * @param op
     * @throws CalculationException
     */
    public void eval(Op op) throws CalculationException {
        if (2 > stack.size()) { throw new CalculationException("Stack underflow"); }

        int n1 = stack.pop().intValue();
        int n2  = stack.pop().intValue();

        int r = 0;
        switch (op) {
            case ADD: r = n2 + n1; break;
            case SUB: r = n2 - n1; break;
            case MUL: r = n2 * n1; break;
            case DIV:
                if (0 == n1) { throw new CalculationException("Divide by zero"); }
                r = n2 / n1;
                break;
        }

        stack.push(Integer.valueOf(r));

        notifyListener();
    }

    private void notifyListener() {
        if (null != listener) {
            listener.stackChanged(new ArrayList<Integer>(stack));
        }
    }
}
