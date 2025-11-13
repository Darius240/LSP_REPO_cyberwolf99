package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test cases for IntegerSet class.
 * Covers normal and edge cases for all public methods.
 */
public class IntegerSetTest {

    @Test
    public void testAddAndLength() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(2);  // duplicate ignored
        assertEquals(2, set.length());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
    }

    @Test
    public void testClearAndIsEmpty() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    public void testEquals() {
        IntegerSet s1 = new IntegerSet();
        IntegerSet s2 = new IntegerSet();
        s1.add(1); s1.add(2);
        s2.add(2); s2.add(1);
        assertTrue(s1.equals(s2));
    }

    @Test
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        assertTrue(set.contains(5));
        assertFalse(set.contains(6));
    }

    @Test
    public void testLargestAndSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(7);
        set.add(1);
        assertEquals(7, set.largest());
        assertEquals(1, set.smallest());
    }

    @Test
    public void testLargestThrowsExceptionWhenEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> set.largest());
    }

    @Test
    public void testSmallestThrowsExceptionWhenEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> set.smallest());
    }

    @Test
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(4);
        set.add(5);
        set.remove(4);
        assertFalse(set.contains(4));
        assertTrue(set.contains(5));
    }

    @Test
    public void testUnion() {
        IntegerSet s1 = new IntegerSet();
        s1.add(1);
        s1.add(2);
        IntegerSet s2 = new IntegerSet();
        s2.add(2);
        s2.add(3);
        s1.union(s2);
        assertTrue(s1.equals(s2) || s1.contains(1) && s1.contains(2) && s1.contains(3));
    }

    @Test
    public void testIntersect() {
        IntegerSet s1 = new IntegerSet();
        s1.add(1);
        s1.add(2);
        IntegerSet s2 = new IntegerSet();
        s2.add(2);
        s2.add(3);
        s1.intersect(s2);
        assertEquals("[2]", s1.toString());
    }

    @Test
    public void testDiff() {
        IntegerSet s1 = new IntegerSet();
        s1.add(1);
        s1.add(2);
        IntegerSet s2 = new IntegerSet();
        s2.add(2);
        s1.diff(s2);
        assertEquals("[1]", s1.toString());
    }

    @Test
    public void testComplement() {
        IntegerSet s1 = new IntegerSet();
        s1.add(1);
        IntegerSet s2 = new IntegerSet();
        s2.add(1);
        s2.add(2);
        s1.complement(s2);
        assertEquals("[2]", s1.toString());
    }

    @Test
    public void testToString() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(10);
        String result = set.toString();
        assertTrue(result.contains("5") && result.contains("10"));
    }
}
