package ru.academits.potekaeva.main;

import ru.academits.potekaeva.list.List;

public class Main {

    public static void main(String[] args) {
        List list = new List(1);
        list.addFront(1);
        list.addFront(6);
        list.addFront("f");
        list.addFront(1.3);
        list.addFront(0);
        list.addFront("f");
        list.addFront(5);

        System.out.println(list);

        System.out.println("Первый узел =" + list.getHead());
        System.out.println("Удален первый узел = " + list.deleteFirst()+ " "+list);
        System.out.println("Получение узла по индексу 0 = " + list.getNodeAtIndex(3));
        System.out.println("Удаление элемента по индексу 0 " + list.deleteElementIndex(0));
        list.setElementIndex(0, 55);
        System.out.println("Вставка элемента по индексу 0 " + list);
        list.deleteNodeAtData("f");
        System.out.println("Удалить узел по значению: f " + list);
        list.deleteAfterNode(list.getNodeAtIndex(1));
        System.out.println("Удаление  после указанного узла 1 " + list);
        list.insertAfterNode(list.getNodeAtIndex(1), "f");
        System.out.println("Вставка  после указанного узла " + list);
        System.out.println("Размер списка " + list.getSizeElements());
    }
}
