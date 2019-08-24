package edu.cpp.cs.cs241.prog_assgmt;

public interface HeapInterface<V extends Comparable<V>> {

	  public void add(V value);

	  public V[] toArray();

	  public void remove();

	  public void fromArray(V[] array);

	  public V[] getSortedContents();

}


