package mitko.data.base;


import io.reactivex.Observable;

/**
 * Created by dimki on 21.02.2017 г..
 */

public interface IData<T> {
    Observable<T[]> getAll();
    Observable<T> getById(Object id);
    Observable<T> add(T obj);

}
