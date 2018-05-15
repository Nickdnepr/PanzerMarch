package com.nickdnepr.panzermarch.utils.constants;

import com.badlogic.gdx.math.Vector2;

public class Properties {

    public static class GlobalSizes {
        public static final float BULLET_RADIUS = 0.3f;
        public static final int BUTTON_SIZE = 8;
    }

    public static class TankProperties {

        public static class TestLightTank {
            public static final float UPPER_PLATE_WIDTH = 2.2f;
            public static final float LOWER_PLATE_WIDTH = 2;
            public static final float ROOF_AND_BOTTOM_PLATE_WIDTH = 6;
            public static final float PLATE_HEIGHT = 0.2f;
            public static final float TURRET_SIDE_PALE_WIDTH = 1.5f;
            public static final float TURRET_ROOF_WIDTH = 1;
            public static final int ARMOR = 20;
        }

        public static class Panzer1LightTank {
            public static final float BATTLE_BAY_ROOF_WIDTH = 6;
            public static final float BATTLE_BAY_ROOF_HEIGHT = 0.2f;
            public static final Vector2 BATTLE_BAY_ROOF_POSITION = new Vector2(0, 2);
            public static final int BATTLE_BAY_ROOF_ARMOR = 8;
            public static final int BATTLE_BAY_ROOF_ANGLE = 0;

            public static final float FRONT_TOP_WIDTH = 2;
            public static final float FRONT_TOP_HEIGHT = 0.7f;
            public static final Vector2 FRONT_TOP_POSITION = new Vector2(3, 1.2f);
            public static final int FRONT_TOP_ANGLE = -70;
            public static final int FRONT_TOP_ARMOR = 13;

            public static final float FRONT_MIDDLE_WIDTH = 5;
            public static final float FRONT_MIDDLE_HEIGHT = 0.7f;
            public static final Vector2 FRONT_MIDDLE_POSITION = new Vector2(5.3f, -0.8f);
            public static final int FRONT_MIDDLE_ANGLE = -28;
            public static final int FRONT_MIDDLE_ARMOR = 8;
        }

    }


}
