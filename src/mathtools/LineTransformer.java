package mathtools;

import main.GamePanel;
import main.Config;

import java.awt.geom.Point2D;

public class LineTransformer {

    private GamePanel gp;

    /* y = kx + b, where `k` is angleCoef and `b` is freeVar */
    private double angle = 0;
    private double angleCoef = 0;
    private double freeVar   = 0;

    private Matrix<Double> transformMatrix;

    public LineTransformer(GamePanel gp) {
        this.gp = gp;

        /*
         * There is no need to build transform matrix if the ratio of the full screen is the
         * same as the ratio of the temporary screen, so we just add an Identity matrix
         */
        double tempScreenRatio = Config.SCREEN_WIDTH / (double) Config.SCREEN_HEIGHT;
        double fullScreenRatio = gp.getFullScreenWidth() / (double) gp.getFullScreenHeight();

        if (tempScreenRatio == fullScreenRatio) {
            Double[][] transformMatrixArr = {
                                                {1.0, 0.0},
                                                {0.0, 1.0}
            };

            transformMatrix = new Matrix<Double>(transformMatrixArr);
            return;
        }

        buildTransformMatrix();      // If the ratio is not the same;
    }

    private void buildTransformMatrix() {
        int c1 = Config.SCREEN_WIDTH / 2;
        int c2 = Config.SCREEN_HEIGHT / 2;
        int w1 = gp.getFullScreenWidth() / 2;
        int w2 = gp.getFullScreenHeight() / 2;

        /*
         * We want to solve the system of linear equations:
         *
         * -w1 = a11 * -c1 + a12 * c2
         *  w2 = a21 * -c1 + a22 * c2
         * -w1 = a11 * -c1 + a12 * -c2
         * -w2 = a21 * -c1 + a22 * -c2
         *
         * This system can be solved by using Cramer's rule
         */
        Integer[][] systemMatrixArr = {
                                        {-c1,  c2,   0,   0},
                                        {0,     0, -c1,  c2},
                                        {-c1, -c2,   0,   0},
                                        {0,     0, -c1, -c2}
        };

        Integer[][] systemMatrixArrCol1 = {
                                            {-w1,  c2,   0,   0},
                                            {w2,    0, -c1,  c2},
                                            {-w1, -c2,   0,   0},
                                            {-w2,   0, -c1, -c2}
        };

        Integer[][] systemMatrixArrCol2 = {
                                            {-c1, -w1,   0,   0},
                                            {0,    w2, -c1,  c2},
                                            {-c1, -w1,   0,   0},
                                            {0,   -w2, -c1, -c2}
        };

        Integer[][] systemMatrixArrCol3 = {
                                            {-c1,  c2, -w1,   0},
                                            {0,     0,  w2,  c2},
                                            {-c1, -c2, -w1,   0},
                                            {0,     0, -w2, -c2}
        };

        Integer[][] systemMatrixArrCol4 = {
                                            {-c1,  c2,   0, -w1},
                                            {0,     0, -c1,  w2},
                                            {-c1, -c2,   0, -w1},
                                            {0,     0, -c1, -w2}
        };

        Matrix<Integer> systemMatrix     = new Matrix<Integer>(systemMatrixArr);
        Matrix<Integer> systemMatrixCol1 = new Matrix<Integer>(systemMatrixArrCol1);
        Matrix<Integer> systemMatrixCol2 = new Matrix<Integer>(systemMatrixArrCol2);
        Matrix<Integer> systemMatrixCol3 = new Matrix<Integer>(systemMatrixArrCol3);
        Matrix<Integer> systemMatrixCol4 = new Matrix<Integer>(systemMatrixArrCol4);

        try {

            double a11 = systemMatrixCol1.getDeterminant() / systemMatrix.getDeterminant();
            double a12 = systemMatrixCol2.getDeterminant() / systemMatrix.getDeterminant();
            double a21 = systemMatrixCol3.getDeterminant() / systemMatrix.getDeterminant();
            double a22 = systemMatrixCol4.getDeterminant() / systemMatrix.getDeterminant();

            Double[][] transformArray = {
                                         {a11, a12},
                                         {a21, a22}
            };

            transformMatrix = new Matrix<Double>(transformArray);

        } catch (DeterminantIsUndefinedException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method rebuilds the line represented by two points to coincide with the direction between
     * Ahmad and the mouse after screen scaling
     *
     * @param point1
     * @param point2
     */
    public void rebuilt(Point2D.Double cursorPoint, Point2D.Double centerPoint) {
        double cursorX = cursorPoint.getX();
        double cursorY = cursorPoint.getY();
        double centerX = centerPoint.getX();
        double centerY = centerPoint.getY();

        try {

            /*
             * We change the sign of vector's `y` because the coordinate system of the
             * graphic interface is gained by symmetry regarding the `x` axis of the original coordinate system.
             *
             * But here we consider that we work on the original coordinate system for convenience.
             * (0, 0) is the center of the temporary screen (i.e. in the original basis),
             * the reference point of the new basis coincides with the reference point
             * of the original basis.
             */
            Vector2D dirVec = new Vector2D(cursorX - centerX, -(cursorY - centerY));

            Double[][] matrixInvArr             = transformMatrix.getInverse();
            Matrix<Double> transformMatrixInv   = new Matrix<Double>(matrixInvArr);

            Vector2D transformedVec = Vector2D.transform(dirVec, transformMatrixInv);

            angle     = transformedVec.getAngle();
            angleCoef = Math.tan(transformedVec.getAngle());
            freeVar   = (centerPoint.getY() - angleCoef * centerPoint.getX());   // b = y - kx

        } catch (ShapesInconsistencyException e) {
            e.printStackTrace();
        } catch (InverseMatrixDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * This methods calculates a point of transformed line according to the gained line equation.
     * This point is outside the screen to make an aim line
     *
     * @return Two points of the line
     */
    public Point2D.Double getPointOutside() {
        double pointX;
        double pointY;

        if (angle >= Config.ANGLE_DIAG && angle <= Config.PI - Config.ANGLE_DIAG) {

            pointY = -Config.SCREEN_OFFSET;
            pointX = Config.SCREEN_WIDTH / 2 + (1 / Math.tan(angle) * (Config.SCREEN_HEIGHT / 2 - pointY));

        } else if (angle >= -Config.PI + Config.ANGLE_DIAG && angle <= -Config.ANGLE_DIAG) {

            pointY = Config.SCREEN_HEIGHT + Config.SCREEN_OFFSET;
            pointX = Config.SCREEN_WIDTH / 2
                  - (1 / Math.tan(angle) * (Config.SCREEN_HEIGHT / 2 + Config.SCREEN_OFFSET));

        } else if (angle > -Config.ANGLE_DIAG && angle < Config.ANGLE_DIAG) {

            pointX = Config.SCREEN_WIDTH + Config.SCREEN_OFFSET;
            pointY = Config.SCREEN_HEIGHT / 2 - Math.tan(angle) * (Config.SCREEN_WIDTH / 2 + Config.SCREEN_OFFSET);

        } else {

            pointX = -Config.SCREEN_OFFSET;
            pointY = Config.SCREEN_HEIGHT / 2 + Math.tan(angle) * (Config.SCREEN_WIDTH / 2 + Config.SCREEN_OFFSET);

        }

        return new Point2D.Double(pointX, pointY);
    }

    /**
     * This method returns the angle of the direction vector
     *
     * @return Angle value
     */
    public double getAngle() {
        return angle;
    }
}
