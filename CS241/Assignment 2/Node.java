package edu.cpp.cs.cs241.prog_assgmnt_2;
/**
 * This class is the underlying structure of the tree based
 * heap. Each node holds a value and is the parent of 2 children (whether
 * they are null or not).
 * @author Michael
 *
 * @param <V>
 */
public class Node<V extends Comparable<V>> {
/**
 * Entails the value of the node.
 */
	protected V value;
/**
 * The parent of a node.
 */
	@SuppressWarnings("rawtypes")
	protected Node parent;
/**
 * Left child of a node.
 */
	@SuppressWarnings("rawtypes")
	protected Node leftChild;
/**
 * Right child of a node.
 */
	@SuppressWarnings("rawtypes")
	protected Node rightChild;
/**
 * Creates a new node with a specified value.
 */
	public Node(V value) {
		this.value = value;
	}
		
}
