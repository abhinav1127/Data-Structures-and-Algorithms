import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * This is a basic set of unit tests for SinglyLinkedList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author An Tran
 * @version 1.0
 */
public class LinkedListStudentTests {
    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitializingList() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }
    // test AddAtIndex
    @org.junit.Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testInvalidIndex1() {
        list.addAtIndex("Trang", 2400);
    }

    @org.junit.Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testInvalidIndex2() {
        list.addAtIndex("Trang", Integer.MIN_VALUE);
    }

    @org.junit.Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testInvalidIndex3() {
        list.addAtIndex("Trang", Integer.MAX_VALUE);
    }

    @org.junit.Test(timeout = TIMEOUT, expected =
            IllegalArgumentException.class)
    public void testNullData() {
        list.addAtIndex(null, 0);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex1() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("Andy", 0);

        assertEquals(1, list.size());

        LinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertSame(list.getHead(), current);

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead());

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead());

    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2() {

        list.addAtIndex("A", 0);
        list.addAtIndex("B", 0);

        // B A
        assertEquals(2, list.size());

        LinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertEquals("B", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("A", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead());

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead().getNext());
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex3() {

        for (int i = 0; i < 8; i++) {
            list.addAtIndex(i + "a", i);
        }
        // 0a 1a 2a 3a 4a 5a 6a 7a

        assertEquals(8, list.size());

        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < 8; i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }
        assertNotNull(current);
        assertSame(list.getHead(), current);

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead().getNext());
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testAddAtIndex4() {
        list.addAtIndex("An", 0);
        list.addAtIndex("Trang", 1);
        list.addAtIndex("Yes", 3);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex5() {

        for (int i = 0; i < 5; i++) {
            list.addAtIndex((i * 2) + "a", i);
        } // 0a 2a 4a 6a 8a

        assertEquals(5, list.size());

        for (int i = 1; i < 10; i += 2) {
            list.addAtIndex(i + "a", i);
        } // 0a 1a 2a 3a 4a 5a 6a 7a 8a 9a

        assertEquals(10, list.size());

        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < 10; i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }
    }
    //test addToFront

    @org.junit.Test(timeout = TIMEOUT, expected =
            IllegalArgumentException.class)
    public void testNullAddtoFront() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront1() {
        list.addToFront("a");
        assertSame(list.getHead().getNext(), list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront2() {
        for (int i = 5; i > -1; i--) {
            list.addToFront(i + "a");
        }
        // 0a 1a 2a 3a 4a 5a
        assertEquals(list.size(), 6);
        LinkedListNode<String> currentNode = list.getHead();

        for (int i = 0; i < 6; i++) {
            assertEquals(currentNode.getData(), i + "a");
            currentNode = currentNode.getNext();
        }
        assertSame(currentNode, list.getHead());
        assertEquals(currentNode.getNext().getData(), "1a");
    }

    // test addToBack
    @org.junit.Test(timeout = TIMEOUT, expected =
            IllegalArgumentException.class)
    public void testNullAddtoBack() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack1() {
        list.addToBack("a");
        assertSame(list.getHead().getNext(), list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack2() {
        list.addToBack("0a");
        list.addToBack("1a");
        LinkedListNode<String> current = list.getHead();

        assertEquals(current.getData(), "0a");
        current = current.getNext();
        assertEquals(current.getData(), "1a");
        current = current.getNext();
        assertEquals(current.getData(), "0a");
    }
    @Test(timeout = TIMEOUT)
    public void testAddToBack3() {
        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        assertEquals(list.size(), 5);

        LinkedListNode<String> currentNode = list.getHead();

        for (int i = 0; i < 5; i++) {
            assertEquals(currentNode.getData(), i + "a");
            currentNode = currentNode.getNext();
        }
        assertSame(currentNode, list.getHead());
        assertEquals(currentNode.getNext().getData(), "1a");
    }
    // test adding mixed

    @Test(timeout = TIMEOUT)
    public void testAddingMixed1() {
        list.addToFront("0a");
        list.addToBack("1a");

        assertEquals(list.getHead().getData(), "0a");
        assertEquals(list.getHead().getNext().getData(), "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testAddingMixed2() {
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                list.addToFront(i + "a");
            } else {
                list.addToBack(i + "a");
            }
        }
        //  10a 8a 6a 4a 2a 0a 1a 3a 5a 7a 9a

        LinkedListNode<String> current = list.getHead();

        for (int i = 10; i > -1; i -= 2) {
            assertEquals(current.getData(), i + "a");
            current = current.getNext();
        }
        for (int i = 1; i < 10; i += 2) {
            assertEquals(current.getData(), i + "a");
            current = current.getNext();
        }
        assertSame(current, list.getHead());
    }

    //test removeAtIndex
    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testRemoveAtIndex1() {
        list.addToFront("0a");
        list.removeAtIndex(1);
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testRemoveAtIndex2() {
        for (int i = 5; i > -1; i--) {
            list.addToFront(i + "a");
        }
        // 0a 1a 2a 3a 4a 5a
        for (int i = 1; i < 6; i += 2) {
            list.removeAtIndex(i);
        }
        // 0a 2a 4a
        LinkedListNode<String> current = list.getHead();

        for (int i = 0; i < 5; i += 2) {
            assertEquals(current.getData(), i + "a");
            current = current.getNext();
        }

        assertSame(current, list.getHead());
        assertEquals(current.getNext().getData(), "2a");
    }
    //test removeFromFront
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront1() {
        assertEquals(null, list.removeFromFront());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront2() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        //0a 1a 2a
        assertEquals(list.removeFromFront(), "0a");
        assertEquals(list.getHead().getData(), "1a");
        assertEquals(list.removeFromFront(), "1a");
        assertEquals(list.getHead().getData(), "2a");
        assertEquals(list.removeFromFront(), "2a");
        assertEquals(list.removeFromFront(), null);
    }
    //test removeFromBack
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack1() {
        assertEquals(null, list.removeFromBack());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack2() {
        list.addToFront("2a");
        list.addToFront("1a");
        list.addToFront("0a");
        //0a 1a 2a
        assertEquals(list.removeFromBack(), "2a");
        assertEquals(list.getHead().getData(), "0a");
        assertEquals(list.removeFromBack(), "1a");
        assertEquals(list.getHead().getData(), "0a");
        assertEquals(list.removeFromBack(), "0a");
        assertEquals(list.removeFromBack(), null);
    }
    //test removeMix
    @Test(timeout = TIMEOUT)
    public void testRemoveMixed1() {
        for (int i = 0; i < 11; i++) {
            list.addAtIndex(i + "a", i);
        } // 0a 1a 2a 3a 4a 5a 6a 7a 8a 9a 10a

        for (int i = 0; i < 5; i++) {
            assertEquals(list.removeFromBack(), 10 - i + "a");
            assertEquals(list.removeFromFront(), i + "a");
        }
        assertEquals(list.getHead().getData(), "5a");
        assertEquals(list.removeFromFront(), "5a");
        assertEquals(list.removeFromFront(), null);
    }
    //test toArray
    @Test(timeout = TIMEOUT)
    public void testToArray1() {

        for (int i = 0; i < 11; i++) {
            list.addAtIndex(i + "a", i);
        } // 0a 1a 2a 3a 4a 5a 6a 7a 8a 9a 10a

        String[] expectedArr = {"0a", "1a", "2a", "3a", "4a", "5a", "6a",
                "7a", "8a", "9a", "10a"};
        assertEquals(list.size(), expectedArr.length);
        assertArrayEquals(expectedArr, list.toArray());
    }
    @Test(timeout = TIMEOUT)
    public void testToArray2() {
        list.addToFront("0a");
        assertArrayEquals(list.toArray(), new String[]{"0a"});
    }
    //test get

    @Test(timeout = TIMEOUT)
    public void testGet1() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }
    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testGet2() {
        list.addToFront("0a");
        list.get(1);
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testGet3() {
        list.addToFront("0a");
        list.get(-1);
    }
    //test removeLastOccurence
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence1() {
        for (int i = 5; i > -1; i--) {
            list.addToFront(i + "a");
        }
        list.addToBack("1a");
        // 0a 1a 2a 3a 4a 5a 1a
        assertEquals(list.size(), 7);
        list.removeLastOccurrence("0a");
        assertEquals("1a", list.getHead().getData());
        list.removeLastOccurrence("1a");
        assertArrayEquals(list.toArray(), new String[]{
                "1a", "2a", "3a", "4a", "5a"});
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence2() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("1a");
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("1a");
        list.addToBack("0a");
        // 0a 1a 1a 0a 1a 1a 0a
        assertEquals(list.size(), 7);
        list.removeLastOccurrence("0a");
        assertEquals("0a", list.getHead().getData());
        assertEquals("1a", list.get(list.size() - 1));
        list.removeLastOccurrence("1a");
        assertArrayEquals(list.toArray(), new String[]{
                "0a", "1a", "1a", "0a", "1a"});
        list.removeLastOccurrence("0a");
        assertArrayEquals(list.toArray(), new String[]{"0a", "1a", "1a", "1a"});
        assertEquals(list.removeFromBack(), "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence3() {
        list.addToBack("1a");
        assertEquals(list.size(), 1);
        list.removeLastOccurrence("1a");
        assertSame(list.getHead(), null);
        assertEquals(list.size(), 0);
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence4() {
        list.addToBack("1a");
        list.addToFront("0a");
        //0a 1a
        assertEquals(list.size(), 2);
        list.removeLastOccurrence("0a");
        assertSame(list.getHead().getData(), "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence5() {
        list.addToBack("1a");
        list.addToFront("0a");
        //0a 1a
        assertEquals(list.size(), 2);
        list.removeLastOccurrence("1a");
        assertSame(list.getHead().getData(), "0a");
    }
}