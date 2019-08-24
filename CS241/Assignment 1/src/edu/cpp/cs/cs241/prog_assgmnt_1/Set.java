package edu.cpp.cs.cs241.prog_assgmnt_1;
/**
 * The following interface determines the layout for the class SetImplementation.
 * @author Michael
 *
 * @param <V>
 */
public interface Set<V> {
/**
 * pre: Value is not null.
 * post: Value is in the set. The size of the set is increased by 1 if the value
 * was not already inside the set.
 * @param value
 */
	public void add(V value);
/**
 * pre: Value is not null.
 * post: Value is no longer a member of the set. If the value was not in the set, nothing
 * has been removed.
 * @param value
 */
	public void remove(V value);
/**
 * pre: Value is not null.
 * post: Function is a pure function. Returns true if the set contains the value.
 * @param value
 * @return
 */
	public boolean contains(V value);
/**
 * pre: true(none).
 * post: Size of the set is returned.
 * @return
 */
	public int size();
/**
 * pre: true(none).
 * post: Return if map is empty or not. IsEmpty is a pure function.
 * @return
 */
	public boolean isEmpty();
}
