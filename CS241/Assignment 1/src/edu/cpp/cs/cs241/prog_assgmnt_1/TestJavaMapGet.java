package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.HashMap;
import java.util.Random;

//Average Get(K) Calculation for HashMap is 0 ms

/**
 * Tests java's Get(K) function in their implementation of Hashmap. It will run 100,000 lookups and time the average
 * in milliseconds.
 * @author Michael
 *
 */
public class TestJavaMapGet {

	public static void main(String[] args) {
		
	HashMap<Integer,String> map = new HashMap<Integer,String>();
	Random ran = new Random();
	
	String getNullValue;
	int count = 100000;
	int addNumber = 0;
	String nullValue = null;
	long avg = 0;
	long total = 0;
	
	for(int i = 0; i < 1000000; i++) {
		map.put(addNumber, nullValue);
		System.out.println("Added: " + addNumber);
		addNumber++;
	}
	
	do {
		
		long initTime = System.currentTimeMillis();
		
		getNullValue = map.get(ran.nextInt(1000000));
		
		long finalTime = System.currentTimeMillis();
		long timeTotal = finalTime - initTime;
		
		System.out.println("Value: " + getNullValue);
		
		count--;
		total = total + timeTotal;
		
	} while(count != 0);
	
	avg = total / 100000;
	System.out.println("Average Get(K) Calculation: " + avg);

	}

}

