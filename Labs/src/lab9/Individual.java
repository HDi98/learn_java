// name: Haonan Di
// andrew id: hdi
package lab9;

import java.util.Random;

public class Individual extends Guest{

	static int individualCount;
	static final int MAX_ORDER_SIZE = 2;
	
	
	Individual(){
		individualCount++;
	}
	
	@Override
	public void placeOrder() {
		// TODO Auto-generated method stub
		Random random = new Random();
		drinks = random.nextInt(MAX_ORDER_SIZE) + 1;
		food = random.nextInt(MAX_ORDER_SIZE) + 1;
	}

}
