package functions;
import functions.Determinant;

/*
 * NOTES
 * 1. Ini juga masih pakai double[][]
 * 2. Belum menangani kasus yang tidak ada penyelesaian atau solusi tidak unik
 */

public class Cramer {
	static double[] cramerSPL (int var, double[][] augmentedMatrix) {
		double d;
		double [][] A = new double [var][var];
		double [] B = new double [var];
		double [][] temp = new double [var][var];
		double[] result = new double[var];
		
		// isi A dan B agar membentuk Ax=B
		for (int i=0; i<var; i++) {
			for (int j=0; j<var; j++) {
				A[i][j] = augmentedMatrix[i][j];
			}
		}
		for (int i=0; i<var; i++) {
			B[i] = augmentedMatrix[i][var];
		}
		
		//hitung determinan A, simpan di d
		d = Determinant.detExCof(A, var);
		
		// if (d != 0) 
		//hitung nilai x ke-n
		for (int n=0; n<var; n++) {
			//isi temp
			for (int i=0; i<var; i++) {
				for (int j=0; j<var; j++) {
					if (j==n) {
						temp[i][j] = B[i];
					}
					else {
						temp[i][j] = A[i][j];
					}
				}
			}
			// hitung det(temp)/d, simpan di result
			result[n] = Determinant.detExCof(temp, var)/d;
		}
		//else : tidak ada penyelesaian. 
		
		return result;
	}
	
	public static void main(String[] args) {
		double[][] A = {{8,1,3,2,0}, 
						{2,9,-1,-2,1}, 
						{1,3,2,-1,2}, 
						{1,0,6,4,3}};
		double[] solution = cramerSPL(4, A);
		for (int i=0; i<4; i++) {
			System.out.println(solution[i]);
		}
 		
	}
}
