package ru.academits.potekaeva.main;

import ru.academits.potekaeva.vector.Vector;

public class Main {

    public static void main(String[] args) {
        double[] vector1 = { 1.0, 2.0 };
        double[] vector2 = { 5.0, 2.0, 4.0, 1.0 };
        Vector x = new Vector(vector1);
        Vector y = new Vector(vector2);

        System.out.println("   x       = " + x);
        System.out.println("   y       = " + y);
 //Прибавление к вектору другого вектора
        Vector plus = x.plus(y);
        System.out.println("   x + y   = " + plus);

        Vector minus=x.minus(y);
        System.out.println("   x - y   = " + minus);
// Умножение вектора на скаляр
        x = x.scale(10.0);
        System.out.println("Умножение вектора на скаляр: 10x = " +  x);

        System.out.println("  |x|      = " + x.magnitude());
        System.out.println(" <x, y>    = " + x.dot(y));
        System.out.println("dist(x, y) = " + x.distanceTo(y));
       // System.out.println("dir(x)     = " + x.direction());

    }

}
