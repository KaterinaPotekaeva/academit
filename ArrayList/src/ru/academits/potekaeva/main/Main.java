package ru.academits.potekaeva.main;

import ru.academits.potekaeva.myArrayList.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.add(1, 6);
        list.remove(1);

        MyArrayList<Integer> list2 = new MyArrayList<Integer>();
        for (int i = 0; i < 15; i++) {
            list2.add(i);
        }


        list2.remove(list);

        System.out.println(list);
        System.out.println(list2);
    }
}



