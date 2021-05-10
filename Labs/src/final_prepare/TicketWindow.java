package final_prepare;

import java.util.ArrayList;
import java.util.List;

public class TicketWindow implements Runnable{

	static int ticketSoldCount;
	static boolean isWindowOpen = true;
	
	int ticketProcessingTime;
	List<Customer> customerList = new ArrayList<>();
	
	TicketWindow(int ticketProcessingTime){
		this.ticketProcessingTime = ticketProcessingTime;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (isWindowOpen) {
			Customer newCustomer = null;
			synchronized (MovieHall.customerQueue) {
				newCustomer = MovieHall.customerQueue.poll();
			}
			if (newCustomer != null) {
				ticketProcessingTime = newCustomer.numberOfTickets * ticketProcessingTime;
				if (newCustomer instanceof ImpatientCustomer) {
					System.out.println("                       Impatient Customer " + newCustomer.id + " bought " + newCustomer.numberOfTickets + " tickets");
				}else {
					System.out.println("                       Customer " + newCustomer.id + " bought " + newCustomer.numberOfTickets + " tickets");
				}
				ticketSoldCount += newCustomer.numberOfTickets;
				customerList.add(newCustomer);
			}
			
			if (ticketSoldCount >= MovieHall.maxSeats) {
				isWindowOpen = false;
			}
			
			
		}//end of while 
		
	}
}
