package ru.academits.potekaeva.main;


import ru.academits.potekaeva.shape.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {

    public int compare(Shape obj1, Shape obj2) {

        double area1 = obj1.getArea();
        double area2 = obj2.getArea();

        return Double.compare(area1, area2);
    }
}
