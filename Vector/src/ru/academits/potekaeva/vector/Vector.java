package ru.academits.potekaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] data;

    public void setData(double[] data) {
        this.data = data;
    }

    public double[] getData() {
        return data;
    }

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

        data = Arrays.copyOf(a, n);
    }


    private int getSize() {
        return data.length;
    }

    // Умножение на скаляр, разворот вектора
    public Vector scale(double scalar) {
        Vector newVector = new Vector(getSize());
        for (int i = 0; i < getSize(); i++) {
            newVector.data[i] = scalar * data[i];
        }
        return newVector;
    }

    // Сложение
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

    //Вычитание
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

    //Получение длины вектора
    public double getLength() {
        double sum = 0.0;
        for (int i = 0; i < getSize(); i++) {
            sum = sum + (data[i] * data[i]);
        }
        return Math.sqrt(sum);
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this, this));
    }
//Скалярное произведение векторов

    public static double dot(Vector vector1, Vector vector2) {
        double sum = 0.0;
        if (vector1.getSize() < vector2.getSize()) {
            for (int i = 0; i < vector1.getSize(); i++) {
                sum = sum + (vector1.data[i] * vector2.data[i]);
            }
            sum = sum + Arrays.stream(vector2.data, vector1.getSize(), vector2.getSize()).sum();
            return sum;
        } else {
            for (int i = 0; i < vector2.getSize(); i++) {
                sum = sum + (vector1.data[i] * vector2.data[i]);
            }
            sum = sum + Arrays.stream(vector1.data, vector2.getSize(), vector1.getSize()).sum();
            return sum;
        }
    }

    //Компонента вектора х по индексу
    public void setElement(double element, int i) {
        try {
            data[i] = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Обращение по недопустимому индексу " + i + " массива " + this);
        }
    }

    public Vector getElement() {
        return this;
    }

    private static Vector getNewSize(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        if (vector1.getSize() < vector2.getSize()) {
            newVector.data = Arrays.copyOf(vector1.data, vector2.getSize());

        } else if (vector1.getSize() >= vector2.getSize()) {
            newVector.data = Arrays.copyOf(vector2.data, vector1.getSize());
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
        StringBuilder s = new StringBuilder("{" + data[0]);
        for (int i = 1; i < getSize(); i++)
            s.append(", ").append(data[i]);
        return s.toString() + '}';
    }
}
