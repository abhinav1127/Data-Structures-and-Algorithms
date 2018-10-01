import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data != null) {
            backingArray = (T[]) (new Comparable[(2 * data.size()) + 1]);
            size = data.size();
            int iterator = 1;
            for (T datum : data) {
                if (datum == null) {
                    throw new IllegalArgumentException("Cannot add null data to"
                            + " the heap.");
                }
                backingArray[iterator] = datum;
                iterator++;
            }
            for (int i = size / 2; i > 0; i--) {
                heapify(i);
            }
        } else {
            throw new IllegalArgumentException("ArrayList cannot be null");
        }

    }

    /**
     * Performs down heap when given an index; sorts and switches items so that
     * each parent is larger than its child
     * @param index We are comparing this index to its children
     */
    private void heapify(int index) {
        if (index * 2 > size) {
            return;
        } else if (((index * 2) + 1) > size) {
            if ((backingArray[index].compareTo(backingArray[index * 2]) < 0)) {
                T switching = backingArray[index];
                backingArray[index] = backingArray[index * 2];
                backingArray[index * 2] = switching;
            }
            return;
        } else if ((backingArray[index].compareTo(backingArray[index * 2]) < 0)
                || (backingArray[index].compareTo(
                        backingArray[(index * 2) + 1]) < 0)) {
            if (backingArray[index * 2].compareTo(
                    backingArray[(index * 2) + 1]) >= 0) {
                T switching = backingArray[index];
                backingArray[index] = backingArray[index * 2];
                backingArray[index * 2] = switching;
                heapify(index * 2);
            } else {
                T switching = backingArray[index];
                backingArray[index] = backingArray[(index * 2) + 1];
                backingArray[(index * 2) + 1] = switching;
                heapify((index * 2) + 1);
            }
        }
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null data"
                    + " to Maxheap.");
        }
        if (size >= backingArray.length - 1) {
            T[] doubleArraySize = (T[])
                    (new Comparable[backingArray.length * 2]);
            for (int i = 1; i <= size; i++) {
                doubleArraySize[i] = backingArray[i];
            }
            backingArray = doubleArraySize;
        }
        backingArray[size + 1] = item;
        size++;
        int checker = size;
        while ((checker / 2) > 0 && backingArray[checker].compareTo(
                backingArray[checker / 2]) > 0) {
            T switching = backingArray[checker];
            backingArray[checker] = backingArray[checker / 2];
            backingArray[checker / 2] = switching;
            checker /= 2;
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("The tree is empty. There "
                    + "is nothing to remove.");
        }
        T dataToReturn = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        int checker = 1;
        while (checker * 2 < size
                && ((backingArray[checker].compareTo(
                        backingArray[checker * 2]) < 0)
                || (backingArray[checker].compareTo(
                        backingArray[(checker * 2) + 1]) < 0))) {

            if (backingArray[checker * 2].compareTo(
                    backingArray[(checker * 2) + 1]) >= 0) {
                T switching = backingArray[checker];
                backingArray[checker] = backingArray[checker * 2];
                backingArray[checker * 2] = switching;
                checker *= 2;
            } else {
                T switching = backingArray[checker];
                backingArray[checker] = backingArray[(checker * 2) + 1];
                backingArray[(checker * 2) + 1] = switching;
                checker  = (checker * 2) + 1;
            }
        }
        if (checker * 2 == size) {
            if ((backingArray[checker].compareTo(
                    backingArray[checker * 2]) < 0)) {
                T switching = backingArray[checker];
                backingArray[checker] = backingArray[checker * 2];
                backingArray[checker * 2] = switching;
            }
        }
        return dataToReturn;
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    //
    public void clear() {
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
