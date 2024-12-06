package mapExample;

import java.util.HashMap;
import java.util.Map;

public class MapUtil<T> {
    public Map<T, Integer> countElementsInMap(T[] array) {
        HashMap<T, Integer> map = new HashMap<>();
        for (T t:array){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        return map;
    }
}
