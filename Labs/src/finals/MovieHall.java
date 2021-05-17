// name: Haonan Di
// andrew id: hdi

package finals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MovieHall {

	static int examPart;
	static int maxSeats;
	static final int MIN_TICKETS = 1;
	static final int MAX_TICKETS = 10;
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
		movieHall.startThreads();
		movieHall.printReport();
		movieHall.testResults();
	}
	
	void getInputs() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Part 1 or 2?");
		examPart = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Enter single ticket processing time (ms):");
		ticketProcessingTime = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Enter max tickets to be sold:");
		maxSeats = userInput.nextInt();
		userInput.nextLine();
		
//		System.out.println("Enter single ticketprocessing time (ms):");
//		maxSeats = userInput.nextInt();
//		userInput.nextLine();
		
		System.out.println("Enter max customer delay(ms):");
		customerDelay = userInput.nextInt();
		userInput.nextLine();
		
		if (examPart == 2) {
			System.out.println("Enter impatient customer's balk-queue-length:");
			balkQueueLength = userInput.nextInt();
			userInput.nextLine();
		}
		System.out.println("--------------- Detailed Customer Report ---------------");
		
		userInput.close();
	}
	
	void startThreads() {
		startTime = System.currentTimeMillis();
		queueManager = new QueueManager(customerDelay);
		ticketWindow = new TicketWindow(ticketProcessingTime);
		
		Thread queueThread = new Thread(queueManager);
		Thread ticketWindowThread = new Thread(ticketWindow);
		
		queueThread.start();
		ticketWindowThread.start();
		
		try {
			queueThread.join();
			ticketWindowThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		
	}
	
	void printReport() {
		System.out.println("--------------- Part 1 Report ---------------");
		System.out.println("Ticket window open duration             : " + Long.toString(endTime - startTime) + "ms");
		System.out.println("Total customers                         : " + Customer.customerCount);
		System.out.println("Customers who bought tickets            : " + ticketWindow.customerList.size());
		System.out.println("Customers in queue when window closed   : " + customerQueue.size());

		System.out.println();
		System.out.println("Total tickets sold                      : " + TicketWindow.ticketSoldCount);
		
		if (examPart == 2) {
			// this is for part 2 report
			System.out.println();
			System.out.println("--------------- Part 2 Report ---------------");
			
			System.out.println("Impatient customers                     : " + ImpatientCustomer.impatientCustomerCount);
			System.out.println("Customers who balked                    : " + queueManager.balkCount);
		}
		
		System.out.println("--------------- Customer Summary Report ---------------");
		Collections.sort(ticketWindow.customerList);
		int index = 1;
		int total = 0;
		for (Customer c: ticketWindow.customerList) {
			total += c.numberOfTickets;
			if (c instanceof ImpatientCustomer) {
				System.out.println(index + ". ImpatientCustomer " + c.id + " bought: " + c.numberOfTickets + " tickets.\tCumulative total: " + total);
			}else {
				System.out.println(index + ".          Customer " + c.id + " bought: " + c.numberOfTickets + " tickets.\tCumulative total: " + total);
			}
			index++;
		}
	}
	
	void testResults() {

		int ticketsSold = 0;  //total tickets sold

		int minTickets = MAX_TICKETS, maxTickets = MIN_TICKETS;

		//find the min, max, and total tickets sold from the customerList

		for (Customer c : ticketWindow.customerList) {

		ticketsSold += c.numberOfTickets;
		if (minTickets > c.numberOfTickets) minTickets = c.numberOfTickets;
		if (maxTickets < c.numberOfTickets) maxTickets = c.numberOfTickets;

		}

		//test whether total customerCount matches the sum of customers in customerList, //customers in customerQueue, and customers who balked

		assertEquals("Total customers", Customer.customerCount,

		ticketWindow.customerList.size() + customerQueue.size() + queueManager.balkCount);

		//test total tickets sold calculated above matches the total count at TicketWindow

		assertEquals("Total tickets sold", ticketsSold, TicketWindow.ticketSoldCount );

		//test that total tickets sold is equal to or more than maxSeats 

		assertTrue(ticketsSold >= maxSeats);

		//test the minTickets calculated above is greater than or equal to MIN_TICKETS

		assertTrue("Min tickets", minTickets >= MIN_TICKETS);

		//test the maxTickets calculated above is less than or equal to MIN_TICKETS

		assertTrue("Max tickets", maxTickets <= MAX_TICKETS);

		}
	
}

