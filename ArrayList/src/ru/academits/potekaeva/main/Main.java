package ru.academits.potekaeva.main;

import ru.academits.potekaeva.myArrayList.MyArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>(3);
        for (int i = 0; i < 5; i++){
            list.add(i);
        }
        MyArrayList<String> list2 = new MyArrayList<String>(1);
        list2.add(".");
        System.out.println(list);
        System.out.println(list2);
      }
}



