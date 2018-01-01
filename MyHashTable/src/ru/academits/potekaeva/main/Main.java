package ru.academits.potekaeva.main;

import ru.academits.potekaeva.myHashTable.MyHashTable;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        MyHashTable<Integer> hashTable = new MyHashTable<>();
        hashTable.add(0);
        hashTable.add(100);
        hashTable.add(200);
        hashTable.add(300);
        hashTable.add(17);
        hashTable.add(9);
        MyHashTable<Integer> hashTable1 = new MyHashTable<>();
        hashTable1.add(3);
        hashTable1.addAll(hashTable);
        System.out.println(hashTable);

    }
}