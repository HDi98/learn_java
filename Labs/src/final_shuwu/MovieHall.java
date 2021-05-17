package final_shuwu;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieHall {
    static int examPart;
    static int maxSeats;
    static int MIN_TICKETS = 1;
    static int MAX_TICKETS = 10;
    static Queue<Customer> customerQueue = new LinkedList<>();
    static int balkQueueLength;

    int ticketProcessingTime;
    int customerDelay;
    long startTime;
    long endTime;
    QueueManager queueManager;
    TicketWindow ticketWindow;

    public static void main(String[] args) {
        MovieHall movieHall = new MovieHall();
        movieHall.getInputs();
        System.out.println("---------------Detailed Customer Report---------------");
        movieHall.startThreads();
        movieHall.printReport();
        movieHall.testResults();
    }

    void getInputs() {
        Scanner input = new Scanner(System.in);
        System.out.println("Part 1 or 2?");
        examPart = input.nextInt();
        if (examPart == 1) {
            System.out.println("Enter single ticket processing time (ms):");
            ticketProcessingTime = input.nextInt();
            System.out.println("Enter max tickets to be sold");
            maxSeats = input.nextInt();
            System.out.println("Enter max customer delay(ms):");
            customerDelay = input.nextInt();
        } else {
            System.out.println("Enter single ticket processing time (ms):");
            ticketProcessingTime = input.nextInt();
            System.out.println("Enter max tickets to be sold");
            maxSeats = input.nextInt();
            System.out.println("Enter max customer delay(ms):");
            customerDelay = input.nextInt();
            System.out.println("Enter impatient customer's balk-queue-length");
            balkQueueLength = input.nextInt();
        }
        input.close();

    }

    void startThreads() {
        startTime = System.currentTimeMillis();
        ticketWindow = new TicketWindow(ticketProcessingTime);
        queueManager = new QueueManager(customerDelay);
        Thread t1 = new Thread(ticketWindow);
        Thread t2 = new Thread(queueManager);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
    }

    void printReport() {
            System.out.println("---------------Part 1 Report---------------");
            System.out.printf("%-50s%5d%n", "Ticket window open duration: ", (endTime - startTime));
            System.out.printf("%-50s%5d%n", "Total customers: ", Customer.customerCount);
            System.out.printf("%-50s%5d%n", "Customers who bought tickets: ", ticketWindow.customerList.size());
            System.out.printf("%-50s%5d%n", "Customers in queue when window closed:", customerQueue.size());
            System.out.println();
            System.out.printf("%-50s%5d%n", "Total tickets sold", ticketWindow.ticketSoldCount);

            System.out.println();
            if(examPart == 2){

                System.out.println("---------------Part 2 Report---------------");
                System.out.printf("%-50s%5d%n","Impatient customers: ", ImpatientCustomer.impatientCustomerCount);
                System.out.printf("%-50s%5d%n", "Customers who balked:", queueManager.balkCount);
            }


            System.out.println("---------------Customer Summary Report---------------");
            Collections.sort(ticketWindow.customerList);
            int i = 1;
            int count = 0;
            for (Customer customer : ticketWindow.customerList) {
                count += customer.numberOfTickets;
                System.out.println(i + ".     Customer " + customer.id + " bought: " + customer.numberOfTickets + " tickets.     Cumulative total: " + count);
                i++;
            }




    }

    void testResults() {
        int ticketsSold = 0;
        int minTickets = MAX_TICKETS, maxTickets = MIN_TICKETS;

        for (Customer c : ticketWindow.customerList) {
            ticketsSold += c.numberOfTickets;
            if (minTickets > c.numberOfTickets) {
                minTickets = c.numberOfTickets;
            }
            if (maxTickets < c.numberOfTickets) {
                maxTickets = c.numberOfTickets;
            }
        }

        System.out.println();
        assertEquals("Total customers", Customer.customerCount, ticketWindow.customerList.size() + customerQueue.size() + queueManager.balkCount);
        assertEquals("Total tickets sold", ticketsSold, TicketWindow.ticketSoldCount);
        assertTrue(ticketsSold >= maxTickets);
        assertTrue("Min tickets", minTickets >= MIN_TICKETS);
        assertTrue("Max tickets", maxTickets <= MAX_TICKETS);
    }

}
