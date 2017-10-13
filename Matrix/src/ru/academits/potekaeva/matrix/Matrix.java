package ru.academits.potekaeva.matrix;

import ru.academits.potekaeva.vector.Vector;

import java.util.Arrays;

public class Matrix {

    private Vector[] matrix;

    public Matrix() {
    }

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

    private int getSize() {
        return matrix.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(matrix[0]);
        for (int i = 1; i < getSize(); i++) {
            s.append(", ").append(matrix[i]);
        }
        s.append("}");
        return s.toString();
    }
}

