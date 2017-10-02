package ru.academits.potekaeva.main;

import ru.academits.potekaeva.shape.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {

    public int compare(Shape obj1, Shape obj2) {

        double perimeter1 = obj1.getPerimeter();
        double perimeter2 = obj2.getPerimeter();

        return Double.compare(perimeter1, perimeter2);
    }
}
