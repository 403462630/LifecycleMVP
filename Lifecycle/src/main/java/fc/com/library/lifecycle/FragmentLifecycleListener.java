package fc.com.library.lifecycle;

import android.os.Bundle;

/**
 * Created by rjhy on 16-11-18
 */
public interface FragmentLifecycleListener extends LifecycleListener {

    void onAttach();

    void onDetach();

    void onViewCreated(Bundle outState);

    void onDestroyView();

    void onActivityCreated(Bundle outState);

    void onSaveInstanceState(Bundle outState);
}
