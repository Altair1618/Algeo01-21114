package functions;
import functions.Determinant;
import dataStructure.*;

/*
 * NOTES
 * 1. Belum menangani kasus tidak ada penyelesaian atau solusi tidak unik
 */

public class Cramer {
	static double[] cramerSPL (Matrix augmentedMatrix, int var) {
		/* NOTE
		 * Menemukan solusi SPL dengan kaidah Cramer, dengan input berupa matriks augmented 
		 * dan jumlah variabel (var) dan mengembalikan array berukuran var yang berisi solusi 
		 * [x1, x2,..., xvar]
		 */
		
		double d;
		Matrix A = new Matrix();
		A.createMatrix(var, var);
		double[] B = new double[var];
		Matrix temp = new Matrix();
		temp.createMatrix(var, var);
		double[] result = new double[var];
		
		// isi A dan B agar membentuk Ax=B
		for (int i=1; i<=var; i++) {
			for (int j=1; j<=var; j++) {
				A.setElement(augmentedMatrix.getElement(i, j), i, j);
			}
		}
		for (int i=0; i<var; i++) {
			B[i] = augmentedMatrix.getElement(i+1, var+1);
		}
		
		//hitung determinan A, simpan di d
		d = Determinant.detExCof(A, var);
		
		// if (d != 0) 
		//hitung nilai x ke-n
		for (int n=1; n<=var; n++) {
			//isi temp
			for (int i=1; i<=var; i++) {
				for (int j=1; j<=var; j++) {
					if (j==n) {
						temp.setElement(B[i-1], i, j);
					}
					else {
						temp.setElement(A.getElement(i, j), i, j);
					}
				}
			}
			// hitung det(temp)/d, simpan di result
			result[n-1] = Determinant.detExCof(temp, var)/d;
		}
		//else : tidak ada penyelesaian unik.
		
		return result;
	}
	
	public static void main(String[] args) {
		Matrix A = new Matrix();
		A.readMatrix();
		double[] solution = cramerSPL(A, 3);
		for (int i=0; i<3; i++) {
			System.out.println(solution[i]);
		}
 		
	}
}
