package ru.academits.potekaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] data;       // массив компонент вектора

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = new double[n];
        }
    }

    public Vector(Vector vector) {

        this.data = vector.data;
    }

    public Vector(double... a) {
        if (a.length <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = Arrays.copyOf(a, a.length);
        }
    }

    public Vector(int n, double... a) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = Arrays.copyOf(a, n);
        }
    }

    private int getSize() {
        return data.length;
    }

    public Vector scale(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            data[i] = scalar * data[i];
        }
        return this;
    }

    public Vector plus(Vector that) {
        Vector plusVector = getNewSize(this, that);
        Vector maxVector = getMaxVector(this, that);

        for (int i = 0; i < plusVector.getSize(); i++) {
            plusVector.data[i] = plusVector.data[i] + maxVector.data[i];
        }
        return plusVector;
    }

    public static Vector plus(Vector vector1, Vector vector2) {

        Vector plusVector = getNewSize(vector1, vector2);
        Vector maxVector = getMaxVector(vector1, vector2);

        for (int i = 0; i < plusVector.getSize(); i++) {
            plusVector.data[i] = plusVector.data[i] + maxVector.data[i];
        }
        return plusVector;
    }

    public Vector minus(Vector that) {
        Vector minusVector = getNewSize(this, that);

        if (this.getSize() <= that.getSize()) {
            for (int i = 0; i < that.getSize(); i++) {
                minusVector.data[i] = minusVector.data[i] - that.data[i];
            }
        } else {
            for (int i = 0; i < this.getSize(); i++) {
                minusVector.data[i] = this.data[i] - minusVector.data[i];
            }
        }
        return minusVector;
    }

    public static Vector minus(Vector vector1, Vector vector2) {

        Vector minusVector = getNewSize(vector1, vector2);
        if (vector1.getSize() <= vector2.getSize()) {
            for (int i = 0; i < vector2.getSize(); i++) {
                minusVector.data[i] = minusVector.data[i] - vector2.data[i];
            }
        } else {
            for (int i = 0; i < vector1.getSize(); i++) {
                minusVector.data[i] = vector1.data[i] - minusVector.data[i];
            }
        }
        return minusVector;
    }

    public double dot(Vector that) {
        double sum = 0.0;
        Vector minVector = getNewSize(this, that);
        Vector maxVector = getMaxVector(this, that);

        for (int i = 0; i < minVector.getSize(); i++) {
            sum = sum + (minVector.data[i] * maxVector.data[i]);
        }
        return sum;
    }

    public static double dot(Vector vector1, Vector vector2) {
        double sum = 0.0;
        Vector minVector = getNewSize(vector1, vector2);
        Vector maxVector = getMaxVector(vector1, vector2);

        for (int i = 0; i < minVector.getSize(); i++) {
            sum = sum + (minVector.data[i] * maxVector.data[i]);
        }
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public double cartesian(int i) {
        if (i >= getSize()) {
            throw new IllegalArgumentException("Index does't exist");
        }
        return data[i];
    }

    private static Vector getNewSize(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        if (vector1.getSize() < vector2.getSize()) {
            System.arraycopy(vector1.data, 0, newVector.data, 0, vector1.getSize());
        } else if (vector1.getSize() >= vector2.getSize()) {
            System.arraycopy(vector2.data, 0, newVector.data, 0, vector2.getSize());
        }
        return newVector;
    }

    private static Vector getMaxVector(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.getSize()) {
            return vector2;
        } else {
            return vector1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector)) {
            return false;
        }

        Vector vector = (Vector) o;

        return getSize() == vector.getSize() && Arrays.equals(data, vector.data);
    }

    @Override
    public int hashCode() {
        int result = getSize();
        final int prime = 31;
        result = prime * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(data[0]);
        for (int i = 1; i < getSize(); i++)
            s.append(", ").append(data[i]);
        return '{' + s.toString() + '}';
    }
}
