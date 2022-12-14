package functions;

import java.io.FileWriter;
import java.io.IOException;

import dataStructure.*;
/* NOTE:
 * Inverse dengan metode minor-kofaktor
 * */
public class Inverse {
	static final Matrix noInv = new Matrix(); 
	//Jika suatu matrix tidak mempunyai inverse, maka akan inverse() mengembalikan noInv
	static final double[] noSol = {-9999.999};
	//Jika suatu SPL tidak mempunyai solusi unik, maka inverseSPL() mengembalikan noSol
	
	
	public static Matrix inverse(Matrix m, int size) {
		/* NOTE
		 * Mengembalikan inverse dari matriks m
		 * */
		
		//Buat matrix untuk menampung hasil kofaktor (cof) dan hasil inverse (result)
		Matrix result = new Matrix();
		result.createMatrix(size, size);
		noInv.createMatrix(0, 0);
		
		Matrix cof = new Matrix();
		cof.createMatrix(size, size);
		
		//Hitung determinan
		double d = Determinant.detExCof(m, size);
		
		
		if (d==0) {
			return noInv;
		}
		else {
			//Isi matrix kofaktor (cof)
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
		}

		//else (d==0) : tidak mempunyai inverse
		
	}
	public static double[] inverseSPL(Matrix augmentedMatrix, int var) {
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
		
		if (temp==noInv) {
			return noSol;
		}
		else {
			//kalikan temp dengan B, simpen di result
			// result = (inverse(A)) * B
			for (int i=0; i<var; i++) {
				result[i] = 0;
				for (int k=0; k<var; k++) {
					result[i] += temp.getElement(i+1, k+1)*B[k];
				}
			}
			return result;
		}
	}
	
	public static void displayInverse(Matrix inv) {
		if (inv == noInv) {
			System.out.println("Matriks tidak memiliki inverse");
		}
		else {
			inv.writeMatrix();
		}
	}

	public static void writeInverse(FileWriter fileWriter, Matrix inv) {
		try {
			if (inv == noInv) {
				fileWriter.write("Matriks tidak memiliki inverse\n");
			}
			else {
				for (int i = 0; i < inv.getRowLength(); i++) {
					for (int j = 0; j < inv.getColumnLength()-1; j++) {
						fileWriter.write(String.valueOf(inv.getElement(i+1, j+1)));
						fileWriter.write(" ");
					}
					fileWriter.write(String.valueOf(inv.getElement(i+1, inv.getColumnLength())));
					fileWriter.write("\n");
				}
			}
			System.out.println("Output telah tersedia pada File.");
		} catch (IOException e) {
			System.out.print("");
		}
	}

	public static void displayInvSPLResult(double[] sol) {
		if (sol.equals(noSol)) {
			System.out.println("Tidak dapat diselesaikan dengan metode Inverse karena matriks koefisien tidak memiliki inverse");
		}
		else {
			for (int i=0; i<sol.length; i++) {
				System.out.printf("x%d = %f\n", i+1, sol[i]);
			}
		}
	}

	public static void writeInvSPLResult(FileWriter fileWriter, double[] sol) {
		try {
			if (sol.equals(noSol)) {
				fileWriter.write("Tidak dapat diselesaikan dengan metode Inverse karena matriks koefisien tidak memiliki inverse");
			}
			else {
				for (int i=0; i<sol.length; i++) {
					fileWriter.write("x");
					fileWriter.write(String.valueOf(i+1));
					fileWriter.write(" = ");
					fileWriter.write(String.valueOf(sol[i]));
					fileWriter.write("\n");
				}
			}
			System.out.println("");
		} catch (IOException e) {
			System.out.print("");
		}
	}
}