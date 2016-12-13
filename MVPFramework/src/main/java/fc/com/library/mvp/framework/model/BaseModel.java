package fc.com.library.mvp.framework.model;

import rx.Observable;

/**
 * Created by rjhy on 16-8-9.
 */
public class BaseModel implements IModel {

    protected Observable asObservable(Object o) {
        if (!(o instanceof Observable)) {
            return Observable.just(o);
        } else {
            return (Observable) o;
        }
    }
}
