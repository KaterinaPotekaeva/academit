package ru.academits.potekaeva.main;

import ru.academits.potekaeva.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(7.7, 55);
        range.print();
        System.out.println(range.isInside(9));

        Range rangeFirst = new Range(1, 3);
        Range rangeSecond = new Range(2, 7);

        Range rangeCrossing = rangeFirst.getCrossing(rangeSecond);
        if (rangeCrossing != null) {
            System.out.println("Пересечение: " + rangeCrossing.getStart() + ", " + rangeCrossing.getEnd());
        } else {
            System.out.println("Пересечений нет");
        }

        Range[] rangeUnion = rangeFirst.getUnion(rangeSecond);
        for (Range aRangeUnion : rangeUnion) {
            System.out.println("Объединение: " + aRangeUnion.getStart() + ", " + aRangeUnion.getEnd());
        }

        Range[] rangeSubtraction = rangeFirst.getSubtraction(rangeSecond);
        for (Range aRangeSubtraction : rangeSubtraction) {
            System.out.println("Разность: " + aRangeSubtraction.getStart() + ", " + aRangeSubtraction.getEnd());
        }
    }
}