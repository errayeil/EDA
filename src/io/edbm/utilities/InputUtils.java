package io.edbm.utilities;

import net.java.games.input.Component;
import net.java.games.input.Component.POV;

public class InputUtils {
    
    public enum POVDirection {CENTER, UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT};
    
    
    
    public static float parseValue(String event) {
        int start = event.lastIndexOf( "=") + 2;
        return Float.valueOf( event.substring( start , event.length() ));
    }
    
    public static String parseInput(String event) {
        int start = event.indexOf( "=" ) + 2;
        int end = event.indexOf( "|" )  - 1;
        return event.substring( start, end );
    }
    
    /**
     *
     * @param value
     * @return
     */
    public static POVDirection getDirection(float value) {
        
        if (value == 0.125f ) {
            return POVDirection.UP_LEFT;
        } else if (value == 0.25f) {
            return POVDirection.UP;
        } else if (value == 0.375f) {
            return POVDirection.UP_RIGHT;
        } else if (value == 0.5f) {
            return POVDirection.RIGHT;
        } else if (value == 0.625f) {
            return POVDirection.DOWN_RIGHT;
        } else if (value == 0.75f) {
            return POVDirection.DOWN;
        } else if (value == 0.875f) {
            return POVDirection.DOWN_LEFT;
        } else if (value == 1.0f) {
            return POVDirection.LEFT;
        }
        
        
        return POVDirection.CENTER;
    }
    
    /**
     *
     * @param direction
     * @return
     */
    public static String getDirectionAsString(POVDirection direction) {
        switch (direction) {
            case UP_LEFT:
                return "UpLeft";
            case UP:
                return "Up";
            case UP_RIGHT:
                return "UpRight";
            case RIGHT:
                return "Right";
            case DOWN_RIGHT:
                return "DownRight";
            case DOWN:
                return "Down";
            case DOWN_LEFT:
                return "DownLeft";
            case LEFT:
                return "Left";
            default: case CENTER:
                return "Center";
        }
    }
}
