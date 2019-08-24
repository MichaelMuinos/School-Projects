package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.HashSet;

//Hashset = 12694 ms = 12.694 seconds

/**
 * Tests Java's implementation of HashSet with its underlying structure being a hashtable. It will add 1 million elements
 * to it and time it in milliseconds.
 * @author Michael
 *
 */
public class TestJavaSet {

	public static void main(String[] args) {
		
		HashSet<Integer> numbers = new HashSet<Integer>();
		int count = 0;
		
		long initTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++) {
			numbers.add(count);
			System.out.println("Added: " + count);
			count++;
		}
		
		long finalTime = System.currentTimeMillis();
		long time = finalTime - initTime;
		System.out.println(time);

	}

}
