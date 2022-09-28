package dataStructure;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixPoint {
    double [][] pointm;
    int countPoint;
    
    // FUNGSI KOSTRUKTOR
    public void readMatrixPoint () {
        Scanner choiceInput = new Scanner(System.in);
        System.out.println("Pilih cara input Point: ");
        System.out.println("- Melalui Keyboard (Ketik 1) ");
        System.out.println("- Melalui File (Ketik 2)");
        System.out.print("Pilihan: ");
        int choice = choiceInput.nextInt();

        while (choice != 1 && choice != 2) {
            System.out.println("Input salah! Masukkan input yang benar");
            readMatrixPoint();
        }

        if (choice == 1) {
            readMatrixPointFromKeyboard();       
        } else {
            readMatrixPointFromFile();
        }
        choiceInput.close();
    }

    public void readMatrixPointFromKeyboard () {
        Scanner input = new Scanner(System.in);
        int count;

        System.out.print("Masukkan jumlah titik: ");
        count = input.nextInt();

        this.pointm = new double[count][2];
        this.countPoint = count;

        for (int i = 0; i < this.countPoint; i++) {
            System.out.print("Input titik ke ");
            System.out.println(i+1);
            Point inputitem = new Point();
            inputitem.readPoint();
            this.pointm[i][0] = inputitem.x;
            this.pointm[i][1] = inputitem.y;
        }
        //input.close();
    }

    public void readMatrixPointFromFile () {
        String filePath;

        System.out.print("Masukkan file path: ");
        Scanner inputFilePath = new Scanner(System.in);
        filePath = inputFilePath.nextLine();
        
        try {
            File matrixFile = new File(filePath);
            Scanner readMatrixFile = new Scanner(matrixFile);
            ArrayList<double[]> inputMatrix = new ArrayList<double[]>();
                
            while (readMatrixFile.hasNextLine()) {
                String line = readMatrixFile.nextLine();
                double[] inputRow = new double[2];
                int j = 0;
                for (int i = 0; i < line.length(); i++) {
                    String currentString = "";
                    while ((line.charAt(i) != ' ')) {
                        currentString += line.charAt(i);
                        i++;
                        if (i == line.length()) {
                            break;
                        }
                    } // (line.charAt(i) == ' ') 
                    inputRow[j] = Double.parseDouble(currentString);
                    j++;
                }
                inputMatrix.add(inputRow);
            }


            this.countPoint = inputMatrix.size();

            this.pointm = new double[this.countPoint][2];
        
            for (int i = 0; i < this.countPoint; i++) {
                this.pointm[i][0] = inputMatrix.get(i)[0];
                this.pointm[i][1] = inputMatrix.get(i)[1];
            }

            inputMatrix.clear();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
        }
    }

    // FUNGSI LAINNYA
    public void writePointTerminal () {
        for (int i = 0; i < this.countPoint; i++) {
            System.out.print(this.pointm[i][0]+" ");
            System.out.println(this.pointm[i][1]);
        }
    }

}
