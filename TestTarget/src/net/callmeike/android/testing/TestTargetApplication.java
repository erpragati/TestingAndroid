/* $Id: $
 */
package net.callmeike.android.testing;

import android.app.Application;


/**
 *
 * @version $Revision: $
 * @author <a href="mailto:blake.meike@gmail.com">G. Blake Meike</a>
 */
public class TestTargetApplication extends Application {
    /**
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();

        if (0 == getApplicationInfo().uid) {
            throw new IllegalStateException("Cannot run as root!");
        }
    }
}
