package ru.academits.potekaeva.main;

import ru.academits.potekaeva.list.List;

public class Main {

    public static void main(String[] args) {
        List list = new List(1);
        list.addFront(1);
        list.addFront(6);
        list.addFront("f");
        list.addFront(1.3);
        list.addFront(null);
        list.addFront("f");
        list.addFront(null);

        System.out.println(list);
        System.out.println("Размер списка " + list.getSize());
        System.out.println("Первый узел =" + list.getHead());
        System.out.println("Размер списка " + list.getSize());
        System.out.println("Удален первый узел = " + list.deleteFirst()+ " "+list);
        System.out.println("Размер списка " + list.getSize());
        System.out.println("Получение узла по индексу = " + list.getNodeAtIndex(0));
        System.out.println("Размер списка " + list.getSize());
        System.out.println("Удаление элемента по индексу =" + list.deleteElementAtIndex(0)+ " "+list);
        System.out.println("Размер списка " + list.getSize());
        list.setElementAtIndex(2, null);
        System.out.println("Замена элемента по индексу " + list);
        System.out.println("Размер списка " + list.getSize());
        list.deleteNodeAtData(null);
        System.out.println("Удалить узел по значению: " + list);
        System.out.println("Размер списка " + list.getSize());
        list.deleteAfterNode(list.getNodeAtIndex(1));
        System.out.println("Удаление  после указанного узла 1 " + list);
        System.out.println("Размер списка " + list.getSize());
        list.insertAfterNode(list.getNodeAtIndex(1), "f");
        System.out.println("Вставка  после указанного узла " + list);
        System.out.println("Размер списка " + list.getSize());
        System.out.println("Копирование списка "+ list.copy());
        list.reverse();
        System.out.println("Разворот списка "+list);

    }

}
