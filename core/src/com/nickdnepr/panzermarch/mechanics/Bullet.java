package com.nickdnepr.panzermarch.mechanics;

public class Bullet extends Item{

    private int armorPiercing;

    public Bullet(String type, int armorPiercing) {
        super(type);
        this.armorPiercing=armorPiercing;
    }

    public int getArmorPiercing() {
        return armorPiercing;
    }

    public void setArmorPiercing(int armorPiercing) {
        this.armorPiercing = armorPiercing;
    }
}
