// name: Haonan Di
// andrew id: hdi
package lab9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Kitchen implements Runnable{

	static final int OPENING_STOCK = 125;
	static final int MIN_STOCK = 4;
	static final int DRINK_RATE = 2;
	static final int FOOD_RATE = 4;
	static final int MAX_COOK_TIME = 20;
	static final int MIN_COOK_TIME = 5;
	
	
	boolean underStock = false;
	int currentFoodStock = OPENING_STOCK;
	int currentDrinkStock = OPENING_STOCK;
	Queue<Guest> guestQ = new LinkedList<>();
	int guestsServed;
	
	Kitchen(Queue<Guest> guestQ){
		this.guestQ = guestQ;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!underStock && Diner.dinerOpen) {
			
			Guest newGuest;
			synchronized (guestQ) {
				newGuest = guestQ.poll();				
			}
			if (newGuest != null) {
				newGuest.placeOrder();
				currentFoodStock -= newGuest.food;
				currentDrinkStock -= newGuest.drinks;
				guestsServed++;
				Diner.income += newGuest.food*FOOD_RATE + newGuest.drinks*DRINK_RATE;
				
				Random random = new Random();
				int orderTime = MIN_COOK_TIME + random.nextInt(MAX_COOK_TIME - MIN_COOK_TIME + 1);
				try {
					if (newGuest instanceof Group) {
						System.out.println("                Kitchen serving guest: " + newGuest.id + " Group");
					}else {
						System.out.println("                Kitchen serving guest: " + newGuest.id + " Individual");
					}
					
					Thread.sleep(orderTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (currentFoodStock <= MIN_STOCK || currentDrinkStock <= MIN_STOCK) {
				underStock = true;
				Diner.endTime = System.currentTimeMillis();
			}
		}// end of while
	}

}
