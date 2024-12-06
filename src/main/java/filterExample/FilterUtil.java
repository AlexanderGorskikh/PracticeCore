package filterExample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FilterUtil<T> {
    public T[] filter(T[] array, Filter<T> filter) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            list.add(filter.apply(t));
        }
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(array.getClass().getComponentType(), list.size());
        return list.toArray(result);
    }
}
