package fc.com.lifecycle.example.view;

import fc.com.library.mvp.framework.view.IView;
import fc.com.lifecycle.example.presenter.MainPresenter;

/**
 * Created by rjhy on 16-12-13
 */
public interface MainView extends IView {
    public void showProgress();
    public void showContent();
}
