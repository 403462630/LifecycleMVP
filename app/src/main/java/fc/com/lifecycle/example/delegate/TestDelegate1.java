package fc.com.lifecycle.example.delegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fc.com.library.mvp.framework.delegate.ViewDelegate;
import fc.com.lifecycle.example.R;
import fc.com.lifecycle.example.TestFragmentActivity;


/**
 * Created by rjhy on 16-12-12
 */
public class TestDelegate1 extends ViewDelegate {
    private final String TAG = toString() + "-TestDelegate1";
    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delegate_test1, container);
    }

    @Override
    protected void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        Log.i(TAG, "-----onViewCreated");
        rootView.findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), TestFragmentActivity.class));
            }
        });
    }

    @Override
    protected void onUnBinded() {
        super.onUnBinded();
        Log.i(TAG, "-----onUnBinded");
    }

    @Override
    protected void onSavedInstanceState(Bundle savedInstanceState) {
        super.onSavedInstanceState(savedInstanceState);
        Log.i(TAG, "-----onSavedInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "-----onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "-----onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "-----onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "-----onStop");
    }

    @Override
    protected void onBinded(View rootView, Bundle savedInstanceState) {
        super.onBinded(rootView, savedInstanceState);
        Log.i(TAG, "-----onBinded");
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Log.i(TAG, "-----onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "-----onDestroy");
    }
}
