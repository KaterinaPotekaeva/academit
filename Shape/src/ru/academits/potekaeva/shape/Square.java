package ru.academits.potekaeva.shape;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Square square = (Square) obj;

        if (width != square.getWidth()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                '}';
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return width;
    }

    public double getArea() {
        return Math.pow(width, 2);
    }

    public double getPerimeter() {
        return 4 * width;
    }
}