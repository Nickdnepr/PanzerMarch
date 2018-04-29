package com.nickdnepr.panzermarch.mechanics;

public class Module extends Armor {

    public static final int ENGINE = 0;
    public static final int TRANSMISSION = 1;
    public static final int BARREL = 2;

    private int module;

    public Module(int module, int armor) {
        super(MODULE, armor);
        this.module = module;
    }
}
