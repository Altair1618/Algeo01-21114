package dataStructure;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    double[][] matrix;
    int row, column;
    final int MARK = -9999;

    //FUNGSI KONSTRUKTOR
    public void createMatrix (int row, int column) {
        // Mengisi semua elemen matriks dengan MARK
        this.row = row;
        this.column = column;
        this.matrix = new double[this.row][this.column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.matrix[i][j] = MARK;
            }
        }
    }

    public void readMatrix () {
        Scanner choiceInput = new Scanner(System.in);
        System.out.println("Pilih cara input matriks: ");
        System.out.println("- Melalui Keyboard (Ketik 1) ");
        System.out.println("- Melalui File (Ketik 2)");
        System.out.print("Pilihan: ");
        int choice = choiceInput.nextInt();
        

        while (choice != 1 && choice != 2) {
            System.out.println("Input salah! Masukkan input yang benar");
            readMatrix();
        }

        if (choice == 1) {
            readMatrixFromKeyboard();       
        } else {
            readMatrixFromFile();
        }
        //choiceInput.close();
    }

    public void readMatrixFromKeyboard () {
        // Membaca matriks melalui keyboard
        Scanner input = new Scanner(System.in);
        int row, column;

        System.out.print("Masukkan jumlah baris: ");
        row = input.nextInt();

        System.out.print ("Masukkan jumlah kolom: ");
        column = input.nextInt();

        createMatrix(row, column);

        double inputitem;

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                inputitem = input.nextDouble();
                this.matrix[i][j] = inputitem;
            }
        }

        //input.close();
        
    }

    public void readMatrixFromFile () {
        // Membaca matrix dari file 
        String filePath;

        System.out.print("Masukkan file path: ");
        Scanner inputFilePath = new Scanner(System.in);
        filePath = inputFilePath.nextLine();
        
        try {
            File matrixFile = new File(filePath);
            Scanner readMatrixFile = new Scanner(matrixFile);
            ArrayList<ArrayList<Double>> inputMatrix = new ArrayList<ArrayList<Double>>();
                
            int row, column;

            while (readMatrixFile.hasNextLine()) {
                String line = readMatrixFile.nextLine();
                ArrayList<Double> inputRow = new ArrayList<Double>();
                for (int i = 0; i < line.length(); i++) {
                    String currentString = "";
                    while ((line.charAt(i) != ' ')) {
                        currentString += line.charAt(i);
                        i++;
                        if (i == line.length()) {
                            break;
                        }
                    } // (line.charAt(i) == ' ') 
                    inputRow.add(Double.valueOf(currentString));
                }
                inputMatrix.add(inputRow);
            }


            row = inputMatrix.size();
            column = inputMatrix.get(0).size();
            
            createMatrix(row, column);
        
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.column; j++) {
                    this.matrix[i][j] = inputMatrix.get(i).get(j);
                }
            }

            inputMatrix.clear();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
        }
    }

    //FUNGSI PRIMITIF SELEKTOR
    public double getElement (int numRow, int numCol) {
        // Mengambil elemen pada matriks
        /* DISCLAMER: PERHATIKAN INDEKS 
         * 1 2 3
         * 4 5 6
         * 7 8 9 
         * Maka getElement(1,1) = 1, getElement(1, 2) = 2, dst.
        */
        return this.matrix[numRow-1][numCol-1];
    }

    public double[] getRow (int numRow) {
        // Melakukan return sebuah array dari baris yang dipilih
        /* DISCLAMER: PERHATIKAN INDEKS 
         * 1 2 3
         * 4 5 6
         * 7 8 9 
         * Maka getRow(1) = [1, 2, 3], getElement(2) = [4, 5, 6], dst.
        */
        double[] rowArray;
        rowArray = this.matrix[numRow-1];
        return rowArray;
    }

    public double[] getColumn (int numCol) {
        // Melakukan return sebuah array dari kolom yang dipilih
        /* DISCLAMER: PERHATIKAN INDEKS 
         * 1 2 3
         * 4 5 6
         * 7 8 9 
         * Maka getColumn(1) = [1, 4, 7], getElement(2) = [2, 5, 8], dst.
        */
        double[] columnArray = new double[this.row];
        for (int i = 0; i < this.row; i++) {
            columnArray[i] = this.matrix[i][numCol - 1];
        }
        return columnArray;
    }

    public int getRowLength() {
        // Mengembalikan jumlah baris pada matriks
        return this.row;
    }

    public int getColumnLength() {
        // Mengembalikan jumlah kolom pada matriks
        return this.column;
    }

    public void setElement(double val, int numRow, int numCol) {
        // Mengubah nilai dari matriks pada baris numRow dan kolom numCol, numRow dan numCol adalah INDEX 
        this.matrix[numRow][numCol] = val;
    }

    //FUNGSI PRIMITIF LAINNYA
    public void writeMatrix () {
        Scanner choiceInput = new Scanner(System.in);
        System.out.println("Pilih cara output matriks: ");
        System.out.println("- Terminal (Ketik 1) ");
        System.out.println("- File Output (Ketik 2)");
        System.out.print("Pilihan: ");
        int choice = choiceInput.nextInt();
        

        while (choice != 1 && choice != 2) {
            System.out.println("Input salah! Masukkan input yang benar");
            readMatrix();
        }

        if (choice == 1) {
            writeTerminal();       
        } else {
            writeFile();
        }
    }

    public void writeTerminal () {
        // Melakukan output matriks pada terminal
        /* Format dalam bentuk segi empat:
         * 1 2 3
         * 4 5 6
         * 7 8 9 
        */
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column-1; j++) {
                System.out.print(this.matrix[i][j]+" ");
            }
            System.out.println(this.matrix[i][this.column-1]);
        }
    }

    public void writeFile () {
        System.out.print("Masukkan path folder tujuan: ");
        try (Scanner input = new Scanner(System.in)) {
            String fileFolderPath = input.nextLine();

            System.out.print("Masukkan nama file (dengan extension): ");
            String fileName = input.nextLine();

            String absolutePath = fileFolderPath + "\\" + fileName;

            try {
                File filePath = new File(absolutePath);
                if (filePath.createNewFile()) {
                    System.out.println("File telah dibuat: " + fileName);
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
                                for (int i = 0; i < this.getRowLength(); i++) {
                                    line = "";
                                    for (int j = 0; j < this.getColumnLength()-1; j++) {
                                        line += String.valueOf(getElement(i+1, j+1));
                                        line += " ";
                                    } // j == getColumnLength - 1
                                    line += String.valueOf(getElement(i+1, getColumnLength()));
                                    line += "\n";
                                    fileWriter.write(line);
                                }
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
    }

    public void writeLine (double[] array) {
        // Melakukan output array pada terminal 
        // Format dalam spasi
        // [1, 2, 3] akan dioutput 1 2 3
        for (int i = 0; i < array.length-1; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array[array.length - 1]);
    }

    public double makeZero (double Element, double leadElement) {
        // Mengembalikan nilai konstanta sehingga OBE dapat menjadi nol
        /* CONTOH
         * 1 2 3
         * 4 5 6
         * 7 8 9 
         * Untuk membuat angka 4 menjadi 0, maka harus dilakukan R2 - 4R1 
         * maka fungsi ini akan mengembalikan nilai -4
         */
        return -1 * (Element/leadElement);
    }

    public boolean isSquare () {
        // Mengembalikan true apabila matriks berbentuk matriks persegi
        return this.row == this.column;
    }

    public int countElement () {
        // Mengembalikan jumlah elemen yang ada pada matriks
        return this.row*this.column;
    }

    public void swapRow (int a, int b) {
        // Menukar baris pada matriks
        /* DISCLAMER: PERHATIKAN INDEKS 
         * 1 2 3
         * 4 5 6
         * 7 8 9
         * Maka swapRow (1, 3) akan menghasilkan
         * 7 8 9
         * 4 5 6
         * 1 2 3
         * swapRow (0, 2) tidak terdefinisi karena tidak terdapat row 0 pada pengguna
         * (namun indeks pada array tetap mulai dari nol)
        */
        double[] temp;
        temp = this.matrix[a-1];
        this.matrix[a-1] = this.matrix[b-1];
        this.matrix[b-1] = temp;
    }

    public void transpose () {
        // melakukan transpose pada matriks
        double[][] tempmatrix = new double[column][row];
        int temprow = this.column;
        int tempcolumn = this.row;

        for (int i = 0; i < temprow; i++) {
            for (int j = 0; j < tempcolumn; j++) {
                 tempmatrix[i][j] = this.matrix[j][i];
            }
        }

        this.matrix = tempmatrix;
        this.row = temprow;
        this.column = tempcolumn;
    }

    public void OBE (int i, int idxrow, double zero) {
        // Melakukan operasi OBE pada matriks
        // i adalah INDEX baris eselon 
        // idxrow adalah INDEX baris dibawah baris eselon yang dioperasikan
        // zero adalah konstanta pembuat nol
         /* DISCLAMER: Prosedur INI MEMAKAI FUNGSI getElement PERHATIKAN INDEKS 
         * Jika kita mempunyai matriks awal
         * 1 2 3
         * 4 5 6
         * 7 8 9
         * Maka OBE (0, 1, -4) berarti R2 - 4R1
         * 1 2 3
         * 0 -3 -6
         * 7 8 9
         * OBE (0, 2, -7) terhadap matriks awal berarti R3 - 7R1
         * 1 2 3
         * 4 5 6
         * 0 -6 -12
        */
        for (int k = 0; k < this.column; k++) {
            this.matrix[idxrow][k] = (getElement(idxrow+1, k+1) + zero * getElement(i+1, k+1));  
        }
    }

    public void divideRow(int i, double val) {
        // Membagi baris dengan val
        for (int j = i; j < this.getColumnLength(); j++) {
            this.setElement(this.getElement(i, j) / val, i, j);
        }
    }

    /*public void setPrecision (int precc) {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.matrix[i][j] = 1;
            }
        }
    }*/


}
