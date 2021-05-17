// name: Haonan Di
// andrew id: hdi
package finals;

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
		
		else if (MovieHall.examPart == 2) {
			balkCount = 0;
			while (TicketWindow.isWindowOpen) {
				int timeDelay = random.nextInt(this.customerDelay);
				try {
					Thread.sleep(timeDelay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int choice = random.nextInt(2);
				// 50% to be an impatient customer
				if (choice == 0) {
					Customer newCustomer = new Customer();
					newCustomer.joinQueue();
				}else {
					Customer newCustomer = new ImpatientCustomer();
					boolean tmp = newCustomer.joinQueue();
					if (!tmp) {
						balkCount++;
					}
				}
				
			}
			
		}//end of if
	}
}

