package final_prepare;

import java.util.List;

public class TicketWindow implements Runnable{

	static int ticketSoldCount;
	static boolean isWindowOpen;
	
	int ticketProcessingTime;
	List<Customer> customerList;
	
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
				
			}
		}//end of while 
		
	}
}
