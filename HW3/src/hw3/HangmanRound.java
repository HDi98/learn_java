//author: Haonan Di
//author andrew id: hdi
package hw3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HangmanRound extends GameRound{

	IntegerProperty hitcount = new SimpleIntegerProperty();
	IntegerProperty missCount = new SimpleIntegerProperty();
	
	int getHitCount() {
		
		return hitcount.get();
	}
	
	void setHitCount(int hitCount) {
		this.hitcount.set(hitCount);   
	}
	
	IntegerProperty hitCountProperty() {
		return hitcount;
	}
	
	int getMissCount() {
		
		return missCount.get();
	}
	
	void setMissCount(int miscount) {
		this.missCount.set(miscount);   
	}
	IntegerProperty missCountProperty() {
		return missCount;
	}
}
