import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        boolean switched = true;
        int index1 = arr.length - 1;
        while (index1 > 0 && switched) {
            switched = false;
            int index2 = 0;
            while (index2 < index1) {
                if (comparator.compare(arr[index2], arr[index2 + 1]) > 0) {
                    switchArray(arr, index2, index2 + 1);
                    switched = true;
                }
                index2++;
            }
            index1--;
        }
    }

    /**
     * switches two objects in an array
     * @param arr the array in which we are conducting the switch
     * @param index1 the first index we are switching
     * @param index2 the second index we are switching
     */
    private static void switchArray(Object[] arr, int index1, int index2) {
        Object toSwitch = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = toSwitch;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        int index1 = 0;
        while (index1 < arr.length - 1) {
            int index2 = index1 + 1;
            while (index2 > 0
                && comparator.compare(arr[index2], arr[index2 - 1]) < 0) {
                switchArray(arr, index2, index2 - 1);
                index2--;
            }
            index1++;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        int index1 = 0;
        while (index1 < arr.length) {
            int index2 = index1 + 1;
            int index3 = index1;
            while (index2 < arr.length) {
                if (comparator.compare(arr[index2], arr[index3]) < 0) {
                    index3 = index2;
                }
                index2++;
            }
            switchArray(arr, index1, index3);
            index1++;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else if (rand == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }

        if (!(arr.length == 0)) {
            quickSortHelper(arr, comparator, rand, 0, arr.length);
        }
    }

    /**
     * Recursive call for quicksort
     * @param arr the array we are sorting
     * @param comparator what we will use to compare our T's to one another
     * @param rand the rand that will help us generate our random pivot
     * @param left the left side of the array we are examining, inclusive
     * @param right the right side of the array we are examining, exclusive
     * @param <T> the generic being passed in
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator,
                                 Random rand, int left, int right) {

        if (right == left) {
            return;
        } else if (right - left == 1) {
            return;
        }

        int pivotIndex = rand.nextInt(right - left) + left;
        T pivot = arr[pivotIndex];
        switchArray(arr, left, pivotIndex);
        int leftIndex = left + 1;
        int rightIndex = right - 1;

        while (leftIndex <= rightIndex) {
            while (leftIndex <= rightIndex
                    && comparator.compare(pivot, arr[leftIndex]) >= 0) {
                leftIndex++;
            }
            while (leftIndex <= rightIndex
                    && comparator.compare(pivot, arr[rightIndex]) <= 0) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                switchArray(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }

        switchArray(arr, left, rightIndex);
        quickSortHelper(arr, comparator, rand, left, rightIndex);
        quickSortHelper(arr, comparator, rand, rightIndex + 1, right);

    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        if (arr.length == 0) {
            return;
        }
        mergeSortHelper(arr, comparator);
    }

    /**
     * Recursive call for merge sort
     * @param arr the array we are sorting
     * @param comparator what we will use to compare our T's to one another
     * @param <T> the generic being passed in
     */
    private static <T> void mergeSortHelper(T[] arr, Comparator<T> comparator) {
        if (arr.length != 1) {
            T[] arr1 = (T[]) new Object[arr.length / 2];
            T[] arr2 = (T[]) new Object[arr.length - (arr.length / 2)];

            for (int i = 0; i < (arr.length / 2); i++) {
                arr1[i] = arr[i];
            }
            for (int i = arr.length / 2; i < arr.length; i++) {
                arr2[i - arr1.length] = arr[i];
            }

            mergeSortHelper(arr1, comparator);
            mergeSortHelper(arr2, comparator);

            int counter1 = 0;
            int counter2 = 0;
            int counterarr = 0;

            while ((counter1 != arr1.length)
                    && counter2 != arr2.length) {

                if (comparator.compare(arr1[counter1], arr2[counter2]) <= 0) {
                    arr[counterarr] = arr1[counter1];
                    counter1++;
                    if (counter1 == arr1.length) {
                        for (int i = counter2; i < arr2.length; i++) {
                            counterarr++;
                            arr[counterarr] = arr2[i];
                        }
                    }
                } else {
                    arr[counterarr] = arr2[counter2];
                    counter2++;
                    if (counter2 == arr2.length) {
                        for (int i = counter1; i < arr1.length; i++) {
                            counterarr++;
                            arr[counterarr] = arr1[i];
                        }
                    }
                }
                counterarr++;
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        if (arr.length == 0) {
            return;
        }

        Queue<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int mostDigits = 1;

        for (int i = 0; i < arr.length; i++) {
            int counter = 1;
            int div10 = arr[i] / 10;
            while (div10 != 0) {
                counter++;
                div10 /= 10;
            }
            if (counter > mostDigits) {
                mostDigits = counter;
            }
        }

        int divideBy = 1;
        for (int i = 0; i < mostDigits; i++) {
            for (int j = 0; j < arr.length; j++) {
                int divided = arr[j] / divideBy;
                int lastDigit = divided % 10;
                buckets[lastDigit + 9].add(arr[j]);
            }

            int placing = 0;
            for (int j = 0; j < buckets.length; j++) {
                while (!buckets[j].isEmpty()) {
                    arr[placing] = buckets[j].remove();
                    placing++;
                }
            }

            divideBy *= 10;
        }
    }
}
