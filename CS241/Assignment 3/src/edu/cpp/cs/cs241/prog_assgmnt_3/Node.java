package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.awt.Color;
import java.io.Serializable;
/**
 * The following class holds the key and value pairing to be used in the red black tree.
 * @author Michael
 *
 * @param <K>
 * @param <V>
 */
public class Node<K extends Comparable<K>,V> implements Serializable {
/**
 * Version ID used for the serializable interface.
 */
	private static final long serialVersionUID = 1L;
/**
 * Parent of node.
 */
	protected Node<K,V> parent;
/**
 * Left child of node.
 */
	protected Node<K,V> leftChild;
/**
 * Right child of node.
 */
	protected Node<K,V> rightChild;
/**
 * Color of node.
 */
	protected Color nodeColor;
/**
 * Key of node.
 */
	protected K key;
/**
 * Value of node.
 */
	protected V value;
/**
 * Creates a node with a new key, value, and color as well as two new children.
 * @param key
 * @param value
 * @param nodeColor
 */
	public Node(K key, V value, Color nodeColor) {
		this.key = key;
		this.value = value;
		this.nodeColor = nodeColor;
		this.leftChild = new Node<K,V>(Color.BLACK);
		this.rightChild = new Node<K,V>(Color.BLACK);
	}
/**
 * Creates a children node in which only has a color.
 * @param nodeColor
 */
	public Node(Color nodeColor) {
		this.nodeColor = nodeColor;
		this.value = null;
		this.key = null;
	}
/**	
 * Returns the uncle of the node.
 * @return
 */
	public Node<K,V> uncle() {
		Node<K,V> uncleNode = this.parent.sibling();
		if(uncleNode != null) {
			return uncleNode;
		} else {
			return null;
		}
	}
/**
 * Returns the sibling of the node.	
 * @return
 */
	public Node<K,V> sibling() {
		if(parent != null) {
			if(this == parent.leftChild) {
				return parent.rightChild;
			} else {
				return parent.leftChild;
			}
		}
		return null;
	}
/**
 * Returns the grandparent of the node.	
 * @return
 */
	public Node<K,V> grandparent() {
		Node<K,V> parentNode = this.parent;
		Node<K,V> grandparentNode = this.parent.parent;
		if(parentNode != null) {
			if(grandparentNode != null) {
				return grandparentNode;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
}