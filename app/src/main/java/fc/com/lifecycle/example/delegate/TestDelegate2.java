package fc.com.lifecycle.example.delegate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fc.com.library.mvp.framework.delegate.ViewDelegate;
import fc.com.lifecycle.example.R;

/**
 * Created by rjhy on 16-12-12
 */
public class TestDelegate2 extends ViewDelegate {
    private final String TAG = toString() + "-TestDelegate2";

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delegate_test2, container);
    }

    @Override
    protected void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        Log.i(TAG, "-----onViewCreated");
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
}
