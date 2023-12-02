public interface DyanmicSet {
    /**
     * @return the number of elements in this set
     */
    int size();
    /**
     * Checks if a specified element is a member of this set.
     *
     * @param x the specified element to be checked for set membership
     * @return <code>true</code> if and only if this set contains the specified element
     */
    boolean contains(Integer x);
    /**
     * Adds a specified element to this set. Addition is successful unless the set already contains
     * this element.
     *
     * @param x the specified element to be added to this set
     * @return <code>true</code> if and only if the specified element was successfully added
     */
    boolean add(Integer x);
    /**
     * Removes a specified element from this set.
     *
     * @param x the specified element to be removed from this set
     * @return <code>true</code> if and only if this element was successfully removed
     */
    boolean remove(Integer x);
}
