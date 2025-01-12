package gameworld.entities;

import gameworld.Component;
import mathtools.SolidArea;

public abstract class Entity extends Component {

    protected SolidArea solidArea;

    /**
     * This method sets a solid area for an entity, consisting of a few points
     */
    protected abstract void setSolidArea();

    public final SolidArea getSolidArea() {
        return solidArea;
    }

    /**
     * This method updates position of a solid area of the entity on the screen
     * whenever the camera position changes
     *
     * @param xDiff Difference of x
     * @param yDiff Difference of y
     */
    public final void updateSolidAreaScreenPos(double xDiff, double yDiff) {
        solidArea.move(-xDiff, yDiff);
    }
}
