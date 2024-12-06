package filterExample;

public interface Filter<T> {
    T apply(T input);
}
