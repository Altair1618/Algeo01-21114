import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import dataStructure.*;
import functions.*;

public class Main {
	static void mainMenu(String command) {
		if (command == "mainMenu") {
			System.out.println("MAIN MENU");
			System.out.println("Silahkan pilih menu: ");
			System.out.println("1. Sistem Persamaan Linear");
			System.out.println("2. Determinan");
			System.out.println("3. Matriks Balikan");
			System.out.println("4. Interpolasi Polinom");
			System.out.println("5. Interpolasi Bicubic");
			System.out.println("6. Regresi Linear Berganda");
			System.out.println("7. Image Scalling");
			System.out.println("8. Keluar");
			System.out.print("Pilihan Anda: ");
		} else if (command == "menuSPL") {
			System.out.println("Penyelesaian Solusi Sistem Persamaan Linear");
			System.out.println("Pilih Metode Penyelesaian SPL:");
			System.out.println("1. Metode Eliminasi Gauss");
			System.out.println("2. Metode Eliminasi Gauss-Jordan");
			System.out.println("3. Metode Matriks Balikan");
			System.out.println("4. Kaidah Cramer");
			System.out.println("Catatan: Kaidah Cramer dan Matriks Balikan hanya dapat berlaku pada Matriks yang mempunyai solusi unik");
			System.out.print("Pilihan Anda: ");
		} else if (command == "menuRead") {
			System.out.println("Pilih Metode Input");
			System.out.println("1. Melalui Keyboard");
			System.out.println("2. Melalui External File");
			System.out.print("Pilihan Anda: ");
		} else if (command == "menuWrite") {
			System.out.println("Pilih Metode Output");
			System.out.println("1. Terminal");
			System.out.println("2. Output File");
			System.out.print("Pilihan Anda: ");
		} else if (command == "menuDeterminan") {
			System.out.println("Perhitungan Nilai Determinan");
			System.out.println("Pilih metode perhitungan determinan:");
			System.out.println("1. Metode Reduksi Baris");
			System.out.println("2. Metode Ekspansi Kofaktor");
			System.out.print("Pilihan Anda: ");
		}
		
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner inputStr = new Scanner(System.in);
		
		System.out.println("SELAMAT DATANG DI KALKULATOR MATRIX COFFEE LAKE");
		mainMenu("mainMenu");
		int inputMenu = input.nextInt();

		while (inputMenu != 8) {
			while (inputMenu <= 0 || inputMenu > 8) {
				System.out.println("Masukkan salah! Masukkan input yang valid");
				System.out.print("Pilihan Anda: ");
				inputMenu = input.nextInt();
			} // 1 < inputMenu <= 7

			if (inputMenu == 1) {
				mainMenu("menuSPL");
				int inputMenu1 = input.nextInt();
				while (inputMenu1 <= 0 || inputMenu1 > 4) {
					System.out.println("Masukkan salah! Masukkan input yang valid");
					System.out.print("Pilihan Anda: ");
					inputMenu1 = input.nextInt();
				} // 1 < inputMenu1 <= 4
				if (inputMenu1 == 1) {
					System.out.println("PENYELESAIAN MATRIX MENGGUNAKAN METODE GAUSS");
					Matrix matrixinput = new Matrix();
					mainMenu("menuRead");
					int inputMenu2 = input.nextInt();
					while (inputMenu2 != 1 && inputMenu2 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu2 = input.nextInt();
					} // inputMenu2 == 1 || inputMenu2 == 2

					if (inputMenu2 == 1) {
						int row, column;
						System.out.print("Masukkan jumlah baris: ");
						row = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
					} else /*inputMenu2 == 2*/ {
						System.out.print("Masukkan file path: ");
						String filePath;
						filePath = inputStr.nextLine();
						matrixinput.readMatrixFromFile(filePath);
					}
					
					Matrix outputMatrixGauss = new Matrix();
					outputMatrixGauss = Gauss.matrixGauss(matrixinput);

					mainMenu("menuWrite"); 
					int inputMenu3;
					inputMenu3 = input.nextInt();

					while (inputMenu3 != 1 && inputMenu3 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu3 = input.nextInt();
					} // inputMenu3 == 1 || inputMenu3 == 2

					if (inputMenu3 == 1) {
						System.out.println("----------------------------------");
						System.out.println("HASIL PERHITUNGAN");
						System.out.println("1. Matrix Gauss");
						outputMatrixGauss.writeTerminal();
						System.out.println("2. Hasil Penyelesaian");
						Gauss.solveSPL(outputMatrixGauss);
						System.out.println("----------------------------------");
					} else /*inputMenu3 == 2*/ {

					}
				} else if (inputMenu1 == 2) {
					System.out.println("PENYELESAIAN MATRIX MENGGUNAKAN METODE GAUSS-JORDAN");
					Matrix matrixinput = new Matrix();
					mainMenu("menuRead");
					int inputMenu2 = input.nextInt();
					while (inputMenu2 != 1 && inputMenu2 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu2 = input.nextInt();
					} // inputMenu2 == 1 || inputMenu2 == 2

					if (inputMenu2 == 1) {
						int row, column;
						System.out.print("Masukkan jumlah baris: ");
						row = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
					} else /*inputMenu2 == 2*/ {
						System.out.print("Masukkan file path: ");
						String filePath;
						filePath = inputStr.nextLine();
						matrixinput.readMatrixFromFile(filePath);
					}
					
					Matrix outputMatrixGaussJordan = new Matrix();
					outputMatrixGaussJordan = Gauss.matrixGaussJordan(matrixinput);

					mainMenu("menuWrite"); 
					int inputMenu3;
					inputMenu3 = input.nextInt();

					while (inputMenu3 != 1 && inputMenu3 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu3 = input.nextInt();
					} // inputMenu3 == 1 || inputMenu3 == 2

					if (inputMenu3 == 1) {
						System.out.println("----------------------------------");
						System.out.println("HASIL PERHITUNGAN");
						System.out.println("1. Matrix Gauss-Jordan");
						outputMatrixGaussJordan.writeTerminal();
						System.out.println("2. Hasil Penyelesaian");
						Gauss.solveSPL(outputMatrixGaussJordan);
						System.out.println("----------------------------------");
					} else /*inputMenu3 == 2*/ {

					}
				} else if (inputMenu1 == 3) {
					System.out.println("PENYELESAIAN MATRIX MENGGUNAKAN METODE MATRIKS BALIKAN");
					Matrix matrixinput = new Matrix();
					mainMenu("menuRead");
					int inputMenu2 = input.nextInt();
					while (inputMenu2 != 1 && inputMenu2 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu2 = input.nextInt();
					} // inputMenu2 == 1 || inputMenu2 == 2

					if (inputMenu2 == 1) {
						int row, column;
						System.out.print("Masukkan jumlah baris: ");
						row = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
					} else /*inputMenu2 == 2*/ {
						System.out.print("Masukkan file path: ");
						String filePath;
						filePath = inputStr.nextLine();
						matrixinput.readMatrixFromFile(filePath);
					}
					
					Matrix matrixSquare = new Matrix();
					matrixSquare.createMatrix(matrixinput.getRowLength(), matrixinput.getColumnLength()-1);

					for (int i = 0; i < matrixinput.getRowLength(); i++) {
						for (int j = 0; j < matrixinput.getColumnLength() - 1; j++) {
							matrixSquare.setElement(matrixinput.getElement(i+1, j+1), i+1, j+1);
						}
					}

					if (matrixSquare.getColumnLength() != matrixSquare.getRowLength()) {
						System.out.println("Matriks variabel tidak berbentuk persegi. Silahkan gunakan metode Gauss atau Gauss Jordan.");
					} else {
						Matrix outputMatrixInverse = new Matrix();
						outputMatrixInverse = Inverse.inverse(matrixSquare, matrixSquare.getRowLength());

						mainMenu("menuWrite"); 
						int inputMenu3;
						inputMenu3 = input.nextInt();

						while (inputMenu3 != 1 && inputMenu3 != 2) {
							System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
							System.out.print("Pilihan Anda: ");
							inputMenu3 = input.nextInt();
						} // inputMenu3 == 1 || inputMenu3 == 2

						if (inputMenu3 == 1) {
							System.out.println("----------------------------------");
							System.out.println("HASIL PERHITUNGAN");
							System.out.println("1. Matrix Invers");
							outputMatrixInverse.writeTerminal();
							System.out.println("2. Hasil Penyelesaian");
							Inverse.displayInvSPLResult(Inverse.inverseSPL(matrixinput, matrixinput.getColumnLength()-1));
							System.out.println("----------------------------------");
						} else /*inputMenu3 == 2*/ {

						}
					}
				} else /*inputMenu1 == 4*/ {
					System.out.println("PENYELESAIAN MATRIX MENGGUNAKAN METODE CRAMER");
					Matrix matrixinput = new Matrix();
					mainMenu("menuRead");
					int inputMenu2 = input.nextInt();
					while (inputMenu2 != 1 && inputMenu2 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu2 = input.nextInt();
					} // inputMenu2 == 1 || inputMenu2 == 2

					if (inputMenu2 == 1) {
						int row, column;
						System.out.print("Masukkan jumlah baris: ");
						row = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
					} else /*inputMenu2 == 2*/ {
						System.out.print("Masukkan file path: ");
						String filePath;
						filePath = inputStr.nextLine();
						matrixinput.readMatrixFromFile(filePath);
					}
					
					if (matrixinput.getColumnLength() != matrixinput.getRowLength()+1) {
						System.out.println("Matriks variabel tidak berbentuk persegi. Silahkan gunakan metode Gauss atau Gauss Jordan.");
					} else {
						mainMenu("menuWrite"); 
						int inputMenu3;
						inputMenu3 = input.nextInt();

						while (inputMenu3 != 1 && inputMenu3 != 2) {
							System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
							System.out.print("Pilihan Anda: ");
							inputMenu3 = input.nextInt();
						} // inputMenu3 == 1 || inputMenu3 == 2

						if (inputMenu3 == 1) {
							System.out.println("----------------------------------");
							System.out.println("HASIL PERHITUNGAN");
							System.out.println("1. Hasil Penyelesaian");
							Cramer.displaySPLCramerResult(Cramer.cramerSPL(matrixinput, matrixinput.getColumnLength()-1));
							System.out.println("----------------------------------");
						} else /*inputMenu3 == 2*/ {

						}
					}
				}

			} else if (inputMenu == 2) {
				mainMenu("menuDeterminan");
				int inputMenu1 = input.nextInt();
				while (inputMenu1 != 1 && inputMenu1 != 2) {
					System.out.println("Masukkan salah! Masukkan input yang valid");
					System.out.print("Pilihan Anda: ");
					inputMenu1 = input.nextInt();
				} // inputMenu1 == 1 || inputMenu1 == 2
				if (inputMenu1 == 1) {
					System.out.println("MENCARI NILAI DETERMINAN MATRIX MENGGUNAKAN METODE REDUKSI BARIS");
					Matrix matrixinput = new Matrix();
					mainMenu("menuRead");
					int inputMenu2 = input.nextInt();
					while (inputMenu2 != 1 && inputMenu2 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu2 = input.nextInt();
					} // inputMenu2 == 1 || inputMenu2 == 2

					if (inputMenu2 == 1) {
						int row, column;
						System.out.print("Masukkan jumlah baris: ");
						row = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
					} else /*inputMenu2 == 2*/ {
						System.out.print("Masukkan file path: ");
						String filePath;
						filePath = inputStr.nextLine();
						matrixinput.readMatrixFromFile(filePath);
					}
					
					if (matrixinput.getColumnLength() != matrixinput.getRowLength()) {
						System.out.println("Determinan matriks variabel non persegi tidak terdefinisi");
					} else {
						mainMenu("menuWrite"); 
						int inputMenu3;
						inputMenu3 = input.nextInt();

						while (inputMenu3 != 1 && inputMenu3 != 2) {
							System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
							System.out.print("Pilihan Anda: ");
							inputMenu3 = input.nextInt();
						} // inputMenu3 == 1 || inputMenu3 == 2

						Matrix reduMatrix = new Matrix();
						reduMatrix = detRedux.detReduxMatrix(matrixinput);
						if (inputMenu3 == 1) {
							System.out.println("----------------------------------");
							System.out.println("HASIL PERHITUNGAN");
							System.out.println("1. Hasil Reduksi Baris");
							reduMatrix.writeTerminal();
							System.out.print("2. Nilai Determinan = ");
							System.out.println(detRedux.detByReduction(matrixinput));
							System.out.println("----------------------------------");
						} else /*inputMenu3 == 2*/ {
							
						}
					}
				} else /*inputMenu1 == 2*/ {
					System.out.println("MENCARI NILAI DETERMINAN MATRIX MENGGUNAKAN METODE EKSPANSI KOFAKTOR");
					Matrix matrixinput = new Matrix();
					mainMenu("menuRead");
					int inputMenu2 = input.nextInt();
					while (inputMenu2 != 1 && inputMenu2 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu2 = input.nextInt();
					} // inputMenu2 == 1 || inputMenu2 == 2

					if (inputMenu2 == 1) {
						int row, column;
						System.out.print("Masukkan jumlah baris: ");
						row = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
					} else /*inputMenu2 == 2*/ {
						System.out.print("Masukkan file path: ");
						String filePath;
						filePath = inputStr.nextLine();
						matrixinput.readMatrixFromFile(filePath);
					}
					
					if (matrixinput.getColumnLength() != matrixinput.getRowLength()) {
						System.out.println("Determinan matriks variabel non persegi tidak terdefinisi");
					} else {
						mainMenu("menuWrite"); 
						int inputMenu3;
						inputMenu3 = input.nextInt();

						while (inputMenu3 != 1 && inputMenu3 != 2) {
							System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
							System.out.print("Pilihan Anda: ");
							inputMenu3 = input.nextInt();
						} // inputMenu3 == 1 || inputMenu3 == 2

						if (inputMenu3 == 1) {
							System.out.println("----------------------------------");
							System.out.println("HASIL PERHITUNGAN");
							System.out.print("1. Nilai Determinan = ");
							System.out.println(Determinant.detExCof(matrixinput, matrixinput.getColumnLength()));
							System.out.println("----------------------------------");
						} else /*inputMenu3 == 2*/ {
							
						}
					}
				}
			} else if (inputMenu == 3) {
				System.out.println("MENCARI INVERS MATRIKS");
				Matrix matrixinput = new Matrix();
				mainMenu("menuRead");
				int inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int row, column;
					System.out.print("Masukkan jumlah baris: ");
					row = input.nextInt();
					System.out.print("Masukkan jumlah kolom: ");
					column = input.nextInt();
					matrixinput.createMatrix(row, column);
					double inputitem;

					for (int i = 0; i < matrixinput.getRowLength(); i++) {
						for (int j = 0; j < matrixinput.getColumnLength(); j++) {
							inputitem = input.nextDouble();
							matrixinput.setElement(inputitem, i+1, j+1);
						}
					}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}
				
				Matrix matrixSquare = new Matrix();
				matrixSquare.createMatrix(matrixinput.getRowLength(), matrixinput.getColumnLength()-1);

				for (int i = 0; i < matrixinput.getRowLength(); i++) {
					for (int j = 0; j < matrixinput.getColumnLength() - 1; j++) {
						matrixSquare.setElement(matrixinput.getElement(i+1, j+1), i+1, j+1);
					}
				}

				if (matrixSquare.getColumnLength() != matrixSquare.getRowLength()) {
					System.out.println("Matriks variabel yang tidak berbentuk persegi tidak memiliki invers.");
				} else {
					Matrix outputMatrixInverse = new Matrix();
					outputMatrixInverse = Inverse.inverse(matrixSquare, matrixSquare.getRowLength());

					mainMenu("menuWrite"); 
					int inputMenu3;
					inputMenu3 = input.nextInt();

					while (inputMenu3 != 1 && inputMenu3 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu3 = input.nextInt();
					} // inputMenu3 == 1 || inputMenu3 == 2

					if (inputMenu3 == 1) {
						System.out.println("----------------------------------");
						System.out.println("HASIL PERHITUNGAN");
						System.out.println("1. Matrix Invers");
						outputMatrixInverse.writeTerminal();
						System.out.println("----------------------------------");
					} else /*inputMenu3 == 2*/ {

					}
				}
			} else if (inputMenu == 4) {
				System.out.println("MENCARI TAKSIRAN NILAI MELALUI INTERPOLASI POLINOM");
				Matrix matrixinput = new Matrix();
				System.out.println("INPUT DATA TITIK SEBAGAI DATA INTERPOLASI");
				mainMenu("menuRead");
				int inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int count;
					System.out.print("Masukkan jumlah titik: ");
					count = input.nextInt();
					
					matrixinput.createMatrix(count, 2);

					for (int i = 0; i < matrixinput.getRowLength(); i++) {
						System.out.print("Input titik ke ");
						System.out.println(i+1);
						Point inputPoint = new Point();
						inputPoint.readPoint();
						matrixinput.setElement(inputPoint.getPointAbsis(), i+1, 1);
						matrixinput.setElement(inputPoint.getPointOrdinat(), i+1, 2);
					}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path data titik: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}

				System.out.println("INPUT DATA NILAI YANG AKAN DI INTERPOLASI");
				mainMenu("menuRead");
				inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int count;
					System.out.print("Masukkan banyaknya nilai yang akan di interpolasi: ");
					count = input.nextInt();

					matrixinput.createMatrix(count, 1);
					double inputitem;

					for (int i = 0; i < matrixinput.getRowLength(); i++) {
						for (int j = 0; j < matrixinput.getColumnLength(); j++) {
							inputitem = input.nextDouble();
							matrixinput.setElement(inputitem, i+1, j+1);
						}
					}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path data nilai: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}
				
				Matrix matrixSquare = new Matrix();
				matrixSquare.createMatrix(matrixinput.getRowLength(), matrixinput.getColumnLength()-1);

				for (int i = 0; i < matrixinput.getRowLength(); i++) {
					for (int j = 0; j < matrixinput.getColumnLength() - 1; j++) {
						matrixSquare.setElement(matrixinput.getElement(i+1, j+1), i+1, j+1);
					}
				}

				if (matrixSquare.getColumnLength() != matrixSquare.getRowLength()) {
					System.out.println("Matriks variabel yang tidak berbentuk persegi tidak memiliki invers.");
				} else {
					Matrix outputMatrixInverse = new Matrix();
					outputMatrixInverse = Inverse.inverse(matrixSquare, matrixSquare.getRowLength());

					mainMenu("menuWrite"); 
					int inputMenu3;
					inputMenu3 = input.nextInt();

					while (inputMenu3 != 1 && inputMenu3 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu3 = input.nextInt();
					} // inputMenu3 == 1 || inputMenu3 == 2

					if (inputMenu3 == 1) {
						System.out.println("----------------------------------");
						System.out.println("HASIL PERHITUNGAN");
						System.out.println("1. Matrix Invers");
						outputMatrixInverse.writeTerminal();
						System.out.println("----------------------------------");
					} else /*inputMenu3 == 2*/ {

					}
				}
			} else if (inputMenu == 5) {
				System.out.println("MENCARI TAKSIRAN NILAI MELALUI INTERPOLASI BICUBIC");
				Matrix matrixinput = new Matrix();
				System.out.println("INPUT DATA TITIK SEBAGAI DATA INTERPOLASI");
				mainMenu("menuRead");
				int inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int count;
					System.out.print("Masukkan jumlah titik: ");
					count = input.nextInt();
					
					matrixinput.createMatrix(count, 2);

					for (int i = 0; i < matrixinput.getRowLength(); i++) {
						System.out.print("Input titik ke ");
						System.out.println(i+1);
						Point inputPoint = new Point();
						inputPoint.readPoint();
						matrixinput.setElement(inputPoint.getPointAbsis(), i+1, 1);
						matrixinput.setElement(inputPoint.getPointOrdinat(), i+1, 2);
					}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path data titik: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}

				System.out.println("INPUT DATA NILAI YANG AKAN DI INTERPOLASI");
				mainMenu("menuRead");
				inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int count;
					System.out.print("Masukkan banyaknya nilai yang akan di interpolasi: ");
					count = input.nextInt();

					matrixinput.createMatrix(count, 1);
					double inputitem;

					for (int i = 0; i < matrixinput.getRowLength(); i++) {
						for (int j = 0; j < matrixinput.getColumnLength(); j++) {
							inputitem = input.nextDouble();
							matrixinput.setElement(inputitem, i+1, j+1);
						}
					}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path data nilai: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}
				
				Matrix matrixSquare = new Matrix();
				matrixSquare.createMatrix(matrixinput.getRowLength(), matrixinput.getColumnLength()-1);

				for (int i = 0; i < matrixinput.getRowLength(); i++) {
					for (int j = 0; j < matrixinput.getColumnLength() - 1; j++) {
						matrixSquare.setElement(matrixinput.getElement(i+1, j+1), i+1, j+1);
					}
				}

				if (matrixSquare.getColumnLength() != matrixSquare.getRowLength()) {
					System.out.println("Matriks variabel yang tidak berbentuk persegi tidak memiliki invers.");
				} else {
					Matrix outputMatrixInverse = new Matrix();
					outputMatrixInverse = Inverse.inverse(matrixSquare, matrixSquare.getRowLength());

					mainMenu("menuWrite"); 
					int inputMenu3;
					inputMenu3 = input.nextInt();

					while (inputMenu3 != 1 && inputMenu3 != 2) {
						System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
						System.out.print("Pilihan Anda: ");
						inputMenu3 = input.nextInt();
					} // inputMenu3 == 1 || inputMenu3 == 2

					if (inputMenu3 == 1) {
						System.out.println("----------------------------------");
						System.out.println("HASIL PERHITUNGAN");
						System.out.println("1. Matrix Invers");
						outputMatrixInverse.writeTerminal();
						System.out.println("----------------------------------");
					} else /*inputMenu3 == 2*/ {

					}
				}
			} else if (inputMenu == 6) {
				System.out.println("MENCARI TAKSIRAN NILAI MELALUI REGRESI LINEAR BERGANDA");
				Matrix matrixinput = new Matrix();
				System.out.println("INPUT MATRIKS SEBAGAI DATA REGRESI");
				mainMenu("menuRead");
				int inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int row, column;
						System.out.print("Masukkan banyaknya sampel data: ");
						row = input.nextInt();
						System.out.print("Masukkan banyaknya kolom: ");
						column = input.nextInt();
						matrixinput.createMatrix(row, column);
						double inputitem;

						System.out.println("Dalam proses input matriks, kolom variabel dependent (y) berada pada kolom paling akhir");
						System.out.println("Contoh: x1 x2 x3 ... xn y");
        				for (int i = 0; i < matrixinput.getRowLength(); i++) {
            				for (int j = 0; j < matrixinput.getColumnLength(); j++) {
                				inputitem = input.nextDouble();
								matrixinput.setElement(inputitem, i+1, j+1);
							}
						}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path sample data: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}

				Matrix matrixinput2 = new Matrix();
				System.out.println("INPUT DATA NILAI YANG AKAN DITAKSIR MENGGUNAKAN REGRESI");
				mainMenu("menuRead");
				inputMenu2 = input.nextInt();
				while (inputMenu2 != 1 && inputMenu2 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu2 = input.nextInt();
				} // inputMenu2 == 1 || inputMenu2 == 2

				if (inputMenu2 == 1) {
					int count;
					System.out.print("Masukkan banyaknya nilai yang akan ditaksir menggunakan regresi: ");
					count = input.nextInt();

					matrixinput2.createMatrix(count, matrixinput.getColumnLength()-1);
					double inputitem;

					for (int i = 0; i < matrixinput2.getRowLength(); i++) {
						for (int j = 0; j < matrixinput2.getColumnLength(); j++) {
							inputitem = input.nextDouble();
							matrixinput2.setElement(inputitem, i+1, j+1);
						}
					}
				} else /*inputMenu2 == 2*/ {
					System.out.print("Masukkan file path data nilai yang akan ditaksir menggunakan regresi: ");
					String filePath;
					filePath = inputStr.nextLine();
					matrixinput.readMatrixFromFile(filePath);
				}
				
				double[] resultRegressionEquation = new double[matrixinput.getColumnLength()-1];
				double[] resultRegressionValue = new double[matrixinput2.getRowLength()];

				resultRegressionEquation = Regression.evaluateRegressionEquation(matrixinput, matrixinput2);
				resultRegressionValue = Regression.evaluateRegressionValue(resultRegressionEquation, matrixinput2);

				mainMenu("menuWrite"); 
				int inputMenu3;
				inputMenu3 = input.nextInt();

				while (inputMenu3 != 1 && inputMenu3 != 2) {
					System.out.println("Masukkan salah! Silahkan input masukan yang valid!");
					System.out.print("Pilihan Anda: ");
					inputMenu3 = input.nextInt();
				} // inputMenu3 == 1 || inputMenu3 == 2

				if (inputMenu3 == 1) {
					System.out.println("----------------------------------");
					System.out.println("HASIL PERHITUNGAN");
					System.out.println("1. Persamaan Regresi");
					for (int i = 0; i < resultRegressionEquation.length; i++) {
						if (i == 0) {
							System.out.print(resultRegressionEquation[i]);
						} else {
							if (resultRegressionEquation[i] != 0) {
								System.out.print(" ");
								if (resultRegressionEquation[i] > 0) {
									System.out.print("+");
								}
								System.out.print(resultRegressionEquation[i]);
								System.out.print("*(x");
								System.out.print(i);
								System.out.print(")");
							}
						}
					}
					System.out.println();
					System.out.println("2. Hasil Taksiran Berdasarkan Persamaan Regresi");
					for (int i = 0; i < matrixinput2.getRowLength(); i++) {
						System.out.print(" f(");
						for (int j = 0; j < matrixinput2.getColumnLength()-1; j++) {
							System.out.print(matrixinput2.getElement(i+1, j+1));
							System.out.print(", ");
						}
						System.out.print(matrixinput2.getElement(i+1, matrixinput2.getColumnLength()));
						System.out.print(") = ");
						System.out.println(resultRegressionValue[i]);
					}
					System.out.println("----------------------------------");
				} else /*inputMenu3 == 2*/ {
					System.out.print("Masukkan path folder tujuan: ");
					String fileFolderPath = inputStr.nextLine();

					System.out.print("Masukkan nama file (dengan extension): ");
            		String fileName = inputStr.nextLine();

					String absolutePath = fileFolderPath + "\\" + fileName;
					try {
						File filePath = new File(absolutePath);
						if (filePath.createNewFile()) {
							System.out.println("File telah dibuat: " + fileName);
							try {
								String line;
								try (FileWriter fileWriter = new FileWriter(absolutePath)) {
									fileWriter.write("----------------------------------\n");
									fileWriter.write("HASIL PERHITUNGAN\n");
									fileWriter.write("1. Persamaan Regresi\n");
									line = "";

									for (int i = 0; i < resultRegressionEquation.length; i++) {
										if (i == 0) {
											line += String.valueOf(resultRegressionEquation[i]);
										} else {
											if (resultRegressionEquation[i] != 0) {
												line +=(" ");
												if (resultRegressionEquation[i] > 0) {
													line +=("+");
												}
												line += String.valueOf(resultRegressionEquation[i]);
												line += ("*(x");
												line += String.valueOf(i);
												line +=(")");
											}
										}
									}
									line += "\n";
									fileWriter.write(line);
									fileWriter.write("2. Hasil Taksiran Berdasarkan Persamaan Regresi\n");
									for (int i = 0; i < matrixinput2.getRowLength(); i++) {
										line = "";
										line += " f(";
										for (int j = 0; j < matrixinput2.getColumnLength()-1; j++) {
											line += String.valueOf(matrixinput2.getElement(i+1, j+1));
											line += ", ";
										}
										line += String.valueOf(matrixinput2.getElement(i+1, matrixinput2.getColumnLength()));
										line +=") = ";
										line += String.valueOf(resultRegressionValue[i]);
										line += "\n";
										fileWriter.write(line);
									}
									fileWriter.write("----------------------------------\n");
								}
								System.out.println("Output telah tersedia pada File.");
							} catch (IOException e) {
								System.out.println("Terjadi kesalahan.");
							}
						} else {
							System.out.println("File sudah tersedia, apakah Anda ingin overwrite file tersebut? (Ya = 1/No = 2)");
							int choiceScanner = input.nextInt();
		
							while (choiceScanner != 1 && choiceScanner != 2) {
								choiceScanner = input.nextInt();
							}
		
							if (choiceScanner == 1) {
								filePath.delete();
								System.out.println("Menghapus file " + fileName + "...");
								filePath.createNewFile(); 
								System.out.println("File baru telah dibuat: " + fileName);
						
								try {
									String line;
									try (FileWriter fileWriter = new FileWriter(absolutePath)) {
										fileWriter.write("----------------------------------\n");
										fileWriter.write("HASIL PERHITUNGAN\n");
										fileWriter.write("1. Persamaan Regresi\n");
										line = "";

										for (int i = 0; i < resultRegressionEquation.length; i++) {
											if (i == 0) {
												line += String.valueOf(resultRegressionEquation[i]);
											} else {
												if (resultRegressionEquation[i] != 0) {
													line +=(" ");
													if (resultRegressionEquation[i] > 0) {
														line +=("+");
													}
													line += String.valueOf(resultRegressionEquation[i]);
													line += ("*(x");
													line += String.valueOf(i);
													line +=(")");
												}
											}
										}
										line += "\n";
										fileWriter.write(line);
										fileWriter.write("2. Hasil Taksiran Berdasarkan Persamaan Regresi\n");
										for (int i = 0; i < matrixinput2.getRowLength(); i++) {
											line = "";
											line += " f(";
											for (int j = 0; j < matrixinput2.getColumnLength()-1; j++) {
												line += String.valueOf(matrixinput2.getElement(i+1, j+1));
												line += ", ";
											}
											line += String.valueOf(matrixinput2.getElement(i+1, matrixinput2.getColumnLength()));
											line +=") = ";
											line += String.valueOf(resultRegressionValue[i]);
											line += "\n";
											fileWriter.write(line);
										}
										fileWriter.write("----------------------------------\n");
									}
									System.out.println("Output telah tersedia pada File.");
								} catch (IOException e) {
									System.out.println("Terjadi kesalahan.");
								}
							} else {
								System.out.println("Aksi dibatalkan");
							}
						}
					} catch (IOException e) {
						System.out.println("Terjadi kesalahan."); 
					}
				}
			} else /* inputMenu == 6*/ {
				System.out.println("PENERAPAN INTERPOLASI BICUBIC: IMAGE SCALLING");
			}
			mainMenu("mainMenu");
			inputMenu = input.nextInt();
		}

		input.close();
		inputStr.close();
	}
}
