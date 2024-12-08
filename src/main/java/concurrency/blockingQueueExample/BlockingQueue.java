package concurrency.blockingQueueExample;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final int size;
    private final Queue<T> queue = new LinkedList<>();

    public BlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while (queue.size() == size) {
            System.out.println("Queue is full");
            wait();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}
