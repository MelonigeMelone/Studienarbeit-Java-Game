package de.davidtobi.javagame.engine.util;

public class PlayerSpriteSheetUtil {

    public static int getSpriteSheetYBasedOnRotation(float rotation) {
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
}