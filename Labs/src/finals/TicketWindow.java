// name: Haonan Di
// andrew id: hdi
package finals;

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
				int sleepTime = newCustomer.numberOfTickets * ticketProcessingTime;
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (newCustomer instanceof ImpatientCustomer) {
					System.out.println("                       ImpatientCustomer" + newCustomer.id + " bought " + newCustomer.numberOfTickets + " tickets");
				}else {
					System.out.println("                       Customer" + newCustomer.id + " bought " + newCustomer.numberOfTickets + " tickets");
				}
				ticketSoldCount += newCustomer.numberOfTickets;
				customerList.add(newCustomer);
			}
			
			if (ticketSoldCount >= MovieHall.maxSeats) {
				// only way to set the window to close
				isWindowOpen = false;
			}
			
			
		}//end of while 
		
	}

}


