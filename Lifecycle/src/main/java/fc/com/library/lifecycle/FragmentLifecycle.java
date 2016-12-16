package fc.com.library.lifecycle;

import android.os.Bundle;

/**
 * Created by rjhy on 16-11-17
 */
public final class FragmentLifecycle extends Lifecycle<FragmentLifecycleListener> {

    private boolean isAttached;
    private boolean isViewCreated;

    public FragmentLifecycle() {
    }

    public final boolean isAttached() {
        return isAttached;
    }

    public boolean isViewCreated() {
        return isViewCreated;
    }

    void onSaveInstanceState(Bundle outState){
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onSaveInstanceState(outState);
        }
    }

    void onViewCreated(Bundle outState) {
        isViewCreated = true;
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onViewCreated(outState);
        }
    }

    void onDestroyView() {
        isViewCreated = false;
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onDestroyView();
        }
    }

    void onActivityCreated(Bundle outState) {
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onActivityCreated(outState);
        }
    }

    void onAttach() {
        isAttached = true;
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onAttach();
        }
    }

    void onDetach() {
        isAttached = false;
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onDetach();
        }
    }
}
