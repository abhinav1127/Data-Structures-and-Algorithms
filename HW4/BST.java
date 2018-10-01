import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Abhinav Tirath
 * @userid atirath6
 * @GTID 903108718
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data != null) {
            for (T datum: data) {
                add(datum);
            }
        } else {
            throw new IllegalArgumentException("Cannot add null data to BST.");
        }

    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enter "
                    + "null data into BST.");
        }
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            addData(data, root);
        }
    }

    /**
     * Recursive method to add data to BST in the correct position.
     * @param data the datat to be added
     * @param curr the node which we can compare our given data to
     */
    private void addData(T data, BSTNode<T> curr) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                curr.setRight(new BSTNode<T>(data));
                size++;
            } else {
                addData(data, curr.getRight());
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                curr.setLeft(new BSTNode<T>(data));
                size++;
            } else {
                addData(data, curr.getLeft());
            }
        }
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
        BSTNode<T> replacing = new BSTNode<T>(null);
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
    private BSTNode<T> removeData(T data, BSTNode<T> curr,
                                   BSTNode<T> replacing) {
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
            if (curr.getRight() != null) {
                counter++;
            }
            if (curr.getLeft() != null) {
                counter++;
                if (counter == 1) {
                    return curr.getLeft();
                }
            }
            if (counter == 0) {
                return null;
            } else if (counter == 1) {

                return curr.getRight();
            } else {
                BSTNode<T> dummy = new BSTNode<T>(null);
                curr.setLeft(getRightMost(curr.getLeft(), dummy));
                curr.setData(dummy.getData());

                return curr;
            }
        } else {
            throw new NoSuchElementException("Element was not found"
                    + "in the BST.");
        }
        return curr;
    }

    /**
     * Finds the predecessor of a node and returns it
     * @param curr the node we are checking to be the predecessor
     * @param dummy the node we are using to store the data stored
     * @return the predecessor of a the node being removed
     */
    private BSTNode<T> getRightMost(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getRight() != null) {
            curr.setRight(getRightMost(curr.getRight(), dummy));
        } else {
            dummy.setData(curr.getData());
            if (curr.getLeft() == null) {
                return null;
            } else {
                return curr.getLeft();
            }
        }
        return curr;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data"
                    + " in BST.");
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
    private T getData(T data, BSTNode<T> curr) {
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
    private boolean containsData(T data, BSTNode<T> curr) {
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
    //DOES it matter where i add them in the arrayList. Adding at 0 rn
    public List<T> preorder() {
        if (root == null) {
            return new ArrayList<T>();
        }
        ArrayList<T> toReturn = new ArrayList<T>(size);
        treePreorder(root, toReturn);
        return toReturn;
    }

    /**
     * The recursive method that creates a list from the BST data using a
     * preorder traversal
     * @param curr the node that we are calling the children of and of the data
     *             we are adding to the tree
     * @param toReturn the List to be returned
     * @return the List made with a preorder traversal
     */
    private void treePreorder(BSTNode<T> curr, List<T> toReturn) {
        toReturn.add(curr.getData());
        if (curr.getLeft() != null) {
            treePreorder(curr.getLeft(), toReturn);
        }
        if (curr.getRight() != null) {
            treePreorder(curr.getRight(), toReturn);
        }
    }

    @Override
    public List<T> postorder() {
        if (root == null) {
            return new ArrayList<T>();
        }
        ArrayList<T> toReturn = new ArrayList<T>(size);
        return treePostorder(root, toReturn);
    }

    /**
     * The recursive method that creates a list from the BST data using a
     * postorder traversal
     * @param curr the node that we are calling the children of and of the data
     *             we are adding to the tree
     * @param toReturn the List to be returned
     * @return the List made with a postorder traversal
     */
    private List<T> treePostorder(BSTNode<T> curr, List<T> toReturn) {
        if (curr.getLeft() != null) {
            toReturn = treePostorder(curr.getLeft(), toReturn);
        }
        if (curr.getRight() != null) {
            toReturn = treePostorder(curr.getRight(), toReturn);
        }
        toReturn.add(curr.getData());
        return toReturn;
    }

    @Override
    public List<T> inorder() {
        if (root == null) {
            return new ArrayList<T>();
        }
        ArrayList<T> toReturn = new ArrayList<T>(size);
        return treeInorder(root, toReturn);
    }

    /**
     * The recursive method that creates a list from the BST data using a
     * inorder traversal
     * @param curr the node that we are calling the children of and of the data
     *             we are adding to the tree
     * @param toReturn the List to be returned
     * @return the List made with a inorder traversal
     */
    private List<T> treeInorder(BSTNode<T> curr, List<T> toReturn) {
        if (curr.getLeft() != null) {
            toReturn = treeInorder(curr.getLeft(), toReturn);
        }
        toReturn.add(curr.getData());
        if (curr.getRight() != null) {
            toReturn = treeInorder(curr.getRight(), toReturn);
        }
        return toReturn;
    }

    @Override
    public List<T> levelorder() {
        List<T> toReturn = new ArrayList<T>(size);
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        BSTNode<T> curr;
        while (!queue.isEmpty()) {
            curr = queue.remove();
            if (curr != null) {
                queue.add(curr.getLeft());
                queue.add(curr.getRight());
                toReturn.add(curr.getData());
            }
        }
        return toReturn;
    }


    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("One or both of the elements"
                    + " passed in are not in the BST.");
        }
        if (size <= 1) {
            throw new NoSuchElementException();
        }
        return deepestCommonAncestor(data1, data2, root);
    }

    /**
     * Finds the deepest common ancestor between two nodes
     * @throws NoSuchElementException if one or both of the passed in elements
     * are not contained in the tree
     * @param data1 one of the data we are trying to find a common ancestor for
     * @param data2 one of the data we are trying to find a common ancestor for
     * @param curr the node we are currently checking to see if it is the
     *             deepest common ancestor
     * @return the distance between two nodes
     */
    private int deepestCommonAncestor(T data1, T data2, BSTNode<T> curr) {
        if (data1.compareTo(curr.getData()) > 0
                && data2.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                return deepestCommonAncestor(data1, data2, curr.getRight());
            } else {
                throw new NoSuchElementException("One or both of the elements"
                        + " passed in are not in the BST.");
            }
        } else if (data1.compareTo(curr.getData()) < 0
                && data2.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                return deepestCommonAncestor(data1, data2, curr.getLeft());
            } else {
                throw new NoSuchElementException("One or both of the elements"
                        + " passed in are not in the BST.");
            }
        } else {
            return distanceFromNode(data1, curr)
                    + distanceFromNode(data2, curr);
        }
    }

    /**
     * Finds the distance from a certain node to one of its parents
     * @param data the data of the node we are finding
     * @param curr the ancestor of the node that contains the data
     * @return the distance between the ancestor
     */
    private int distanceFromNode(T data, BSTNode<T> curr) {
        if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                return distanceFromNode(data, curr.getRight()) + 1;
            } else {
                throw new NoSuchElementException("One or both of the elements"
                        + " passed in are not in the BST.");
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() != null) {
                return distanceFromNode(data, curr.getLeft()) + 1;
            } else {
                throw new NoSuchElementException("One or both of the elements"
                        + " passed in are not in the BST.");
            }
        } else {
            return 0;
        }
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
        } else {
            return treeHeight(root);
        }
    }

    /**
     * Recursive method that finds the height of the root node in the BST
     * @param curr the node we are currently examining the height of
     * @return the height of the root node
     */
    private int treeHeight(BSTNode<T> curr) {
        int leftBranch = -1;
        int rightBranch = -1;
        if (curr.getLeft() != null) {
            leftBranch = treeHeight(curr.getLeft());
        }
        if (curr.getRight() != null) {
            rightBranch = treeHeight(curr.getRight());
        }
        if (leftBranch > rightBranch) {
            return leftBranch + 1;
        } else {
            return rightBranch + 1;
        }
    }


    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
