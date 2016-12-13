package fc.com.lifecycle.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fc.com.lifecycle.example.R;
import fc.com.lifecycle.example.delegate.FragmentTestDelegate1;
import fc.com.lifecycle.example.presenter.FragmentPresenter;
import fc.com.lifecycle.example.view.FragmentView;


/**
 * Created by rjhy on 16-12-12
 */
public class TestFragment1 extends Fragment {
    private FragmentTestDelegate1 delegate1 = new FragmentTestDelegate1();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        delegate1.bind(this, view.findViewById(R.id.container));
    }
}
