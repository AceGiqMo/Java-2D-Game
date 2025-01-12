package gameworld;

import java.awt.Graphics2D;

public abstract class Component {

    /* The position of the entity's center on the screen in interface coordinate system*/
    protected double screenX;
    protected double screenY;

    /* The position of the entity's center regarding the map in canonical coordinate system*/
    protected double mapX;
    protected double mapY;

    protected double angle;          // In radians

    /**
     * This method fills the field of the entity
     */
    protected abstract void setDefaultValues();

    /**
     * This method updates position of a game object on the screen whenever the camera position changes
     *
     * @param xDiff Difference of x
     * @param yDiff Difference of y
     */
    public final void updateScreenPos(double xDiff, double yDiff) {
        screenX -= xDiff;
        screenY += yDiff;
    }

    /**
     * This method updates the state of the entity
     */
    public abstract void update();

    /**
     * This method draws the entity
     */
    public abstract void draw(Graphics2D g2);

    public final double getScreenX() {
        return screenX;
    }

    public final double getScreenY() {
        return screenY;
    }

    public final double getMapX() {
        return mapX;
    }

    public final double getMapY() {
        return mapY;
    }
}
