package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.LinkedList;
/**
 * The following class uses the interface Set as a template. It will add, remove, and lookup values within a linked
 * list.
 * @author Michael
 *
 * @param <V>
 */
public class SetImplementation<V> implements Set<V> {
/**
 * Instantiates the linked list represented as a set.
 */
	private LinkedList<V> list = new LinkedList<V>();
/**
 * Placeholder for the size of the {@link #list}.
 */
	private int size;
/**
 * Adds a value to the {@link #list}. If {@link #value} entered has already been entered, it will not change
 * the state of the list.
 */
	@Override
	public void add(V value) { //Add function tested properly
		
		if(list == null) {
			list = new LinkedList<V>();
		}
		
		if(list.size() == 0) {
			list.add(value);
		} else {
			if(list.contains(value)) {
				System.out.println("The value entered is a duplicate.");
				return;
			}
			list.add(value);
			System.out.println("Added: " + value);
		}
	
	}
/**
 * Removes a value from the {@link #list}. If the value entered was not in the list, it will not
 * change the state of the list.
 */
	@Override
	public void remove(V value) { //Remove function tested properly
		
		if(list.size() == 0) {
			System.out.println("No items to remove.");
			return;
		} else {
			if(list.contains(value)) {
				list.remove(value);
				return;
			}
			System.out.println("Value was not in the set.");
		}
		
	}
/**
 * The following method looks up the {@link #value} entered. It is a pure function.
 */
	public boolean contains(V value) { //Contains function tested properly
		
		if(list.size() == 0) {
			System.out.println("No items to search.");
		} else {
			if(list.contains(value)) {
				System.out.println("Value is in the set.");
				return true;
			}	
		}
		System.out.println("Value not in the set.");
		return false;
		
	}
/**
 * Returns the size of the {@link #list}.
 * @return size
 */
	@Override
	public int size() { //Size function returns correctly
		
		size = list.size();
		return size;
		
	}
/**
 * Determines whether the map is empty or not. If it is, it returns true, otherwise false.
 */
	@Override
	public boolean isEmpty() { //IsEmpty function tested properly
		
		if(list.size() == 0) {
			System.out.println("Set is empty.");
			return true;
		}
		System.out.println("Set is not empty.");
		return false;
		
	}

}
