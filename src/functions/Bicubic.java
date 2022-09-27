package functions;

import dataStructure.Matrix;

public class Bicubic {
    public static void generateInverseMatrixX() {
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

        Gauss.inverseGauss(m);
    }

    public static void main(String[] args) {
        Matrix m;
        generateInverseMatrixX();
        // m.writeMatrix();
    }
}
