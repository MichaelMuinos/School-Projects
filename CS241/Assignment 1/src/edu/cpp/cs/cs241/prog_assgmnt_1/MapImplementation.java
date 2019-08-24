package edu.cpp.cs.cs241.prog_assgmnt_1;
/**
 * The following class uses the Map interface as a template in creating a map data structure. A key-value pair
 * is mapped inside to be searched and removed as needed.
 * @author Michael
 *
 * @param <K>
 * @param <V>
 */
public class MapImplementation<K,V> implements Map<K,V> {
/**
 * The field below named {@link #SIZE} determines the size of the map data structure.
 */
	private static final int SIZE = 1000000;
/**
 * The field below is an array of the class MapData.
 */
	@SuppressWarnings("rawtypes")
	private MapData[] map = new MapData[SIZE];
/**
 * The field below is a single instantiation of MapData used to be a temporary holding place in the
 * add method.
 */
	@SuppressWarnings("rawtypes")
	private MapData mapdata;
/**
 * The field below counts how many buckets are being used out of the {@link #SIZE} that is determined.
 */
	private int usedBuckets = 0;
/**
 * The field below is a single instantiation of MadData used to be a temporary holding place in the
 * remove method.
 */
	@SuppressWarnings("rawtypes")
	private MapData mapdata2;
/**
 * The field below returns true of in the remove method the index is not null once the
 * index has changed, otherwise it is false.
 */
	private boolean es = false;
/**
 * The following method adds a key {@link #key} - value {@link #value} pair to {@link #map}. If the key is a duplicate,
 * it will not do anything. If the key is null, it will not do anything either. Also, if the map is full, it will not
 * do anything.
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void add(K key, V value) {
		
		if(key == null) {
			System.out.println("Key is null, entry will not be added.");
			return;
		}
		
		if(usedBuckets == SIZE - 1) {
			resize();
		}
		
		if(usedBuckets > 0) {
			for(int i = 0; i < map.length; i++) {
				if(map[i] == null) {
					map[i] = map[i + 1];
					if(i == map.length - 2) {
						break;
					}
				}
				if(map[i] != null) {
					if(map[i].getKey() == key) {
						System.out.println("Duplicate key, entry will not be added.");
						return;
					}
				}
			}
		}
		
		for(int i = 0; i < map.length; i++) {
			if(map[i] == null) {
				mapdata = new MapData(key,value);
				map[i] = mapdata;
				System.out.println("Entry added.");
				usedBuckets++;
				return;
			}
		}
		System.out.println("The map is full.");
		
	}
/**
 * This method resizes the map when it becomes full. It will double its available spaces and copy over
 * all old elements.
 */
	public void resize() {
		System.out.println("Resizing...");
		map = new MapData[SIZE * 2];
		for(int i = 0; i < SIZE; i++) {
			map[i] = map[i];
		}
	}
/**
 * The following method will remove a key {@link #key} - value {@link #value} pair from {@link #map}. If the key
 * cannot be found, or if the key is null, it will not change the state of the map.
 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public V remove(K key) {
		
		if(key == null) {
			System.out.println("Key is null, entry will not be removed.");
			return null;
		}
		
		for(int i = 0; i < map.length; i++) {
			if(map[i] == null) {
				do {
					map[i] = map[i + 1];
					if(map[i] != null) {
						es = true;
					} else {
						es = false;
					}
					if(i == map.length - 2) {
						break;
					}
				} while(es == false);
			}
			if(map[i] != null) {
				if(map[i].getKey() == key) {
					mapdata2 = new MapData(map[i].getKey(),map[i].getValue());
					map[i] = null;
					System.out.println("Entry removed.");
					usedBuckets--;
					return (V) mapdata2.getValue();	
				}
			}
		}
		System.out.println("Entry was not removed.");
		return null;
		
	}
/**
 * The following method will retrieve the value paired with {@link #key}. If the key is null, or if the
 * key cannot be found, the state of the map will not be changed.
 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		
		if(key == null) {
			System.out.println("Key is null, entry cannot be searched.");
			return null;
		}
		
		for(int i = 0; i < map.length; i++) {
			if(map[i] == null) {
				map[i] = map[i + 1];
				if(i == map.length - 2) {
					break;
				}
			}
			if(map[i] != null) {
				if(map[i].getKey() == key) {
					System.out.println("Entry found.");
					return (V) map[i].getValue();
				}
			}
		}
		System.out.println("Entry not found.");
		return null;
	}
/**
 * Returns the number of spaces used in the list.
 * @return usedBuckets
 */
	@Override
	public int size() {
		
		return usedBuckets;
		
	}
/**
 * Determines whether the map is empty or not. If it is, it returns true, otherwise false.
 */
	@Override
	public boolean isEmpty() {

		if(usedBuckets == 0) {
			System.out.println("Map is empty.");
			return true;
		}
		System.out.println("Map is not empty.");
		return false;
		
	}

}
