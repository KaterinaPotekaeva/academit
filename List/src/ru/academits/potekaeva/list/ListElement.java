package ru.academits.potekaeva.list;

public class ListElement<E> {
    private ListElement<E> next;
    private E data;

    public ListElement<E> getNext() {
        return next;
    }

    public E getData() {
        return data;
    }

    public void setNext(ListElement<E> next) {
        this.next = next;
    }

    public void setData(E data) {
        this.data = data;
    }

    ListElement() {
    }

    public ListElement(ListElement<E> next, E data) {
        this.next = next;
        this.data = data;
    }

    public ListElement(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return " "+data ;
    }
}

