package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.HashMap;

//Hashmap = 12683 ms = 12.683 seconds

/**
 * Tests java's implementation of HashMap using hashtable's as the underlying structure. It will add 1 million
 * elements and test how long it takes in milliseconds.
 * @author Michael
 *
 */
public class TestJavaMap {

	public static void main(String[] args) {
		
		HashMap<Integer,String> numbers = new HashMap<Integer,String>();
		int count = 0;
		String nullValue = null;
		
		long initTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++) {
			numbers.put(count, nullValue);
			System.out.println("Added: " + count);
			count++;
		}
		
		long finalTime = System.currentTimeMillis();
		long fi = finalTime - initTime;
		System.out.println(fi);

	}

}

