package lesson3;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void add() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        assertEquals(3, tree.size());
        assertTrue(tree.contains(5));
        tree.add(3);
        tree.add(1);
        tree.add(3);
        tree.add(4);
        assertEquals(6, tree.size());
        assertFalse(tree.contains(8));
        tree.add(8);
        tree.add(15);
        tree.add(15);
        tree.add(20);
        assertEquals(9, tree.size());
        assertTrue(tree.contains(8));
        assertTrue(tree.checkInvariant());
    }

    @Test
    public void testIterator()
    {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(9);
        tree.add(11);
        Iterator<Integer> iterator = tree.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(9, iterator.next().longValue());

        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next().longValue());

        assertTrue(iterator.hasNext());
        assertEquals(11, iterator.next().longValue());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIterator2()
    {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Iterator<Integer> iterator = tree.iterator();
        assertFalse(iterator.hasNext());
    }

}