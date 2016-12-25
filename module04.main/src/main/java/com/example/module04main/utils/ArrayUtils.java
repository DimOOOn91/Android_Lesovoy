package com.example.module04main.utils;

import java.util.Arrays;

public class ArrayUtils {

    public static int[] parseString(String s) throws NumberFormatException {
        int[] result = new int[0];
        String[] strings = s.split(",");
        for (String string : strings) {
            if (string.equals("")) {
                break;
            }
            try {
                int element = Integer.parseInt(string);
                result = addToArray(result, element);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(string);
            }
        }
        return result;
    }

    public static int[] arrayTreatment(int[] arrayA) {
        int[] evenElements = new int[0];
        int[] oddElements = new int[0];
        int[] result = new int[0];

        for (int i = 0; i < arrayA.length; i++) {
            int element = arrayA[i];
            if (element % 2 == 0) {
                evenElements = addToArray(evenElements, element);
            } else {
                oddElements = addToArray(oddElements, element);
            }
        }

        sort(evenElements, true);
        sort(oddElements, false);

        for (int element : evenElements) {
            result = addToArray(result, element);
        }
        for (int element : oddElements) {
            result = addToArray(result, element);
        }

        return result;
    }

    private static int[] addToArray(int[] array, int element) {
        int prevLength = array.length;
        array = Arrays.copyOf(array, prevLength + 1);
        array[prevLength] = element;
        return array;
    }

    private static void sort(int[] array, boolean rise) {
        for (int i = array.length - 1; i >= 1; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i; j++) {
                boolean compare;
                if (rise) {
                    compare = array[j] > array[j + 1];
                } else {
                    compare = array[j] < array[j + 1];
                }
                if (compare) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

        }
    }

    public static int findAverage(int[] array) {
        int maxElement = maxOrMinElement(array, true);
        int minElement = maxOrMinElement(array, false);
        return (minElement + maxElement) / 2;
    }

    public static int maxOrMinElement (int[] array, boolean isMax) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            boolean comparator;
            if (isMax) {
                comparator = result < array[i];
            } else {
                comparator = result > array[i];
            }
            if (comparator) {
                result = array[i];
            }
        }
        return result;
    }
}
