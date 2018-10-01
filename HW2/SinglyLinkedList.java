/**
 * Your implementation of a circular singly linked list.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
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
                        + "is too large. "
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> chosenNode = head;
            for (int i = 1; i < index; i++) {
                chosenNode = chosenNode.getNext();
            }
            LinkedListNode<T> newFront = new LinkedListNode(data,
                        chosenNode.getNext());
            chosenNode.setNext(newFront);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure.");
        }
        if (head == null) {
            LinkedListNode<T> newFront = new LinkedListNode(data, null);
            newFront.setNext(newFront);
            head = newFront;
            size++;
        } else {
            LinkedListNode<T> newFront = new LinkedListNode(data,
                        head.getNext());
            head.setNext(newFront);
            T dataToSwitch = newFront.getData();
            newFront.setData(head.getData());
            head.setData(dataToSwitch);
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure.");
        }
        if (head == null) {
            head = new LinkedListNode(data);
            head.setNext(head);
        } else {
            LinkedListNode<T> newFront = new LinkedListNode(head.getData());
            head.setData(data);
            newFront.setNext(head.getNext());
            head.setNext(newFront);
            head = newFront;
        }
        size++;
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
                        + "is too large. "
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (size == 1) {
            T dataToReturn = head.getData();
            head = null;
            size--;
            return dataToReturn;
        } else {
            LinkedListNode<T> chosenNode = head;
            for (int i = 1; i < index; i++) {
                chosenNode = chosenNode.getNext();
            }
            T dataToReturn = chosenNode.getNext().getData();
            chosenNode.setNext(chosenNode.getNext().getNext());
            size--;
            return dataToReturn;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T dataToReturn = head.getData();
            head = null;
            size--;
            return dataToReturn;
        } else {
            T dataToReturn = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return dataToReturn;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(size - 1);
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure.");
        }
        if (size == 0) {
            return null;
        }
        LinkedListNode<T> chosenNode = head;
        LinkedListNode<T> nodeBeforeData = null;
        for (int i = 0; i < (size - 1); i++) {
            if (chosenNode.getNext().getData().equals(data)) {
                nodeBeforeData = chosenNode;
            }
            chosenNode = chosenNode.getNext();
        }
        if (nodeBeforeData != null) {
            if (size == 1) {
                T dataToReturn = nodeBeforeData.getNext().getData();
                head = null;
                size--;
                return dataToReturn;
            } else {
                T dataToReturn = nodeBeforeData.getNext().getData();
                nodeBeforeData.setNext(nodeBeforeData.getNext().getNext());
                size--;
                return dataToReturn;
            }
        }
        if (head.getData().equals(data)) {
            return removeFromFront();
        }
        return null;
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
                        + "is too large. "
                        + "The bounds are between 0 and " + (size - 1)
                        + ".");
        }
        LinkedListNode<T> chosenNode = head;
        if (index == 0) {
            return head.getData();
        }
        for (int i = 1; i <= index; i++) {
            chosenNode = chosenNode.getNext();
        }
        return chosenNode.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] arrayToReturn = new Object[size];
        LinkedListNode<T> chosenNode = head;
        for (int i = 0; i < size; i++) {
            arrayToReturn[i] = chosenNode.getData();
            chosenNode = chosenNode.getNext();
        }
        return arrayToReturn;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
