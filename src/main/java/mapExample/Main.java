package mapExample;

public class Main {
    public static void main(String[] args) {
        MapUtil<Integer> util = new MapUtil<>();

        System.out.println(util.countElementsInMap(new Integer[]{
                1, 1, 1, 2, 3, 4, 5, 6, 5, 5, 3, 2, 3
        }));
    }
}
