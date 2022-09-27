package org.jhexcast.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.UnaryOperator;

public class Hexutils {

    public static void dumpIntarray(int[] elements) {
        Integer[] javapita = new Integer[elements.length];
        for (int i = 0; i < elements.length; i++) {
            javapita[i] = elements[i];
        }
        dumpArray(javapita);
    }

    public static void dumpArray(int[] elements) {
        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
    }


    public static <T> void dumpArray(T[] elements) {
        String op = "";
        for (T element : elements) {
            op = element + " ";
            System.out.print(op);
        }
    }

    public static <T, V> void dumpMap(Map<T, V> map) {
        String curelm = "";
        List<String> elms = new ArrayList<>();
        for (Map.Entry<T, V> entry : map.entrySet()) {
            curelm = String.format("%s: %s", entry.getKey(), entry.getValue());
            elms.add(curelm);
        }
        String[] outputElms = elms.toArray(new String[0]);
        String output = String.join(", ", outputElms);
        System.out.println("{ " + output + " }");
    }


    public static boolean boolFromString(String item) {
        return item.toLowerCase().trim().equals("true");
    }

    public static String joinedString(int[] array) {
        return String.join("", toStringArray(array));
    }

    public static String[] toStringArray(int[] array) {
        String[] sarr = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            sarr[i] = Integer.toString(array[i]);
        }
        return sarr;
    }

    public static boolean fileExists(String filePath) {
        File f = new File(filePath);
        return f.exists();
    }

    public static int[] toHexArray(char[] characters, UnaryOperator<Integer> func) {
        int[] hexints = new int[characters.length];
        for (int i = 0; i < characters.length; i++) {
            hexints[i] = Character.digit(characters[i], 16);
            if (func != null) {
                hexints[i] = func.apply(hexints[i]);
            }
        }
        return hexints;
    }

    public static List<String> getWindows(String[] arr, int windowSize) {

        ArrayList<String> windows = new ArrayList<>();
        if(arr.length <= windowSize) {
            windows.add(String.join("",arr));
            return windows;
        }

        for (int b = 0; b <= arr.length - windowSize; b++) {
            int e = b + windowSize;
            String[] window = Arrays.copyOfRange(arr, b, e);
            windows.add(String.join("", window));
        }

        return windows;
    }

}