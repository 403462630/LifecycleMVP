package fc.com.library.lifecycle;

import android.os.Bundle;

/**
 * Created by rjhy on 16-11-17
 */
interface LifecycleListener {
    void onCreate(Bundle outState);

    void onResume();

    void onStart();

    void onPause();

    void onStop();

    void onDestroy();
}
