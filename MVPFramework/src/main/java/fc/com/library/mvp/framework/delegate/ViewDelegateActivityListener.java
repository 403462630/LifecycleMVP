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

    @Override
    public void onResume() {
        viewDelegate.onResume();
    }

    @Override
    public void onStart() {
        viewDelegate.onStart();
    }

    @Override
    public void onPause() {
        viewDelegate.onPause();
    }

    @Override
    public void onStop() {
        viewDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        viewDelegate.onDestroyView();
        viewDelegate.onDestroy();
    }
}
