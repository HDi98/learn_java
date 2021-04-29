// name: Haonan Di
// andrew id: hdi
package lab8;

public class ThreadMart {
	
	/** Create a new Shop, 
	 * assign it to a new Thread, and start it. 
	 * Then wait for child thread to join back. 
	 * Finally print the Total wait time and Average wait time as shown.
	 */
	public static void main(String[] args) {
		System.out.println("*** Welcome to ThreadMart ***");
		Shop s = new Shop();
		Thread thread = (new Thread(s));
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("***Total wait time %,d ms. Average wait time %,d ms", Shop.totalQueueTime, Shop.totalQueueTime / Shop.customersServed);
		
		
	}
}
