package ru.academits.potekaeva.main;

import ru.academits.potekaeva.shape.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Shape figure1 = new Circle(2);
        Shape figure2 = new Rectangle(3.0, 2.0);
        Shape figure3 = new Triangle(8, 5, 0, 0, 15, 0);
        Shape figure4 = new Square(2);
        Shape figure5 = new Square(90);

        Shape area = getAreaMax(figure1, figure2, figure3, figure4, figure5);
        Shape perimeter = getPerimeterSecond(figure3, figure2, figure5, figure4, figure1);

        System.out.println(area + " has the largest area = " + area.getArea());
        System.out.println(perimeter + " has the second largest perimeter = " + perimeter.getPerimeter());
    }

    private static Shape getAreaMax(Shape... figure) {
        Arrays.sort(figure, new AreaComparator());
        return figure[figure.length - 1];
    }

    private static Shape getPerimeterSecond(Shape... figure) {
        Arrays.sort(figure, new PerimeterComparator());
        return figure[figure.length - 2];
    }
}
