package filterExample;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Проверка с числами
        Integer[] arr = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };
        FilterUtil<Integer> numFilter = new FilterUtil<>();
        System.out.println(
                Arrays.toString(
                        numFilter.filter(
                                arr,(e)-> e + 1)));

        // Проверка со строками
        String [] str = new String[]{
                "a", "b", "c", "d", "e", "f"
        };
        FilterUtil<String> strFilter = new FilterUtil<>();
        System.out.println(
                Arrays.toString(
                        strFilter.filter(
                                str,(e)-> e + 1)));
    }
}
