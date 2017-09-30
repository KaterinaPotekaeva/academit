package ru.academits.potekaeva.range;

public class Range {
    private double start;
    private double end;

    public Range(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getStart() {
        return start;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public double getEnd() {
        return end;
    }

    public boolean isInside(double someNumber) {
        return (someNumber >= start && someNumber <= end);
    }

    private double getLength() {
        return end - start;
    }

    public void print() {
        System.out.println("Интервал = " + getLength());
    }

    public Range getCrossing(Range rangeSecond) {
        if (start < rangeSecond.end && end > rangeSecond.start) {
            return new Range(Math.max(start, rangeSecond.start), Math.min(rangeSecond.end, end));
        }
        return null;
    }

    public Range[] getUnion(Range rangeSecond) {
        if (start <= rangeSecond.end && end >= rangeSecond.start) {
            return new Range[]{new Range(Math.min(start, rangeSecond.start), Math.max(end, rangeSecond.end))};
        } else {
            return new Range[]{new Range(start, end), new Range(rangeSecond.start, rangeSecond.end)};
        }
    }

    public Range[] getSubtraction(Range rangeSecond) {
        if (start < rangeSecond.start && rangeSecond.end >= end) {
            return new Range[]{new Range(start, Math.min(end, rangeSecond.start))};
        } else if (start >= rangeSecond.start && rangeSecond.end < end) {
            return new Range[]{new Range(Math.max(rangeSecond.end, start), end)};
        } else if (start < rangeSecond.start && rangeSecond.end < end) {
            return new Range[]{new Range(start, rangeSecond.start), new Range(rangeSecond.end, end)};
        } else {
            return new Range[]{};
        }
    }
}
