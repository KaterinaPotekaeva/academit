package ru.academits.potekaeva.list;

import java.util.Objects;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
        head = null;
        length = 0;
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        length = 1;
    }

    public int getLength() {
        return length;
    }

    public ListItem<T> getHead() {
        return head;
    }

    public T getDataAtIndex(int index) {
        return getNodeAtIndex(index).getData();
    }

    public T setDataAtIndex(int index, T data) {
        ListItem<T> p = getNodeAtIndex(index);
        T old = p.getData();
        p.setData(data);
        return old;
    }

    public ListItem<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= getLength()) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }
        int i = 0;
        ListItem<T> p;
        for (p = head; p != null; p = p.getNext(), ++i) {
            if (i == index) {
                break;
            }
        }
        return p;
    }

    public void insertToBegin(T data) {
        ListItem<T> p = new ListItem<>(data);
        p.setNext(head);
        head = p;
        ++length;
    }

    public void insertToEnd(T data) {
        if (length == 0) {
            insertToBegin(data);
            return;
        }
        ListItem<T> p = getNodeAtIndex(getLength() - 1);
        ListItem<T> q = new ListItem<>(data);
        p.setNext(q);
        ++length;
    }

    public void insert(int index, T data) {
        if (index == 0) {
            insertToBegin(data);
            return;
        }
        if (index == getLength()) {
            insertToEnd(data);
            return;
        }
        if (index < 0 || index > getLength()) {
            throw new IndexOutOfBoundsException("Элемента с таким индексом нет");
        }
        ListItem<T> p = getNodeAtIndex(index - 1);
        ListItem<T> q = new ListItem<>(data);
        q.setNext(p.getNext());
        p.setNext(q);
        ++length;
    }

    public void insertAfterNode(ListItem<T> node, T data) {
        if (node == null) {
            insertToBegin(data);
        } else if (node.getNext() == null) {
            ListItem<T> p = new ListItem<>(data);
            node.setNext(p);
        } else {
            ListItem<T> p = new ListItem<>(data);
            p.setNext(node.getNext());
            node.setNext(p);
        }
        ++length;
    }

    public T deleteHead() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }
        T headData = head.getData();
        head = head.getNext();
        --length;
        return headData;
    }

    public T deleteAtIndex(int index) {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }
        if (index == 0) {
            return deleteHead();
        }
        if (index < 0 || index >= getLength()) {
            throw new NoSuchElementException("Элемента с таким индексом нет");
        }
        ListItem<T> p = getNodeAtIndex(index - 1);
        T deleteData = p.getNext().getData();
        ListItem<T> q = p.getNext();
        p.setNext(q.getNext());
        --length;
        return deleteData;
    }

    public boolean delete(T data) {
        ListItem<T> p;
        if (length == 0) {
            return false;
        }
        if (Objects.equals(head.getData(), data)) {
            deleteHead();
            return true;
        }
        for (p = head; p.getNext() != null; p = p.getNext()) {
            if (Objects.equals(p.getNext().getData(), data)) {
                p.setNext(p.getNext().getNext());
                --length;
                return true;
            }
        }
        return false;
    }

    public void deleteAfterNode(ListItem<T> node) {
        if (length == 0) {
            return;
        }
        if (node.getNext() == null) {
            throw new NoSuchElementException("Нет элемента, после указанного");
        }
        node.setNext(node.getNext().getNext());
        --length;
    }

    public void reverse() {
        if (getLength() == 1) {
            return;
        }
        ListItem<T> p, q;
        for (p = head, q = p.getNext(); q != null; ) {
            ListItem<T> nextNode = q.getNext();
            q.setNext(p);
            p = q;
            q = nextNode;
        }
        head.setNext(null);
        head = p;
    }

    public SinglyLinkedList<T> copy() {
        if (length == 0) {
            return new SinglyLinkedList<>();
        }
        SinglyLinkedList<T> list = new SinglyLinkedList<>(head.getData());
        ListItem<T> currentItem = list.getHead();
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> item = new ListItem<>(p.getData());
            currentItem.setNext(item);
            currentItem = item;
        }
        list.length = this.getLength();
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            sb.append(p.getData());
            if (p.getNext() != null) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
