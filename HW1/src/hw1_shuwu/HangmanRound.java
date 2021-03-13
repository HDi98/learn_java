package hw1_shuwu;

public class HangmanRound extends GameRound {

    private int hitcount;
    private int missCount;

    //write all getter and setter methods
    public int getHitCount() {
        return this.hitcount;
    }

    public void setHitCOunt(int hitCount) {
        this.hitcount = hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }
}
