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
        try {

            while(!assigmentPriorityQueue.isEmpty()) {

                Assignment assignment = assigmentPriorityQueue.take();
                System.out.println("Consuming: " + assignment);
                Thread.sleep(500); // Simulating some work
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
