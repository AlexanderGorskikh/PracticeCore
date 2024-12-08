package concurrency.complexTask;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final CyclicBarrier barrier;
    private final ExecutorService executor;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.barrier = new CyclicBarrier(numberOfTasks, () ->
                System.out.println("All tasks have reached the barrier. Proceeding to the next step."));
        this.executor = Executors.newFixedThreadPool(numberOfTasks);
    }

    public void executeTasks(int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    new ComplexTask(taskId).run();
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
