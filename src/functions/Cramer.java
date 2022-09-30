package functions;
import functions.Determinant;
import dataStructure.*;


public class Cramer {
	static final double[] noSol = {-9999.999};
	//Jika suatu SPL tidak mempunyai solusi unik, maka cramerSPL() mengembalikan noSol
	
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
		
		if (d==0) {
			return noSol;
		}
		else {
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
			return result;
		}
		
	}
	
	static void displaySPLCramerResult(double[] sol) {
		if (sol.equals(noSol)) {
			System.out.println("Tidak dapat diselesaikan dengan metode Cramer karena determinan matriks koefisien = 0");
		}
		else {
			for (int i=0; i<sol.length; i++) {
				System.out.printf("x%d = %f\n", i+1, sol[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		Matrix augmented = new Matrix();
		augmented.readMatrix();
		displaySPLCramerResult(cramerSPL(augmented, augmented.getRowLength()));
	}
}
