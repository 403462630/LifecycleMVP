package fc.com.library.mvp.framework.delegate;

import android.os.Bundle;

import fc.com.library.lifecycle.ActivityLifecycleListener;


/**
 * Created by rjhy on 16-12-8
 */
public class ViewDelegateActivityListener implements ActivityLifecycleListener {

    private ViewDelegate viewDelegate;

    public ViewDelegateActivityListener(ViewDelegate viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        viewDelegate.onSavedInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle outState) {
        viewDelegate.onCreate(outState);
        viewDelegate.onViewCreated(outState);
    }

    private boolean checkCallLifecycle() {
        return !viewDelegate.isBindCallLifecycle() || viewDelegate.isBinded();
    }

    @Override
    public void onResume() {
        if (checkCallLifecycle()) {
            viewDelegate.onResume();
        }
    }

    @Override
    public void onStart() {
        if (checkCallLifecycle()) {
            viewDelegate.onStart();
        }
    }

    @Override
    public void onPause() {
        if (checkCallLifecycle()) {
            viewDelegate.onPause();
        }
    }

    @Override
    public void onStop() {
        if (checkCallLifecycle()) {
            viewDelegate.onStop();
        }
    }

    @Override
    public void onDestroy() {
        viewDelegate.onDestroyView();
        viewDelegate.onDestroy();
    }
}
