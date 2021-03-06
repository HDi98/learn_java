package final_prepare;

import java.util.Random;

public class Customer {

	static int customerCount;
	
	int id;
	int numberOfTickets;
	
	Customer(){
		customerCount++;
		id = customerCount;		
		Random random = new Random();
		numberOfTickets = MovieHall.MIN_TICKETS + random.nextInt(MovieHall.MAX_TICKETS - MovieHall.MIN_TICKETS);
	}
	
	boolean joinQueue() {
		
		System.out.println("Customer " + this.id + " joined Q");
		synchronized(MovieHall.customerQueue){
			MovieHall.customerQueue.offer(this);
		}
		
		
		return true;
	}
}