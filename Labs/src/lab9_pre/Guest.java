package lab9_pre;

public abstract class Guest {

	static int guestCount;
	
	int guestId;
	
	Guest(){
		guestCount++;
		guestId = guestCount;
	}
	
	public abstract void placeOrder();
}
