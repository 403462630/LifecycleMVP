package fc.com.lifecycle.example.delegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fc.com.library.mvp.framework.delegate.ViewDelegate;
import fc.com.lifecycle.example.R;
import fc.com.lifecycle.example.TestFragmentActivity;
import fc.com.lifecycle.example.presenter.FragmentTestDelegatePresenter;
import fc.com.lifecycle.example.view.DelegateTestView;


/**
 * Created by rjhy on 16-12-12
 */
public class FragmentTestDelegate1 extends ViewDelegate<FragmentTestDelegatePresenter> implements DelegateTestView {
    private View progress;

    @Override
    public FragmentTestDelegatePresenter createPresenter() {
        return new FragmentTestDelegatePresenter(this);
    }

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delegate_fragment_test1, container, false);
    }

    @Override
    protected void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        rootView.findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), TestFragmentActivity.class));
            }
        });
        TextView textView = (TextView) rootView.findViewById(R.id.tv_text);
        if (savedInstanceState != null && savedInstanceState.containsKey("FragmentTestDelegate1")) {
            textView.setText(savedInstanceState.getString("FragmentTestDelegate1"));
        }
        progress = findViewById(R.id.progress);
    }

    @Override
    protected void onBinded(View rootView, Bundle savedInstanceState) {
        super.onBinded(rootView, savedInstanceState);
    }

    @Override
    protected void onSavedInstanceState(Bundle savedInstanceState) {
        super.onSavedInstanceState(savedInstanceState);
        savedInstanceState.putString("FragmentTestDelegate1", "onSavedInstanceState-FragmentTestDelegate1");
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
