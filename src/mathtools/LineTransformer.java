package mathtools;

import java.awt.geom.Point2D;

public class LineTransformer {

    /* y = kx + b, where `k` is angleCoef and `b` is freeVar */
    private double angleCoef;
    private double freeVar;

    public LineTransformer() {
    }

    /**
     * This method rebuilds the line represented by two points to coincide with the direction between
     * Ahmad and the mouse after screen scaling
     *
     * @param point1
     * @param point2
     */
    public void rebuilt(Point2D.Double point1, Point2D.Double point2) {
        angleCoef = (point2.getY() - point1.getY()) / (point2.getX() - point1.getX());  // k = (y2 - y1)/(x2 - x1)
        freeVar   = (point1.getY() - angleCoef * point1.getX());   // b = y - kx
    }
}
