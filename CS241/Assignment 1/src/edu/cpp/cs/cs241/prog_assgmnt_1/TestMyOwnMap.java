package edu.cpp.cs.cs241.prog_assgmnt_1;

//Map = 4559340 = 1 hour, 15 minutes.

/**
 * Tests my own implementation of the a map using the class MapImplementation. It will add 1 million elements to
 * the map and time it in milliseconds. Its underlying structure is an array.
 * 
 * @author Michael
 * 
 */
public class TestMyOwnMap {

	public static void main(String[] args) {
		
		MapImplementation<Integer,String> map = new MapImplementation<Integer,String>();
		int count = 0;
		String nullValue = null;
		
		long initTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++) {
			map.add(count, nullValue);
			System.out.println("Added: " + count);
			count++;
		}
		
		long finalTime = System.currentTimeMillis();
		long fi = finalTime - initTime;
		System.out.println(fi);
	
	}

}
