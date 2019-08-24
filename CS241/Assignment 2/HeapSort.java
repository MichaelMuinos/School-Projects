package edu.cpp.cs.cs241.prog_assgmnt_2;
/**
 * This class sorts an array that is passed in. It will sort it
 * based on heap sort.
 * @author Michael
 *
 * @param <V>
 */
public class HeapSort<V extends Comparable<V>> {
/**
 * Defines the size of the array.
 */
	private int size = 0;
/**
 * Swaps the elements of 2 arrays.
 * @param Holds the elements of the array.
 * @param Index placeholder.
 * @param Index placeholder.
 */
	private void swap(V[] array, int i, int j) {
		
		V temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		
	}
/**
 * Sorts the array that is passed in.
 * @param Holds the elements in which are to be sorted.
 * @return
 */
	public V[] fix(V[] array) {
		
		size = array.length - 1;
		int split = size / 2;
		
		for(int i = split; i >= 0; i--) {
			heapify(array,i);
		}
		
		for(int i = size; i > 0; i--) {
			swap(array,0,i);
			size = size - 1;
			heapify(array,0);
		}
		
		return array;
		
	}
/**
 * Compares elements of the array passed in to see which
 * contents come before the other.
 * @param Holds the elements of the array.
 * @param Placeholder of the index of the array.
 */
	private void heapify(V[] array, int i) {
		
		int j = i * 2;
		int k = j + 1;
		int l = i;
		
		if(j <= size && array[j].compareTo(array[l]) > 0)
			l = j;
		if(k <= size && array[k].compareTo(array[i]) > 0)
			l = k;
		if(l != i) {
			swap(array,i,l);
			heapify(array,l);
		}
	}
	
}
