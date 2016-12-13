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
        viewDelegate.onCreate(outState);
    }

    @Override
    public void onDestroyView() {
        viewDelegate.unBind();
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
        viewDelegate.onDestroy();
    }
}
