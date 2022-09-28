package functions;

import dataStructure.Matrix;
import utility.Operations;

public class Bicubic {
    public static Matrix generateInverseMatrixX() {
        Matrix m = new Matrix();
        m.createMatrix(16, 16);

        int row = 0;
        for (int x = -1; x <= 2; x++) {
            for (int y = -1; y <= 2; y++) {
                int col = 0;
                for (int i = 0; i <= 3; i++) {
                    for (int j = 0; j <= 3; j++) {
                        m.setElement(Math.pow(x, i) * Math.pow(y, j), row + 1, col + 1);
                        col++;
                    }
                }
                row++;
            }
        }

        return Gauss.inverseGauss(m);
    }

    public static Matrix convMatrix(Matrix m) {
        Matrix res = new Matrix();
        res.createMatrix(16, 1);

        int row = 0;
        for (int i = 0; i < m.getRowLength(); i++) {
            for (int j = 0; j < m.getColumnLength(); j++) {
                res.setElement(m.getElement(i + 1, j + 1), row + 1, 1);
                row++;
            }
        }

        return res;
    }

    public static Matrix getBicubicCof(Matrix invX, Matrix m) {
        return Operations.multiplyMatrix(invX, m);
    }

    public static double calcBicubic(Matrix m, double x, double y) {
        Matrix invX = generateInverseMatrixX();
        Matrix mcof = getBicubicCof(invX, convMatrix(m));

        double val = 0;

        int idx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                val += mcof.getElement(idx + 1, 1) * Math.pow(x, i) * Math.pow(y, j);
                idx++;
            }
        }

        return val;
    }

    public static Matrix generateInverseMatrixXBL() {
        Matrix m = new Matrix();
        m.createMatrix(4, 4);

        int row = 0;
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                int col = 0;
                for (int i = 0; i <= 1; i++) {
                    for (int j = 0; j <= 1; j++) {
                        m.setElement(Math.pow(x, i) * Math.pow(y, j), row + 1, col + 1);
                        col++;
                    }
                }
                row++;
            }
        }

        return Gauss.inverseGauss(m);
    }

    public static Matrix convMatrixBL(Matrix m) {
        Matrix res = new Matrix();
        res.createMatrix(4, 1);

        int row = 0;
        for (int i = 0; i < m.getRowLength(); i++) {
            for (int j = 0; j < m.getColumnLength(); j++) {
                res.setElement(m.getElement(i + 1, j + 1), row + 1, 1);
                row++;
            }
        }

        return res;
    }

    public static Matrix getBilinearCof(Matrix invX, Matrix m) {
        return Operations.multiplyMatrix(invX, m);
    }

    public static double calcBilinear(Matrix m, double x, double y) {
        Matrix invX = generateInverseMatrixXBL();
        Matrix mcof = getBilinearCof(invX, convMatrixBL(m));

        double val = 0;

        int idx = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                val += mcof.getElement(idx + 1, 1) * Math.pow(x, i) * Math.pow(y, j);
                idx++;
            }
        }

        return val;
    }

    public static void main(String[] args) {
        // Matrix invX = generateInverseMatrixX();

        Matrix m = new Matrix();
        m.readMatrix();

        System.out.println(calcBilinear(m, 0.5, 0.5));
    }
}
