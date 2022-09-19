package dataStructure;

import java.util.Scanner;

public class Matrix {
    double[][] matrix;
    int row, column;
    final int MARK = -9999;

    //FUNGSI KONSTRUKTOR
    public void createMatrix (int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.matrix[i][j] = MARK;
            }
        }
    }

    public void readMatrix () {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris: ");
        this.row = input.nextInt();

        System.out.print ("Masukkan jumlah kolom: ");
        this.column = input.nextInt();

        this.matrix = new double[this.row][this.column];
        createMatrix(this.row, this.column);

        double inputitem;

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                inputitem = input.nextDouble();
                this.matrix[i][j] = inputitem;
            }
        }

        writeMatrix();
        input.close();
    }

    //FUNGSI PRIMITIF SELEKTOR
    public double getElement (int numRow, int numCol) {
        return this.matrix[numRow-1][numCol-1];
    }

    public double[] getRow (int numRow) {
        double[] rowArray = new double[this.column];
        rowArray = this.matrix[numRow-1];
        return rowArray;
    }

    public double[] getColumn (int numCol) {
        double[] columnArray = new double[this.row];
        for (int i = 0; i < this.row; i++) {
            columnArray[i] = this.matrix[i][numCol - 1];
        }
        return columnArray;
    }

    //FUNGSI PRIMITIF LAINNYA
    public void writeMatrix () {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column-1; j++) {
                System.out.print(this.matrix[i][j]+" ");
            }
            System.out.println(this.matrix[i][this.column-1]);
        }
    }

    public void writeLine (double[] array) {
        for (int i = 0; i < array.length-1; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array[array.length - 1]);
    }

    public void swapRow (int a, int b) {
        double temp[];
        temp = this.matrix[a-1];
        this.matrix[a-1] = this.matrix[b-1];
        this.matrix[b-1] = temp;
    }

    public void transpose () {
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

    public static void main (String[] args) {
        /*Matrix mymatrix = new Matrix();
        mymatrix.readMatrix();
        mymatrix.swapRow(1, 2);
        mymatrix.writeMatrix();
        //mymatrix.transpose();
        //mymatrix.writeMatrix();
        System.out.println(("row 1"));
        mymatrix.writeLine(mymatrix.getRow(1));
        mymatrix.writeLine(mymatrix.getColumn(1));*/
    }
}
