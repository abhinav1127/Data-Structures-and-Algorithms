import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed stack.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * You should replace any spots that you pop from with null. Failure to do
     * so can result in a loss of points.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("There are no elements"
                    + "in the list; therefore, there is no data.");
        } else {
            size = size - 1;
            T toReturn = backingArray[size];
            backingArray[size] = null;
            return toReturn;
        }
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null."
                    + "Please only pass non-null data.");
        } else if (size >= backingArray.length) {
            T[] doubledArray = (T[]) (new Object[backingArray.length * 2]);
            for (int i = 0; i < size; i++) {
                doubledArray[i] = backingArray[i];
            }
            backingArray = doubledArray;
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return backingArray[size - 1];
        }
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

    /**
     * Returns the backing array of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}