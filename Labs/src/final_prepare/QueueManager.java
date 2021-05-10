package final_prepare;

import java.util.Random;

public class QueueManager implements Runnable{

	int customerDelay;
	int balkCount;
	
	QueueManager(int customerDelay){
		this.customerDelay = customerDelay;
	}

	@Override
	public void run() {
		Random random = new Random();
		
		if (MovieHall.examPart == 1) {
			while (TicketWindow.isWindowOpen) {
				int timeDelay = random.nextInt(this.customerDelay);
				try {
					Thread.sleep(timeDelay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Customer newCustomer = new Customer();
				newCustomer.joinQueue();
			}
			
		}//end of if
		
		
	}
}