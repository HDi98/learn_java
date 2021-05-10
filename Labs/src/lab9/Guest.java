// name: Haonan Di
// andrew id: hdi
package lab9;

public abstract class Guest {

	static int guestCount;
	
	int id, drinks, food;
	
	
	Guest(){
		guestCount++;
		id = guestCount;
	}
	
	public abstract void placeOrder();
}
