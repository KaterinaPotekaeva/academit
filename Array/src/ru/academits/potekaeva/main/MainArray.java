package ru.academits.potekaeva.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainArray {


    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list1 = new ArrayList<String>(5);
        ArrayList<Integer> numberInt = new ArrayList<>(Arrays.asList(5, 4, 2, 10, 2, 8, 6, 1, 3, 3, 7, 7, 4));

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list1.add(s);
            }
        }
        int i = 0;
        while (i < numberInt.size()) {
            if (numberInt.get(i) % 2 == 0) {
                numberInt.remove(i);
            } else {
                i++;
            }
        }

        ArrayList<Integer> numberWithoutRepetition = new ArrayList<>();
        for (Integer e : numberInt) {
            if (!numberWithoutRepetition.contains(e)) {
                numberWithoutRepetition.add(e);
            }
        }

        System.out.println(list1);
        System.out.println(numberInt);
        System.out.println(numberWithoutRepetition);
    }
}
