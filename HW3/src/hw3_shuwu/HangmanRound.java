package hw3_shuwu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * name: Shu Wu
 * andrew id: shuwu
 */
public class HangmanRound extends GameRound {

    private IntegerProperty hitcount = new SimpleIntegerProperty();
    private IntegerProperty misscount = new SimpleIntegerProperty();


    //write all getter and setter methods
    public int getHitCount() {
        return hitcount.getValue();
    }

    public void setHitCount(int hitCount) {
        this.hitcount.setValue(hitCount);
    }

    public IntegerProperty hitCountProperty(){
        return hitCountProperty();
    }


    public int getMissCount() {
        return misscount.getValue();
    }

    public void setMissCount(int missCount){
        this.misscount.setValue(missCount);
    }

    public IntegerProperty missCountProperty(){
        return misscount;
    }
}
