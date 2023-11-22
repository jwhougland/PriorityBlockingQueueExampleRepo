import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
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

        // Create an atomic boolean that the producer thread will be able to use to
        // signal the consumer thread that production is done
        AtomicBoolean isProductionDone = new AtomicBoolean(false);

        // Create an atomic integer that the producer thread will be able ot use to
        // signal the consumer thread how many assignments have been produced.  This
        // will be initialized to the max value and then will be updated to a real
        // value once production is complete so the consumer thread will know how
        // many assignments are left to consume
        AtomicInteger numberOfAssignments = new AtomicInteger(Integer.MAX_VALUE);

        // Creating producer and consumer threads
        Thread producerThread = new Thread(
                new AssignmentProducer(assignmentPriorityQueue, isProductionDone, numberOfAssignments));
        Thread consumerThread = new Thread(
                new AssignmentConsumer(assignmentPriorityQueue, isProductionDone, numberOfAssignments));

        // Starting the threads
        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("All assignments produced and consumed");
    }
}