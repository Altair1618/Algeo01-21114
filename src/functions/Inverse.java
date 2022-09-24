package functions;
/* NOTE:
 * Inverse dengan metode minor-kofaktor, belum menangani kasus tidak ada penyelesaian atau 
 * solusi tidak unik
 * */
public class Inverse {
	static double[][] inverse(double[][] matrix, int size) {
		double[][] result = new double[size][size];
		double [][] cof = new double[size][size];
		double d = Determinant.detExCof(matrix, size);
		// if (d != 0)
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				cof[i][j] = Determinant.cofactor(matrix, i+1, j+1, size);
			}
		}
		
		//transpose cof, kalikan setiap elemen dengan 1/d, simpen di result
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				result[i][j] = cof[j][i]*(1/d);
			}
		}	
	
		return result;
	}
	static double[] inverseSPL(double[][] augmentedMatrix, int var) {
		double [][] A = new double [var][var];
		double [] B = new double [var];
		double [][] temp = new double [var][var];
		double[] result = new double[var];
		
		//isi A dan B
		for (int i=0; i<var; i++) {
			for (int j=0; j<var; j++) {
				A[i][j] = augmentedMatrix[i][j];
			}
		}
		for (int i=0; i<var; i++) {
			B[i] = augmentedMatrix[i][var];
		}
		
		//inverse A, simpen di temp
		temp = inverse(A, var);
		
		//kalikan temp dengan B, simpen di result
		for (int i=0; i<var; i++) {
			result[i] = 0;
			for (int k=0; k<var; k++) {
				result[i] += (temp[i][k]*B[k]);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		double[][] A = {{1,1,1,6},{1,-1,2,5},{3,1,-1,2}};
		double[] B = inverseSPL(A, 3);
		for (int i=0; i<3; i++) {
			System.out.println(B[i]);
		}	
	}
}
