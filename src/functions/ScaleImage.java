package functions;

import dataStructure.Matrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ScaleImage {
    public static BufferedImage readImage() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan Nama File Gambar:");
        String namafile = in.next();
        File image = new File(String.format("../Algeo01-21114/test/%s", namafile));

        in.close();
        return ImageIO.read(image);
    }

    public static Matrix canvasScaledImage(Matrix mimg) {
        Matrix smimg = new Matrix();
        smimg.createMatrix(mimg.getRowLength() * 2, mimg.getColumnLength() * 2);

        for (int i = 0; i < mimg.getRowLength(); i++) {
            for (int j = 0; j < mimg.getColumnLength(); j++) {
                smimg.setElement(mimg.getElement(i + 1, j + 1), (2 * i) + 1, (2 * j) + 1);
            }
        }

        return smimg;
    }

    public static void scaleImage(BufferedImage img) {
        double newRed, newBlue, newGreen;
        int rgb;

        assert img != null;
        int width = img.getWidth();
        int height = img.getHeight();

        Matrix mimg = new Matrix();
        mimg.createMatrix(width, height);
        System.out.printf("%d %d\n", width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.printf("%d %d\n", i, j);
                mimg.setElement(Math.abs(img.getRGB(i, j)), i + 1, j + 1);
            }
        }

        Matrix scaledImage = canvasScaledImage(mimg);
        mimg = addPadding(mimg);

        for (int i = 1; i < mimg.getRowLength() - 2; i++) {
            for (int j = 1; j < mimg.getColumnLength() - 2; j++) {
                // Membuat matriks input bicubic
                Matrix cr = new Matrix();
                cr.createMatrix(4, 4);
                Matrix cb = new Matrix();
                cb.createMatrix(4, 4);
                Matrix cg = new Matrix();
                cg.createMatrix(4, 4);

                for (int k = i - 1; k <= i + 2; k++) {
                    for (int l = j - 1; l <= j + 2 ; l++) {
                        // System.out.printf("%d %d %d %d\n", k + 1, l + 1, k - i + 2, l - j + 2);
                        Color temp = new Color((int) mimg.getElement(k + 1, l + 1));
                        cr.setElement(temp.getRed(), k - i + 2, l - j + 2);
                        cb.setElement(temp.getBlue(), k - i + 2, l - j + 2);
                        cg.setElement(temp.getGreen(), k - i + 2, l - j + 2);
                    }
                }

                newRed = Math.abs(Bicubic.calcBicubic(cr, 0.5, 0));
                newBlue = Math.abs(Bicubic.calcBicubic(cb, 0.5, 0));
                newGreen = Math.abs(Bicubic.calcBicubic(cg, 0.5, 0));
                if (newRed > 255) {
                    newRed = 255;
                }
                if (newBlue > 255) {
                    newBlue = 255;
                }
                if (newGreen > 255) {
                    newGreen = 255;
                }
                rgb = new Color((int) newRed, (int) newGreen, (int) newBlue).getRGB();

                scaledImage.setElement(rgb, 2 * i - 1, 2 * j);

                newRed = Math.abs(Bicubic.calcBicubic(cr, 0, 0.5));
                newBlue = Math.abs(Bicubic.calcBicubic(cb, 0, 0.5));
                newGreen = Math.abs(Bicubic.calcBicubic(cg, 0, 0.5));
                if (newRed > 255) {
                    newRed = 255;
                }
                if (newBlue > 255) {
                    newBlue = 255;
                }
                if (newGreen > 255) {
                    newGreen = 255;
                }
                rgb = new Color((int) newRed, (int) newGreen, (int) newBlue).getRGB();

                scaledImage.setElement(rgb, 2 * i, 2 * j - 1);

                newRed = Math.abs(Bicubic.calcBicubic(cr, 0.5, 0.5));
                newBlue = Math.abs(Bicubic.calcBicubic(cb, 0.5, 0.5));
                newGreen = Math.abs(Bicubic.calcBicubic(cg, 0.5, 0.5));
                if (newRed > 255) {
                    newRed = 255;
                }
                if (newBlue > 255) {
                    newBlue = 255;
                }
                if (newGreen > 255) {
                    newGreen = 255;
                }
                rgb = new Color((int) newRed, (int) newGreen, (int) newBlue).getRGB();

                scaledImage.setElement(rgb, 2 * i, 2 * j);

                if (j == 1) {
                    newRed = Math.abs(Bicubic.calcBicubic(cr, 1, 0.5));
                    newBlue = Math.abs(Bicubic.calcBicubic(cb, 1, 0.5));
                    newGreen = Math.abs(Bicubic.calcBicubic(cg, 1, 0.5));
                    if (newRed > 255) {
                        newRed = 255;
                    }
                    if (newBlue > 255) {
                        newBlue = 255;
                    }
                    if (newGreen > 255) {
                        newGreen = 255;
                    }
                    rgb = new Color((int) newRed, (int) newGreen, (int) newBlue).getRGB();

                    scaledImage.setElement(rgb, 2 * i, 2 * j + 1);
                }

                if (i == 1) {
                    newRed = Math.abs(Bicubic.calcBicubic(cr, 0.5, 1));
                    newBlue = Math.abs(Bicubic.calcBicubic(cb, 0.5, 1));
                    newGreen = Math.abs(Bicubic.calcBicubic(cg, 0.5, 1));
                    if (newRed > 255) {
                        newRed = 255;
                    }
                    if (newBlue > 255) {
                        newBlue = 255;
                    }
                    if (newGreen > 255) {
                        newGreen = 255;
                    }
                    rgb = new Color((int) newRed, (int) newGreen, (int) newBlue).getRGB();

                    // System.out.printf("%d %d\n", 2 * j + 1, 2 * i);
                    scaledImage.setElement(rgb, 2 * i + 1, 2 * j);
                }

                // i = 1, j = 1, x = 0.5, y = 0.5 -> 2 2
                // i = 1, j = 1, x = 0, y = 0.5 -> 1 2
                // i = 1, j = 1, x = 0.5, y = 0 -> 2 1
                // i = 1, j = 2, x = 0.5, y = 0 -> 4 1
                // 2*(j+x) - 1, 2*(i+y) - 1
            }
        }

        BufferedImage simg = new BufferedImage(img.getWidth() * 2, img.getHeight() * 2, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < simg.getWidth(); i++) {
            for (int j = 0; j < simg.getHeight(); j++) {
                simg.setRGB(i, j, (int) (-1 * scaledImage.getElement(i + 1, j + 1)));
            }
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Masukkan Nama File Gambar Tujuan:");
        String namafile = in.next();

        try {
            File f = new File(
                    String.format("../BackupTubes/src/functions/%s", namafile));
            ImageIO.write(simg, "png", f);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        in.close();
    }

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

    public static void main(String[] args) throws IOException {
        // Matrix m = new Matrix();
        // m.readMatrix();

        // Matrix pad = addPadding(m);
        // pad.writeMatrix();

        scaleImage(readImage());
    }
}
