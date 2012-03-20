/* $Id: $
 */
package net.callmeike.android.testing.calc;

import java.util.List;

import junit.framework.TestCase;

import net.callmeike.android.testing.calc.Calculator;
import net.callmeike.android.testing.calc.Calculator.CalculationException;

import org.junit.Before;
import org.junit.Test;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class TestCalc extends TestCase {
    private Calculator calc;
    private MockChangeListener listener;

    class MockChangeListener implements Calculator.StackChangeListener {
        private int size;
        private int top;

         public void expect(int stackSize, int stackTop) {
            size = stackSize;
            top = stackTop;
        }

        @Override
        public void stackChanged(List<Integer> contents) {
            int n = contents.size();
            assertEquals(size, n);
            assertEquals(Integer.valueOf(top), contents.get(n - 1));
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
        listener = new MockChangeListener();
        calc.setStackChangeListener(listener);
    }

    /**
     * Test addition
     */
    @Test
    public void testAdd() {
        listener.expect(1, 9);
        calc.push(Integer.valueOf(9));
        listener.expect(2, 3);
        calc.push(Integer.valueOf(3));
        listener.expect(1, 12);
        try { calc.eval(Calculator.Op.ADD); }
        catch (CalculationException e) { fail(e.getMessage()); }
    }

    /**
     * Test subtraction
     */
    @Test
    public void testSub() {
        listener.expect(1, 9);
        calc.push(Integer.valueOf(9));
        listener.expect(2, 3);
        calc.push(Integer.valueOf(3));
        listener.expect(1, 6);
        try { calc.eval(Calculator.Op.SUB); }
        catch (CalculationException e) { fail(e.getMessage()); }
    }

    /**
     * Test multiplication
     */
    @Test
    public void testMul() {
        listener.expect(1, 9);
        calc.push(Integer.valueOf(9));
        listener.expect(2, 3);
        calc.push(Integer.valueOf(3));
        listener.expect(1, 27);
        try { calc.eval(Calculator.Op.MUL); }
        catch (CalculationException e) { fail(e.getMessage()); }
    }

    /**
     * Test division
     */
    @Test
    public void testDiv() {
        listener.expect(1, 9);
        calc.push(Integer.valueOf(9));
        listener.expect(2, 3);
        calc.push(Integer.valueOf(3));
        listener.expect(1, 3);
        try { calc.eval(Calculator.Op.DIV); }
        catch (CalculationException e) { fail(e.getMessage()); }
    }

    /**
     * Test division
     */
    @Test
    public void testDivByZero() {
        listener.expect(1, 9);
        calc.push(Integer.valueOf(9));
        listener.expect(2, 0);
        calc.push(Integer.valueOf(0));
        try {
            calc.eval(Calculator.Op.DIV);
            fail("expected div-by-zero");
        }
        catch (CalculationException e) { }
    }

    /**
     * Test underflow
     */
    @Test
    public void testUnderflow() {
        try {
            calc.eval(Calculator.Op.DIV);
            fail("expected underflow");
        }
        catch (CalculationException e) {  }
    }
}
