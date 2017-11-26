package ru.academits.potekaeva.list;

public class List<E> {
    private ListElement head;
    private ListElement tail;
    private int size;


    // получение первого узла
    public ListElement getFirstElement() {
        return head;
    }

    // удаление первого
    public ListElement deleteFirst() {
        ListElement temp = head;

        if (head == null) {
            System.out.println("Список пуст!");
        } else {
            head = head.next;
        }
        return temp;
    }

    //размер списка
    public int getSizeElements() {
        int count = 0;
        ListElement temp = head;

        if (temp != null) {
            count++;
            while (temp.next != null) {
                count++;
                temp = temp.next;
            }
        } else {
            return size = 0;
        }
        return size = count;
    }

    //добавить спереди
    public void addFront(E data) {
        ListElement<E> newElement = new ListElement<>();
        newElement.data = data;

        if (head == null) {
            head = newElement;
            tail = newElement;
        } else {
            newElement.next = head;
            head = newElement;
        }
    }

    //  удаление узла по значению +
    public void deleteElementData(E data) {
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }

        ListElement temp = head;
        while (temp.next != null) {

            if (temp.next.data.equals(data)) {

                if (tail == temp.next) {
                    tail = temp;
                }
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    //получение узла по индексу
    public ListElement getElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }

        ListElement temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //вставка элемента по индексу
    public void setElementIndex(E data, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }

        ListElement<E> newElement = new ListElement<>();
        newElement.data = data;
        ListElement temp = head;

        if (index == 0) {
            newElement.next = head;
            head = newElement;
            return;
        }

        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        newElement.next = temp.next;
        temp.next = newElement;

    }

    //удаление  после указанного узла
    public void deleteElementNextIndex(E data) {
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        if (head.data.equals(data)) {
            head.next = head.next.next;
            return;
        }

        ListElement temp = head;

        while (temp.next != null) {

            if (temp.next.data.equals(data)) {

                if (tail.next == temp.next.next) {
                    tail.next = temp.next;
                }
                temp.next.next = temp.next.next.next;
            }
            temp = temp.next;
        }

    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        ListElement t = head;
        while (t != null) {
            s.append(t.data).append(" ");
            t = t.next;
        }
        s.append("}");
        return s.toString();
    }
}