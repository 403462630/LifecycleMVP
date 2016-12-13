package fc.com.library.mvp.framework.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import fc.com.library.lifecycle.FragmentLifecycle;
import fc.com.library.lifecycle.FragmentLifecycleListener;
import fc.com.library.lifecycle.LifecycleManager;
import fc.com.library.mvp.framework.model.IModel;
import fc.com.library.mvp.framework.view.IView;


/**
 * Created by rjhy on 16-8-9.
 */
public abstract class FragmentPresenter<M extends IModel, V extends IView> extends BasePresenter<M, V> implements FragmentLifecycleListener {
    private FragmentLifecycle lifecycle;

    public FragmentPresenter(V view) {
        super(view);
        if (view instanceof Fragment) {
            bindLifecycle((Fragment) view);
        }
    }

    public FragmentPresenter(M model, V view) {
        super(model, view);
        if (view instanceof Fragment) {
            bindLifecycle((Fragment) view);
        }
    }

    public final void bindLifecycle(Fragment fragment) {
        lifecycle = LifecycleManager.get().bind(fragment, this);
    }

    public boolean isResume() {
        if (lifecycle != null) {
            return lifecycle.isResume();
        }
        return false;
    }

    public boolean isStart() {
        if (lifecycle != null) {
            return lifecycle.isStart();
        }
        return false;
    }

    public boolean isDestroy() {
        if (lifecycle != null) {
            return lifecycle.isDestroy();
        }
        return false;
    }

    public boolean isAttached() {
        if (lifecycle != null) {
            return lifecycle.isAttached();
        }
        return false;
    }

    @Override
    public void onViewCreated(Bundle outState) {

    }

    @Override
    public void onDestroyView() {
        unsubscribe();
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onActivityCreated(Bundle outState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onCreate(Bundle outState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
