package edu.cpp.cs.cs241.prog_assgmnt_2;
/**
 * This interface is the underlying structure of the heap implementation class.
 * @author Michael
 *
 * @param <V>
 */
public interface Heap<V extends Comparable<V>> {
/**
 * pre: Value is not null.
 * post: Value is added to the heap. The size is increased by 1.
 * Order is preserved to ensure max heap property.
 * @param value
 */
	 public void add(V value);
/**
 * pre: Heap is not null. 
 * post: Converts whatever was stored in the heap to an array representation.
 * Elements follow max heap property.
 * @param array
 * @return
 */
	 public V[] toArray();
/**
 * pre: There is something to remove.
 * post: The item was removed from the heap and, succeedingly,
 * order if maintained to satisfy the max heap property.
 * @return
 */
	 public V remove();
/**
 * pre: Array is not null. Array represents an order heap of nodes.
 * post: Array is transformed to a tree representation. Max heap property is fulfilled.
 * @param array
 */
	 public void fromArray(V[] array);
/**
 * pre: Array is not null.
 * post: Array is sorted to satisfy max heap property.
 * @param array
 * @return
 */
	 public V[] getSortedContents(V[] array);
	 
}
