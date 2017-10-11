package ru.academits.potekaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] data;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = new double[n];
        }
    }

    public Vector(Vector vector) {
        this.data = Arrays.copyOf(vector.data, vector.data.length);
    }

    public Vector(double... a) {
        if (a.length <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = Arrays.copyOf(a, a.length);
        }
    }

    public Vector(int n, double... a) {
        if (a.length <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = Arrays.copyOf(a, n);
        }
    }

    //Компонента вектора х по индексу
    public void setElement(int i, double element) {
        if (i >= getSize() || i < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            data[i] = element;
        }
    }

    public double getElement(int i) {
        if (i >= getSize() || i < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return data[i];
        }
    }

    private int getSize() {
        return data.length;
    }

    // Умножение на скаляр, разворот вектора
    public Vector scale(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            data[i] = scalar * data[i];
        }
        return this;
    }

    // Сложение
    public Vector plus(Vector that) {
        if (getSize() < that.getSize()) {
            data = Arrays.copyOf(data, that.getSize());
        }

        for (int i = 0; i < that.getSize(); i++) {
            this.data[i] += that.data[i];
        }
        return this;
    }

    public static Vector plus(Vector vector1, Vector vector2) {
        return new Vector(vector1).plus(vector2);
    }

    //Вычитание
    public Vector minus(Vector that) {
        if (getSize() < that.getSize()) {
            data = Arrays.copyOf(data, that.getSize());
        }
        for (int i = 0; i < that.getSize(); i++) {
            this.data[i] -= that.data[i];
        }
        return this;
    }

    public static Vector minus(Vector vector1, Vector vector2) {
        return new Vector(vector1).minus(vector2);
    }

    //Получение длины вектора
    // в.1
    public double getLength() {
        double sum = 0.0;

        for (int i = 0; i < getSize(); i++) {
            sum += (data[i] * data[i]);
        }
        return Math.sqrt(sum);
    }

    // в.2
    public double magnitude() {
        return Math.sqrt(dot(this, this));
    }

    //Скалярное произведение векторов
    public static double dot(Vector vector1, Vector vector2) {
        int lengthMix = Math.min(vector1.getSize(), vector2.getSize());
        double sum = 0.0;

        for (int i = 0; i < lengthMix; i++) {
            sum += (vector1.data[i] * vector2.data[i]);
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;

        return Arrays.equals(data, vector.data);
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
        StringBuilder s = new StringBuilder("{");
        s.append(data[0]);
        for (int i = 1; i < getSize(); i++) {
            s.append(", ").append(data[i]);
        }
        s.append("}");
        return s.toString();
    }
}
