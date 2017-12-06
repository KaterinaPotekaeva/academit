package ru.academits.potekaeva.matrix;

import ru.academits.potekaeva.vector.Vector;

import java.util.Arrays;

public class Matrix {

    private Vector[] rows;

    public Matrix(int columns, int rows) {
        if (columns <= 0) {
            throw new IllegalArgumentException("The column size does't exist");
        }
        if (rows <= 0) {
            throw new IllegalArgumentException("The row size  does't exist");
        }

        this.rows = new Vector[rows];
        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The rows size does't exist");
        }

        int columnsCount = array.length;

        for (int i = 1; i < columnsCount; i++) {
            if (array[i].length == 0 || array[i - 1].length != array[i].length) {
                throw new IllegalArgumentException("The length of row is different");
            }
        }

        this.rows = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vector) {
        if (vector.length <= 0) {
            throw new IllegalArgumentException("The rows size does't exist");
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

    private int getCountColumns() {
        return rows[0].getSize();
    }

    private int getSizeRows() {
        return rows.length;
    }

    //Задание вектора строки по индексу
    public void setRow(int i, Vector vector) {
        int rowsCount = getSizeRows();
        int columnsCount = vector.getSize();

        if (i >= rowsCount || rowsCount < 0 || getCountColumns() != columnsCount) {
            throw new IllegalArgumentException("The rows size does't exist");
        }
        rows[i] = new Vector(vector);
    }

    //Получение  вектора строки по индексу
    public Vector getRow(int i) {
        int rowsCount = getSizeRows();

        if (i >= rowsCount || rowsCount < 0) {
            throw new IndexOutOfBoundsException();
        }
        return new Vector(rows[i]);
    }

    //Получение  вектора столбца по индексу
    public Vector getColumn(int i) {
        int columnsCount = getCountColumns();

        if (i >= columnsCount || columnsCount < 0) {
            throw new IndexOutOfBoundsException();
        }

        double[] newColumn = new double[getSizeRows()];

        for (int j = 0; j < getSizeRows(); j++) {
            newColumn[j] = rows[j].getElement(i);
        }
        return new Vector(newColumn);
    }

    //Умножение матрицы на число
    public Matrix getMultiplicationByScalar(int number) {
        int rowsCount = getSizeRows();

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = rows[i].scale(number);
        }
        return this;
    }

    // Сложение
    public Matrix getSum(Matrix that) {
        int rowsCount = getSizeRows();

        if (rowsCount != that.getSizeRows()) {
            throw new IllegalArgumentException("The length of row  is different");
        }

        if (getCountColumns() != that.getCountColumns()) {
            throw new IllegalArgumentException("The number of colums is different");
        }

        for (int i = 0; i < rowsCount; i++) {
            this.rows[i].plus(that.rows[i]);
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {

        if (matrix1.getSizeRows() != matrix2.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }

        return new Matrix(matrix1.getSum(matrix2));
    }

    //Вычитание
    public Matrix getMinus(Matrix that) {
        int rowsCount = getSizeRows();

        if (rowsCount != that.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }

        for (int i = 0; i < rowsCount; i++) {
            this.rows[i].minus(that.rows[i]);
        }
        return this;
    }

    public static Matrix getMinus(Matrix matrix1, Matrix matrix2) {

        if (matrix1.getSizeRows() != matrix2.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }
        return new Matrix(matrix1.getMinus(matrix2));
    }

    //Транспонирование матрицы
    public Matrix getTransposition() {
        int columnsCount = getCountColumns();
        Vector[] vector = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            vector[i] = this.getColumn(i);
        }
        rows = vector;
        return this;
    }

    //Умножение матрицы на вектор
    public Vector getMatrixVectorMultiplication(Vector vector) {
        int rowsCount = getSizeRows();

        if (getCountColumns() != vector.getSize()) {
            throw new IllegalArgumentException("The length of vector and the matrix is different");
        }

        Vector vectorNew = new Vector(getSizeRows());

        for (int i = 0; i < rowsCount; i++) {
            vectorNew.setElement(i, Vector.dot(rows[i], vector));
        }
        return vectorNew;
    }

    //Умножение матрицы на матрицу
    public static Matrix getMatrixMatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getCountColumns() != matrix2.getSizeRows()) {
            throw new IllegalArgumentException("The row length and column height the matrixs is different");
        }

        int columnMatrix2 = matrix2.getCountColumns();
        Vector[] vector = new Vector[columnMatrix2];

        for (int i = 0; i < columnMatrix2; i++) {
            vector[i] = matrix1.getMatrixVectorMultiplication(matrix2.getColumn(i));
        }
        return new Matrix(vector).getTransposition();
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
        int result = getSizeRows();
        final int prime = 31;
        result = prime * result + Arrays.hashCode(rows);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(rows[0]);
        for (int i = 1; i < getSizeRows(); i++) {
            s.append(", ").append(rows[i]);
        }
        s.append("}");
        return s.toString();
    }
}

