package mathtools;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class SolidArea {
    private Point2D[] vertices;
    private Point2D[] currentVertices;
    private double angle;

    public SolidArea(Point2D[] vertices) {
        this.vertices        = vertices;
        this.currentVertices = vertices.clone();
        this.angle           = 0;
    }

    /**
     * This method rotates the basic points of the solid area by the given angle counterclockwise relative to axis
     *
     * @param angle
     * @param axis The point relative to which the rotation is executed
     */
    public void rotate(double angle, Point2D axis) {
        double pointX;
        double pointY;

        double newPointX;
        double newPointY;

        for (int i = 0; i < vertices.length; i++) {
            pointX = vertices[i].getX() - axis.getX();
            pointY = vertices[i].getY() - axis.getY();

            newPointX = pointX * Math.cos(angle) - pointY * Math.sin(angle) + axis.getX();
            newPointY = pointX * Math.sin(angle) + pointY * Math.cos(angle) + axis.getY();

            currentVertices[i] = new Point2D.Double(newPointX, newPointY);
        }

        this.angle = -angle;       // Angle in the canonical coordinate system

    }

    /**
     * This method returns current angle of the solid area
     *
     * @return angle
     */
    public double getAngle() {
        return angle;
    }

    public final void draw(Graphics2D g2) {
        for (Point2D vertex : currentVertices) {
            g2.drawRect((int) vertex.getX(), (int) vertex.getY(), 1, 1);
        }
    }

}
