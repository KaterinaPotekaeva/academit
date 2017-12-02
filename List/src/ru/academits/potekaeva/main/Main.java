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
        System.out.println("Размер списка " + list.getSizeElements());

        list.deleteElementNextIndex("f");
        System.out.println("удаление  после указанного узла f " + list);

        System.out.println("Первый элемент " + list.getHead());
        System.out.println("Удаление первого элемента" + list.deleteFirst());
        System.out.println(list);
        list.getElementAtIndex(0);
        System.out.println("Получение узла по индексу 0" +  list.getElementAtIndex(0));
        System.out.println(list);
        list.deleteElementData(1);
        System.out.println("Удаление узла по значению 1 " + list);
        list.setElementIndex(1, 0);
        System.out.println("Вставка элемента 0 по индексу 1 "+list);

        list.deleteAfterNode(list.getNodeAtIndex(1));

        System.out.println(list);
        System.out.println("Удалить узел по значению: " + list.delete(2));

        list.insertAfterNode(list.getNodeAtIndex(1), 111);
        list.deleteAfterNode(list.getNodeAtIndex(1));
        System.out.println(list);



    }
}
