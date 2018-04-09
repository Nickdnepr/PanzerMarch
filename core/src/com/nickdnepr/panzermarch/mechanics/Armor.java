package com.nickdnepr.panzermarch.mechanics;

public class Armor extends Item{

    private int armor;

    public Armor(String type, int armor) {
        super(type);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
