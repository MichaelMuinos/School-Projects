package edu.cpp.cs.cs241.prog_assgmnt_2;
/**
 * This is the superclass for all sub-type customer classes. It will allow for a call-out for a new Customer,
 * the hierarchical priority, and the number of multiples of this particular class. It can retrieve and change
 * this data as well.
 * @author Michael
 *
 */
public class Customer implements Comparable<Customer> {
/**
 * Field in which identifies the call-out for a Customer.
 */
	private String name;
/**
 * Field in which identifies the order of comparison.
 */
	private int priority;
/**
 * Field in which identifies the duplicate objects in the priority queue.
 */
	private int id;
/**
 * Constructor that instantiates the call-out for a new Customer, the hierarchical priority, and the number
 * of multiples of this particular class. 
 * @param Call-out for Customer
 * @param Determines the order in which is added to the priority queue.
 * @param Identifies the amount of duplicates to allow for proper comparison of objects.
 */
	public Customer(String name, int priority, int id) {
		this.name = name;
		this.setId(id);
		this.setPriority(priority);
	}
/**
 * Returns the {@link #name}.
 * @return
 */
	public String getName() {
		return name;
	}
/**
 * Compares two Customer objects and returns if they are alike or not.
 * @param Second customer object to compare.
 */
	public int compareTo(Customer customer) {
		
		int comparison = 0;
		int getPri = this.getPriority();
		int get2ndPri = customer.getPriority();
		
		if (getPri > get2ndPri) {
			comparison = 1;
		} else if (getPri == get2ndPri) {
			comparison =  0;
		} else if (getPri < get2ndPri) {
			comparison = -1;
		}
		
		return comparison;
	}
	
/**
 * Changes the value of {@link #name}.
 * @param name
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * Returns the {@link #priority}.
 * @return
 */
	public int getPriority() {
		return priority;
	}
/**
 * Changes the value of {@link #priority}.
 * @param priority
 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
/**
 * Returns the value of {@link #id}.
 * @return
 */
	public int getId() {
		return id;
	}
/**
 * Changes the value of {@link #id}.
 * @param id
 */
	public void setId(int id) {
		this.id = id;
	}	
}
