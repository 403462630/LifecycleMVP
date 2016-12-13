package fc.com.lifecycle.example.presenter;

import android.os.Bundle;
import android.os.Handler;

import fc.com.library.mvp.framework.model.BaseModel;
import fc.com.library.mvp.framework.presenter.ViewDelegatePresenter;
import fc.com.lifecycle.example.view.DelegateTestView;

/**
 * Created by rjhy on 16-12-13
 */
public class FragmentTestDelegatePresenter extends ViewDelegatePresenter<BaseModel, DelegateTestView> {
    private Handler handler = new Handler();

    public FragmentTestDelegatePresenter(DelegateTestView view) {
        super(view);
    }

    @Override
    public void onBinded(Bundle savedInstanceState) {
        super.onBinded(savedInstanceState);
        view.showProgress();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.showContent();
            }
        }, 2000);
    }
}
