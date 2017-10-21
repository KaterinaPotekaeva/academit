package ru.academits.potekaeva.matrix;

import ru.academits.potekaeva.vector.Vector;

import java.util.Arrays;

public class Matrix {

    private Vector[] matrix;

    public Matrix(int column, int row) {
        if (row <= 0 || column <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            this.matrix = new Vector[column];
            for (int i = 0; i < column; i++) {
                matrix[i] = new Vector(row);
            }
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            int sizeColumn = array.length;

            for (int i = 1; i < sizeColumn; i++) {
                if (array[i].length == 0 || array[i - 1].length != array[i].length) {
                    throw new IllegalArgumentException("Size does't exist");
                }
            }
            this.matrix = new Vector[sizeColumn];

            for (int i = 0; i < sizeColumn; i++) {
                matrix[i] = new Vector(array[i]);
            }
        }
    }

    public Matrix(Vector... vector) {
        if (vector.length <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            int sizeColumn = vector.length;

            for (int i = 1; i < sizeColumn; i++) {
                if (vector[i].getSize() == 0 || vector[i - 1].getSize() != vector[i].getSize()) {
                    throw new IllegalArgumentException("Size does't exist");
                }
            }
            this.matrix = new Vector[sizeColumn];
            for (int i = 0; i < sizeColumn; i++) {
                matrix[i] = new Vector(vector[i]);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this.matrix = Arrays.copyOf(matrix.matrix, matrix.matrix.length);
    }

    private int[] getSize() {
        return new int[]{matrix.length, matrix[0].getSize()};
    }

    //Задание вектора строки по индексу
    public void setRow(int i, Vector vector) {
        int column = getSize()[0];
        int row = vector.getSize();

        if (i >= column || column < 0 || matrix[0].getSize() != row) {
            throw new IndexOutOfBoundsException();
        } else {
            matrix[i] = new Vector(vector);
        }
    }

    //Получение  вектора строки по индексу
    public Vector getRow(int i) {
        int column = getSize()[0];
        if (i >= column || column < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return matrix[i];
        }
    }

    //Получение  вектора столбца по индексу
    public double[] getColumn(int i) {
        int row = matrix[0].getSize();
        if (i >= row || row < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            int sizeRow = getSize()[0];
            double[] newColumn = new double[sizeRow];

            for (int j = 0; j < sizeRow; j++) {
                newColumn[j] = matrix[j].getElement(i);
            }
            return newColumn;
        }
    }

    //Умножение матрицы на число
    public Matrix getMultiplicationByScalar(int number) {
        int column = getSize()[0];
        for (int i = 0; i < column; i++) {
            matrix[i] = matrix[i].scale(number);
        }
        return this;
    }

    // Сложение
    public Matrix getSum(Matrix that) {
        int row = getSize()[0];
        for (int i = 0; i < row; i++) {
            this.matrix[i].plus(that.matrix[i]);
        }
        return this;
    }

    //Вычитание
    public Matrix getMinus(Matrix that) {
        int row = getSize()[0];
        for (int i = 0; i < row; i++) {
            this.matrix[i].minus(that.matrix[i]);
        }
        return this;
    }

    //Транспонирование матрицы
    public Matrix getTransposition() {
        int row = getSize()[0];
        int column = getSize()[1];
        Matrix transposedMatrix = new Matrix(column, row);

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                transposedMatrix.matrix[i].setElement(j, matrix[j].getElement(i));
            }
        }
        return transposedMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix that = (Matrix) o;

        return Arrays.equals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = getSize()[0];
        final int prime = 31;
        result = prime * result + Arrays.hashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(matrix[0]);
        for (int i = 1; i < getSize()[0]; i++) {
            s.append(", ").append(matrix[i]);
        }
        s.append("}");
        return s.toString();
    }
}

