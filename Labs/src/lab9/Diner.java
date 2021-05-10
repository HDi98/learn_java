// name: Haonan Di
// andrew id: hdi
package lab9;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;




public class Diner implements Runnable{
	static final int GUEST_ARRIVAL_GAP = 30;
	static final int MAX_RUN_TIME = 1000;
	static long startTime, endTime;
	static volatile boolean dinerOpen = true;
	static int maxQLength;
	static int guestsEntered;
	static int income;
	
	Queue<Guest> guestQ = new LinkedList<>(); 
	
	
	Kitchen kitchen;
	
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Diner.dinerOpen && !kitchen.underStock) {
			Random random = new Random();
			Guest newGuest;
			int nextTime = 1 + random.nextInt(Diner.GUEST_ARRIVAL_GAP);
			
			//generate a new guest
			int nextOne = random.nextInt(2);
			
			if (nextOne == 0) {
				newGuest = new Individual();
				System.out.println("Diner welcone guest: " + newGuest.id + " Individual");
			}else {
				newGuest = new Group();
				System.out.println("Diner welcone guest: " + newGuest.id + " Group");
			}
			synchronized (guestQ) {
				guestQ.offer(newGuest);
				guestsEntered++;
				if (guestQ.size() > maxQLength) {
					maxQLength = guestQ.size();
				}
			}
			try {
				Thread.sleep(nextTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (System.currentTimeMillis() - startTime > MAX_RUN_TIME) {
				dinerOpen = false;
				endTime = System.currentTimeMillis();
			}
			
		}
	}

	public static void main(String[] args) {
		Diner diner = new Diner();
		diner.openDiner();	
	}
	
	void openDiner() {
		Diner diner = new Diner();
		Diner.startTime = System.currentTimeMillis();
		diner.kitchen = new Kitchen(diner.guestQ);
		Thread dinerThread = new Thread(diner);
		Thread kitchenThread = new Thread(diner.kitchen);
		
		dinerThread.start();
		kitchenThread.start();
		
		try {
			dinerThread.join();
			kitchenThread.join();
		}catch (InterruptedException e) { e.printStackTrace();}
		//diner.endTime = System.currentTimeMillis();
		diner.printReport();
		diner.checkReport();
	}
	
	void printReport() {
        System.out.println("------------Guests------------");
        System.out.printf("%-25s%5d%n", "Total guests entered:", guestsEntered);
        System.out.printf("%-25s%5d%n", "Individuals entered:", Individual.individualCount);
        System.out.printf("%-25s%5d%n", "Groups entered:", Group.groupCount);
        System.out.printf("%-25s%5d%n", "Total guests served::", kitchen.guestsServed);
        System.out.printf("%-25s%5d%n", "Guests declined service:", guestQ.size());
        System.out.println("----------Kitchen----------");
        System.out.printf("%-25s%5d%n%-25s%5d%n", "Drinks left:", kitchen.currentDrinkStock,
                "Food left:", kitchen.currentFoodStock);
        System.out.printf("%-25s%s%n", "Closing status",
                (kitchen.currentDrinkStock <= Kitchen.MIN_STOCK
                        || kitchen.currentFoodStock <= Kitchen.MIN_STOCK)
                        ? "Under stock" : "Overstock");
        System.out.println("----------Dinner----------");
        System.out.printf("%-25s%5d%n", "MAX Q length", Diner.maxQLength);
        System.out.printf("%-25s%,d ms%n", "Diner was open for:", Diner.endTime - Diner.startTime);
        System.out.printf("%-25s$%,5d%n", "Income:", income);
        System.out.println("-------------------------");
    }

    void checkReport(){
        assertEquals(guestsEntered, Guest.guestCount);
        assertEquals(guestsEntered, Individual.individualCount + Group.groupCount);
        assertEquals(guestsEntered, kitchen.guestsServed + guestQ.size());
        assertEquals(income, (Kitchen.OPENING_STOCK - kitchen.currentDrinkStock)
         * Kitchen.DRINK_RATE + (Kitchen.OPENING_STOCK - kitchen.currentFoodStock) *
                Kitchen.FOOD_RATE);
    }
}
