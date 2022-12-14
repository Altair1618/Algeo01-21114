package functions;
import dataStructure.*;
import utility.Operations;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Interpolasi_polinom {
	public static Matrix interpolasi(Matrix givenData) {
		int n= givenData.getCountPoint();
		// Buat matrix A (koefisien) dan B (hasil persamaan)
		Matrix A = new Matrix();
		A.createMatrix(n, n);
		Matrix B = new Matrix();
		B.createMatrix(n, 1);
		
		// Isi matrix A dan B
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				A.setElement(Math.pow(givenData.getElement(i, 1), j-1), i, j);
			}
		}
		
		for (int i=1; i<=n; i++) {
			B.setElement(givenData.getElement(i, 2), i, 1);
		}
		
		//temp = inverse(A)
		Matrix temp = new Matrix();
		temp.createMatrix(n, n);
		temp = Gauss.inverseGauss(A);
		
		// Solusi SPL = (temp)(B)
		Matrix solution = new Matrix();
		solution.createMatrix(n, 1);
		solution = Operations.multiplyMatrix(temp, B);
		solution.roundMatElmt(5);
		return solution;
	}
	
	public static void displayPolinom(Matrix solution) {
		int n = solution.getRowLength();
		System.out.printf("f(x) = ");
		if (solution.getElement(1, 1) != 0) {
			System.out.print(solution.getElement(1, 1) + " ");
		}
		if (solution.getElement(2, 1) > 0) {
			System.out.print("+ (" + solution.getElement(2, 1) + ")x ");
		}
		else if (solution.getElement(2, 1) < 0) {
			System.out.print("- (" + (-solution.getElement(2, 1)) + ")x ");
		}
		
		for (int i=3; i<=n; i++) {
			if (i % 5 ==0) {
				System.out.println();
			}
			if (solution.getElement(i, 1) > 0) {
				System.out.print("+ (" + solution.getElement(i, 1) + ")x^" + (i-1) + " ");
			}
			else if (solution.getElement(i, 1) < 0) {
				System.out.print("- (" + (-solution.getElement(i, 1)) + ")x^" + (i-1) + " ");
			}
		}
		System.out.println();
	}

	public static void writePolinom(FileWriter fileWriter, Matrix solution) {
		try {
			int n = solution.getRowLength();
			fileWriter.write("f(x) = ");
			if (solution.getElement(1, 1) != 0) {
				fileWriter.write(String.valueOf(solution.getElement(1, 1) + " "));
			}
			if (solution.getElement(2, 1) > 0) {
				fileWriter.write(String.valueOf("+ (" + solution.getElement(2, 1) + ")x "));
			}
			else if (solution.getElement(2, 1) < 0) {
				fileWriter.write(String.valueOf("- (" + (-solution.getElement(2, 1)) + ")x "));
			}
			
			for (int i=3; i<=n; i++) {
				if (i % 5 ==0) {
					fileWriter.write("\n");
				}
				if (solution.getElement(i, 1) > 0) {
					fileWriter.write(String.valueOf("+ (" + solution.getElement(i, 1) + ")x^" + (i-1) + " "));
				}
				else if (solution.getElement(i, 1) < 0) {
					fileWriter.write(String.valueOf("- (" + (-solution.getElement(i, 1)) + ")x^" + (i-1) + " "));
				}
			}
			fileWriter.write("\n");
		} catch (IOException e) {
			System.out.println("Terjadi Kesalahan");
		}
	}
	
	public static void displayValueOfFx(Matrix solution, Matrix findValueOf) {
		double result;
		double x;
		int n = findValueOf.getCountPoint();
		for (int i=1; i<=n; i++) {
			result =0;
			x=findValueOf.getElement(i, 1);
			for (int j=1; j<=solution.getRowLength(); j++) {
				result += solution.getElement(j, 1)*Math.pow(x, j-1);
			}
			result = BigDecimal.valueOf(result).setScale(5, RoundingMode.HALF_UP).doubleValue();
			System.out.println("f(" + x + ") = " + result);
			
		}
	}

	public static void writeValueOfFx(FileWriter fileWriter, Matrix solution, Matrix findValueOf) {
		try {
			double result;
			double x;
			int n = findValueOf.getCountPoint();
			for (int i=1; i<=n; i++) {
				result =0;
				x=findValueOf.getElement(i, 1);
				for (int j=1; j<=solution.getRowLength(); j++) {
					result += solution.getElement(j, 1)*Math.pow(x, j-1);
				}
				result = BigDecimal.valueOf(result).setScale(5, RoundingMode.HALF_UP).doubleValue();
				fileWriter.write("f(");
				fileWriter.write(String.valueOf(x));
				fileWriter.write(") = ");
				fileWriter.write(String.valueOf(result));
				fileWriter.write("\n");
			}
		} catch (IOException e) {
			System.out.println("Terjadi Kesalahan");
		}
	}
}