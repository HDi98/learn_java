// name: Haonan Di
// andrew id: hdi
package lab8;


public class SalesCounter implements Runnable {
	int id; 			//Unique sequential identifier for each sales counter
	static int count;	//Counts SalesCounter objects created so far

	
	/**SalesCounter() increments count, initializes id  */
	SalesCounter() {
		//Write your code here
		id = count;
		count++;
	}
	
	/** run() runs while isShopOpen is true. It does the following: 
	 * -	Poll next customer 
	 * -	Print the message: Salescounter0: CustomerX served. Q length: Y 鈥� 
	 * -	Sleep for (processingTime x itemsBought) by Customer 
	 * -	Assign current time to Customer鈥檚 dequeueTime 
	 * -	Shop.totalQueueTime += dequeueTime 鈥� enqueueTime 
	 * -	Increment Shop.customersServed 
	 * */
	@Override
	public void run() {
		//write your code here
		while (Shop.isShopOpen) {
			
			Customer nextCustomer = null;
			synchronized (Shop.customerQ){
				nextCustomer = Shop.customerQ.poll();
				if (nextCustomer != null) {
					System.out.println("    Salescounter0: Customer" + nextCustomer.id + " served. Q length:" + Shop.customerQ.size());
					try {
						Thread.sleep(Shop.processingTime*nextCustomer.itemsBought);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					nextCustomer.dequeueTime = System.currentTimeMillis();
					
					Shop.totalQueueTime += nextCustomer.dequeueTime - nextCustomer.enqueueTime;
					
					Shop.customersServed++;
				}//end of if
			}
			
			//System.out.println(Shop.customersServed);
			if (Shop.customersServed >= Shop.maxCustomer) {
				Shop.isShopOpen = false;
			}
		}
	}
}

