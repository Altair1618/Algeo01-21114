package functions;
import java.util.Scanner;
import dataStructure.*;
import utility.Operations;

public class Interpolasi_polinom {
	static void interpolasi() {
		System.out.print("Masukkan jumlah titik (n): ");
		Scanner inputN = new Scanner(System.in);
		int n = inputN.nextInt();
		Point[] tabPoint = new Point[n];
		for (int i=0; i<n; i++) {
			tabPoint[i] = new Point();
			tabPoint[i].x = inputN.nextDouble();
			tabPoint[i].y = inputN.nextDouble();
		}
		
		// Buat matrix A (koefisien) dan B (hasil persamaan)
		Matrix A = new Matrix();
		A.createMatrix(n, n);
		Matrix B = new Matrix();
		B.createMatrix(n, 1);
		
		// Isi matrix A dan B
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				A.setElement(Math.pow(tabPoint[i-1].x, j-1), i, j);
			}
		}
		
		for (int i=1; i<=n; i++) {
			B.setElement(tabPoint[i-1].y, i, 1);
		}
		
		//temp = inverse(A)
		Matrix temp = new Matrix();
		temp.createMatrix(n, n);
		temp = Gauss.inverseGauss(A);
		
		// Solusi SPL = (temp)(B)
		Matrix solution = new Matrix();
		solution.createMatrix(n, 1);
		solution = Operations.multiplyMatrix(temp, B);
		
		// Display fungsi polinomial
		System.out.println("Fungsi polinomial: ");
		System.out.printf("f(x) = ");
		if (solution.getElement(1, 1) != 0) {
			System.out.printf("%f ", solution.getElement(1, 1));
		}
		if (solution.getElement(2, 1) > 0) {
			System.out.printf("+ (%f)x ", solution.getElement(2, 1));
		}
		else if (solution.getElement(2, 1) < 0) {
			System.out.printf("- (%f)x ", -solution.getElement(2, 1));
		}
		
		for (int i=3; i<=n; i++) {
			if (i % 5 ==0) {
				System.out.println();
			}
			if (solution.getElement(i, 1) > 0) {
				System.out.printf("+ (%f)x^%d ", solution.getElement(i, 1), i-1);
			}
			else if (solution.getElement(i, 1) < 0) {
				System.out.printf("- (%f)x^%d ", -solution.getElement(i, 1), i-1);
			}
		}
		System.out.println();
		
		while (true) {
			// Terima input x yang akan diaproksimasi
			System.out.print("Masukkan nilai x yang akan diaproksimasi: ");
			double x = inputN.nextDouble();
			
			// Hitung nilai f(x)
			double result =0;
			
			for (int i=1; i<=n; i++) {
				result += solution.getElement(i, 1) * Math.pow(x, i-1);
			}
			
			// Tampilkan f(x)
			System.out.printf("f(%f) = %f\n", x, result);
			
			System.out.print("Lanjutkan? (Y/N): ");
			String opsi = inputN.next();
			while (!(opsi.equals("Y")) && !(opsi.equals("N"))) {
				System.out.print("Masukkan salah! Ulangi (Y/N): ");
				opsi = inputN.next();
			}
			if (opsi.equals("N")) {
				break;
			}
		}
		/*
		Matrix augmentedMatrix = new Matrix();
		augmentedMatrix.createMatrix(n, n+1);
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				augmentedMatrix.setElement(Math.pow(tabPoint[i-1].x, j-1), i, j);
			}
		}
		for (int i=1; i<=n; i++) {
			augmentedMatrix.setElement(tabPoint[i-1].y, i, n+1);
		} 
		
		double[] solution = Cramer.cramerSPL(augmentedMatrix, n); 
		 */
		
		/*
		System.out.printf("f(x) = %f", solution[0]);
		System.out.printf(" + (%f)x", solution[1]);
		for (int i=2; i<n; i++) {
			System.out.printf (" + (%f)x^%d ", solution[i], i);
		}
		
		
		System.out.println();
		
		while (true) {
			System.out.print("Masukkan nilai x yang akan diaproksimasi: ");
			inputN = new Scanner(System.in);
			double x = inputN.nextDouble();
			double result =0;
			
			for (int i=0; i<n; i++) {
				result += solution[i]*Math.pow(x, i);
			}
			
			System.out.printf("Nilai f(%f) = %f \n", x, result);
			System.out.print("Lanjutkan? (Y/N): ");
			
			String opsi = inputN.next();
			if (opsi.equals("N")) {
				break;
			}
			
		}		 
		 */
		//Display persamaan polinomial

		inputN.close();
		
		
	}
	public static void main(String[] args) {
		interpolasi();
	}
	
}
