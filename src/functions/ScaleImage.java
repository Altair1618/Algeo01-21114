package functions;

import dataStructure.Matrix;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScaleImage {
    public static Matrix addPadding(Matrix m) {
        Matrix pad = new Matrix();
        pad.createMatrix(m.getRowLength() + 3, m.getColumnLength() + 3);

        // Matriks Awal
        for (int i = 0; i < m.getRowLength(); i++) {
            for (int j = 0; j < m.getColumnLength(); j++) {
                pad.setElement(m.getElement(i + 1, j + 1), i + 2, j + 2);
            }
        }

        // Padding Atas
        for (int i = 0; i < pad.getColumnLength(); i++) {
            if (i == 0) {
                pad.setElement(m.getElement(1, 1), 1, 1);
            } else if (i >= pad.getColumnLength() - 2) {
                pad.setElement(m.getElement(1, m.getColumnLength()), 1, i + 1);
            } else {
                pad.setElement(m.getElement(1, i), 1, i + 1);
            }
        }

        // Padding Kiri
        for (int i = 0; i < m.getRowLength(); i++) {
            pad.setElement(m.getElement(i + 1, 1), i + 2, 1);
        }

        // Padding Kanan
        for (int i = 0; i < m.getRowLength(); i++) {
            pad.setElement(m.getElement(i + 1, m.getColumnLength()), i + 2, pad.getColumnLength() - 1);
            pad.setElement(m.getElement(i + 1, m.getColumnLength()), i + 2, pad.getColumnLength());
        }

        // Padding Bawah
        for (int i = 0; i < pad.getColumnLength(); i++) {
            if (i == 0) {
                pad.setElement(m.getElement(m.getRowLength(), 1), pad.getRowLength() - 1, 1);
                pad.setElement(m.getElement(m.getRowLength(), 1), pad.getRowLength(), 1);
            } else if (i >= pad.getColumnLength() - 2) {
                pad.setElement(m.getElement(m.getRowLength(), m.getColumnLength()), pad.getRowLength() - 1, i + 1);
                pad.setElement(m.getElement(m.getRowLength(), m.getColumnLength()), pad.getRowLength(), i + 1);
            } else {
                pad.setElement(m.getElement(m.getRowLength(), i), pad.getRowLength() - 1, i + 1);
                pad.setElement(m.getElement(m.getRowLength(), i), pad.getRowLength(), i + 1);
            }
        }

        return pad;
    }

    public static void main(String[] args) {
        // Matrix m = new Matrix();
        // m.readMatrix();

        // Matrix pad = addPadding(m);
        // pad.writeMatrix();
    }
}
