import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Contains the data and behavior of an assignment consumer
 */
public class AssignmentConsumer implements Runnable {

    /**
     * Priority blocking queue for assignments
     */
    private final PriorityBlockingQueue<Assignment> assigmentPriorityQueue;

    /**
     * Creates a fully initialized assignment producer using the given data
     *
     * @param assigmentPriorityQueue Priority blocking queue for assignments
     */
    public AssignmentConsumer(PriorityBlockingQueue<Assignment> assigmentPriorityQueue) {
        this.assigmentPriorityQueue = assigmentPriorityQueue;
    }

    /**
     * Override of the Runnable interface's run method that
     * consumes assignments from a priority blocking queue.
     */
    @Override
    public void run() {

        // Create a List to store the drained elements
        while (true) {
            // Drain elements from the queue
            List<Assignment> drainedList = new ArrayList<>();
            int numElements = assigmentPriorityQueue.drainTo(drainedList);

            // Process the drained elements
            for (int i = 0; i < numElements; i++) {
                Assignment element = drainedList.get(i);
                System.out.println("Consumed: " + element);
                // Process the element as needed
            }

            try {
                Thread.sleep(1000); // Simulate some work being done
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
