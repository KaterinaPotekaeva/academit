package ru.academits.potekaeva.matrix;

import ru.academits.potekaeva.vector.Vector;

import java.util.Arrays;

public class Matrix {

    private Vector[] rows;

    public Matrix(int columns, int rows) {
        if (columns <= 0) {
            throw new IllegalArgumentException("The column size isn't correct");
        }
        if (rows <= 0) {
            throw new IllegalArgumentException("The row size isn't correct");
        }

        this.rows = new Vector[rows];
        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(double[][] array) {
        int rowsCount = array.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("The rows size isn't correct");
        }

        for (int i = 1; i < rowsCount; i++) {
            if (array[i].length == 0 || array[i - 1].length != array[i].length) {
                throw new IllegalArgumentException("The length of row is different");
            }
        }

        this.rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vector) {
        if (vector.length <= 0) {
            throw new IllegalArgumentException("The rows of the vector isn't correct");
        }

        int rowsCount = vector.length;

        for (int i = 1; i < rowsCount; i++) {
            if (vector[i].getSize() == 0 || vector[i - 1].getSize() != vector[i].getSize()) {
                throw new IllegalArgumentException("The length of row is different");
            }
        }

        this.rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(vector[i]);
        }
    }

    public Matrix(Matrix matrix) {
        int sizeMatrix = matrix.rows.length;
        this.rows = new Vector[sizeMatrix];

        for (int i = 0; i < sizeMatrix; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    private int getColumnsCount() {
        return rows[0].getSize();
    }

    private int getRowsCount() {
        return rows.length;
    }

    //Задание вектора строки по индексу
    public void setRow(int i, Vector vector) {
        int rowsCount = getRowsCount();

        if (i >= rowsCount || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        rows[i] = new Vector(vector);
    }

    //Получение  вектора строки по индексу
    public Vector getRow(int i) {
        int rowsCount = getRowsCount();

        if (i >= rowsCount || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        return new Vector(rows[i]);
    }

    //Получение  вектора столбца по индексу
    public Vector getColumn(int i) {
        if (i >= getColumnsCount() || getColumnsCount() < 0) {
            throw new IndexOutOfBoundsException();
        }

        int rowsCount = getRowsCount();
        double[] newColumn = new double[getRowsCount()];
        for (int j = 0; j < rowsCount; j++) {
            newColumn[j] = rows[j].getElement(i);
        }

        return new Vector(newColumn);
    }

    //Умножение матрицы на число
    public Matrix getMultiplicationByScalar(int number) {
        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = rows[i].scale(number);
        }
        return this;
    }

    // Сложение
    public Matrix getSum(Matrix that) {
        int rowsCount = getRowsCount();

        if (rowsCount != that.getRowsCount()) {
            throw new IllegalArgumentException("Number of rows of matrices is different");
        }

        if (getColumnsCount() != that.getColumnsCount()) {
            throw new IllegalArgumentException("Number of columns of matrices is different");
        }

        for (int i = 0; i < rowsCount; i++) {
            this.rows[i].plus(that.rows[i]);
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        return new Matrix(matrix1).getSum(matrix2);
    }

    //Вычитание
    public Matrix getMinus(Matrix that) {
        int rowsCount = getRowsCount();

        if (rowsCount != that.getRowsCount()) {
            throw new IllegalArgumentException("Number of rows of matrices is different");
        }

        if (getColumnsCount() != that.getColumnsCount()) {
            throw new IllegalArgumentException("Number of columns of matrices is different");
        }

        for (int i = 0; i < rowsCount; i++) {
            this.rows[i].minus(that.rows[i]);
        }
        return this;
    }

    public static Matrix getMinus(Matrix matrix1, Matrix matrix2) {
        return new Matrix(matrix1).getMinus(matrix2);
    }

    //Транспонирование матрицы
    public Matrix getTransposition() {
        int columnsCount = getColumnsCount();
        Vector[] vector = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            vector[i] = this.getColumn(i);
        }
        rows = vector;
        return this;
    }

    //Умножение матрицы на вектор
    public Vector getMatrixVectorMultiplication(Vector vector) {
        int rowsCount = getRowsCount();

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("The size of the matrix and the vector does't match");
        }

        Vector vectorNew = new Vector(getRowsCount());

        for (int i = 0; i < rowsCount; i++) {
            vectorNew.setElement(i, Vector.dot(rows[i], vector));
        }
        return vectorNew;
    }

    //Умножение матрицы на матрицу
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {

        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("The size of matrices does't match");
        }

        int columnMatrix2 = matrix2.getColumnsCount();
        Vector[] vector = new Vector[columnMatrix2];

        for (int i = 0; i < columnMatrix2; i++) {
            vector[i] = matrix1.getMatrixVectorMultiplication(matrix2.getColumn(i));
        }
        return new Matrix(vector).getTransposition();
    }

    private int getIndexValueMax(int index) {
        Vector column = getColumn(index);
        double maxValue = Math.abs(column.getElement(index));
        int temp = index;

        for (int i = index; i < getRowsCount(); ++i) {
            if (Math.abs(column.getElement(i)) > maxValue) {
                maxValue = Math.abs(column.getElement(i));
                temp = i;
            }
        }
        return temp;
    }

    public double getMatrixDeterminant() {
        Matrix matrix = new Matrix(this);
        int columnsCount = matrix.getColumnsCount();
        int rowsCount = getRowsCount();

        if (getColumnsCount() != rowsCount) {
            throw new IllegalArgumentException("The size of the row and column does't match");
        }

        int rowsPermutationsCount = 0;
        for (int i = 0; i < columnsCount - 1; i++) {

            for (int j = i + 1; j < rowsCount; j++) {

                if (matrix.rows[j].getElement(i) != 0) {
                    if (matrix.getIndexValueMax(i) != i) {
                        Vector vector = matrix.rows[i];
                        matrix.rows[i] = matrix.rows[j];
                        matrix.rows[j] = vector;
                        ++rowsPermutationsCount;
                    }

                    double num = matrix.rows[j].getElement(i) / matrix.rows[i].getElement(i);
                    for (int k = i; k < columnsCount; k++) {
                        matrix.rows[j].setElement(k, matrix.rows[j].getElement(k) - matrix.rows[i].getElement(k) * num);
                    }
                }
            }

            if (matrix.rows[i].getElement(i) == 0) {
                return 0;
            }
        }

        double determinate = 1;
        for (int i = 0; i < columnsCount; i++) {
            determinate *= matrix.rows[i].getElement(i);
        }

        if (rowsPermutationsCount % 2 != 0) {
            return -determinate;
        }
        return determinate;
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

        return Arrays.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        int result = getRowsCount();
        final int prime = 31;
        result = prime * result + Arrays.hashCode(rows);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(rows[0]);
        for (int i = 1; i < getRowsCount(); i++) {
            s.append(", ").append(rows[i]);
        }
        s.append("}");
        return s.toString();
    }
}

