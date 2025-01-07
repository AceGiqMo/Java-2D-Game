package mathtools;

public class Matrix<T extends Number> {
    private Double[][] matrix;

    private int rowNum;
    private int colNum;

    private double det;

    private Double[][] inverse = null;

    public Matrix(T[][] matrix) {
        rowNum = matrix.length;
        colNum = matrix[0].length;

        convertToDoubleMatrix(matrix);
        calculateDeterminant();
        constructInverse();

    }

    /**
     * This method returns representation of the matrix as a double array
     *
     * @return matrix
     */
    public Double[][] getMatrix() {
        return matrix;
    }

    /**
     * This method returns calculated determinant of the matrix if it exists
     *
     * @return The value of the determinant or NaN
     * @throws DeterminantIsUndefined
     */
    public double getDeterminant() throws DeterminantIsUndefinedException {
        if (Double.isNaN(det)) {
            throw new DeterminantIsUndefinedException();
        }

        return det;
    }

    /**
     * This method returns inverse matrix if it exists
     *
     * @return Inverse matrix
     * @throws InverseMatrixDoesNotExist
     */
    public Double[][] getInverse() throws InverseMatrixDoesNotExistException {
        if (inverse == null) {
            throw new InverseMatrixDoesNotExistException();
        }

        return inverse;
    }

    /**
     * This method launches the calculation of determinant of the matrix, if it is of square type
     */
    private void calculateDeterminant() {
        if (rowNum != colNum) {
            det = Double.NaN;
            return;
        }

        det = determinant(matrix, rowNum, colNum);
    }

        /**
     * This method calculates recursively matrix's determinant using Laplace expansion
     *
     * @param matrix
     * @return The determinant of the original matrix or the minor of a cut matrix in case of nested function
     */
    private double determinant(Double[][] matrix, int rows, int cols) {
        if (matrix.length == 1) {
            return (double) matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double result = 0.0;

        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            /* Cut of the matrix without the row `rowIndex` and 0th column */
            Double[][] mCut = constructMatrixCut(matrix, rowIndex, 0);

            result += Math.pow(-1.0, (double) rowIndex) * matrix[rowIndex][0] * determinant(mCut, rows - 1, cols - 1);
        }

        return result;

    }

    private void constructInverse() {
        if (det == 0) {
            return;
        }

        inverse = new Double[rowNum][colNum];

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {

                Double[][] mCut = constructMatrixCut(matrix, row, col);

                inverse[col][row] = Math.pow(-1.0, row + col)
                                  * determinant(mCut, rowNum - 1, colNum - 1)
                                  / det;
            }
        }
    }

    /**
     * This method builds a cut of the matrix without the row `elemRow` and the column `elemCol`
     *
     * @param matrix
     * @param elemRow row index of the element
     * @param elemCol column index of the element
     *
     * @return Matrix's cut
     */
    private Double[][] constructMatrixCut(Double[][] matrix, int elemRow, int elemCol) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        Double[][] mCut = new Double[rows - 1][cols - 1];

        int mCutRow = 0;

        for (int row = 0; row < rows; row++) {

            int mCutCol = 0;

            if (row == elemRow) {
                /* Algebraic complement of the element does not include the row of the element */
                continue;
            }

            for (int col = 0; col < cols; col++) {

                if (col == elemCol) {
                    /* Algebraic complement of the element does not include the column of the element */
                    continue;
                }

                mCut[mCutRow][mCutCol] = matrix[row][col];

                mCutCol++;
            }

            mCutRow++;
        }

        return mCut;
    }

    private void convertToDoubleMatrix(T[][] matrix) {
        this.matrix = new Double[rowNum][colNum];

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                this.matrix[row][col] = matrix[row][col].doubleValue();
            }
        }
    }
}


class DeterminantIsUndefinedException extends Exception {
    @Override
    public String getMessage() {
        return "The determinant of this matrix is undefined, since it is not square matrix";
    }
}


class InverseMatrixDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "The inverse of the matrix does not exist, since the determinant is equal to 0";
    }
}
