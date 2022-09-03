package org.jhexcast;

import java.util.List;

public class Hexutils {

    public static void dumpIntarray(int[] elements) {
        Integer[] javapita = new Integer[elements.length];
        for (int i = 0; i < elements.length; i++) {
            javapita[i] = elements[i];
        }
        dumpArray(javapita);
    }

    public static <T> void dumpArray(T[] elements) {
        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
    }

    public static boolean areEqual(int[] arr1, int[] arr2) {
        if(arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {

            }
        }
        return true;
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

}
