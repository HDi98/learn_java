// name: Haonan Di
// andrew id: hdi
package finals;

public class ImpatientCustomer extends Customer{

	static int impatientCustomerCount;
	
	ImpatientCustomer(){
		super();
		impatientCustomerCount++;
	}
	
	boolean joinQueue() {
		
		if (MovieHall.customerQueue.size() > MovieHall.balkQueueLength) {
			System.out.println("***ImpateientCustomer" + this.id + " balked");
			return false;
		}else {
			
			System.out.println("ImpatientCustomer" + this.id + " joined Q");
			synchronized(MovieHall.customerQueue){
				MovieHall.customerQueue.offer(this);
			}
			return true;
		}
		
	}

}
