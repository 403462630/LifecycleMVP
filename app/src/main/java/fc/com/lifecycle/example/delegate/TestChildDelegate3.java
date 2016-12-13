package fc.com.lifecycle.example.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fc.com.library.mvp.framework.delegate.ViewDelegate;
import fc.com.lifecycle.example.R;


/**
 * Created by rjhy on 16-12-12
 */
public class TestChildDelegate3 extends ViewDelegate {

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delegate_test3_child, container, false);
    }

    @Override
    protected void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
    }


}
