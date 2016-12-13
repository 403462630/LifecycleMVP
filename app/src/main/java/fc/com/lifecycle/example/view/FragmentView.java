package fc.com.lifecycle.example.view;

import fc.com.library.mvp.framework.view.IView;

/**
 * Created by rjhy on 16-12-13
 */
public interface FragmentView extends IView {
    public void showProgress();
    public void showContent();
}
