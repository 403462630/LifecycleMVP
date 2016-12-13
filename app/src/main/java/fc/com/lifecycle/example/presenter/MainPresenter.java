package fc.com.lifecycle.example.presenter;

import android.os.Bundle;
import android.os.Handler;

import fc.com.library.mvp.framework.model.BaseModel;
import fc.com.library.mvp.framework.presenter.ActivityPresenter;
import fc.com.lifecycle.example.view.MainView;

/**
 * Created by rjhy on 16-12-13
 */
public class MainPresenter extends ActivityPresenter<BaseModel, MainView> {

    private Handler handler = new Handler();
    public MainPresenter(MainView view) {
        super(view);
    }

    @Override
    public void onCreate(Bundle outState) {
        super.onCreate(outState);
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
