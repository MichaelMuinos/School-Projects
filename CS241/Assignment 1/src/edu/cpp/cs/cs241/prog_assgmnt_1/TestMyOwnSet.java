package edu.cpp.cs.cs241.prog_assgmnt_1;

//Set = 4754270 = 1 hour, 19 minutes.

/**
 * Tests my own implementation of a set using SetImplementation. The underlying structure is using linked lists
 * instead of a hashtable. It adds 1 millions elements and times it in milliseconds.
 * @author Michael
 *
 */
public class TestMyOwnSet {
	
	public static void main(String[] args) {
		
		SetImplementation<Integer> set = new SetImplementation<Integer>();
		int count = 0;
		
		long initTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++) {
			set.add(count);
			count++;
		}
		
		long finalTime = System.currentTimeMillis();
		long time = finalTime - initTime;
		System.out.println(time);
		
	}

}
