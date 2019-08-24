package edu.cpp.cs.cs241.prog_assgmnt_1;

import java.util.Random;

//Average Contains() Calculation in SetImplementation is 0 ms

/**
 * Tests my own Contains() method in SetImplementation. It performs 100,000 searches and calculates their average runtime
 * in milliseconds.
 * @author Michael
 *
 */
public class TestMyOwnSetContains {
	
	public static void main(String[] args) {

		SetImplementation<Integer> set = new SetImplementation<Integer>();
		Random ran = new Random();
	
		boolean getContains;
		int count = 100000;
		int addNumber = 0;
		long avg = 0;
		long total = 0; 
	
		for(int i = 0; i < 100000; i++) {
			set.add(addNumber);
			System.out.println("Added: " + addNumber);
			addNumber++;
		}
	
		do {
		
			long initTime = System.currentTimeMillis();
		
			getContains = set.contains(ran.nextInt(10000));
		
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

