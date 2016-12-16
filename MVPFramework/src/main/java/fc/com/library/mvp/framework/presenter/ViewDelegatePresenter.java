package fc.com.library.mvp.framework.presenter;

import android.os.Bundle;

import fc.com.library.mvp.framework.model.IModel;
import fc.com.library.mvp.framework.view.IView;

/**
 * @author rjhy
 * @created on 16-8-12
 * @desc desc
 */
public abstract class ViewDelegatePresenter<M extends IModel, V extends IView> extends BasePresenter<M, V> {
    private boolean isResume;
    private boolean isStart;
    private boolean isBinded;

    public boolean isResume() {
        return isResume;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isBinded() {
        return isBinded;
    }

    public ViewDelegatePresenter(V view) {
        super(view);
    }

    public ViewDelegatePresenter(M model, V view) {
        super(model, view);
    }

    public void onViewCreated(Bundle savedInstanceState) {

    }

    public void onSavedInstanceState(Bundle outState) {

    }

    public void onResume() {
        isResume = true;
    }

    public void onStart() {
        isStart = true;
    }

    public void onPause() {
        isResume = false;
    }

    public void onStop() {
        isStart = false;
    }

    public void onBinded(Bundle savedInstanceState) {
        isBinded = true;
    }

    public void onUnBinded() {
        isBinded = false;
        unsubscribe();
    }

    public void onDestroy() {

    }

    public void onCreate(Bundle savedInstanceState) {

    }
}
