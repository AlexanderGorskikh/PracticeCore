package concurrency.complexTask;

public class ComplexTask implements Runnable{
    private final int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is executing.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task " + taskId + " is completed.");
    }
}
