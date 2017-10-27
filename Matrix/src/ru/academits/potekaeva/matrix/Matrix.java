package ru.academits.potekaeva.matrix;

import ru.academits.potekaeva.vector.Vector;

import java.util.Arrays;

import static ru.academits.potekaeva.vector.Vector.dot;
import static ru.academits.potekaeva.vector.Vector.minus;
import static ru.academits.potekaeva.vector.Vector.plus;

public class Matrix {

    private Vector[] rows;

    public Matrix(int column, int row) {
        if (column <= 0) {
            throw new IllegalArgumentException("The column size does't exist");
        }
        if (row <= 0) {
            throw new IllegalArgumentException("The row size  does't exist");
        }

        this.rows = new Vector[row];
        for (int i = 0; i < row; i++) {
            rows[i] = new Vector(column);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The rows size does't exist");
        }
        int sizeColumn = array.length;

        for (int i = 1; i < sizeColumn; i++) {
            if (array[i].length == 0 || array[i - 1].length != array[i].length) {
                throw new IllegalArgumentException("The length of row is different");
            }
        }
        this.rows = new Vector[sizeColumn];

        for (int i = 0; i < sizeColumn; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vector) {
        if (vector.length <= 0) {
            throw new IllegalArgumentException("The rows size does't exist");
        }
        int sizeRows = vector.length;

        for (int i = 1; i < sizeRows; i++) {
            if (vector[i].getSize() == 0 || vector[i - 1].getSize() != vector[i].getSize()) {
                throw new IllegalArgumentException("The length of row is different");
            }
        }
        this.rows = new Vector[sizeRows];

        for (int i = 0; i < sizeRows; i++) {
            rows[i] = new Vector(vector[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = Arrays.copyOf(matrix.rows, matrix.rows.length);
    }

    private int getSizeColumns() {
        return rows[0].getSize();
    }

    private int getSizeRows() {
        return rows.length;
    }

    //Задание вектора строки по индексу
    public void setRow(int i, Vector vector) {
        int row = getSizeRows();
        int column = vector.getSize();

        if (i >= row || row < 0 || getSizeColumns() != column) {
            throw new IndexOutOfBoundsException();
        }

        rows[i] = new Vector(vector);
    }

    //Получение  вектора строки по индексу
    public Vector getRow(int i) {
        int row = getSizeRows();

        if (i >= row || row < 0) {
            throw new IndexOutOfBoundsException();
        }
        return rows[i];
    }

    //Получение  вектора столбца по индексу
    public Vector getColumn(int i) {
        int column = getSizeColumns();

        if (i >= column || column < 0) {
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
        int row = getSizeRows();
        for (int i = 0; i < row; i++) {
            rows[i] = rows[i].scale(number);
        }
        return this;
    }

    // Сложение
    public Matrix getSum(Matrix that) {
        int row = getSizeRows();

        if (row != that.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }

        for (int i = 0; i < row; i++) {
            this.rows[i].plus(that.rows[i]);
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        int row = matrix1.getSizeRows();

        if (matrix1.getSizeRows() != matrix2.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }

        Vector[] vector = new Vector[matrix1.getSizeColumns()];

        for (int i = 0; i < row; i++) {
            vector[i] = new Vector(plus(matrix1.rows[i], matrix2.rows[i]));
        }
        return new Matrix(vector);
    }

    //Вычитание
    public Matrix getMinus(Matrix that) {
        int row = getSizeRows();

        if (row != that.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }

        for (int i = 0; i < row; i++) {
            this.rows[i].minus(that.rows[i]);
        }
        return this;
    }

    public static Matrix getMinus(Matrix matrix1, Matrix matrix2) {
        int row = matrix1.getSizeRows();

        if (row != matrix2.getSizeRows()) {
            throw new IllegalArgumentException("The length of row is different");
        }

        Vector[] vector = new Vector[matrix1.getSizeColumns()];

        for (int i = 0; i < row; i++) {
            vector[i] = new Vector(minus(matrix1.rows[i], matrix2.rows[i]));
        }
        return new Matrix(vector);
    }

    //Транспонирование матрицы
    public Matrix getTransposition() {
        int column = getSizeColumns();
        Vector[] vector = new Vector[column];

        for (int i = 0; i < column; i++) {
            vector[i] = this.getColumn(i);
        }

        rows = Arrays.copyOf(vector, column);
        return this;
    }

    //Умножение матрицы на вектор
    public Vector getMatrixVectorMultiplication(Vector vector) {
        int row = getSizeRows();
        int column = getSizeColumns();

        if (column != vector.getSize()) {
            throw new IllegalArgumentException("The length of vector and the matrix is different");
        }

        Vector vectorNew = new Vector(getSizeRows());

        for (int i = 0; i < row; i++) {
            vectorNew.setElement(i, dot(rows[i], vector));
        }
        return vectorNew;
    }

    //Умножение матрицы на матрицу
    public static Matrix getMatrixMatrixMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSizeColumns() != matrix2.getSizeRows()) {
            throw new IllegalArgumentException("The row length and column height the matrixs is different");
        }

        int columnMatrix2 = matrix2.getSizeColumns();
        Vector[] vector = new Vector[columnMatrix2];

        for (int i = 0; i < columnMatrix2; i++) {
            vector[i] = matrix1.getMatrixVectorMultiplication(matrix2.getColumn(i));
        }

        return  new Matrix(vector).getTransposition();
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

