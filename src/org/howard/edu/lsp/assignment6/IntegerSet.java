package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The IntegerSet class represents a mathematical set of integers.
 * It supports standard set operations such as union, intersection,
 * difference, and complement. Duplicate values are not allowed.
 * 
 * @author Darius
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * @return number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Compares this set to another object for equality.
     * Two sets are equal if they contain the same elements, regardless of order.
     * @param o the object to compare with
     * @return true if both contain the same elements, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;
        IntegerSet other = (IntegerSet) o;
        return set.containsAll(other.set) && other.set.containsAll(set);
    }

    /**
     * Checks if the set contains a given value.
     * @param value integer to check
     * @return true if present, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest value in the set.
     * @return largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest value in the set.
     * @return smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
        return Collections.min(set);
    }

    /**
     * Adds an integer to the set if not already present.
     * @param item integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an integer from the set if present.
     * @param item integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Modifies this set to include all unique elements from both sets.
     * @param other another IntegerSet
     */
    public void union(IntegerSet other) {
        for (int val : other.set) {
            if (!set.contains(val)) {
                set.add(val);
            }
        }
    }

    /**
     * Modifies this set to include only elements common to both sets.
     * @param other another IntegerSet
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Removes all elements from this set that are also in another set.
     * @param other another IntegerSet
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Modifies this set to become the complement of itself relative to another set.
     * That is, it becomes (other \ this).
     * @param other another IntegerSet
     */
    public void complement(IntegerSet other) {
        List<Integer> result = new ArrayList<Integer>(other.set);
        result.removeAll(this.set);
        this.set = result;
    }

    /**
     * Checks if the set is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set.
     * Example: [1, 2, 3]
     * @return formatted string of elements
     */
    @Override
    public String toString() {
        return set.toString();
    }
}
