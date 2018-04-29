package com.nickdnepr.panzermarch.mechanics;

public class Bullet extends Item{

    private int armorPiercing;


    public Bullet(int armorPiercing) {
        super(BULLET);
        this.armorPiercing=armorPiercing;
    }

    public int getArmorPiercing() {
        return armorPiercing;
    }

    public void setArmorPiercing(int armorPiercing) {
        this.armorPiercing = armorPiercing;
    }
}
