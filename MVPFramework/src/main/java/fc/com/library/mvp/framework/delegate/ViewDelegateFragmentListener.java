package fc.com.library.mvp.framework.delegate;

import android.os.Bundle;

import fc.com.library.lifecycle.FragmentLifecycleListener;


/**
 * Created by rjhy on 16-12-8
 */
public class ViewDelegateFragmentListener implements FragmentLifecycleListener {

    private ViewDelegate viewDelegate;

    public ViewDelegateFragmentListener(ViewDelegate viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onViewCreated(Bundle outState) {
        viewDelegate.onViewCreated(outState);
    }

    @Override
    public void onDestroyView() {
        viewDelegate.onDestroyView();
    }

    @Override
    public void onActivityCreated(Bundle outState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        viewDelegate.onSavedInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle outState) {
        viewDelegate.onCreate(outState);
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
        viewDelegate.onDestroy();
    }
}
