package ru.academits.potekaeva.myArrayList;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    private E[] elements;
    private int index;

    public MyArrayList(int size){
        elements = (E[])new Object[size];
    }

   // добавление элемента
    public void add(E i) {
        if (index == getSize()){
            elements = Arrays.copyOf(elements, getSize()+10);
        }
        elements[index] = i;
        index++;
    }

    // размер массива
    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        return "elements=" + Arrays.toString(elements) ;
    }
}

