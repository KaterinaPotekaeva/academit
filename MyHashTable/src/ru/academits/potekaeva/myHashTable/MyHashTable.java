package ru.academits.potekaeva.myHashTable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private ArrayList<T>[] list;
    private int size;
    private int modCount;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int capacity) {
        //noinspection unchecked
        list = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            list[i] = new ArrayList<>();
        }
    }

    private int getIndex(Object o) {
        return Math.abs(Objects.hashCode(o) % list.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return contains(o, getIndex(o));
    }

    private boolean contains(Object o, int index) {
        //noinspection SuspiciousMethodCalls
        return list[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex;
        private int cursor;
        private Iterator<T> iterator = list[0].iterator();
        private int expectedModCount = modCount;
        private T element;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!iterator.hasNext()) {
                currentIndex++;
            } else {
                element = iterator.next();
                cursor++;
                return element;
            }

            for (int i = currentIndex; i < list.length; i++) {
                iterator = list[i].iterator();
                if (iterator.hasNext()) {
                    element = iterator.next();
                    cursor++;
                    return element;
                }
                currentIndex++;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (element == null) {
                throw new IllegalStateException();
            }
            element = null;
            iterator.remove();
            expectedModCount++;
            modCount++;
            cursor--;
            size--;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (ArrayList<T> list : list) {
            for (T t : list) {
                array[i] = t;
                i++;
            }
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Objects.requireNonNull(a);

        int aLength = a.length;
        if (aLength < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);
        if (aLength > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);
        return !contains(t, index) && add(t, index);
    }

    private boolean add(T t, int index) {
        if (list[index].add(t)) {
            modCount++;
            size++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Objects.requireNonNull(c);
        int expectedModCount = modCount;

        for (T listElement : c) {
            add(listElement);
        }
        return expectedModCount != modCount;
    }

    @Override
    public boolean remove(Object o) {
        return remove(o, getIndex(o));
    }

    private boolean remove(Object o, int index) {
        //noinspection SuspiciousMethodCalls
        if (list[index].remove(o)) {
            modCount++;
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);

        int expectedModCount = modCount;
        for (Object aC : c) {
            remove(aC);
        }
        return expectedModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);

        int expectedSize = size;
        removeIf(next -> !c.contains(next));
        return expectedSize != size;
    }

    @Override
    public void clear() {
        modCount++;

        for (ArrayList<T> aList : list) {
            aList.clear();
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < list.length; i++) {
            stringBuilder.append("{");
            int size = list[i].size();
            int j = 0;

            for (T t : list[i]) {
                stringBuilder.append(t);
                j++;
                if (j < size) {
                    stringBuilder.append(", ");
                }
            }

            stringBuilder.append("}");
            if (i == list.length - 1) {
                stringBuilder.append("]");
            } else {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}


