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
    private View parentView;
    private View rootView;
    protected T presenter;
    private boolean isBinded;
    private Lifecycle lifecycle;
    private ViewDelegateActivityListener activityListener = new ViewDelegateActivityListener(this);
    private ViewDelegateFragmentListener fragmentListener = new ViewDelegateFragmentListener(this);
    private Object object;

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
        }
    }

    /**
     * not allow Override, please use onBinded method
     * onBinded to onCreate, onUnBinded to onDestroy
     * @param saveInstanceState
     */
    protected final void onCreate(Bundle saveInstanceState) {
        bindView(parentView, saveInstanceState);
    }

    private final void bindView(View parent, Bundle savedInstanceState) {
        if (parent != null && parent instanceof ViewGroup) {
            if (this.rootView == null) {
                View rootView = onCreateView(LayoutInflater.from(parent.getContext()), (ViewGroup) parent, savedInstanceState);
                if (rootView != null) {
                    if (rootView != parent) {
                        ((ViewGroup) parent).addView(rootView);
                        this.rootView = rootView;
                    } else {
                        this.rootView = parent;
                    }
                } else {
                    this.rootView = null;
                }
                onViewCreated(rootView, savedInstanceState);
            } else {
                if (parent != rootView) {
                    ((ViewGroup) parent).addView(rootView);
                }
            }
        }
        onBinded(rootView, savedInstanceState);
        this.isBinded = true;
        this.parentView = parent;
    }

    public final View findViewById(int id) {
        if (getRootView() != null) {
            return getRootView().findViewById(id);
        }
        return null;
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
        unBindLifecycle(object);
        if (isBinded) {
            onUnBinded();
            onDestroy();
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

    protected View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {

        return null;
    }

    /**
     * only call for create view
     * @param rootView
     * @param savedInstanceState
     */
    protected void onViewCreated(View rootView, Bundle savedInstanceState) {
        if (presenter != null) {
            presenter.onViewCreated(savedInstanceState);
        }
    }

    public final View getRootView() {
        return rootView;
    }

    public Context getContext() {
        if (rootView != null) {
            return rootView.getContext();
        } else if (parentView != null) {
            return parentView.getContext();
        }
        return null;
    }

    protected void onSavedInstanceState(Bundle savedInstanceState) {
        if (presenter != null) {
            presenter.onSavedInstanceState(savedInstanceState);
        }
    }

    protected void onResume() {
        if (presenter != null) {
            presenter.onResume();
        }
    }

    protected void onPause() {
        if (presenter != null) {
            presenter.onPause();
        }
    }

    protected void onStart() {
        if (presenter != null) {
            presenter.onStart();
        }
    }

    protected void onStop() {
        if (presenter != null) {
            presenter.onStop();
        }
    }

    /**
     * please use onUnBinded method
     * onBinded to onCreate, onUnBinded to onDestroy
     */
    @Deprecated
    protected void onDestroy() {
//        if (presenter != null) {
//            presenter.onDestroy();
//        }
    }

    public final boolean isResume() {
        if (lifecycle != null) {
            return lifecycle.isResume();
        }
        return false;
    }

    public final boolean isStart() {
        if (lifecycle != null) {
            return lifecycle.isStart();
        }
        return false;
    }

    public final boolean isBinded() {
        return isBinded;
    }
}
