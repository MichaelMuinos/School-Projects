package edu.cpp.cs.cs241.prog_assgmnt_2;
/**
 * This class is a type of Customer. It contains the 2nd highest priority.
 * @author Michael
 *
 */
public class AdvanceCallCustomer extends Customer {

/**
 * Constructor that instantiates the call-out for a new Customer, the hierarchical priority, and the number
 * of multiples of this particular class. 
 * @param Call-out for Customer
 * @param Determines the order in which is added to the priority queue.
 * @param Identifies the amount of duplicates to allow for proper comparison of objects.
 */
	public AdvanceCallCustomer(String name, int priority,int id) {
		super(name,priority,id);
	}
	
}
