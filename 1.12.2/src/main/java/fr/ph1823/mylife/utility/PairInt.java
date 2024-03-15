package fr.ph1823.mylife.utility;

public class PairInt {

    private int x, y;


    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void down() {
        if(this.y < 4) ++this.y;
    }
    public void up() {
        if(this.y > 1) --this.y;
    }

    public void right() {
        if(this.x < 10) ++this.x;
    }

    public void left() {
        if(this.x > 1) --this.x;
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                "\ny=" + y;
    }
}
