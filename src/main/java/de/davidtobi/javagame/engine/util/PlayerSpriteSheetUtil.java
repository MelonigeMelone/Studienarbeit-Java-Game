package de.davidtobi.javagame.engine.util;

public class PlayerSpriteSheetUtil {

    public static int getSpriteSheetYBasedOnRotationPlayer(float rotation) {
        if(rotation == 0) {
            return 3;
        } else if(rotation == 90) {
            return 2;
        } else if(rotation == 180) {
            return 0;
        } else if(rotation == 270) {
            return 1;
        }

        return 0;

    }

    public static int[] getSpriteSheetCoordsBasedOnRotationRoboter(float rotation) {
        if(rotation == 0) {
            return new int[]{0, 0};
        } else if(rotation == 90) {
            return new int[]{1, 0};
        } else if(rotation == 180) {
            return new int[]{2, 0};
        } else if(rotation == 270) {
            return new int[]{3, 0};
        }

        return new int[]{0, 0};
    }
}
