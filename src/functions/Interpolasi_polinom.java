package functions;
import java.util.Scanner;
import dataStructure.*;

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
		
		//Display persamaan polinomial
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
		inputN.close();
		
		
	}
	public static void main(String[] args) {
		interpolasi();
	}
	
}
