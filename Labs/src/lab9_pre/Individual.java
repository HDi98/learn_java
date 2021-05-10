package lab9_pre;

public class Individual extends Guest{

	static int individualCount;
	
	int individualId;
	
	Individual(){
		individualCount++;
		individualId = individualCount;
	}
	
	@Override
	public void placeOrder() {
		// TODO Auto-generated method stub
		
	}

}
