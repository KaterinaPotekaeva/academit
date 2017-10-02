package ru.academits.potekaeva.shape;

import java.util.Comparator;

public class Parameter {

    private Shape figure;
    private double parameter;

    public Parameter(Shape figure, double parameter) {
        this.figure = figure;
        this.parameter = parameter;
    }
    public static Comparator<Parameter> PARAMETER_COMPARATOR = new Comparator<Parameter>() {
        @Override
        public int compare(final Parameter o1, final Parameter o2) {
            return Double.valueOf(o1.parameter).compareTo(o2.parameter);
        }
    };
    public Shape getFigure() {
        return figure;
    }

    public String toString() {
        return "[name: " + figure + ", parameter: " + parameter + "]";
    }
}
