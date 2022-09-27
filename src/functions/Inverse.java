package functions;
import dataStructure.*;
/* NOTE:
 * Inverse dengan metode minor-kofaktor, belum menangani kasus tidak ada penyelesaian atau 
 * solusi tidak unik
 * */
public class Inverse {
	public static Matrix inverse(Matrix m, int size) {
		/* NOTE
		 * Mengembalikan inverse dari matriks m
		 * */
		Matrix result = new Matrix();
		result.createMatrix(size, size);
		Matrix cof = new Matrix();
		cof.createMatrix(size, size);
		
		double d = Determinant.detExCof(m, size);
		// if (d != 0)
		for (int i=1; i<=size; i++) {
			for (int j=1; j<=size; j++) {
				cof.setElement(Determinant.cofactor(m, i, j, size), i, j);
			}
		}
		//transpose cof, kalikan setiap elemen dengan 1/d, simpen di result
		cof.transpose();
		for (int i=1; i<=size; i++) {
			for (int j=1; j<=size; j++) {
				result.setElement(cof.getElement(i, j)*(1/d), i, j);
			}
		}	
	
		return result;
		//else (d==0) : tidak mempunyai inverse
		
	}
	static double[] inverseSPL(Matrix augmentedMatrix, int var) {
		/* NOTE
		 * Menemukan solusi SPL dengan metode inverse, dengan input berupa matriks augmented 
		 * dan jumlah variabel (var) dan mengembalikan array berukuran var yang berisi solusi 
		 * [x1, x2,..., xvar]
		 */
		
		Matrix A = new Matrix();
		A.createMatrix(var,var);
		double [] B = new double [var];
		Matrix temp = new Matrix();
		temp.createMatrix(var, var);
		double[] result = new double[var];
		
		//isi A dan B agar membentuk Ax=B
		for (int i=1; i<=var; i++) {
			for (int j=1; j<=var; j++) {
				A.setElement(augmentedMatrix.getElement(i, j), i, j);
			}
		}
		for (int i=0; i<var; i++) {
			B[i] = augmentedMatrix.getElement(i+1, var+1);
		}
		
		//inverse A, simpen di temp
		temp = inverse(A, var);
		
		//kalikan temp dengan B, simpen di result
		for (int i=0; i<var; i++) {
			result[i] = 0;
			for (int k=0; k<var; k++) {
				result[i] += temp.getElement(i+1, k+1)*B[k];
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Matrix A = new Matrix();
		A.readMatrix();
		double[] B = inverseSPL(A, A.getRowLength());
		for (int i=0; i<A.getRowLength(); i++) {
			System.out.println(B[i]);
		}

	}	
}