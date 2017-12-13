package ru.academits.potekaeva.myArrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] elementData;
    private int modCount;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;


    public MyArrayList() {
        //noinspection unchecked
        elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        //noinspection unchecked
        this.elementData = (E[]) new Object[capacity];
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
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int aLength = a.length;

        if (aLength < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elementData, 0, a, 0, size);

        if (aLength > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        elementData[size] = e;
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elementData[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = elementData[index];
        int moved = size - index - 1;

        if (moved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, moved);
        }
        size--;
        modCount++;
        return oldValue;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);

        for (Object aC : c) {
            if (!contains(aC)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(this.size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Objects.requireNonNull(c);

        Object[] a = c.toArray();
        int collectionLength = a.length;

        if (collectionLength == 0) {
            return false;
        }

        if (index == size) {
            return addAll(c);
        }

        ensureCapacity(size + collectionLength);
        int addedPartLastIndex = index + collectionLength - 1;
        System.arraycopy(elementData, index, elementData, addedPartLastIndex + 1, size - index);
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(a, 0, elementData, index, collectionLength);
        size += collectionLength;
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        int expectedModCount = modCount;

        for (int i = 0; i < size; ++i) {
            if (c.contains(elementData[i])) {
                remove(i);
                --i;
            }
        }
        return expectedModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        int expectedModCount = modCount;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(elementData[i])) {
                remove(i);
                --i;
            }
        }
        return expectedModCount != modCount;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(o, elementData[i])) {
                return i;
            }
        }
        return -1;
    }


    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        int cursor;
        int currentIndex = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }

            Object[] elementData = MyArrayList.this.elementData;

            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            //noinspection unchecked
            return (E) elementData[currentIndex = i];
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new IndexOutOfBoundsException("The method subList isn't defined.");
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;

            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void trimToSize() {
        if (elementData.length > size) {
            elementData = Arrays.copyOf(elementData, size);
            ++modCount;
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}

