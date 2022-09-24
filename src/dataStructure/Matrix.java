import java.util.Scanner;
//import java.io.File;

public class Matrix {
    double[][] matrix;
    int row, column;
    final int MARK = -9999;

    //FUNGSI KONSTRUKTOR
    public void createMatrix (int row, int column) {
        // Mengisi semua elemen matriks dengan MARK;
        this.row = row;
        this.column = column;
        this.matrix = new double[this.row][this.column];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.matrix[i][j] = MARK;
            }
        }
        writeMatrix();
    }

    public void readMatrix () {
        // Membaca matriks melalui keyboard
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris: ");
        this.row = input.nextInt();

        System.out.print ("Masukkan jumlah kolom: ");
        this.column = input.nextInt();

        createMatrix(this.row, this.column);

        double inputitem;

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                inputitem = input.nextDouble();
                this.matrix[i][j] = inputitem;
            }
        }

        input.close();
    }

   /* public void readMatrixfromFile () {
        Scanner inputFileName = new Scanner(System.in);
        System.out.print("Masukkan nama file masukan: ");
        String fileName = inputFileName.nextLine();
        inputFileName.close();

        File inputFileMatrix = new File("");
        if ()
    }*/

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
        double[] rowArray = new double[this.column];
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

    //FUNGSI PRIMITIF LAINNYA
    public void writeMatrix () {
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
        if (this.row == this.column) {
            return true;
        }
        return false;
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
        double temp[];
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

    public static void main (String[] args) {
        //Matrix mymatrix = new Matrix();
        //mymatrix.createMatrix(3, 3);
        //mymatrix.readMatrix();
        //mymatrix.writeMatrix();
        /*mymatrix.readMatrix();
        mymatrix.swapRow(1, 2);
        mymatrix.writeMatrix();
        //mymatrix.transpose();
        //mymatrix.writeMatrix();
        System.out.println(("row 1"));
        mymatrix.writeLine(mymatrix.getRow(1));
        mymatrix.writeLine(mymatrix.getColumn(1));*/
    }
}
