package ru.academits.potekaeva.main;

import ru.academits.potekaeva.vector.Vector;

import static ru.academits.potekaeva.vector.Vector.minus;
import static ru.academits.potekaeva.vector.Vector.plus;
import static ru.academits.potekaeva.vector.Vector.dot;

public class Main {

    public static void main(String[] args) {

        double[] vector1 = {1, 1, 1};
        double[] vector2 = {1, 1, 1};

        Vector x = new Vector(vector1);
        Vector y = new Vector(vector2);

        System.out.println("   x       = " + x);
        System.out.println("   y       = " + y);
        System.out.println("   x + y   = " + plus(x, y));
        System.out.println("   x + y   = " + x.plus(y));

        System.out.println("   x       = " + x);
        System.out.println("   y       = " + y);
        System.out.println("   x - y   = " + x.minus(y));
        System.out.println("   x - y   = " + minus(x, y));

        System.out.println("  |x|      = " + x.getLength());
        System.out.println("  |x|      = " + x.magnitude());

        System.out.println("  -x       = " + x.scale(-1));

        System.out.println("  <x,y>       = " + dot(x, y));
        System.out.println("Умножение вектора на скаляр: 10x  = " + x.scale(10.0));

        x.setElement(6, 11);
        System.out.println("   x       = " + x.getElement());
    }
}
