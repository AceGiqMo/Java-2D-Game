package gameworld.objects;

import mathtools.SolidArea;

public abstract class SolidGameObject extends GameObject {
    private boolean breakable;

    protected SolidArea solidArea;

    public final void setBreakable(boolean isbreakable) {
        breakable = isbreakable;
    }

    /**
     * This methods sets a solid area for the component if necessary
     */
    public abstract void setSolidArea();

    public final SolidArea getSolidArea() {
        return solidArea;
    }

    /**
     * This method updates position of a solid area of the game object on the screen
     * whenever the camera position changes
     *
     * @param xDiff Difference of x
     * @param yDiff Difference of y
     */
    public final void updateSolidAreaScreenPos(double xDiff, double yDiff) {
        solidArea.move(-xDiff, yDiff);
    }

    public final boolean isBreakable() {
        return breakable;
    }

}
