import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StacksQueuesStudentTests
{
    private LinkedStack<Integer> stack;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        stack = new LinkedStack<>();
    }

    // Tests correct exception is thrown for null element on push
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPushNullException() {
        stack.push(null);
    }

    // Tests correct exception is thrown for empty stack on pop
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPopEmptyException() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.pop();
    }

    // Tests null is returned for empty stack on peek
    @Test(timeout = TIMEOUT)
    public void testPeekEmptyReturnValue() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertNull(stack.peek());
    }

    // Tests push
    @Test(timeout = TIMEOUT)
    public void testPushGeneral() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        assertEquals(5, stack.size());

        LinkedNode<Integer> current = stack.getHead();
        assertEquals(Integer.valueOf(472), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(129), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(234), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(134), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(465), current.getData());
        current = current.getNext();
        assertNull(current);
    }

    // Tests peek with an empty stack
    @Test(timeout = TIMEOUT)
    public void testPeekEmpty() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertNull(stack.peek());
    }

    // Tests peek
    @Test(timeout = TIMEOUT)
    public void testPeekGeneral() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        assertEquals(5, stack.size());

        assertEquals(Integer.valueOf(472), stack.peek());

        LinkedNode<Integer> current = stack.getHead();
        assertEquals(Integer.valueOf(472), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(129), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(234), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(134), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(465), current.getData());
        current = current.getNext();
        assertNull(current);
    }

    // Tests pop with one element
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPopOneElement() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(913);
        assertEquals(1, stack.size());
        assertEquals(new Integer(913), stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.getHead());
        stack.pop();
    }

    // Tests pop
    @Test(timeout = TIMEOUT)
    public void testPopGeneral() {
        assertEquals(0, stack.size());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        assertEquals(5, stack.size());

        assertEquals(new Integer(472), stack.pop());
        assertEquals(new Integer(129), stack.pop());
        assertEquals(new Integer(234), stack.pop());

        assertEquals(2, stack.size());

        LinkedNode<Integer> current = stack.getHead();
        assertEquals(Integer.valueOf(134), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(465), current.getData());
        current = current.getNext();
        assertNull(current);
    }
}