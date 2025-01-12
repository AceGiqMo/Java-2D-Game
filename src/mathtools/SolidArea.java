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
        /* Screen position, since we create an object only if it is close to the Ahmad */
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
     * This method moves solid area in terms of screen position
     *
     * @param xDiff x offset
     * @param yDiff y offset
     */
    public void move(double xDiff, double yDiff) {
        double pointX;
        double pointY;

        double rotatedPointX;
        double rotatedPointY;

        for (int i = 0; i < vertices.length; i++) {
            pointX = vertices[i].getX();
            pointY = vertices[i].getY();

            rotatedPointX = currentVertices[i].getX();
            rotatedPointY = currentVertices[i].getY();

            vertices[i].setLocation(pointX + xDiff, pointY + yDiff);
            currentVertices[i].setLocation(rotatedPointX + xDiff, rotatedPointY + yDiff);
        }

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
