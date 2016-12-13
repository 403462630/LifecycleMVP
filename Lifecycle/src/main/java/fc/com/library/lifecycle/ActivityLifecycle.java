package fc.com.library.lifecycle;

import android.os.Bundle;

/**
 * Created by rjhy on 16-11-17
 */
public final class ActivityLifecycle extends Lifecycle<ActivityLifecycleListener> {

    void onSaveInstanceState(Bundle outState){
        for (ActivityLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onSaveInstanceState(outState);
        }
    }
}
