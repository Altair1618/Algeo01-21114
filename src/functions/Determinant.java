package functions;
import dataStructure.*;
/* NOTES:
*  1. Ini determinan yg pake ekspansi kofaktor
 * 2. Untuk sekarang input sama return method nya dalam tipe double[][] ya gais, soalnya pas aku coba pake class
 *   Matrix malah aneh hasilnya (tapi kalo pake double[][] aman)
 * 3. Soon : Penyelesaian SPL dengan kaidah cramer
 */

public class Determinant {

	//main buat ngetest
	public static void main(String[] args) {
		Matrix nice = new Matrix();
		nice.readMatrix();
		System.out.println(detExCof(nice.matrix, nice.row));
	}

	public static double detExCof(double[][] matrix, int size) {
		if (size==2) {
			return (matrix[0][0]*matrix[1][1])-(matrix[1][0]*matrix[0][1]);
		}
		else {
			double result =0;
			for (int k=0; k<size; k++) {
				result += matrix[k][0]*cofactor(matrix, k+1, 1, size);
			}
			return result;
		}
	}
	
	public static double[][] minor(double[][] matrix, int brs, int klm, int size) {
		double [][] result = new double[size-1][size-1];
		for (int i=0; i<size-1; i++) {
			for (int j=0; j<size-1; j++) {
				int k=i, l=j;
				if (i>=brs-1) {
					k++;
				}
				if (j>=klm-1) {
					l++;
				}
				result[i][j] = matrix[k][l];
			}
		}
		return result;
	}
	
	public static double cofactor(double[][] matrix, int brs, int klm, int size) {
		return Math.pow(-1, brs+klm)*detExCof(minor(matrix, brs, klm, size), size-1);
	}
	
	
}
