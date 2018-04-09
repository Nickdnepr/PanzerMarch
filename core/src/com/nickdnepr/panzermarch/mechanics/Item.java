package com.nickdnepr.panzermarch.mechanics;

public class Item {



    private boolean isDead = false;
    private String type;

    public Item(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void markAsDead() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}
