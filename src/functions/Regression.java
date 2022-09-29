package functions;
import dataStructure.*;

public class Regression {
    static Matrix multiplyMatrix (Matrix m1, Matrix m2) {
        Matrix matrixTemp = new Matrix();

        matrixTemp.createMatrix(m1.getRowLength(), m2.getColumnLength());
        for (int i = 0; i < matrixTemp.getRowLength(); i++) {
            for (int j = 0; j < matrixTemp.getColumnLength(); j++) {
                double times = 0;
                for (int k = 0; k < m1.getColumnLength(); k++) {
                    times += (m1.getElement(i+1, k+1) * m2.getElement(k+1, j+1));
                }
                matrixTemp.setElement(times, i+1, j+1);
            }
        }
        
        return matrixTemp;
    }

    static Matrix copyMatrix (Matrix inputMatrix) {
        Matrix matrixTemp = new Matrix();
        matrixTemp.createMatrix(inputMatrix.getRowLength(), inputMatrix.getColumnLength());
        for (int i = 0; i < inputMatrix.getRowLength(); i++) {
            for (int j = 0; j < inputMatrix.getColumnLength(); j++) {
                matrixTemp.setElement(inputMatrix.getElement(i+1, j+1), i+1, j+1);
            }
        }
        return matrixTemp;
    }


    public static double[] evaluateRegressionEquation (Matrix matrixDataSample, Matrix matrixDataPredict) {
        final double[] UNDEF_REG = new double[1];
        UNDEF_REG[0] = -9999; 

        if (matrixDataSample.getColumnLength() != matrixDataPredict.getColumnLength() + 1) {
            System.out.println("Terjadi kesalahan input");
            System.out.println("Jumlah kolom sampel data harus sama dengan jumlah kolom data prediksi ditambah 1");

            return UNDEF_REG;
        }
        // matrixDataSample.column == matrixDataPredict.column + 1
        
        // Salin Matrix Parameter
        Matrix matrixParameter = new Matrix();
        matrixParameter.createMatrix(matrixDataSample.getRowLength(), matrixDataSample.getColumnLength());
        for (int i = 0; i < matrixDataSample.getRowLength(); i++) {
            for (int j = 0; j < matrixDataSample.getColumnLength(); j++) {
                if (j == 0) {
                    matrixParameter.setElement(1, i+1, j+1);
                } else {
                    matrixParameter.setElement(matrixDataSample.getElement(i+1, j), i+1, j+1);
                }
            }
        }

        // Salin Matrix Parameter Value
        Matrix matrixParameterValue = new Matrix();
        matrixParameterValue.createMatrix(matrixDataSample.getRowLength(), 1);
        for (int i = 0; i < matrixDataSample.getRowLength(); i++) {
            matrixParameterValue.setElement(matrixDataSample.getElement(i+1, matrixDataSample.getColumnLength()), i+1, 1);
        }

        // Salin Matrix Parameter Transpose
        Matrix matrixParameterTranspose = new Matrix();
        matrixParameterTranspose = copyMatrix(matrixParameter);
        matrixParameterTranspose.transpose();

        // Operasi
        Matrix mxmt = new Matrix();
        Matrix mtxy = new Matrix();
        Matrix mxmti = new Matrix();
        Matrix beta = new Matrix();
        mxmt = multiplyMatrix(matrixParameterTranspose, matrixParameter);
        mtxy = multiplyMatrix(matrixParameterTranspose, matrixParameterValue);
        mxmti = Inverse.inverse(mxmt, mxmt.getColumnLength());
        beta = multiplyMatrix(mxmti, mtxy);

        return beta.getColumn(1);
    }

    public static double[] evaluateRegressionValue (double[] regressionEquation, Matrix matrixPredict) {
        double[] resArray = new double[matrixPredict.getRowLength()];
        for (int i = 0; i < matrixPredict.getRowLength(); i++) {
            double result = 0;
            result += regressionEquation[0];
            for (int j = 0; j < matrixPredict.getColumnLength(); j++) {
                result += (matrixPredict.getElement(i+1, j+1) * regressionEquation[j+1]);
            }
            resArray[i] = result;
        }
        return resArray;
    }
}
