package ru.academits.potekaeva.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Integer> numberInt = new ArrayList<>(Arrays.asList(5, 4, 2, 10, 2, 8, 6, 1, 3, 3, 7, 7, 4));

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list.add(s);
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

        i = 0;
        ArrayList<Integer> numberWithoutRepetition = new ArrayList<>(numberInt);
        while (i < numberWithoutRepetition.size()) {
            for (int j = i + 1; j < numberWithoutRepetition.size(); j++) {
                if (Objects.equals(numberWithoutRepetition.get(i), numberWithoutRepetition.get(j))) {
                    numberWithoutRepetition.remove(j);
                }
            }
            i++;
        }

        System.out.println(list);
        System.out.println(numberInt);
        System.out.println(numberWithoutRepetition);
    }
}



