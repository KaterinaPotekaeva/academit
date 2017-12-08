package ru.academits.potekaeva.main;

import ru.academits.potekaeva.matrix.Matrix;
import ru.academits.potekaeva.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[][] matrix1 = {{0, 1}, {3, 4}};
        double[][] matrix2 = {{0, 0, 0, 1}, {0, 1, 3, 4}, {1, 0, 3, 2}, {1, 0, 0, 0}};
        double[] vector1 = {1, 5};
        double[] vector2 = {2, 1};
        double[] vector3 = {1, 4};
        double[] vector4 = {1, 4};

        Vector[] vector = {new Vector(vector1), new Vector(vector2)};
        Vector vector5 = new Vector(vector4);
        Matrix x = new Matrix(2, 2);
        Matrix z = new Matrix(vector);
        Matrix y = new Matrix(matrix1);
        Matrix k = new Matrix(matrix2);

        System.out.println("   x                         = " + x);
        System.out.println("   y                         = " + y);
        System.out.println("   z                         = " + z);

        System.out.println("Matrix-matrix multiplication = " + Matrix.multiply(z, y));

        System.out.println("Addition static              = " + Matrix.getSum(z, y));
        System.out.println("Addition                     = " + z.getSum(y));
        System.out.println("Subtraction static           = " + Matrix.getMinus(z, y));
        System.out.println("Subtraction                  = " + z.getMinus(y));

        z.setRow(0, new Vector(vector2));

        System.out.println("Row by index                 = " + z.getRow(0));
        System.out.println("Column by index              = " + (z.getColumn(1)));
        System.out.println("Multiplication by scalar     = " + z.getMultiplicationByScalar(3));
        System.out.println("Multiplication by vector     = " + z.getMatrixVectorMultiplication(vector5));
        System.out.println("Transposition                = " + y.getTransposition());

        System.out.println("Determinate                = " + k.getMatrixDeterminant());
    }
}