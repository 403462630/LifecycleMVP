package fc.com.lifecycle.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fc.com.lifecycle.example.R;
import fc.com.lifecycle.example.delegate.TestDelegate3;
import fc.com.lifecycle.example.presenter.FragmentPresenter;
import fc.com.lifecycle.example.view.FragmentView;


/**
 * Created by rjhy on 16-12-12
 */
public class TestFragment2 extends Fragment  implements FragmentView {
    private TestDelegate3 testDelegate3 = new TestDelegate3();
    private View progress;
    private FragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progress = view.findViewById(R.id.progress);

        testDelegate3.bind(this, view.findViewById(R.id.container));
        presenter = new FragmentPresenter(this);
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
