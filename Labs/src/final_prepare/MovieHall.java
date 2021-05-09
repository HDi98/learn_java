package final_prepare;

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
		
		System.out.println("Enter single ticketprocessing time (ms):");
		ticketProcessingTime = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Enter max tickets to be sold:");
		ticketProcessingTime = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Enter single ticketprocessing time (ms):");
		maxSeats = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Enter max customer delay(ms):");
		customerDelay = userInput.nextInt();
		userInput.nextLine();
		
		userInput.close();
	}
	
	void startThreads() {
		
	}
	
	void printReport() {
		
	}
	
	void testResults() {
		
	}
	
}
