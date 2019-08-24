package edu.cpp.cs.cs241.prog_assgmnt_1;
/**
 * The following interface determines the layout for the class MapImplementation.
 * @author Michael
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K,V> {
	/**
	 * pre: Key is not null.
	 * post: Mapping pairing of key-value to map. The number of mappings is increased by 1.
	 * Mapping is only added if key is not part of the map.
	 * @param key
	 * @param value
	 */
	public void add(K key, V value);
	/**
	 * pre: Key is not null.
	 * post: Return value associated with key and removed mapping, if key is part of the map,
	 * otherwise return null.
	 * @param key
	 * @return
	 */
	public V remove(K key);
	/**
	 * pre: Key is not null.
	 * post: Return value if key was part of map, otherwise return null. Function is a pure function.
	 * @param key
	 * @return
	 */
	public V get(K key);
	/**
	 * pre: true(none).
	 * post: Returns number of mappings. Size is a pure function.
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
