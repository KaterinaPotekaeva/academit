package ru.academits.potekaeva.main;

import ru.academits.potekaeva.matrix.Matrix;
import ru.academits.potekaeva.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[][] matrix1 = {{1, 5, 1}, {1, 2, 1}};
        double[] vector1 = {1, 5};
        double[] vector2 = {2, 1};
        double[] vector3 = {1, 4};
        double[] vector4 = {1, 4};

        Vector[] vector = {new Vector(vector1), new Vector(vector2)};
        Matrix x = new Matrix(2, 2);
        Matrix z = new Matrix(vector);
        Matrix y = new Matrix(z);

        System.out.println("   x                         = " + x);
        System.out.println("   y                         = " + y);
        System.out.println("   z                         = " + z);

        System.out.println("Matrix-vector multiplication = " + y.getMatrixVectorMultiplication(new Vector(vector4)));
        System.out.println("Matrix-matrix multiplication = " + Matrix.getMatrixMatrixMultiplication(z, y));

        System.out.println("Addition static              = " + Matrix.getSum(z, y));
        System.out.println("Addition                     = " + z.getSum(y));
        System.out.println("Subtraction static           = " + Matrix.getMinus(z, y));
        System.out.println("Subtraction                  = " + z.getMinus(y));

        z.setRow(0, new Vector(vector2));

        System.out.println("Row by index                 = " + z.getRow(0));
        System.out.println("Column by index              = " + (z.getColumn(1)));
        System.out.println("Multiplication by scalar     = " + z.getMultiplicationByScalar(3));
        System.out.println("Transposition                = " + y.getTransposition());
        System.out.println("   y                         = " + y);
    }
}