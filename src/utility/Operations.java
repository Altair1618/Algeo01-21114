package utility;

import dataStructure.Matrix;

public class Operations {
    public static Matrix copyMatrix(Matrix mat) {
        Matrix newmat = new Matrix();
        newmat.createMatrix(mat.getRowLength(), mat.getColumnLength());
        for (int i = 0; i < mat.getRowLength(); i++) {
            for (int j = 0; j < mat.getColumnLength(); j++) {
                newmat.setElement(mat.getElement(i + 1, j + 1), i, j);
            }
        }
        return newmat;
    }
}
