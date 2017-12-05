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
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkList() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }
    }

    //добавить спереди
    public void addFront(E data) {
        ListElement<E> newElement = new ListElement<>(data);
        newElement.setNext(head);
        head = newElement;
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
    public int getSize() {
        return size;
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

    //удаление элемента по индексу +
    public E deleteElementAtIndex(int index) {
        checkList();
        checkPositionIndex(index);

        if (index == 0) {
            return deleteFirst();
        } else {
            ListElement<E> temp = getNodeAtIndex(index - 1);

            E deleteData = temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
            --size;

            return deleteData;
        }
    }

    //замена элемента по индексу +
    public E setElementAtIndex(int index, E data) {
        ListElement<E> temp = getNodeAtIndex(index);
        E old = temp.getData();
        temp.setData(data);

        return old;
    }

    //вставка элемента по индексу +
    public void insertElementAtIndex(int index, E data) {
        if (index == 0) {
            addFront(data);
        } else {
            ListElement<E> temp = getNodeAtIndex(index - 1);
            temp.setNext(new ListElement<>(temp.getNext(), data));
            ++size;
        }
    }

    //  удаление узла по значению +
    public boolean delete(E data) {
        checkList();
        if (Objects.equals(head.getData(), data)) {
            deleteFirst();
            return true;
        } else {
            ListElement<E> temp = head;
            while (temp.getNext() != null) {
                if (Objects.equals(temp.getNext().getData(), data)) {
                    temp.setNext(temp.getNext().getNext());
                    --size;
                    return true;
                }
                temp = temp.getNext();
            }
            return false;
        }
    }

    //удаление узла по после указанного узла +
    public void deleteAfterNode(ListElement<E> node) {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
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
        } else {
            ListElement<E> temp = new ListElement<>(data);
            temp.setNext(node.getNext());
            node.setNext(temp);
            ++size;
        }
    }

    public List<E> copy() {
        if (size == 0) {
            return new List<>();
        }

        List<E> list = new List<>(head.getData());
        ListElement<E> temp = list.getHead();

        for (ListElement<E> p = head.getNext(); p != null; p = p.getNext()) {
            ListElement<E> item = new ListElement<>(p.getData());
            temp.setNext(item);
            temp = item;
        }
        list.size = this.getSize();
        return list;
    }

    public void reverse() {
        if (size == 1 || size == 0) {
            return;
        }
        ListElement<E> p, q;
        for (p = head, q = p.getNext(); q != null; ) {
            ListElement<E> nextNode = q.getNext();
            q.setNext(p);
            p = q;
            q = nextNode;
        }
        head.setNext(null);
        head = p;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        for (ListElement<E> p = head; p != null; p = p.getNext()) {
            s.append(p.getData());
            if (p.getNext() != null) {
                s.append(", ");
            }
        }
        s.append("}");
        return s.toString();
    }
}


