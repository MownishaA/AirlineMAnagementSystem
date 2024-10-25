package airline;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingList {
    private Queue<String> queue;

    public WaitingList() {
        queue = new LinkedList<>();
    }

    public void addPassenger(String name) {
        queue.add(name);
    }

    public String removeNextPassenger() {
        return queue.poll(); // Returns and removes the head of the queue, or null if empty
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

