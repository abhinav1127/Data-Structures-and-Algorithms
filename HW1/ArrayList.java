/**
 * Your implementation of an ArrayList.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure.");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Provided index "
                        + "is negative. "
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Provided index "
                        + "is too large."
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        }
        doubleArraySizeIfNeeded();
        for (int i = (size - 1); i >= index; i--) {
            backingArray[i + 1] = backingArray[i];
        }
        backingArray[index] = data;
        size++;

    }

    @Override
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure.");
        }
        doubleArraySizeIfNeeded();
        addAtIndex(0, data);

    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure.");
        }
        doubleArraySizeIfNeeded();
        backingArray[size] = data;
        size++;
    }

    /**
     * If the backingArray does not have enough room to add elements,
     * this method will double the arrays size
     */
    public void doubleArraySizeIfNeeded() {
        if (size == backingArray.length) {
            T[] doubledArray = (T[]) (new Object[size * 2]);
            for (int i = 0; i < size; i++) {
                doubledArray[i] = backingArray[i];
            }
            backingArray = doubledArray;
        }
    }

    @Override
    public T removeAtIndex(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("Provided index "
                        + "is negative. "
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Provided index "
                        + "is too large."
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        }
        T toReturn = backingArray[index];
        for (int i = index + 1; i < size; i++) {
            backingArray[i - 1] = backingArray[i];
        }
        backingArray[size - 1] = null;
        size--;
        return toReturn;

    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else {
            T toReturn = backingArray[0];
            for (int i = 1; i < size; i++) {
                backingArray[i - 1] = backingArray[i];
            }
            backingArray[size - 1] = null;
            size--;
            return toReturn;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        } else {
            T toReturn = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return toReturn;
        }
    }

    @Override
    public T get(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("Provided index "
                        + "is negative. "
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Provided index "
                        + "is too large."
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
