package fc.com.lifecycle.example.presenter;

import android.os.Bundle;
import android.os.Handler;

import fc.com.library.mvp.framework.model.BaseModel;
import fc.com.lifecycle.example.view.FragmentView;

/**
 * Created by rjhy on 16-12-13
 */
public class FragmentPresenter extends fc.com.library.mvp.framework.presenter.FragmentPresenter<BaseModel, FragmentView> {
    private Handler handler = new Handler();

    public FragmentPresenter(FragmentView view) {
        super(view);
    }

    @Override
    public void onViewCreated(Bundle outState) {
        super.onViewCreated(outState);
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
