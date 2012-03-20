/* $Id: $
 */
package net.callmeike.android.testing;

import android.test.ActivityInstrumentationTestCase2;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class CalcActivityTest extends ActivityInstrumentationTestCase2<TestTargetActivity> {

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
    }
}
