// name: Haonan Di
// andrew id: hdi
package lab9;

import java.util.Random;

public class Group extends Guest{

	static final int MAX_ORDER_SIZE = 4;
	static int groupCount;
	
	Group(){
		groupCount++;
	}
	
	
	@Override
	public void placeOrder() {
		// TODO Auto-generated method stub
		Random random = new Random();
		drinks = random.nextInt(MAX_ORDER_SIZE) + 1;
		food = random.nextInt(MAX_ORDER_SIZE) + 1;
	}

}
