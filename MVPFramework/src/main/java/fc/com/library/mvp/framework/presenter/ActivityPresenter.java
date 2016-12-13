package fc.com.library.mvp.framework.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import fc.com.library.lifecycle.ActivityLifecycle;
import fc.com.library.lifecycle.ActivityLifecycleListener;
import fc.com.library.lifecycle.LifecycleManager;
import fc.com.library.mvp.framework.model.IModel;
import fc.com.library.mvp.framework.view.IView;

/**
 * Created by rjhy on 16-8-10.
 */
public abstract class ActivityPresenter<M extends IModel, V extends IView> extends BasePresenter<M, V> implements ActivityLifecycleListener {

    private ActivityLifecycle lifecycle;

    public ActivityPresenter(V view) {
        super(null, view);
        if (view instanceof FragmentActivity) {
            bindLifecycle((FragmentActivity) view);
        } else if (view instanceof Activity) {
            bindLifecycle((Activity) view);
        }
    }

    public ActivityPresenter(M model, V view) {
        super(model, view);
        if (view instanceof FragmentActivity) {
            bindLifecycle((FragmentActivity) view);
        } else if (view instanceof Activity) {
            bindLifecycle((Activity) view);
        }
    }

    public final void bindLifecycle(Activity activity) {
        lifecycle = LifecycleManager.get().bind(activity, this);
    }

    public final void bindLifecycle(FragmentActivity activity) {
        lifecycle = LifecycleManager.get().bind(activity, this);
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
        unsubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

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
}
