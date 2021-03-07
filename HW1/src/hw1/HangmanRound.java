package hw1;

public class HangmanRound extends GameRound{

	int hitcount;
	int missCount;
	
	int getHitCount() {
		
		return hitcount;
	}
	
	void setHitCount(int hitCount) {
		this.hitcount = hitCount;
	}
	
	int getMissCount() {
		
		return missCount;
	}
	
	void setMissCount(int miscount) {
		this.missCount = miscount;
	}
}
