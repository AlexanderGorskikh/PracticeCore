package filterExample;

public class FilterUtil<T> {
    public T[] filter(T[] array, Filter<T> filter) {
        for (int i = 0; i < array.length; i++) {
            array[i] = filter.apply(array[i]);
        }
        return array;
    }
}
