import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main class for the priority blocking queue console app.
 */
public class Main {

    /**
     * Entry method for the priority blocking queue console app.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        // Creating a priority blocking queue of assignments
        PriorityBlockingQueue<Assignment> assignmentPriorityQueue = new PriorityBlockingQueue<>(3);

        // Creating producer and consumer threads
        Thread producerThread = new Thread(new AssignmentProducer(assignmentPriorityQueue));
        Thread consumerThread = new Thread(new AssignmentConsumer(assignmentPriorityQueue));

        // Starting the threads
        producerThread.start();
        consumerThread.start();
    }
}