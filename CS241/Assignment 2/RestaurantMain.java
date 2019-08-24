package edu.cpp.cs.cs241.prog_assgmnt_2;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class involves the usage of the heap implementation as well as a priority queue. It will add customers
 * to the queue based on their priority and retrieve their table in order.
 * @author Michael
 *
 */
public class RestaurantMain {

/**
 * Instantiates an object of the heap implementation.
 */
	@SuppressWarnings({ "rawtypes"})
	private static HeapImplementation heap = new HeapImplementation();
/**
 * Instantiates a scanner object for user input.
 */
	private static Scanner sc = new Scanner(System.in);
/**
 * Runs the program for user input and selection.
 * @param args
 */
	public static void main(String[] args) {
		System.out.println("/////// Restaurant Seating Manager ///////");
		menu();
	}
/**
 * Forces the user to choose to add to the priority queue or remove from it.	
 * @throws InputMismatchException
 */
	public static void menu() throws InputMismatchException {
		System.out.println("Options: \n1. New Arrival\n2. Check Available Seats");
		int choice = sc.nextInt();
		if(choice == 1) {
			addCustomer();
		} else {
			checkSeating();
		}
	}
/**
 * Removes the head of the priority queue and displays it as the next person
 * to be seated.	
 */
	public static void checkSeating() {
		
		String status = "";
		
		if(heap.getSize() == 0) {
			System.out.println("No one is in the system!\n");
			menu();
		} else {
			Customer cust = (Customer) heap.remove();
			
			System.out.println("Next Available Seat Belongs To: ");
			
			switch(cust.getPriority()) {
			case 7:
				status = "VIP";
				break;
			case 6:
				status = "Advanced Call";
				break;
			case 5:
				status = "Senior";
				break;
			case 4:
				status = "Veteran";
				break;
			case 3:
				status = "Large Group";
				break;
			case 2:
				status = "Family";
				break;
			case 1:
				status = "Other";
				break;
			default:
				menu();
				break;
			}
			System.out.println(cust.getName() + ": " + status);
			menu();
		}
	}
/**
 * Adds to the priority queue based on their priority rating.	
 * @throws InputMismatchException
 */
	@SuppressWarnings("unchecked")
	public static void addCustomer() throws InputMismatchException {
		System.out.println("Customer: \n1. VIP\n2. Advance Call\n3. Senior\n4. Veteran"
				+ "\n5. Large Group\n6. Family\n7. Other");
		int choice = sc.nextInt();
		System.out.println("\nType In Customer Name: ");
		String name = sc.next();
		switch(choice) {
		case 1:
			VIPCustomer cust0 = new VIPCustomer(name,7,0);
			heap.add(cust0);
			menu();
		case 2:
			AdvanceCallCustomer cust2 = new AdvanceCallCustomer(name,6,0);
			heap.add(cust2);
			menu();
		case 3:
			SeniorCustomer cust3 = new SeniorCustomer(name,5,0);
			heap.add(cust3);
			menu();
		case 4:
			VeteranCustomer cust4 = new VeteranCustomer(name,4,0);
			heap.add(cust4);
			menu();
		case 5:
			GroupCustomer cust5 = new GroupCustomer(name,3,0);
			heap.add(cust5);
			menu();
		case 6:
			FamilyCustomer cust6 = new FamilyCustomer(name,2,0);
			heap.add(cust6);
			menu();
		case 7:
			OtherCustomer cust7 = new OtherCustomer(name,1,0);
			heap.add(cust7);
			menu();
		default:
			menu();
		}
	}
	
}
