package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.Random;

//Average Get(K) Calculation for MapImplementation is 2 ms

/**
 * Tests my own Get(K) method in MapImplementation. It performs 100,000 searches and times the average on each.
 * @author Michael
 *
 */
public class TestMyOwnMapGet {

	public static void main(String[] args) {
	
		MapImplementation<Integer,String> map = new MapImplementation<Integer,String>();
		Random ran = new Random();
		
		String getNullValue;
		int count = 100000;
		int addNumber = 0;
		String nullValue = null;
		long avg = 0;
		long total = 0;
		
		for(int i = 0; i < 1000000; i++) {
			map.add(addNumber, nullValue);
			System.out.println("Added: " + addNumber);
			addNumber++;
		}
		
		do {
			
			long initTime = System.currentTimeMillis();
			
			getNullValue = (String) map.get(ran.nextInt(1000000));
			
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
