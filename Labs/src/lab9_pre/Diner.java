package lab9_pre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lab8_pre.TA;


public class Diner implements Runnable{
	static final int GUEST_ARRIVAL_GAP = 5;
	static Queue<Guest> guestQ = new LinkedList<>(); 
	static volatile boolean dinerOpen;
	
	Kitchen kitchen;
	
	int maxQLength;
	long startTime, endTime;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (Diner.dinerOpen && !Kitchen.underStock) {
			
		}
	}

	public static void main(String[] args) {

		
	}
	
	void openDiner() {
		Diner diner = new Diner();
		diner.startTime = System.currentTimeMillis();
		diner.openDiner();
		
		Thread dinerThread = new Thread(diner);
		Thread kitchenThread = new Thread(diner.kitchen);
		
		dinerThread.start();
		kitchenThread.start();
		
		try {
			dinerThread.join();
			kitchenThread.join();
		}catch (InterruptedException e) { e.printStackTrace();}
		//diner.endTime = System.currentTimeMillis();
//		diner.printReport();
//		diner.checkReport();
	}
	
//	void printReport() {
//        System.out.println("------------Guests------------");
//        System.out.printf("%-25s%5d%n", "Total guests entered:", guestsEntered);
//        System.out.printf("%-25s%5d%n", "Individuals entered:", Individual.individualCount);
//        System.out.printf("%-25s%5d%n", "Groups entered:", Group.groupCount);
//        System.out.printf("%-25s%5d%n", "Total guests served::", kitchen.guestsServed);
//        System.out.printf("%-25s%5d%n", "Guests declined service:", guestQ.size());
//        System.out.println("----------Kitchen----------");
//        System.out.printf("%-25s%5d%n%-25s%5d%n", "Drinks left:", kitchen.currentDrinkStock,
//                "Food left:", kitchen.currentFoodStock);
//        System.out.printf("%-25s%s%n", "Closing status",
//                (kitchen.currentDrinkStock <= Kitchen.MIN_STOCK
//                        || kitchen.currentFoodStock <= Kitchen.MIN_STOCK)
//                        ? "Under stock" : "Overstock");
//        System.out.println("----------Dinner----------");
//        System.out.printf("%-25s%5d%n", "MAX Q length", Diner.maxQLength);
//        System.out.printf("%-25s%,d ms%n", "Diner was open for:", Diner.endTime - Diner.startTime);
//        System.out.printf("%-25s$%,5d%n", "Income:", income);
//        System.out.println("-------------------------");
//    }
//
//    void checkReport(){
//        assertEquals(guestsEntered, Guest.guestCount);
//        assertEquals(guestsEntered, Individual.individualCount + Group.groupCount);
//        assertEquals(guestsEntered, kitchen.guestsServed + guestQ.size());
//        assertEquals(income, (Kitchen.OPENING_STOCK - kitchen.currentDrinkStock)
//         * Kitchen.DRINK_RATE + (Kitchen.OPENING_STOCK - kitchen.currentFoodStock) *
//                Kitchen.FOOD_RATE);
//    }
}
