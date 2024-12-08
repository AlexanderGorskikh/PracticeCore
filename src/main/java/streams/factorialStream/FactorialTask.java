package streams.factorialStream;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int initialNumber;

    public FactorialTask(int n) {
        this.initialNumber = n;
    }

    @Override
    protected Long compute() {
        if (initialNumber <= 1) {
            return 1L;
        }
        FactorialTask subTask = new FactorialTask(initialNumber - 1);
        subTask.fork();
        return initialNumber * subTask.join();
    }
}
