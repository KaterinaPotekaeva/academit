package ru.academits.potekaeva.main;
import ru.academits.potekaeva.list.List;
public class Main {

    public static void main(String[] args) {
        List ml = new List();
        ml.addFront(1);
        ml.addFront(6);
        ml.addFront("f");
        ml.addFront(1.3);
        ml.addFront(0);
        ml.addFront("f");

        System.out.println(ml);
        System.out.println("Размер списка " + ml.getSizeElements());

        System.out.println(ml);

        ml.deleteElementNextIndex(9);
        System.out.println(ml);
        System.out.println("Первый элемент " + ml.getFirstElement());
        System.out.println("Удаление первого элемента" + ml.deleteFirst());
        System.out.println(ml);
        System.out.println("Получение узла по индексу " + ml.getElementIndex(1));
        ml.deleteElementData(1);
        System.out.println(ml);
        ml.setElementIndex(44, 0);
        System.out.println(ml);

    }
}
