package utility;

import dataStructure.Matrix;

public class Operations {
    public static Matrix copyMatrix(Matrix mat) {
        Matrix newmat = new Matrix();
        newmat.createMatrix(mat.getRowLength(), mat.getColumnLength());
        for (int i = 0; i < mat.getRowLength(); i++) {
            for (int j = 0; j < mat.getColumnLength(); j++) {
                newmat.setElement(mat.getElement(i + 1, j + 1), i + 1, j + 1);
            }
        }
        return newmat;
    }

    public static Matrix multiplyMatrix(Matrix m1, Matrix m2) {
        Matrix res = new Matrix();
        res.createMatrix(m1.getRowLength(), m2.getColumnLength());

        for (int i = 0; i < m1.getRowLength(); i++) {
            for (int j = 0; j < m2.getColumnLength(); j++) {
                res.setElement(0, i + 1, j + 1);
                for (int k = 0; k < m2.getRowLength(); k++) {
                    double temp = m1.getElement(i + 1, k + 1) * m2.getElement(k + 1, j + 1);
                    res.setElement(res.getElement(i + 1, j + 1) + temp, i + 1, j + 1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // Matrix m1 = new Matrix();
        // Matrix m2 = new Matrix();

        // m1.readMatrix();
        // m2.readMatrix();

        // Matrix res = multiplyMatrix(m1, m2);
        // res.writeMatrix();
    }
}
