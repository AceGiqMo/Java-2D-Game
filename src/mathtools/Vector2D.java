package mathtools;

import main.Config;

public class Vector2D {
    private double x;
    private double y;

    private double length;
    private double angle;       // In randians

    public <T extends Number> Vector2D(T x, T y) {
        this.x = x.doubleValue();
        this.y = y.doubleValue();

        setLength();
        setAngle();
    }

    private void setLength() {
        length = Math.sqrt(x * x + y * y);
    }

    private void setAngle() {
        if (y != 0) {
            angle = Math.acos(x / length) * Math.signum(y);
            return;
        }

        angle = Math.acos(x / length);
    }

    public static Vector2D add(Vector2D vec1, Vector2D vec2) {
        return new Vector2D(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());
    }

    public static double dotProduct(Vector2D vec1, Vector2D vec2) {
        return vec1.getX() * vec2.getX() + vec1.getY() * vec2.getY();
    }

    public static double angleBetween(Vector2D vec1, Vector2D vec2) {
        return Math.abs(vec1.getAngle() - vec2.getAngle()) % Config.PI;
    }

    public static Vector2D transform(Vector2D vec, Matrix<? extends Number> matrix)
                                    throws ShapesInconsistencyException {

        Double[][] matrixArr = matrix.getMatrix();

        if (matrixArr[0].length != 2) {
            throw new ShapesInconsistencyException();
        }

        double newVecX = matrixArr[0][0] * vec.getX() + matrixArr[0][1] * vec.getY();
        double newVecY = matrixArr[1][0] * vec.getX() + matrixArr[1][1] * vec.getY();

        return new Vector2D(newVecX, newVecY);
    }

    /**
     * This method returns `x` of the vector
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * This method returns `y` of the vector
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * This method returns the length of the vector
     *
     * @return length
     */
    public double getLength() {
        return length;
    }

    /**
     * This method returns the angle between the vector and `x` axis in radians
     *
     * @return angle
     */
    public double getAngle() {
        return angle;
    }


    /**
     * This method returns a friendly representation of the matrix
     */
    @Override
    public String toString() {
        return "Vector{" + x + ", " + y + "}";
    }
}


class ShapesInconsistencyException extends Exception {
    @Override
    public String getMessage() {
        return "Cannot execute the product of vector matrix, "
             + "because the dimensionality of the vector does not suit "
             + "the dimensionality of the matrix's row";
    }
}
