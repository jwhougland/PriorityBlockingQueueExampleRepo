import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Contains the data and behavior of an assignment consumer
 */
public class AssignmentConsumer implements Runnable {

    /**
     * Priority blocking queue for assignments
     */
    private final PriorityBlockingQueue<Assignment> assignmentPriorityQueue;

    /**
     * Maintains state about whether production of assignments is complete
     */
    private AtomicBoolean isProductionDone;

    /**
     * Final number of assignments produced
     */
    private AtomicInteger numberOfAssignments;

    /**
     * Creates a fully initialized assignment consumer using the given data
     *
     * @param assignmentPriorityQueue Priority blocking queue for assignments
     */
    public AssignmentConsumer(PriorityBlockingQueue<Assignment> assignmentPriorityQueue,
                              AtomicBoolean isProductionDone,
                              AtomicInteger numberOfAssignments) {

        if (assignmentPriorityQueue == null) {
            throw new IllegalArgumentException("Unable to consume assignments from a null data structure");
        }

        if (isProductionDone == null) {
            throw new IllegalArgumentException("Unable to receive notification about assignment production being done");
        }

        if (numberOfAssignments == null) {
            throw new IllegalArgumentException("Unable to receive a report about the number of assignments produced");
        }
        
        this.assignmentPriorityQueue = assignmentPriorityQueue;
        this.isProductionDone = isProductionDone;
        this.numberOfAssignments = numberOfAssignments;
    }

    /**
     * Override of the Runnable interface's run method that
     * consumes assignments from a priority blocking queue.
     */
    @Override
    public void run() {

        // Keep cycling until production is done and the all assignments are consumed
        int numberOfAssignmentsConsumed = 0;
        while (!isProductionDone.get() || (numberOfAssignmentsConsumed < numberOfAssignments.get())) {

            // Drain elements from the queue
            List<Assignment> drainedList = new ArrayList<>();
            int numElements = assignmentPriorityQueue.drainTo(drainedList);

            // Process the drained elements
            for (int i = 0; i < numElements; i++) {
                Assignment element = drainedList.get(i);
                System.out.println("Consumed: " + element);
                numberOfAssignmentsConsumed++;
            }

            try {
                Thread.sleep(1000); // Simulate some work being done
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
