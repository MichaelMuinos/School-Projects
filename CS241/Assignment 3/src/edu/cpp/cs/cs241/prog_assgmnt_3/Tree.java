package edu.cpp.cs.cs241.prog_assgmnt_3;
/**
 * The following interface is here to be a template for the red black tree data structure.
 * @author Michael
 *
 * Invariant 1: Nodes of the tree contain the color of red or black.
 * Invariant 2: Root node is black.
 * Invariant 3: Leaves have no values and are always black.
 * Invariant 4: If a node is red, then its children are black.
 * Invariant 5: All paths to node leaves should have the same count of black nodes.
 * 
 * @param <K>
 * @param <V>
 */
public interface Tree<K extends Comparable<K>, V> {
/**
 * pre: Key is not null.
 * post: New entry has been added to the red black tree. Size has increased by 1.
 * Invariants are satisfied.
 * @param key
 * @param value
 */
	  public void add(K key, V value);
/**
 * pre: Key is not null.
 * post: Entry with key has been removed from the red black tree. Size has decreased by 1.
 * Invariants are satisfied.
 * @param key
 * @return
 */
	  public V remove(K key);
/**
 * pre: Key is not null.
 * post: Value has been found and returned.
 * The following function is a pure function because it does not affect the state of the
 * red black tree.
 * @param key
 * @return
 */
	  public V lookup(K key);
/**
 * Returns a string representation of the red black tree.
 * @return
 */
	  public String toPrettyString();

}
