package streams.numbersGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        System.out.println(
                orders.stream()
                        .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
                        .entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        .limit(3)
                        .peek(e -> System.out.println(e.getKey() + " : " + e.getValue()))
                        .mapToDouble(Map.Entry::getValue)
                        .reduce(0, Double::sum));
    }
}
