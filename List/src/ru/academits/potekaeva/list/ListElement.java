package ru.academits.potekaeva.list;

public class ListElement<E> {
    ListElement next;
    E data;

    @Override
    public String toString() {
        return " "+data ;
    }
}

