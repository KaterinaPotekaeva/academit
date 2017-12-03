package ru.academits.potekaeva.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class List<E> {
    private ListElement<E> head;
    private int size;

    public List() {
        head = null;
        size = 0;
    }

    public List(E data) {
        head = new ListElement<>(data);
        size = 1;
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || size < index)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void checkList() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }
    }

    //добавить спереди
    public void addFront(E data) {
        ListElement<E> newElement = new ListElement<>();
        newElement.setData(data);

        if (head == null) {
            head = newElement;
        } else {
            newElement.setNext(head);
            head = newElement;
        }
        ++size;
    }

    // получение первого узла +
    public ListElement<E> getHead() {
        return head;
    }

    // удаление первого +
    public E deleteFirst() {
        checkList();

        E temp = head.getData();
        head = head.getNext();
        --size;

        return temp;
    }

    //размер списка +
    public int getSizeElements() {
        int count = 0;
        ListElement temp = head;

        if (temp != null) {
            count++;
            while (temp.getNext() != null) {
                count++;
                temp = temp.getNext();
            }
        } else {
            return 0;
        }
        return count;
    }

    // получение узла по индексу +
    public ListElement<E> getNodeAtIndex(int index) {
        checkPositionIndex(index);

        ListElement<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    //удаление 'элемента по индексу +
    public E deleteElementIndex(int index) {
        checkList();
        checkPositionIndex(index);

        if (index == 0) {
            --size;
            return deleteFirst();
        } else {
            ListElement<E> temp = getNodeAtIndex(index - 1);
            E deleteData = temp.getNext().getData();

            temp.setNext(temp.getNext().getNext());
            --size;
            
            return deleteData;
        }
    }

    //вставка элемента по индексу +
    public void setElementIndex(int index, E data) {
        ListElement<E> temp = getNodeAtIndex(index);
        temp.setNext(new ListElement<>(temp.getNext(), data));
        ++size;
    }

    //  удаление узла по значению +
    public void deleteNodeAtData(E data) {
        checkList();

        if (head.getData().equals(data)) {
            deleteFirst();
            return;
        }

        ListElement<E> temp = head;
        while (temp.getNext() != null) {

            if (temp.getNext().getData().equals(data)) {

                temp.setNext(temp.getNext().getNext());
                return;
            }
            temp = temp.getNext();
            --size;
        }
    }

    //удаление узла по после указанного узла +
    public void deleteAfterNode(ListElement<E> node) {
        if (size == 0) {
            return;
        }
        if (node.getNext() == null) {
            throw new NoSuchElementException("No node after the specified");
        }
        node.setNext(node.getNext().getNext());
        --size;
    }

    //  вставка  узла после указанного узла +
    public void insertAfterNode(ListElement<E> node, E data) {
        if (node == null) {
            addFront(data);
        } else if (node.getNext() == null) {
            ListElement<E> temp = new ListElement<>(data);
            node.setNext(temp);
        } else {
            ListElement<E> temp = new ListElement<>(data);
            temp.setNext(node.getNext());
            node.setNext(temp);
        }
        ++size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        ListElement t = head;

        while (t != null) {
            s.append(t.getData()).append(" ");
            t = t.getNext();
        }
        s.append("}");
        return s.toString();
    }
}