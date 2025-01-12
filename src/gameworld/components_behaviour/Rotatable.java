package gameworld.components_behaviour;

import java.awt.geom.Point2D;

public interface Rotatable {
    /**
     * This method rotates the component of the game world around the given axis
     */
    public void rotate(Point2D axis);
}
