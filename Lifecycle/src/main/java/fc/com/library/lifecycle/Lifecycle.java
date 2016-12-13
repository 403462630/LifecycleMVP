package fc.com.library.lifecycle;

import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by rjhy on 16-11-17
 */
public abstract class Lifecycle<T extends LifecycleListener> {

    private boolean isResume;
    private boolean isStart;
    private boolean isDestroy;
    private boolean isCreated;

    protected final CopyOnWriteArraySet<T> lifecycleListeners =
            new CopyOnWriteArraySet();

    public final boolean isResume() {
        return isResume;
    }

    public final boolean isStart() {
        return isStart;
    }

    public final boolean isDestroy() {
        return isDestroy;
    }

    public final boolean isCreated() {
        return isCreated;
    }

    final boolean addListener(T listener) {
        return lifecycleListeners.add(listener);
    }

    final void removeListener(T listener) {
        lifecycleListeners.remove(listener);
    }

    void onCreate(Bundle outState) {
        isCreated = true;
        for (T lifecycleListener : lifecycleListeners) {
            lifecycleListener.onCreate(outState);
        }
    }

    void clear() {
        lifecycleListeners.clear();
    }

    void onResume() {
        isResume = true;
        for (T lifecycleListener : lifecycleListeners) {
            lifecycleListener.onResume();
        }
    }

    void onStart() {
        isStart = true;
        for (T lifecycleListener : lifecycleListeners) {
            lifecycleListener.onStart();
        }
    }

    void onPause() {
        isResume = false;
        for (T lifecycleListener : lifecycleListeners) {
            lifecycleListener.onPause();
        }
    }

    void onStop() {
        isStart = false;
        for (T lifecycleListener : lifecycleListeners) {
            lifecycleListener.onStop();
        }
    }

    void onDestroy() {
        isDestroy = true;
        for (T lifecycleListener : lifecycleListeners) {
            lifecycleListener.onDestroy();
        }
    }
}
