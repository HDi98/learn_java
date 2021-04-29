// name: Haonan Di
// andrew id: hdi
package lab8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Shop implements Runnable{
	
	static Queue<Customer> customerQ = new LinkedList<>(); 
	static boolean isShopOpen = true;  //starts as true. Set to false when all customers served
	static int maxCustomer; //Maximum number of customers created
	static int customersServed; //Incremented after serving each customer
	static int processingTime;	//Time for SalesCounter to process one sale-item
	static long totalQueueTime; //Incremented  after serving each customer
	
	SalesCounter salesCounters; //instances of SalesCounter
	Thread salesCounterThreads; //threads to run the salesCounters 
	int customerGapTime;	//interval between customer arrivals
	
	/** setupCounters() takes user inputs, creates SalesCounter object, 
	 * assigns it to salesCounterThread, and starts it 
	 **/
	void setupCounters() {
		//write your code here
		Scanner userInput = new Scanner(System.in);
		System.out.println("Sales item processing time?");
		processingTime = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Max customer count?");
		maxCustomer = userInput.nextInt();
		userInput.nextLine();
		
		System.out.println("Customer gap time?");
		customerGapTime = userInput.nextInt();
		userInput.nextLine();
		
		userInput.close();
		
		salesCounters = new SalesCounter();
		salesCounterThreads = new Thread(salesCounters);
		salesCounterThreads.start();
	}

	/** joinQueue() adds customer c to customeQ, 
	 * Prints the message 鈥淪alesCounter0: CustomerX joined with Y items. Q length:Z鈥�. 
	 * Initialize c鈥檚 enqueueTime to current time
	 */
	public void joinQueue(Customer c) {
		//write your code here
		
		synchronized (Shop.customerQ){
			Shop.customerQ.offer(c);
		}
		System.out.println("SalesCounter0: Customer" + c.id + " joined with " + c.itemsBought + " items. Q length: " + Shop.customerQ.size());
		c.enqueueTime = System.currentTimeMillis();
	}

	/** run() invokes setupCounters(), and runs the following 
	 * as long as CustomersServed < maxCustomer 
	 * - 	If Customer.count < maxCustomer, create new customer and pass it to joinQueue() 
	 * -	Sleep for customerGapTime 
	 * -	Set isShopOpen to false 
	 * -	Wait for salesCounterThread to join 
	 * */
	@Override
	public void run() {
		//write your code here
		Shop newShop = new Shop();
		newShop.setupCounters();
		
		while (customersServed < maxCustomer) {
			//System.out.print(customersServed);
			Shop.isShopOpen = true;
			if (customersServed >= maxCustomer) {
				Shop.isShopOpen = false;
			}
			
			if (Customer.count < maxCustomer) {
				// add customer if count < max
				synchronized (Shop.customerQ){
					newShop.joinQueue(new Customer());
				}
							
				try {
					Thread.sleep(customerGapTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				break;
			}
			
			
			
		}//end of while
		if (customersServed >= maxCustomer) {
			Shop.isShopOpen = false;
		}
		
		
		try {
			newShop.salesCounterThreads.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
