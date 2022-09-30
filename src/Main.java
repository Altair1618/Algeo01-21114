import java.util.Scanner;

import dataStructure.*;
import functions.*;
import utility.*;

public class Main {
	static void mainMenu(String command) {
		if (command == "mainMenu") {
			System.out.println("SELAMAT DATANG DI KALKULATOR MATRIX COFFEE LAKE");
			System.out.println("SILAHKAN PILIH MENU");
			System.out.println("1. Sistem Persamaan Linear");
			System.out.println("2. Determinan");
			System.out.println("3. Matriks Balikan");
			System.out.println("4. Interpolasi Polinom");
			System.out.println("5. Interpolasi Bicubic");
			System.out.println("6. Regresi Linear Berganda");
			System.out.println("7. Keluar");
			System.out.print("Pilihan Anda: ");
		} else if (command == "menuSPL") {
			System.out.println("Penyelesaian Solusi Sistem Persamaan Linear");
			System.out.println("Pilih Metode Penyelesaian SPL:");
			System.out.println("1. Metode Eliminasi Gauss");
			System.out.println("2. Metode Eliminasi Gauss-Jordan");
			System.out.println("3. Metode Matriks Balikan");
			System.out.println("4. Kaidah Cramer");
			System.out.println("Catatan: Kaidah Cramer hanya dapat berlaku pada n persamaan pada n buah variabel");
			System.out.print("Pilihan Anda: ");
		} else if (command == "menuRead") {
			System.out.println("Pilih Metode Pembacaan Matriks");
			System.out.println("1. Melalui Keyboard");
			System.out.println("2. Melalui External File");
			System.out.print("Pilihan Anda: ");
		}
		
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner inputStr = new Scanner(System.in);
		
		mainMenu("mainMenu");
		int inputMenu = input.nextInt();

		while (inputMenu != 7) {
			while (inputMenu <= 0 || inputMenu > 7) {
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
					outputMatrixGauss.writeMatrix();
				}

			}


			mainMenu("mainMenu");
			inputMenu = input.nextInt();
		}
	}
}
