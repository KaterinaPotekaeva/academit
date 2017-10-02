package ru.academits.potekaeva.vector;

import java.util.Arrays;

public class Vector {
    private int n;               // мерность вектора
    private double[] data;       // массив компонент вектора

    public Vector(int n) {
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = new double[n];
        }
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.data = vector.data;
    }

    public Vector(double... a) {
        n = a.length;
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = new double[n];
            System.arraycopy(a, 0, data, 0, n);
        }
    }

    public Vector(int n, double... a) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            this.n = n;
            data = new double[n];
            if (n < a.length) {
                System.arraycopy(a, 0, data, 0, n);
            } else {
                System.arraycopy(a, 0, data, 0, a.length);
            }
        }
    }

    public int getSize() {
        return n;
    }

    public Vector scale(double scalar) {
        for (int i = 0; i < n; i++) {
            data[i] = scalar * data[i];
        }
        return this;
    }

    public Vector turnVector() {
        for (int i = 0; i < n; i++) {
            data[i] = -1 * data[i];
        }
        return this;
    }

    public Vector plus(Vector that) {
        Vector plusVector = getNewSize(this, that);
        Vector maxVector = getMaxVector(this, that);

        for (int i = 0; i < plusVector.data.length; i++) {
            plusVector.data[i] = plusVector.data[i] + maxVector.data[i];
        }
        return plusVector;
    }

    public Vector minus(Vector that) {
        Vector minusVector = getNewSize(this, that);

        if (this.n <= that.n) {
            for (int i = 0; i < that.n; i++) {
                minusVector.data[i] = minusVector.data[i] - that.data[i];
            }
        } else {
            for (int i = 0; i < n; i++) {
                minusVector.data[i] = this.data[i] - minusVector.data[i];
            }
        }
        return minusVector;
    }

    public double dot(Vector that) {
        double sum = 0.0;
        Vector minVector = getNewSize(this, that);
        Vector maxVector = getMaxVector(this, that);

        for (int i = 0; i < minVector.data.length; i++) {
            sum = sum + (minVector.data[i] * maxVector.data[i]);
        }
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public double cartesian(int i) {
        if (i >= data.length) {
            throw new IllegalArgumentException("Index does't exist");
        }
        return data[i];
    }

    private Vector getNewSize(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(Math.max(vector1.n, vector2.n));

        if (vector1.n < vector2.n) {
            System.arraycopy(vector1.data, 0, newVector.data, 0, vector1.n);
        } else if (vector1.n >= vector2.n) {
            System.arraycopy(vector2.data, 0, newVector.data, 0, vector2.n);
        }
        return newVector;
    }

    private Vector getMaxVector(Vector vector1, Vector vector2) {
        if (vector1.n < vector2.n) {
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

        if (n != vector.n) {
            return false;
        }
        return Arrays.equals(data, vector.data);
    }

    @Override
    public int hashCode() {
        int result = n;
        final int prime = 31;
        result = prime * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(data[0]);
        for (int i = 1; i < n; i++)
            s.append(", ").append(data[i]);
        return '{' + s.toString() + '}';
    }
}
