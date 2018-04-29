package com.nickdnepr.panzermarch.mechanics;

public class Armor extends Item {

    private int armor;
    private float angle;

    public Armor(int armor, float angle) {
        super(ARMOR);
        this.armor = armor;
        this.angle = angle;
    }

    protected Armor(int type, int armor) {
        super(type);
        this.armor = armor;
        this.angle = 0;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
