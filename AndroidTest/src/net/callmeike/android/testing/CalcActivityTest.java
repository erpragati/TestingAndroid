/* $Id: $
 */
package net.callmeike.android.testing;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.EditText;
import android.widget.TextView;

import net.callmeike.android.testing.calc.Calculator;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class CalcActivityTest extends ActivityInstrumentationTestCase2<TestTargetActivity> {
    private TestTargetActivity activity;

    /**
     *
     */
    public CalcActivityTest() { super(TestTargetActivity.class); }

    /**
     * @see android.test.ActivityInstrumentationTestCase2#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    /**
     *
     */
    @UiThreadTest
    public void testGetAndClear() {
        EditText enter = (EditText) activity.findViewById(R.id.input);

        assertNotNull(enter);

        final Integer content = Integer.valueOf(666);
        enter.setText(content.toString());

        assertEquals(content, activity.getAndClear());
        assertEquals("", enter.getText().toString());
    }

    /**
     *
     */
    @UiThreadTest
    public void testUnderflow() {
        activity.eval(Calculator.Op.ADD);
        TextView top = (TextView) activity.findViewById(R.id.top);
        assertEquals("Stack underflow", top.getText().toString());
    }
}
