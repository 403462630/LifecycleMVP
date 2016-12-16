package fc.com.lifecycle.example.view;

import fc.com.library.mvp.framework.view.IView;
import fc.com.lifecycle.example.presenter.FragmentTestDelegatePresenter;

/**
 * Created by rjhy on 16-12-13
 */
public interface DelegateTestView extends IView {
    public void showProgress();
    public void showContent();
}
