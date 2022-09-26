package functions;

import dataStructure.*;
import utility.*;

import java.util.Arrays;

public class Gauss {
    public static Matrix matrixGauss(Matrix mat) {
        // Membuat matriks
        Matrix newmat = new Matrix();
        newmat.createMatrix(mat.getRowLength(), mat.getColumnLength());
        newmat = Operations.copyMatrix(mat);

        int j = 0;
        for (int i = 0; i < newmat.getRowLength(); i++) {
            while (j < newmat.getColumnLength() && newmat.getElement(i+1, j+1) == 0) {
                int temp = Gauss.indexNotZeroInCol(newmat, j, i+1);
                if (temp != -1) {
                    newmat.swapRow(i+1, temp+1);
                } else {
                    j++;
                }
            }

            if (j >= newmat.getColumnLength()) {
                break;
            }

            if (newmat.getElement(i+1, j+1) != 0) {
                double temp = newmat.getElement(i+1, j+1);
                newmat.divideRow(i, temp);
            }

            for (int k = i + 1; k < newmat.getRowLength(); k++) {
                if (newmat.getElement(k+1, j+1) != 0) {
                    double temp = newmat.getElement(k+1, j+1);
                    newmat.OBE(i, k, (-1) * temp);
                }
            }

            j++;
        }

        mat.writeMatrix();

        return newmat;
    }

    public static Matrix matrixGaussJordan(Matrix mat) {
        Matrix newmat = new Matrix();
        newmat.createMatrix(mat.getRowLength(), mat.getColumnLength());
        newmat = Operations.copyMatrix(mat);

        int j = 0;
        for (int i = 0; i < newmat.getRowLength(); i++) {
            while (j < newmat.getColumnLength() && newmat.getElement(i+1, j+1) == 0) {
                int temp = Gauss.indexNotZeroInCol(newmat, j, i+1);
                if (temp != -1) {
                    newmat.swapRow(i+1, temp+1);
                } else {
                    j++;
                }
            }

            if (j >= newmat.getColumnLength()) {
                break;
            }

            if (newmat.getElement(i+1, j+1) != 0) {
                double temp = newmat.getElement(i+1, j+1);
                newmat.divideRow(i, temp);
            }

            for (int k = 0; k < newmat.getRowLength(); k++) {
                if (newmat.getElement(k+1, j+1) != 0 && k != i) {
                    double temp = newmat.getElement(k+1, j+1);
                    newmat.OBE(i, k, (-1) * temp);
                }
            }

            j++;
        }

        return newmat;
    }

    public static int indexNotZeroInCol(Matrix m, int numCol, int rowMin) {
        for (int i = rowMin; i < m.getRowLength(); i++) {
            if (m.getElement(i+1, numCol+1) != 0) {
                return i;
            }
        }

        return (-1);
    }

    public static void solveSPL(Matrix m) {
        // Inisialisasi huruf untuk variabel dan indeks huruf yang digunakan
        String var = "abcdefghijklmnopqrstuvwxyz";
        int ivar = 0;

        // Inisialisasi Array
        double[][] solusinum = new double[m.getColumnLength() - 1][m.getColumnLength() - 1];
        int[] neffsn = new int[m.getColumnLength() - 1];
        char[][] solusivar = new char[m.getColumnLength() - 1][m.getColumnLength() - 1];
        int[] neffsv = new int[m.getColumnLength() - 1];
        boolean[] param = new boolean[m.getColumnLength() - 1];
        Arrays.fill(param, true);
        Arrays.fill(neffsn, 1);

        // Mengecek  apakah SPL memiliki solusi
        boolean hasSolution = false;
        for (int row = m.getRowLength() - 1; row >= 0; row--) {
            for (int col = 0; col < m.getColumnLength(); col++) {
                // Mengecek apakah ada 1 utama pada suatu baris
                if (m.getElement(row + 1, col + 1) == 1 && col != m.getColumnLength() - 1) {
                    hasSolution = true;
                    break;
                }

                // Jika masih tidak ditemukan 1 utama namun terdapat nilai pada
                // kolom terkanan maka SPL tidak memiliki solusi
                if (m.getElement(row + 1, col + 1) != 0 && col == m.getColumnLength() - 1) {
                    System.out.println("SPL tidak memiliki solusi");
                    return;
                }
            }
            if (hasSolution) {
                break;
            }
        }
        
        // Mengecek variabel mana yang akan menjadi parameter
        for (int row = 0; row < m.getRowLength(); row++) {
            for (int col = 0; col < m.getColumnLength() - 1; col++) {
                if (m.getElement(row + 1, col + 1) == 1) {
                    param[col] = false;
                }
            }
        }

        // Mengassign variabel pada parameter
        for (int col = 0; col < m.getColumnLength() - 1; col++) {
            if (param[col]) {
                solusinum[col][neffsn[col]] = 1.0;
                solusivar[col][neffsv[col]] = var.charAt(ivar);
                ivar++;
                neffsn[col]++;
                neffsv[col]++;
            }
        }

        // Mencari solusi untuk setiap variabel
        for (int row = m.getRowLength() - 1; row >= 0; row--) {
            for (int col = 0; col < m.getColumnLength() - 1; col++) {
                if (m.getElement(row + 1, col + 1) == 1) {
                    // Inisialisasi hasil dengan nilai pada kolom paling kanan
                    solusinum[col][0] = m.getElement(row + 1, m.getColumnLength());

                    // Menyelesaikan persamaan linear pada suatu baris
                    for (int nextcol = col + 1; nextcol < m.getColumnLength() - 1; nextcol++) {
                        // Mengurangi solusi yang berupa konstanta
                        solusinum[col][0] -= m.getElement(row + 1, nextcol + 1) * solusinum[nextcol][0];
                        if (neffsn[nextcol] > 1) {
                            for (int i = 0; i < neffsv[nextcol]; i++) {
                                int idx = -1;
                                for (int j = 0; j < neffsn[nextcol]; j++) {
                                    if (solusivar[nextcol][i] == solusivar[col][j]) {
                                        idx = j;
                                        break;
                                    }
                                }

                                double temp = (-1) * m.getElement(row+1, nextcol+1) * solusinum[nextcol][i+1];
                                if (idx == -1) {
                                    if (m.getElement(row+1, nextcol+1) != 0) {
                                        solusivar[col][neffsv[col]] = solusivar[nextcol][i];
                                        neffsv[col]++;
                                        solusinum[col][neffsn[col]] = temp;
                                        neffsn[col]++;
                                    }
                                } else {
                                    solusinum[col][idx + 1] += temp;
                                }
                            }
                        }
                    }

                    break;
                }
            }
        }

        // System.out.println(Arrays.deepToString(solusinum));
        // System.out.println(Arrays.toString(neffsn));
        // System.out.println(Arrays.deepToString(solusivar));
        // System.out.println(Arrays.toString(neffsv));

        // OUTPUT Solusi SPL
        System.out.println("Solusi SPL:");
        for (int sol = 0; sol < m.getColumnLength() - 1; sol++) {
            boolean adanum = false;

            System.out.printf("x%d = ", sol + 1);
            if (solusinum[sol][0] != 0) {
                adanum = true;
                System.out.printf("%f ", solusinum[sol][0]);
            } else if (solusinum[sol][0] == 0 && neffsv[sol] == 0) {
                System.out.printf("%f ", solusinum[sol][0]);
            }

            for (int v = 0; v < neffsv[sol]; v++) {
                if (solusinum[sol][v + 1] < 0) {
                    if (v == 0 && !adanum) {
                        System.out.print("-");
                    } else {
                        System.out.print("- ");
                    }

                    if (solusinum[sol][v + 1] != -1) {
                        System.out.print((-1) * solusinum[sol][v + 1]);
                    }
                    System.out.printf("%c ", solusivar[sol][v]);
                } else {
                    if (v != 0 || adanum) {
                        System.out.print("+ ");
                    }

                    if (solusinum[sol][v + 1] != 1) {
                        System.out.print(solusinum[sol][v + 1]);
                    }

                    System.out.printf("%c ", solusivar[sol][v]);
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
		Matrix m = new Matrix();
        m.readMatrix();

        m = matrixGaussJordan(m);
        // System.out.println("\nMatriks Hasil\n");
        // m.writeMatrix();
        System.out.println();

        solveSPL(m);
    }
}
