package fc.com.lifecycle.example;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * 目标getFragment工具类
 */
public class FragmentUtils {

    private static final int DEFAULT_FRAGMENT_CONTAINER_ID = R.id.fragment_container;

    /**
     * fragment进栈
     * @param fm
     * @param containerViewId
     * @param fragment
     * @param tag
     * @param addToBack
     */
    public static void pushFragment(FragmentManager fm, @IdRes int containerViewId,
                                    Fragment fragment, String tag, boolean addToBack) {
        FragmentTransaction ft = fm.beginTransaction();
        //        ft.setCustomAnimations(R.anim.right_to_left_enter, R.anim.left_to_right_exit,
        //        R.anim.pop_left_to_right_enter, R.anim.pop_left_to_right_exit);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (TextUtils.isEmpty(tag)) {
            ft.replace(containerViewId, fragment);
        } else {
            ft.replace(containerViewId, fragment, tag);
        }
        if (addToBack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.commit();
        fm.executePendingTransactions();
    }

    /**
     * fragment进栈
     * @param fm
     * @param containerViewId
     * @param fragment
     */
    public static void pushFragment(FragmentManager fm, @IdRes int containerViewId,
                                    Fragment fragment) {
        pushFragment(fm, containerViewId, fragment, null, false);
    }

    /**
     * fragment进栈
     * @param fm
     * @param fragment
     * @param addToBack
     */
    public static void pushFragment(FragmentManager fm, Fragment fragment, boolean addToBack) {
        pushFragment(fm, DEFAULT_FRAGMENT_CONTAINER_ID, fragment, null, addToBack);
    }

    /**
     * fragment进栈
     * @param fm
     * @param fragment
     */
    public static void pushFragment(FragmentManager fm, Fragment fragment) {
        pushFragment(fm, DEFAULT_FRAGMENT_CONTAINER_ID, fragment, null, false);
    }

    /**
     * fragment进栈
     * @param fm
     * @param fragment
     * @param tag
     */
    public static void pushFragment(FragmentManager fm, Fragment fragment, String tag) {
        pushFragment(fm, DEFAULT_FRAGMENT_CONTAINER_ID, fragment, tag, false);
    }

    /**
     * fragment进栈
     * @param fm
     * @param fragment
     * @param tag
     * @param addToBack
     */
    public static void pushFragment(FragmentManager fm, Fragment fragment, String tag,
                                    boolean addToBack) {
        pushFragment(fm, DEFAULT_FRAGMENT_CONTAINER_ID, fragment, tag, addToBack);
    }

    /**
     * fragment出栈
     * @param fm
     * @return true:操作成功
     */
    public static boolean popFragment(FragmentManager fm) {
        final int entryCount = fm.getBackStackEntryCount();
        FragmentTransaction ft = fm.beginTransaction();
        boolean popSucceed = true;
        if (entryCount <= 1) {
            fm.popBackStack();
        } else {
            popSucceed = fm.popBackStackImmediate();
        }
        ft.commit();
        return popSucceed;
    }

    /**
     * 获取当前fragment
     * @param fm
     * @return 当前fragment
     */
    public static Fragment getCurrentFragment(FragmentManager fm) {
        return getCurrentFragment(fm, DEFAULT_FRAGMENT_CONTAINER_ID);
    }

    /**
     * 获取当前fragment
     * @param fm
     * @param id
     * @return 当前fragment
     */
    public static Fragment getCurrentFragment(FragmentManager fm, @IdRes int id) {
        return fm.findFragmentById(id);
    }
}