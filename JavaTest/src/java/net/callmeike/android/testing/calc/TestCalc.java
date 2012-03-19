/* $Id: $
 */
package net.callmeike.android.testing.calc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class TestCalc {
    private Calculator calc;

    class MockChangeListener implements Calculator.StackChangeListener {

        public void expect(int stackSize, int stackTop) {
            // set expected size and top value
            // for next call to stackChanged
        }

        @Override
        public void stackChanged(List<Integer> contents) {
            // verify that the stack is the right size
            // and that the right thing is on top.
        }
    }

    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        fail("Not yet implemented");
    }

    @Test
    public void testSub() {
        fail("Not yet implemented");
    }

    @Test
    public void testMul() {
        fail("Not yet implemented");
    }

    @Test
    public void testDiv() {
        fail("Not yet implemented");
    }

    @Test
    public void testDivideByZero() {
        fail("Not yet implemented");
    }

    @Test
    public void testUnderflow() {
        fail("Not yet implemented");
    }
}
