package ru.academits.potekaeva.main;

import ru.academits.potekaeva.shape.Shape;

import java.util.Comparator;

class Parameter {
      private Shape figure;

      Parameter(Shape figure) {
        this.figure = figure;
    }

    Shape getFigure() {
        return figure;
    }

    public String toString() {
        return "name: " + figure+ " area "+ figure.getArea();
    }  static Comparator<Parameter> PARAMETER_COMPARATOR = new Comparator<Parameter>() {

        @Override
        public int compare(final Parameter o1, final Parameter o2) {
            return Double.valueOf(o1.figure.getArea()).compareTo(o2.figure.getArea());
        }
    };
}

