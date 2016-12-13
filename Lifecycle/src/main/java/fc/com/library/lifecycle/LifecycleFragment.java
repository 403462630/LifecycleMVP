package fc.com.library.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by rjhy on 16-11-17
 */
public class LifecycleFragment extends Fragment {
    private static final String TAG = "SupportFragment";
    private int state = 0;
    private ActivityLifecycle activityLifecycle = new ActivityLifecycle();
    private FragmentLifecycle fragmentLifecycle = new FragmentLifecycle();
    private Bundle outState;
    private String flag;

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LifecycleFragment() {
    }

    final ActivityLifecycle getActivityLifecycle() {
        return activityLifecycle;
    }

    final FragmentLifecycle getFragmentLifecycle() {
        return fragmentLifecycle;
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        boolean flag = false;
        if (lifecycleListener instanceof ActivityLifecycleListener) {
            flag = activityLifecycle.addListener((ActivityLifecycleListener) lifecycleListener);
        } else if (lifecycleListener instanceof FragmentLifecycleListener) {
            flag = fragmentLifecycle.addListener((FragmentLifecycleListener) lifecycleListener);
        }
        if (flag) {
            callLifecycle(lifecycleListener);
        }
    }

    public void removeLifecycleListener(LifecycleListener lifecycleListener) {
        if (lifecycleListener instanceof ActivityLifecycleListener) {
            activityLifecycle.removeListener((ActivityLifecycleListener) lifecycleListener);
        } else if (lifecycleListener instanceof FragmentLifecycleListener) {
            fragmentLifecycle.removeListener((FragmentLifecycleListener) lifecycleListener);
        }
    }

    private void callLifecycle(LifecycleListener lifecycleListener) {
        if ((state & StateConstant.STATE_ON_ATTACH) == 0) {
            return ;
        }
        if (lifecycleListener instanceof FragmentLifecycleListener) {
            ((FragmentLifecycleListener) lifecycleListener).onAttach();
        }

        if ((state & StateConstant.STATE_ON_CREATE) == 0) {
            return ;
        }
        lifecycleListener.onCreate(outState);

        if ((state & StateConstant.STATE_ON_VIEW_CREATED) == 0) {
            return ;
        }
        if (lifecycleListener instanceof FragmentLifecycleListener) {
            ((FragmentLifecycleListener) lifecycleListener).onViewCreated(outState);
        }

        if ((state & StateConstant.STATE_ON_ACTIVITY_CREATED) == 0) {
            return ;
        }
        if (lifecycleListener instanceof FragmentLifecycleListener) {
            ((FragmentLifecycleListener) lifecycleListener).onActivityCreated(outState);
        }

        if ((state & StateConstant.STATE_ON_START) == 0) {
            return ;
        }
        lifecycleListener.onStart();

        if ((state & StateConstant.STATE_ON_RESUME) == 0) {
            return ;
        }
        lifecycleListener.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, toString() + "-----onCreate");
        outState = savedInstanceState;
        state |= StateConstant.STATE_ON_CREATE;
        activityLifecycle.onCreate(savedInstanceState);
        fragmentLifecycle.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, toString() + "-----onSaveInstanceState");
        activityLifecycle.onSaveInstanceState(outState);
        fragmentLifecycle.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, toString() + "-----onViewCreated");
        state |= StateConstant.STATE_ON_VIEW_CREATED;
        fragmentLifecycle.onViewCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, toString() + "-----onDestroyView");
        state &= ~StateConstant.STATE_ON_VIEW_CREATED;
        fragmentLifecycle.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, toString() + "-----onActivityCreated");
        state |= StateConstant.STATE_ON_ACTIVITY_CREATED;
        fragmentLifecycle.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, toString() + "-----onStart");
        state |= StateConstant.STATE_ON_START;
        activityLifecycle.onStart();
        fragmentLifecycle.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, toString() + "-----onResume");
        state |= StateConstant.STATE_ON_RESUME;
        activityLifecycle.onResume();
        fragmentLifecycle.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, toString() + "-----onPause");
        state &= ~StateConstant.STATE_ON_RESUME;
        activityLifecycle.onPause();
        fragmentLifecycle.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, toString() + "-----onStop");
        state &= ~StateConstant.STATE_ON_START;
        activityLifecycle.onStop();
        fragmentLifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, toString() + "-----onDestroy");
        state &= ~StateConstant.STATE_ON_CREATE;
        activityLifecycle.onDestroy();
        fragmentLifecycle.onDestroy();
        LifecycleManager.get().removeCache(flag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, toString() + "-----onAttach");
        state |= StateConstant.STATE_ON_ATTACH;
        fragmentLifecycle.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, toString() + "-----onDetach");
        state &= ~StateConstant.STATE_ON_ATTACH;
        fragmentLifecycle.onDetach();
    }
}
