package functions;

import dataStructure.*;

public class detRedux {
    Matrix onMatrix;
    final double UNDEF_DET = -9999.99;

    public detRedux(Matrix inMatrix) {
        this.onMatrix = inMatrix;
    }

    public double detByReduction () {
        double det = 1;
        if (!onMatrix.isSquare()) {
            return UNDEF_DET;
        }
        // Matriks berbentuk persegi
        if (onMatrix.countElement() == 1) {
            return onMatrix.getElement(0, 0);
        } 

        // Matriks bukan berukuran 1 x 1
        for (int i = 0; i < onMatrix.getColumnLength() - 1; i++) {
            boolean isValid = true;
            if (onMatrix.getElement(i+1, i+1) == 0) {
                isValid = false;
                for (int search_index = i+1; search_index < onMatrix.getRowLength(); search_index++) {
                    if (onMatrix.getElement(search_index+1, i+1) != 0) {
                        onMatrix.swapRow(i+1, search_index+1);
                        isValid = true;
                        break;
                    }
                }
            }
            if (!isValid) {
                return 0;
            }
            for (int j = i+1; j < onMatrix.getRowLength(); j++) {
                
                double zero = onMatrix.makeZero(onMatrix.getElement(j+1, i+1), onMatrix.getElement(i+1, i+1));
                
                onMatrix.OBE(i, j, zero);
            }
            
            // Determinan
            for (int j = 0; j < onMatrix.getRowLength(); j++){
                det *= onMatrix.getElement(j+1, j+1);
            }
        }
        return det;
    }

    public static void main (String[] args) {
        Matrix m = new Matrix();
        m.readMatrix();
        double determinant;
        detRedux det = new detRedux(m);
        determinant = det.detByReduction();
        System.out.print(determinant);
    }
}
