package com.nickdnepr.panzermarch.mechanics;

public class Module extends Armor {

    public static final int ENGINE = 0;
    public static final int TRANSMISSION = 1;
    public static final int BARREL = 2;

    private int module;
    private int hp;
    private boolean burnable;
    private boolean isHuman;
    private boolean explodable;

    public Module(int module, int armor) {
        super(MODULE, armor);
        this.module = module;
        switch (module) {
            case ENGINE: {
                hp = 200;
                burnable = true;
                isHuman = false;
                explodable = false;
                break;
            }
        }
    }

    public int getModule() {
        return module;
    }

    public int getHp() {
        return hp;
    }

    public boolean isBurnable() {
        return burnable;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public boolean isExplodable() {
        return explodable;
    }
}
