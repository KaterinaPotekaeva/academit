package ru.academits.potekaeva.main;

import ru.academits.potekaeva.vector.Vector;

public class Main {

    public static void main(String[] args) {
        double[] vector1 = {-1.4, 2.0, 3.0, 9.9, 8.1};
        double[] vector2 = {5.0, 2.0, 4.0, 1.0};
        Vector x = new Vector(9, vector1);
        Vector y = new Vector(vector2);

        System.out.println("   x       = " + x);
        System.out.println("   y       = " + y);
        System.out.println("   x + y   = " + x.plus(y));
        System.out.println("   x - y   = " + x.minus(y));
        System.out.println("  |x|      = " + x.magnitude());
        System.out.println("  -x       = " + x.turnVector());
        System.out.println("Умножение вектора на скаляр: 10x  = " + x.scale(10.0));
        System.out.println("Скалярное произведение векторов x ,y  = " + x.dot(y));
        System.out.println("Компонента вектора х по индексу 0 = " + x.cartesian(0));
    }

}
