package fc.com.library.mvp.framework.presenter;


import fc.com.library.mvp.framework.model.IModel;
import fc.com.library.mvp.framework.view.IView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rjhy on 16-8-9.
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<M, V> {
    private CompositeSubscription compositeSubscription;
    protected M model;
    protected V view;

    public BasePresenter(V view) {
        compositeSubscription = new CompositeSubscription();
        this.view = view;
    }

    public BasePresenter(M model, V view) {
        compositeSubscription = new CompositeSubscription();
        this.view = view;
        this.model = model;
    }

    public void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        compositeSubscription.unsubscribe();
    }
}
