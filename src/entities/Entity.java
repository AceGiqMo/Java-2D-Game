package entities;

import java.awt.Graphics2D;

public abstract class Entity {

    /* The position of the entity on the screen */
    protected int screenX;
    protected int screenY;

    /* The position of the entity regarding the map */
    protected int mapX;
    protected int mapY;

    protected int speed;

    protected double angle;          // In radians
    protected double angleCoef;      // Angle coefficient (basically, tan(pi/2 - angle))

    /**
     * This method fills the field of the entity
     */
    public abstract void setDefaultValues();

    /**
     * This method updates the state of the entity
     */
    public abstract void update();

    /**
     * This method draws the entity
     */
    public abstract void draw(Graphics2D g2);

    /**
     * This method returns x position on the screen of the entity
     * @return x integer
     */
    public final int getScreenX() {
        return screenX;
    }

    /**
     * This method returns y position on the screen of the entity
     * @return y integer
     */
    public final int getScreenY() {
        return screenY;
    }

    /**
     * This method returns x position on the map of the entity
     * @return x integer
     */
    public final int getMapX() {
        return mapX;
    }

    /**
     * This method returns y position on the map of the entity
     * @return y integer
     */
    public final int getMapY() {
        return mapY;
    }
}
