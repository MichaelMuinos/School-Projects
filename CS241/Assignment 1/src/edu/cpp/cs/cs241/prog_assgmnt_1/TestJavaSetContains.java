package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.HashSet;
import java.util.Random;

//Average Contains() for HashSet is 0 ms

/**
 * Tests Java's Contains() method in the implementation of HashSet. It will perform 100,000 lookups and determine the average
 * time in milliseconds.
 * @author Michael
 *
 */

public class TestJavaSetContains {

	public static void main(String[] args) {
		
		HashSet<Integer> set = new HashSet<Integer>();
		Random ran = new Random();
		
		boolean getContains;
		int count = 100000;
		int addNumber = 0;
		long avg = 0;
		long total = 0;
		
		for(int i = 0; i < 1000000; i++) {
			set.add(addNumber);
			System.out.println("Added: " + addNumber);
			addNumber++;
		}
		
		do {
			
			long initTime = System.currentTimeMillis();
			
			getContains = set.contains(ran.nextInt(1000000));
			
			long finalTime = System.currentTimeMillis();
			long timeTotal = finalTime - initTime;
			
			System.out.println("Value: " + getContains);
			
			count--;
			total = total + timeTotal;
			
		} while(count != 0);
		
		avg = total / 100000;
		System.out.println("Average Contains() Calculation: " + avg);

	}

}

