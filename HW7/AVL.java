import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Collection passed in is null");
        } else {
            for (T datum: data) {
                add(datum);
            }
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enter "
                    + "null data into BST.");
        }
        if (root == null) {
            root = new AVLNode<>(data);
            size++;
        } else {
            root = addData(data, root);
        }
    }

    /**
     * Recursive method to add data to AVL in the correct position. Also
     * handles rebalancing
     * @param data the data to be added
     * @param curr the node which we can compare our given data to
     * @return the node that we are enforcing
     */
    private AVLNode<T> addData(T data, AVLNode<T> curr) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                curr.setRight(new AVLNode<T>(data));
                size++;
            } else {
                curr.setRight(addData(data, curr.getRight()));
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                curr.setLeft(new AVLNode<T>(data));
                size++;
            } else {
                curr.setLeft(addData(data, curr.getLeft()));
            }
        }
        return update(curr);
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for "
                    + "null data in BST.");
            //needed?
        } else if (root == null) {
            throw new NoSuchElementException("The tree does not contain"
                    + " any elements.");
        }
        AVLNode<T> replacing = new AVLNode<T>(null);
        root = removeData(data, root, replacing);
        return replacing.getData();
    }

    /**
     * This method uses pointer reinforcement to find the node to be removed,
     * remove it, and replace it with
     * either null or its own child
     * @throws NoSuchElementException if the data is not in the tree
     * @param data the data to be removed
     * @param curr the node we are comparing are data to
     * @param replacing the node that we are using to retrieve the data stored
     *                  in the node that is being deleted
     * @return the root, whether or not it has changed
     */
    private AVLNode<T> removeData(T data, AVLNode<T> curr,
                                  AVLNode<T> replacing) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                curr.setRight(removeData(data, curr.getRight(), replacing));
            } else {
                throw new NoSuchElementException("Element was not found"
                        + "in the BST.");
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                curr.setLeft(removeData(data, curr.getLeft(), replacing));
            } else {
                throw new NoSuchElementException("Element was not found"
                        + "in the BST.");
            }
        } else if (data.compareTo(curr.getData()) == 0) {
            int counter = 0;
            replacing.setData(curr.getData());
            size--;
            if (curr.getLeft() != null) {
                counter++;
            }
            if (curr.getRight() != null) {
                counter++;
                if (counter == 1) {
                    return update(curr.getRight());
                }
            }
            if (counter == 0) {
                return null;
            } else if (counter == 1) {

                return update(curr.getLeft());
            } else {
                AVLNode<T> dummy = new AVLNode<T>(null);
                curr.setRight(getLeftMost(curr.getRight(), dummy));
                curr.setData(dummy.getData());

                return update(curr);
            }
        } else {
            throw new NoSuchElementException("Element was not found"
                    + "in the BST.");
        }
        return update(curr);
    }

    /**
     * Finds the predecessor of a node and returns it
     * @param curr the node we are checking to be the predecessor
     * @param dummy the node we are using to store the data stored
     * @return the predecessor of a the node being removed
     */
    private AVLNode<T> getLeftMost(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() != null) {
            curr.setLeft(getLeftMost(curr.getLeft(), dummy));
        } else {
            dummy.setData(curr.getData());
            if (curr.getRight() == null) {
                return null;
            } else {
                return update(curr.getRight());
            }
        }
        return update(curr);
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data"
                    + " in AVL.");
        } else if (root == null) {
            throw new NoSuchElementException("The tree does not contain"
                    + " any elements.");
        }
        return getData(data, root);
    }

    /**
     * The method uses recursion to find the data of the node that
     * matches the given data
     * @throws NoSuchElementException if the tree does not contain
     *          the given data
     * @param data the data we are searching for in a node
     * @param curr the node we are comparing data to
     * @return the data of the node that matches the given data
     */
    private T getData(T data, AVLNode<T> curr) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("The tree does not contain"
                        + " the given data.");
            } else {
                return getData(data, curr.getRight());
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                throw new NoSuchElementException("The tree does not contain"
                        + " the given data.");
            } else {
                return getData(data, curr.getLeft());
            }
        } else {
            return curr.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data"
                    + " in BST.");
        }
        if (root == null) {
            return false;
        }
        return containsData(data, root);
    }

    /**
     * The recursive method that finds whether the BST has a certain piece
     * of data
     * @param data The data we are searching for
     * @param curr the node we are comparing the data to
     * @return  true if the BST contains a piece of
     *          data and returns false if it does not
     */
    private boolean containsData(T data, AVLNode<T> curr) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                return false;
            } else {
                return containsData(data, curr.getRight());
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                return false;
            } else {
                return containsData(data, curr.getLeft());
            }
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        if (size < 2) {
            throw new NoSuchElementException("Not enough elements in tree "
                    + "to have a second largest data");
        }
        return getSecondLargestData(root, root.getData());
    }

    /**
     * Find and return the second largest element
     * @param curr the node we are currently examining
     * @param prevLargest the parent node of the node with the largest element
     * @return the second largest data
     */
    private T getSecondLargestData(AVLNode<T> curr, T prevLargest) {
        if (curr.getRight() != null) {
            return getSecondLargestData(curr.getRight(), curr.getData());
        } else {
            if (curr.getLeft() != null) {
                return getRightMost(curr.getLeft());
            } else {
                return prevLargest;
            }
        }
    }

    /**
     * Finds the rightmost element given a parent
     * @param curr the node we are currently examining
     * @return the data in the right most node given a parent
     */
    private T getRightMost(AVLNode<T> curr) {
        if (curr.getRight() != null) {
            return getRightMost(curr.getRight());
        } else {
            return curr.getData();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AVL)) {
            return false;
        }
        AVL<T> other = (AVL) obj;
        return equalsHelper(root, other.root);
    }

    /**
     * helps recursively determine if the two trees have the same values in the
     * same positions
     * @param curr1 a node of the tree in this class
     * @param curr2 a node of the passed in tree
     * @return true if the trees are equal and false otherwise
     */
    private boolean equalsHelper(AVLNode<T> curr1, AVLNode<T> curr2) {
        if (curr1 == null && curr2 == null) {
            return true;
        }
        if ((curr1 == null && curr2 != null)
                || (curr1 != null && curr2 == null)) {
            return false;
        }
        if (!(curr1.getData().equals(curr2.getData()))) {
            return false;
        }
        return (equalsHelper(curr1.getLeft(), curr2.getLeft())
                && equalsHelper(curr1.getRight(), curr2.getRight()));
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * updates the trees heights and balance factors and calls rotations
     * @param curr the node that we are updating
     * @return the node that should be the child of the parent
     */
    private AVLNode<T> update(AVLNode<T> curr) {

        updateHeightandBF(curr);

        if (curr.getBalanceFactor() >= 2) {
            if (curr.getLeft().getBalanceFactor() == -1) {
                return leftRightRotation(curr);
            } else {
                return rightRotation(curr);
            }
        } else if (curr.getBalanceFactor() <= -2) {
            if (curr.getRight().getBalanceFactor() == 1) {
                return rightLeftRotation(curr);
            } else {
                return leftRotation(curr);
            }
        } else {
            return curr;
        }
    }

    /**
     * updates height and balance factor of a node
     * @param curr the node we are updating
     */
    private void updateHeightandBF(AVLNode<T> curr) {
        int leftHeight = -1;
        int rightHeight = -1;
        if (curr.getLeft() != null) {
            leftHeight = curr.getLeft().getHeight();
        }
        if (curr.getRight() != null) {
            rightHeight = curr.getRight().getHeight();
        }
        curr.setBalanceFactor(leftHeight - rightHeight);
        curr.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    /**
     * rotates the tree to the right
     * @param curr the parent node that we are rotating
     * @return the child node that we are rotating that should become the
     * child of the previous parent
     */
    private AVLNode<T> rightRotation(AVLNode<T> curr) {

        AVLNode<T> child = curr.getLeft();
        curr.setLeft(child.getRight());
        child.setRight(curr);

        updateHeightandBF(curr);
        updateHeightandBF(child);

        return child;
    }

    /**
     * rotates the tree to the right and then the left
     * @param curr the parent node that we are rotating
     * @return the child node that we are rotating that should become the
     * child of the previous parent
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> curr) {
        curr.setRight(rightRotation(curr.getRight()));
        return leftRotation(curr);
    }

    /**
     * rotates the tree to the left
     * @param curr the parent node that we are rotating
     * @return the child node that we are rotating that should become the
     * child of the previous parent
     */
    private AVLNode<T> leftRotation(AVLNode<T> curr) {
        AVLNode<T> child = curr.getRight();
        curr.setRight(child.getLeft());
        child.setLeft(curr);
        updateHeightandBF(curr);
        updateHeightandBF(child);
        return child;
    }

    /**
     * rotates the tree to the left and then the right
     * @param curr the parent node that we are rotating
     * @return the child node that we are rotating that should become the
     * child of the previous parent
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> curr) {
        curr.setLeft(leftRotation(curr.getLeft()));
        return rightRotation(curr);
    }
}
