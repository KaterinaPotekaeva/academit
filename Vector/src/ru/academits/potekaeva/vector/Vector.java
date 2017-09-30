package ru.academits.potekaeva.vector;

import java.lang.reflect.Array;
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
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException("Size does't exist");
        } else {
            data = new double[n];
            System.arraycopy(a, 0, data, 0, n);
        }
    }

    // узнать размерность
    public int getSize() {
        return n;
    }

    // Умножение на скаляр
    public Vector scale(double scalar) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++) {
            c.data[i] = scalar * data[i];
        }
        return c;
    }

    // Прибавление к вектору другого вектора
    public Vector plus(Vector that) {
        Vector c = new Vector(Math.max(n, that.n));
        Vector newVector = new Vector(Math.max(n, that.n));
        if (n < that.n) {

            System.out.println(Arrays.toString(data));
            for (int i = 0; i < that.n; i++) {
                c.data[i] = data[i] + that.data[i];
            }
        }
        return c;
    }

    public Vector minus(Vector that) {
        Vector c = new Vector(n);
        for (int i = 0; i < n; i++) {
            c.data[i] = this.data[i] - that.data[i];
        }
        return c;
    }

    public double dot(Vector that) {
        double sum = 0.0;
        for (int i = 0; i < n; i++)
            sum = sum + (this.data[i] * that.data[i]);
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public double distanceTo(Vector that) {
        if (this.n != that.n) throw new IllegalArgumentException("Dimensions don't agree");
        return this.minus(that).magnitude();
    }


    public double cartesian(int i) {
        return data[i];
    }

  /*  public Vector direction() {
        if (this.magnitude() == 0.0) throw new ArithmeticException("Zero-vector has no direction");
        return this.scale(1.0 / this.magnitude());
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector = (Vector) o;

        if (n != vector.n) return false;
        return Arrays.equals(data, vector.data);
    }

    @Override
    public int hashCode() {
        int result = n;
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(data) + '}';
    }
}
