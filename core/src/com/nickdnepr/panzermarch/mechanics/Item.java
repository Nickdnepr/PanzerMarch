package com.nickdnepr.panzermarch.mechanics;

public abstract class Item {

    public static final int ARMOR = 0;
    public static final int BULLET = 1;
    public static final int GROUND = 2;
    public static final int MODULE = 3;

    private boolean isDead = false;
    private int type;

    public Item(int type) {

        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void markAsDead() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}
