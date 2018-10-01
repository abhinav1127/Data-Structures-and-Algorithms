import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.LinkedList;
import java.awt.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;


/**
 * Sample JUnit test cases for BST.
 *
 * @version 1.0
 * @author started by Ta's updated by Tania Nasser
 */
public class BSTStudentTests {
    private BSTInterface<Integer> bst;
    private BSTInterface<String> strBst;
    private BSTInterface<Colors> colorsBst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
        strBst = new BST<String>();
        colorsBst = new BST<Colors>();

    }

    @Test(timeout = TIMEOUT)
    public void testNoArgConstructor() {
        bst = new BST<Integer>();
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorWithLinkedListCollection() {
        Collection<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(50);
        linkedList.add(30);
        linkedList.add(70);
        linkedList.add(25);
        linkedList.add(100);
        linkedList.add(40);
        linkedList.add(60);

        bst = new BST<Integer>(linkedList);
        assertEquals(7, bst.size());

        assertEquals((Integer) 50, bst.getRoot().getData());
        assertEquals((Integer) 30, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 70, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 60, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 100, bst.getRoot().getRight().getRight().getData());

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructorNullCollection() {
        Collection<Integer> linkedList = null;

        bst = new BST<Integer>(linkedList);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructorWithCollectionContainsNullElem() {
        Collection<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(50);
        linkedList.add(30);
        linkedList.add(null);
        linkedList.add(25);
        linkedList.add(100);
        linkedList.add(40);
        linkedList.add(60);
        bst = new BST<Integer>(linkedList);
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicates() {
        assertEquals(0, bst.size());

        bst.add(20);
        bst.add(10);
        bst.add(30);
        assertEquals(3, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());

        bst.add(20);
        assertEquals(3, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());

        bst.add(10);
        assertEquals(3, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());

        bst.add(40);
        bst.add(7);
        bst.add(25);
        assertEquals(6, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());

        bst.add(30);
        assertEquals(6, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);

        assertEquals(3, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullDataToEmptyList() {
        assertEquals(0, bst.size());
        bst.add(null);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullDataToPopulatedList() {
        assertEquals(0, bst.size());
        bst.add(2);
        bst.add(1);
        bst.add(3);
        assertEquals(3, bst.size());
        bst.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAfterRemoveNodeWithNoChildren() {
        assertEquals(0, bst.size());

        bst.add(20);
        bst.add(10);
        bst.add(30);

        assertEquals(3, bst.size());

        bst.remove(30);
        assertEquals(2, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());

        bst.add(40);
        bst.add(7);
        bst.add(25);
        assertEquals(5, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());


    }


    @Test(timeout = TIMEOUT)
    public void testAddAfterRemoveNodeWithOneChild() {
        assertEquals(0, bst.size());

        bst.add(20);
        bst.add(10);
        bst.add(30);
        assertEquals(3, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        bst.add(40);
        bst.remove(30);
        bst.add(7);
        bst.add(25);
        assertEquals(5, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());


    }


    @Test(timeout = TIMEOUT)
    public void testAddAfterRemoveNodeWithTwoChildren() {
        assertEquals(0, bst.size());
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(40);
        bst.add(7);
        bst.add(25);
        bst.add(13);
        assertEquals(7, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

        bst.remove(30);
        bst.add(27);
        bst.add(4);
        assertEquals(8, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());
        assertEquals((Integer) 27, bst.getRoot().getRight().getRight().
                getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getLeft().getLeft().getLeft().
                getData());

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNullElement() {
        assertEquals(0, bst.size());
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(40);
        bst.add(7);
        bst.add(25);
        bst.add(13);
        assertEquals(7, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

        bst.remove(null);

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveLeafNode() {
        assertEquals(0, bst.size());
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(40);
        bst.add(7);
        bst.add(25);
        bst.add(13);
        assertEquals(7, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

        bst.remove(null);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNodeWithOneChild() {
        assertEquals(0, strBst.size());
        strBst.add("helen");
        strBst.add("courtney");
        strBst.add("pam");
        strBst.add("adam");
        strBst.add("xavier");
        strBst.add("tiffany");

        assertEquals(6, strBst.size());
        assertEquals((String) "helen", strBst.getRoot().getData());
        assertEquals((String) "courtney", strBst.getRoot().getLeft().getData());
        assertEquals((String) "pam", strBst.getRoot().getRight().getData());
        assertEquals((String) "xavier", strBst.getRoot().getRight().getRight()
                .getData());
        assertEquals((String) "adam", strBst.getRoot().getLeft().getLeft().getData());
        assertEquals((String) "tiffany", strBst.getRoot().getRight().getRight()
                .getLeft().getData());

        strBst.remove("pam");
        assertEquals(5, strBst.size());
        assertEquals((String) "helen", strBst.getRoot().getData());
        assertEquals((String) "courtney", strBst.getRoot().getLeft().getData());
        assertEquals((String) "xavier", strBst.getRoot().getRight().getData());
        assertEquals((String) "tiffany", strBst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((String) "adam", strBst.getRoot().getLeft().getLeft().getData());

    }

    @Test(timeout = TIMEOUT )
    public void testRemoveRootNode() {
        assertEquals(0, strBst.size());
        strBst.add("helen");
        strBst.add("courtney");
        strBst.add("pam");
        strBst.add("adam");
        strBst.add("xavier");
        strBst.add("tiffany");

        assertEquals(6, strBst.size());
        assertEquals((String) "helen", strBst.getRoot().getData());
        assertEquals((String) "courtney", strBst.getRoot().getLeft().getData());
        assertEquals((String) "pam", strBst.getRoot().getRight().getData());
        assertEquals((String) "xavier", strBst.getRoot().getRight().getRight()
                .getData());
        assertEquals((String) "adam", strBst.getRoot().getLeft().getLeft().getData());
        assertEquals((String) "tiffany", strBst.getRoot().getRight().getRight()
                .getLeft().getData());

        strBst.remove("helen");
        assertEquals(5, strBst.size());
        assertEquals((String) "courtney", strBst.getRoot().getData());
        assertEquals((String) "adam", strBst.getRoot().getLeft().getData());
        assertEquals((String) "pam", strBst.getRoot().getRight().getData());
        assertEquals((String) "xavier", strBst.getRoot().getRight()
                .getRight().getData());
        assertEquals((String) "tiffany", strBst.getRoot().getRight()
                .getRight().getLeft().getData());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNodeWithTwoChildren() {
        assertEquals(0, bst.size());
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(40);
        bst.add(7);
        bst.add(25);
        bst.add(13);
        assertEquals(7, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

        bst.remove(30);

        assertEquals(6, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveDataNotInBST() {
        assertEquals(0, bst.size());
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(40);
        bst.add(7);
        bst.add(25);
        bst.add(13);
        assertEquals(7, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

        bst.remove(19);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAllElems() {
        assertEquals(0, bst.size());
        bst.add(20);
        bst.add(10);
        bst.add(30);
        bst.add(40);
        bst.add(7);
        bst.add(25);
        bst.add(13);
        assertEquals(7, bst.size());
        assertEquals((Integer) 20, bst.getRoot().getData());
        assertEquals((Integer) 10, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 30, bst.getRoot().getRight().getData());
        assertEquals((Integer) 40, bst.getRoot().getRight().getRight()
                .getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 25, bst.getRoot().getRight().getLeft()
                .getData());
        assertEquals((Integer) 13, bst.getRoot().getLeft().getRight()
                .getData());

        bst.remove(25);
        assertEquals(6, bst.size());
        bst.remove(10);
        assertEquals(5, bst.size());
        bst.remove(30);
        assertEquals(4, bst.size());
        bst.remove(7);
        assertEquals(3, bst.size());
        bst.remove(20);
        assertEquals(2, bst.size());
        bst.remove(40);
        assertEquals(1, bst.size());
        bst.remove(13);
        assertEquals(0, bst.size());

    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertTrue(bst.contains(58));
        assertEquals((Integer) 58, bst.get(58));
        assertTrue(bst.contains(12));
        assertEquals((Integer) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Integer) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Integer) 24, bst.get(24));
    }

    @Test(timeout = TIMEOUT)
    public void testGetDifferent() {
        Integer testingInteger = new Integer(12);

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(testingInteger);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertSame(testingInteger, bst.get(new Integer(12)));
    }

    @Test(timeout = TIMEOUT)
    public void testGetString() {
        String testingString = new String("helen");

        strBst.add("callie");
        strBst.add("bob");
        strBst.add("reina");
        strBst.add(testingString);
        strBst.add("rob");
        strBst.add("maddie");
        strBst.add("helen");

        assertSame(testingString, strBst.get(new String("helen")));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNullElem() {
        String testingString = new String("helen");

        strBst.add("callie");
        strBst.add("bob");
        strBst.add("reina");
        strBst.add(testingString);
        strBst.add("rob");
        strBst.add("maddie");
        strBst.add("helen");

        strBst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetElemNotInBST() {

        strBst.add("callie");
        strBst.add("bob");
        strBst.add("reina");
        strBst.add("rob");
        strBst.add("maddie");
        strBst.add("helen");

        strBst.get("bobby");
    }

    @Test(timeout = TIMEOUT)
    public void testGetColors() {
        Colors purple = new Colors("purple");
        colorsBst.add(new Colors("red"));
        colorsBst.add(new Colors("blue"));
        colorsBst.add(purple);
        colorsBst.add(new Colors("green"));
        colorsBst.add(new Colors("orange"));

        assertEquals(purple, colorsBst.get(new Colors("purple")));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsColors() {
        Colors purple = new Colors("purple");
        colorsBst.add(new Colors("red"));
        colorsBst.add(new Colors("blue"));
        colorsBst.add(new Colors("green"));
        colorsBst.add(new Colors("purple"));
        colorsBst.add(new Colors("orange"));

        assertEquals(true, colorsBst.contains(purple));
        assertEquals(true, colorsBst.contains(new Colors("orange")));

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsWithNullParam() {
        Colors purple = new Colors("purple");
        colorsBst.add(new Colors("red"));
        colorsBst.add(new Colors("blue"));
        colorsBst.add(new Colors("green"));
        colorsBst.add(new Colors("orange"));

        assertEquals(true, colorsBst.contains(null));

    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        bst.add(24);
        bst.add(12);
        bst.add(7);
        bst.add(1);
        bst.add(94);
        bst.add(73);
        bst.add(58);
        bst.add(77);
        bst.add(68);

        //Order: 24, 1, 7, 12, 94, 58, 73, 68, 77
        List<Integer> preorder = new ArrayList<>();
        preorder.add(24);
        preorder.add(12);
        preorder.add(7);
        preorder.add(1);
        preorder.add(94);
        preorder.add(73);
        preorder.add(58);
        preorder.add(68);
        preorder.add(77);

        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        //Order: 24, 1, 7, 12, 94, 58, 73, 68, 77
        List<Integer> postorder = new ArrayList<>();
        postorder.add(12);
        postorder.add(7);
        postorder.add(1);
        postorder.add(68);
        postorder.add(77);
        postorder.add(73);
        postorder.add(58);
        postorder.add(94);
        postorder.add(24);

        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void clear() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);
        bst.clear();
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addExpectException() {
        bst.add(null);
    }


    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getEmpty() {
        bst.get(20);
    }


    private class Colors implements Comparable<BSTStudentTests.Colors> {
        String colorName = null;

        public Colors(String color) {
            this.colorName = color;
        }

        @Override
        public int compareTo(Colors obj){
            return this.colorName.compareTo(obj.colorName);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Colors) {
                return colorName.equals(((Colors) obj).colorName);
            }

            return false;
        }

    }

}