package com.nickdnepr.panzermarch.utils.math;

public class AngleUtil {

    public static double getAngle(float width, float height) {

        if (width == 0 && height == 0) {
            return 0;
        }
        if (width == 0 && height > 0) {
            return 90;
        }
        if (width == 0 && height < 0) {
            return 270;
        }
        if (width > 0 && height == 0) {
            return 0;
        }
        if (width < 0 && height == 0) {
            return 180;
        }

        //  1/4
        if (width > 0 && height > 0) {

        }
        //  2/4
        if (width < 0 && height > 0) {

        }
        //  3/4
        if (width < 0 && height < 0) {

        }
        //  4/4
        if (width > 0 && height < 0) {

        }


        return 0;
    }
}
