package dataStructure;

import java.util.Scanner;

public class Point {
    double x,y;

    //FUNGSI KONSTRUKTOR
    public void readPoint () {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan absis titik: ");
        this.x = input.nextDouble();
        System.out.print("Masukkan ordinat titik: ");
        this.y = input.nextDouble();
    }

    public void setAbsis (double val) {
        this.x = val;
    }

    public void setOrdinat (double val) {
        this.y = val;
    }

    //FUNGSI SELEKTOR
    public double getPointAbsis () {
        return this.x;
    }

    public double getPointOrdinat () {
        return this.y;
    }

    //FUNGSI PRIMITIF LAINNYA
    public void writePoint () {
        System.out.print("(" + this.x + "," + this.y + ")\n");
    }

    public boolean isOrigin () {
        return ((this.x == 0 && this.y == 0));
    }

    public boolean isOnSbX () {
        return (this.y == 0);
    }

    public boolean isOnSbY () {
        return (this.x == 0);
    }

    public int Kuadran () {
        if (this.x > 0 && this.y > 0) {
            return 1;
        } else if (this.x < 0 && this.y > 0) {
            return 2;
        } else if (this.x < 0 && this.y < 0) {
            return 3;
        } else if (this.x > 0 && this.y < 0) {
            return 4;
        }
        return 0;
    }

    public void plusDelta (double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public double distancefromOrigin() {
        return (Math.sqrt((this.x*this.x) + (this.y * this.y)));
    }

    public void moveToSbX () {
        this.y = 0;
    }

    public void moveToSbY () {
        this.x = 0;
    }

    public void mirror (boolean toSbX) {
        if (toSbX) {
            this.y *= -1;
        } else {
            this.x *= -1;
        }
    }

    public void PutartoOrigin (double sudutDerajat) {
        double x, y;

        x = this.x;
        y = this.y;

        this.x = (Math.cos(Math.toRadians(sudutDerajat)) * x) - (Math.sin(Math.toRadians(sudutDerajat)) * y);
        this.y = (Math.sin(Math.toRadians(sudutDerajat)) * x) + (Math.cos(Math.toRadians(sudutDerajat)) * y);
    }

    public static void main (String[] args) {
        /*Point mypoint = new Point();
        mypoint.readPoint();
        mypoint.writePoint();
        mypoint.moveToSbY();
        mypoint.writePoint();*/
    }
}
