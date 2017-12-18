package ru.academits.potekaeva.main;

import ru.academits.potekaeva.myArrayList.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        MyArrayList<Integer> list2 = new MyArrayList<Integer>();
        for (int i = 8; i > 3; i--) {
            list2.add(i);
        }

        list2.addAll(5, list);

        System.out.println("list " + list);
        System.out.println("list2 " + list2);
    }
}



