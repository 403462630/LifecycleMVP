package fc.com.library.lifecycle;


import android.os.Bundle;

/**
 * Created by rjhy on 16-11-18
 */
public interface ActivityLifecycleListener extends LifecycleListener {
    public void onSaveInstanceState(Bundle outState);
}
