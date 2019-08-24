package edu.cpp.cs.cs241.prog_assgmnt_1;
/**
 * The following class instantiates a key and value pair.
 * @author Michael
 *
 * @param <K>
 * @param <V>
 */
public class MapData<K,V> {
/**
 * The field below named {@link #key} is passed to the constructor to be created.
 */
	private K key;
/**
 * The field below named {@link #value} is passed to the constructor to be created and mapped to {@link #key}.
 */
	private V value;
/**
 * This constructor of {@link #MapData(Object, Object)} creates a key-value pair.
 * @param key
 * @param value
 */
	public MapData(K key, V value) {
		this.key = key;
		this.value = value;
	}
/**
 * Returns the {@link #key}.
 * @return key
 */
	public K getKey() {
		return key;
	}
/**
 * Returns the {@link #value}.
 * @return value
 */
	public V getValue() {
		return value;
	}
	
}
