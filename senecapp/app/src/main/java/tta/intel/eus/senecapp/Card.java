package tta.intel.eus.senecapp;

import android.widget.Button;

/**
 * Created by jose on 27/01/17.
 */
public class Card {
    private int x;
    private int y;
    public Button button;

    public Card(int x, int y, Button button) {
        this.x = x;
        this.y = y;
        this.button = button;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
