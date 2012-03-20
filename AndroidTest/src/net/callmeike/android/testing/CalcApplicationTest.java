package net.callmeike.android.testing;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.test.ApplicationTestCase;


/**
 * CalcApplicationTest
 */
public class CalcApplicationTest extends ApplicationTestCase<TestTargetApplication> {
    private static class TestContext extends ContextWrapper {
        private final int uid;

        public TestContext(Context ctxt, int uid) {
            super(ctxt);
            this.uid = uid;
        }

        @Override
        public ApplicationInfo getApplicationInfo() {
            final ApplicationInfo appInfo =  new ApplicationInfo();
            appInfo.uid = uid;
            return appInfo;
        }
    }

    /**
     *
     */
    public CalcApplicationTest() { super(TestTargetApplication.class); }

    /**
     *
     */
    public void testRunAsUser() {
        setContext(new TestContext(getSystemContext(), 1001));
        createApplication();
    }

    /**
     *
     */
    public void testRunAsRoot() {
        setContext(new TestContext(getSystemContext(), 0));
        try {
            createApplication();
            fail("Should not run as root");
        }
        catch (Exception e) { }
    }
}
