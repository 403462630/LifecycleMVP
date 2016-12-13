package fc.com.lifecycle.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import fc.com.lifecycle.example.delegate.TestDelegate1;
import fc.com.lifecycle.example.delegate.TestDelegate2;
import fc.com.lifecycle.example.presenter.MainPresenter;
import fc.com.lifecycle.example.view.MainView;


/**
 * Created by rjhy on 16-12-12
 */
public class MainActivity extends FragmentActivity implements MainView {
    private TestDelegate1 testDelegate1 = new TestDelegate1();
    private TestDelegate2 testDelegate2 = new TestDelegate2();

    private MainPresenter presenter;
    private View progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDelegate1.bind(MainActivity.this, findViewById(R.id.frame1));
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDelegate1.unBind();
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDelegate2.bind(MainActivity.this, findViewById(R.id.frame2));
            }
        });
        findViewById(R.id.bt_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDelegate2.unBind();
            }
        });

        progress = findViewById(R.id.progress);

        presenter = new MainPresenter(this);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        progress.setVisibility(View.GONE);
    }
}
