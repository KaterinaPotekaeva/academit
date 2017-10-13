package ru.academits.potekaeva.main;

import ru.academits.potekaeva.matrix.Matrix;
import ru.academits.potekaeva.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[][] matrix1 = {{1, 5, 1}, {1, 2, 0}, {1, 5, 5}};
        double[] vector1 = {1, 5, 1};
        double[] vector2 = {2, 3, 1};
        double[] vector3 = {2, 2, 3};

        Matrix x = new Matrix(3, 4);
        Matrix y = new Matrix(matrix1);
        Matrix z = new Matrix(new Vector(vector1),new Vector(vector2), new Vector(vector3));

        System.out.println("   x       = " + x);
        System.out.println("   y       = " + y);
        System.out.println("   z       = " + z);
    }
}