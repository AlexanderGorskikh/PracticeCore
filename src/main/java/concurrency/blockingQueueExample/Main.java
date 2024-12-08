package concurrency.blockingQueueExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        BlockingQueue<Integer> queue = new BlockingQueue<>(2);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Производитель добавляет: " + i);
                    queue.enqueue(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    int item = queue.dequeue();
                    System.out.println("Потребитель извлекает: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
