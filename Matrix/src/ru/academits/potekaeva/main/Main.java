package ru.academits.potekaeva.main;

import ru.academits.potekaeva.matrix.Matrix;
import ru.academits.potekaeva.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        double[][] matrix1 = {{1, 5, 1}, {1, 2, 0}, {1, 5, 5}};
        double[] vector1 = {1, 5, 8};
        double[] vector2 = {2, 3, 4};
        double[] vector3 = {6, -2, 7};

        Matrix x = new Matrix(3, 3);
        Matrix y = new Matrix(matrix1);
        Matrix z = new Matrix(new Vector(vector1), new Vector(vector2), new Vector(vector3));

        System.out.println("   x                    = " + x);
        System.out.println("   y                    = " + y);
        System.out.println("   z                    = " + z);

        System.out.println("Transposition           = " + z.getTransposition());

        z.setRow(0, new Vector(vector1));
        System.out.println("Row ny index            =  " + z.getRow(0));
        System.out.println("Column by index         = " + Arrays.toString((z.getColumn(0))));
        System.out.println("Multiplication by scalar= " + z.getMultiplicationByScalar(-11));

    }
}