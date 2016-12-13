package fc.com.library.lifecycle;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rjhy on 16-11-17
 */
public class LifecycleManager {
    private static final String TAG = "LifecycleManager";
    static final String FRAGMENT_TAG = "com.baidao.lifecycle.manager";
    private static final LifecycleManager INSTANCE = new LifecycleManager();
    private Map<String, Object> cache = new HashMap<>();

    public static LifecycleManager get() {
        return INSTANCE;
    }

    final Object getCache(String key) {
        return cache.get(key);
    }

    final void putCache(String key, Object object) {
        cache.put(key, object);
    }

    final void removeCache(String key) {
        cache.remove(key);
    }

    public ActivityLifecycle bind(FragmentActivity activity, ActivityLifecycleListener... lifecycleListener) {
        SupportLifecycleFragment current = getSupportLifecycleFragment(activity);
        SupportLifecycleFragment fragment = bindLifecycle(current, lifecycleListener);
        return fragment.getActivityLifecycle();
    }

    public ActivityLifecycle bind(Activity activity, ActivityLifecycleListener... lifecycleListener) {
        LifecycleFragment current = getLifecycleFragment(activity);
        LifecycleFragment fragment = bindLifecycle(current, lifecycleListener);
        return fragment.getActivityLifecycle();
    }

    public FragmentLifecycle bind(Fragment fragment, FragmentLifecycleListener... lifecycleListener) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException(
                    "You cannot start a load on a fragment before it is attached");
        }
        SupportLifecycleFragment current = getSupportLifecycleFragment(fragment);
        SupportLifecycleFragment f = bindLifecycle(current, lifecycleListener);
        return f.getFragmentLifecycle();
    }

    public void unBind(Fragment fragment, FragmentLifecycleListener... lifecycleListener) {
        SupportLifecycleFragment current = getSupportLifecycleFragment(fragment);
        if (lifecycleListener != null && lifecycleListener.length > 0) {
            for (LifecycleListener listener : lifecycleListener) {
                current.removeLifecycleListener(listener);
            }
        }
    }

    public void unBind(FragmentActivity activity, ActivityLifecycleListener... lifecycleListener) {
        SupportLifecycleFragment current = getSupportLifecycleFragment(activity);
        if (lifecycleListener != null && lifecycleListener.length > 0) {
            for (LifecycleListener listener : lifecycleListener) {
                current.removeLifecycleListener(listener);
            }
        }
    }

    public void unBind(Activity activity, ActivityLifecycleListener... lifecycleListener) {
        LifecycleFragment current = getLifecycleFragment(activity);
        if (lifecycleListener != null && lifecycleListener.length > 0) {
            for (LifecycleListener listener : lifecycleListener) {
                current.removeLifecycleListener(listener);
            }
        }
    }

    private SupportLifecycleFragment getSupportLifecycleFragment(Object object) {
        FragmentManager fm = null;
        if (object instanceof Fragment) {
            fm = ((Fragment) object).getChildFragmentManager();
        } else if (object instanceof FragmentActivity) {
            fm = ((FragmentActivity) object).getSupportFragmentManager();
        } else {
            throw new IllegalArgumentException("getSupportLifecycleFragment(object) method parameter must be Fragment or FragmentActivity");
        }
        String flag = object.toString();
        SupportLifecycleFragment current =
                (SupportLifecycleFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            if (getCache(flag) != null) {
                Log.i(TAG, "-----use cache SupportLifecycleFragment: " + getCache(flag).toString());
                return (SupportLifecycleFragment) getCache(flag);
            } else {
                current = new SupportLifecycleFragment();
                Log.i(TAG, "-----create SupportLifecycleFragment: " + current.toString());
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
            }
        } else {
            Log.i(TAG, "-----SupportLifecycleFragment is exits: " + current.toString());
        }
        current.setFlag(flag);
        putCache(flag, current);
        return current;
    }

    private SupportLifecycleFragment bindLifecycle(SupportLifecycleFragment current, LifecycleListener[] lifecycleListener) {
        if (lifecycleListener != null && lifecycleListener.length > 0) {
            for (LifecycleListener listener : lifecycleListener) {
                current.addLifecycleListener(listener);
            }
        }
        return current;
    }

    private LifecycleFragment getLifecycleFragment(Activity activity) {
        android.app.FragmentManager fm = activity.getFragmentManager();
        LifecycleFragment current = (LifecycleFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        String flag = activity.toString();
        if (current == null) {
            if (getCache(flag) != null) {
                Log.i(TAG, "-----use cache LifecycleFragment");
                return (LifecycleFragment) getCache(flag);
            } else {
                Log.i(TAG, "-----create LifecycleFragment");
                current = new LifecycleFragment();
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
            }
        } else {
            Log.i(TAG, "-----LifecycleFragment is exits");
        }
        current.setFlag(flag);
        putCache(flag, current);
        return current;
    }

    private LifecycleFragment bindLifecycle(LifecycleFragment current, LifecycleListener[] lifecycleListener) {
        if (lifecycleListener != null && lifecycleListener.length > 0) {
            for (LifecycleListener listener : lifecycleListener) {
                current.addLifecycleListener(listener);
            }
        }
        return current;
    }
}
