package Coin;

public class Coin {
    public static final int HEADS = 0;
    public static final int TAILS = 1;

    private int face;

    public Coin() {
        flip();
    }
    
    public void flip() {
        face = (int) (Math.random() * 2);
    }

    public boolean isHeads() {
        return face == HEADS;
    }

    public String toString() {
        if (face == HEADS) {
            return "Heads";
        }

        return "Tails";
    }
}