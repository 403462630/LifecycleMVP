package fc.com.library.lifecycle;

import android.os.Bundle;

/**
 * Created by rjhy on 16-11-17
 */
public final class FragmentLifecycle extends Lifecycle<FragmentLifecycleListener> {

    private boolean isAttached;

    public FragmentLifecycle() {
    }

    public final boolean isAttached() {
        return isAttached;
    }

    void onSaveInstanceState(Bundle outState){
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onSaveInstanceState(outState);
        }
    }

    void onViewCreated(Bundle outState) {
        for (FragmentLifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onViewCreated(outState);
        }
    }

    void onDestroyView() {
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
