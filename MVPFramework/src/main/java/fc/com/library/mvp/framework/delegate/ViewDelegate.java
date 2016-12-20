package fc.com.library.mvp.framework.delegate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fc.com.library.lifecycle.Lifecycle;
import fc.com.library.lifecycle.LifecycleManager;
import fc.com.library.mvp.framework.presenter.ViewDelegatePresenter;

/**
 * @author rjhy
 * @created on 16-8-12
 * @desc desc
 */
public abstract class ViewDelegate<T extends ViewDelegatePresenter> {
    public static final int STATE_ON_START = 0x01;
    public static final int STATE_ON_RESUME = 0x02;

    private View parentView;
    private View rootView;
    protected T presenter;
    private boolean isBinded;
    private Lifecycle lifecycle;
    private ViewDelegateActivityListener activityListener = new ViewDelegateActivityListener(this);
    private ViewDelegateFragmentListener fragmentListener = new ViewDelegateFragmentListener(this);
    private Object object;
    private Context context;
    private boolean isViewCreated;
    private int state;

    public ViewDelegate() {
        presenter = createPresenter();
    }

    public T createPresenter() {
        return null;
    }

    public void bind(Context context, View view) {
        if (view != parentView) {
            unBind();
            this.parentView = view;
            if (context instanceof FragmentActivity) {
                object = (FragmentActivity) context;
                lifecycle = LifecycleManager.get().bind((FragmentActivity) context, activityListener);
            } else if (context instanceof Activity) {
                object = (Activity) context;
                lifecycle = LifecycleManager.get().bind((Activity) context, activityListener);
            } else {
                throw new IllegalArgumentException("context must be Activity or FragmentActivity");
            }
            if (isViewCreated && !isBinded()) {
                bindView(null);
            }
        }
    }

    public void bind(ViewDelegate viewDelegate, View view) {
        if (viewDelegate.object != null) {
            if (viewDelegate.object instanceof Fragment) {
                bind((Fragment) viewDelegate.object, view);
            } else if (viewDelegate.object instanceof Context) {
                bind((Context) viewDelegate.object, view);
            } else {
                throw new IllegalArgumentException("viewDelegate.object is null");
            }
        }
    }

    public void bind(Fragment fragment, View view) {
        if (view != parentView) {
            unBind();
            this.parentView = view;
            object = fragment;
            lifecycle = LifecycleManager.get().bind(fragment, fragmentListener);
            if (isViewCreated && !isBinded()) {
                bindView(null);
            }
        }
    }

    final void onViewCreated(Bundle saveInstanceState) {
        isViewCreated = true;
        bindView(saveInstanceState);
    }

    final void onDestroyView() {
        isViewCreated = false;
        unBind();
    }

    private void createRootView(Bundle savedInstanceState) {
        View rootView = onCreateView(LayoutInflater.from(parentView.getContext()), (ViewGroup) parentView, savedInstanceState);
        if (rootView != null) {
            if (rootView != parentView) {
                ((ViewGroup) parentView).addView(rootView);
                this.rootView = rootView;
            } else {
                if (hasCreatedView()) {
                    this.rootView = ((ViewGroup) parentView).getChildAt(((ViewGroup) parentView).getChildCount() - 1);
                } else {
                    this.rootView = parentView;
                }
            }
        } else {
            this.rootView = null;
        }
    }

    final void bindView(Bundle savedInstanceState) {
        if (isBinded()) {
            return ;
        }
        if (parentView != null && parentView instanceof ViewGroup) {
            if (this.rootView == null) {
                createRootView(savedInstanceState);
                onViewCreated(rootView, savedInstanceState);
            } else {
                if (parentView != rootView) {
                    ((ViewGroup) parentView).addView(rootView);
                }
            }
        }
        onBinded(rootView, savedInstanceState);
        if (isBindCallLifecycle()) {
            callBindLifecycle();
        }
        this.isBinded = true;
    }

    protected boolean hasCreatedView() {
        return true;
    }

    public final View findViewById(int id) {
        if (getRootView() != null) {
            return getRootView().findViewById(id);
        }
        return null;
    }

    private void callBindLifecycle() {
        if (lifecycle == null || !lifecycle.isStart()) {
            return ;
        }
        onStart();

        if (lifecycle == null || !lifecycle.isResume()) {
            return ;
        }
        onResume();
    }

    private void callUnbindLifecycle() {
        if ((state & STATE_ON_RESUME) == 0) {
            return ;
        }
        onPause();

        if ((state & STATE_ON_START) == 0) {
            return ;
        }
        onStop();
    }

    protected void onBinded(View rootView, Bundle savedInstanceState) {
        if (presenter != null) {
            presenter.onBinded(savedInstanceState);
        }
    }

    protected void onUnBinded() {
        if (presenter != null) {
            presenter.onUnBinded();
        }
    }

    public final void unBind() {
        if (parentView != null) {
            if (rootView != null && parentView != rootView) {
                if (parentView instanceof ViewGroup) {
                    ((ViewGroup) parentView).removeView(rootView);
                }
            }
            if (parentView == rootView) {
                rootView = null;
            }
            parentView = null;
        }

        if (isBinded) {
            if (isBindCallLifecycle()) {
                callUnbindLifecycle();
            }
            onUnBinded();
            this.isBinded = false;
        }
    }

    private void unBindLifecycle(Object o) {
        if (lifecycle != null && o != null) {
            if (o instanceof Fragment) {
                LifecycleManager.get().unBind((Fragment) o, fragmentListener);
            } else if (o instanceof FragmentActivity){
                LifecycleManager.get().unBind((FragmentActivity) o, activityListener);
            } else if (o instanceof Activity) {
                LifecycleManager.get().unBind((Activity) o, activityListener);
            }
        }
    }

    public final View getRootView() {
        return rootView;
    }

    public Context getContext() {
        if (rootView != null) {
            context = rootView.getContext();
        } else if (parentView != null) {
            context = parentView.getContext();
        }
        return context;
    }

    protected boolean isBindCallLifecycle() {
        return true;
    }

    protected void onSavedInstanceState(Bundle savedInstanceState) {
        if (presenter != null) {
            presenter.onSavedInstanceState(savedInstanceState);
        }
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return container;
    }

    protected void onViewCreated(View rootView, Bundle savedInstanceState) {
        if (presenter != null) {
            presenter.onViewCreated(savedInstanceState);
        }
    }

    protected void onResume() {
        state |= STATE_ON_RESUME;
        if (presenter != null) {
            presenter.onResume();
        }
    }

    protected void onPause() {
        state &= ~STATE_ON_RESUME;
        if (presenter != null) {
            presenter.onPause();
        }
    }

    protected void onStart() {
        state |= STATE_ON_START;
        if (presenter != null) {
            presenter.onStart();
        }
    }

    protected void onStop() {
        state &= ~STATE_ON_START;
        if (presenter != null) {
            presenter.onStop();
        }
    }

    protected void onCreate(Bundle saveInstanceState) {
        if (presenter != null) {
            presenter.onCreate(saveInstanceState);
        }
    }

    protected void onDestroy() {
        unBindLifecycle(object);
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    public final boolean isResume() {
        return (state & STATE_ON_RESUME) != 0;
    }

    public final boolean isStart() {
        return (state & STATE_ON_START) != 0;
    }

    public final boolean isBinded() {
        return isBinded;
    }
}
