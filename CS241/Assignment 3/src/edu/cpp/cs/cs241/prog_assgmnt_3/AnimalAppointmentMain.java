package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
/**
 * This class resembles an animal appointment registry for a hospital in which either dogs, fish, or
 * birds can become patients. It uses a red black tree as the underlying structure.
 * @author Michael
 *
 */ 
public class AnimalAppointmentMain {
/**
 * Instantiation of a red black tree object.
 */
	private static RedBlackTree<String,Owner> tree = new RedBlackTree<String,Owner>();
/**
 * Instantiation of a scanner object.
 */
	private static Scanner sc = new Scanner(System.in);
/**
 * Runs the execution for user input.
 * @param args
 */
	public static void main(String[] args) {
		System.out.println("|||| ANIMAL APPOINTMENT REGISTRY ||||");
		menu();
	}
/**
 * Allows the user to choose what they want to do to the red black tree object.
 * They may add an appointment, search appointments and medical records, as well as save the registry
 * to a file for later uses.
 */
	private static void menu() {
		System.out.println("Enter Task to Execute: ");
		System.out.println("\t1. Create an appointment.");
		System.out.println("\t2. Search appointment by owner name.");
		System.out.println("\t3. Search medical record by owner name.");
		System.out.println("\t4. See all appointments. You may select certain appointments to be resolved here.");
		System.out.println("\t5. Exit and save registry.");
		System.out.println("\t6. Open an existing registry.");
		
		
		int choice;
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			createAppointment();
			menu();
		case 2:
			searchAppointment();
			menu();
		case 3:
			searchMedicalRecord();
			menu();
		case 4:
			seeAllAppointments();
			menu();
		case 5:
			exitAndSave();
			break;
		case 6:
			tree = openRegistry();
			menu();
		default:
			System.out.println("Incorrect input. Try again.\n");
			menu();
		}
	}
/**
 * Adds an owner object to the red black tree in which the owner's name is held as the key, and the
 * object as the value.	
 */
	private static void createAppointment() {
		System.out.println("Choose Animal Type: ");
		System.out.println("\t1. Dog");
		System.out.println("\t2. Bird");
		System.out.println("\t3. Fish");
		
		int animalChoice;
		animalChoice = sc.nextInt();
		
		System.out.println("Enter Owner's Name: ");
		
		String ownerName;
		ownerName = sc.next();
		
		System.out.println("Enter Owner's Address (Street Name Only): ");
		System.out.println("Ex. Portofino");
		
		String ownerAddress;
		ownerAddress = sc.next();
		
		System.out.println("Enter Owner's Number (No Area Code): ");
		
		int number;
		number = sc.nextInt();
		
		System.out.println("Enter Animal's Name: ");
		
		String animalName;
		animalName = sc.next();
		
		switch(animalChoice) {
		case 1:
			System.out.println("Choose the type of Dog: ");
			System.out.println("\t0. Golden Retriever");
			System.out.println("\t1. Beagle");
			System.out.println("\t2. Bulldog");
			System.out.println("\t3. Pitbull");
			System.out.println("\t4. Boxer");
			break;
		case 2:
			System.out.println("Choose the type of Bird: ");
			System.out.println("\t0. Parakeet");
			System.out.println("\t1. Canary");
			System.out.println("\t2. Parrot");
			System.out.println("\t3. Cockatoo");
			System.out.println("\t4. Lory");
			break;
		case 3:
			System.out.println("Choose the type of Fish: ");
			System.out.println("\t0. Catfish");
			System.out.println("\t1. Clownfish");
			System.out.println("\t2. Pufferfish");
			System.out.println("\t3. Sunfish");
			System.out.println("\t4. Eel");
			break;
		default:
			System.out.println("Incorrect Input. Taking you back to the main menu.");
			menu();
		}
		
		int animalType;
		animalType = sc.nextInt();
		
		if(animalType < 0 || animalType > 4) {
			System.out.println("Incorrect Input. Taking you back to the main menu.");
			menu();
		}
		
		System.out.println("Enter Animal's Age: ");
		
		int animalAge;
		animalAge = sc.nextInt();
		
		System.out.println("Enter Animal's Medical History: ");
		System.out.println("Ex. Diabetes");
		
		String medHist;
		medHist = sc.next();
		
		System.out.println("Enter Animal's Vaccinations: ");
		System.out.println("Ex. Rabies");
		
		String vac;
		vac = sc.next();
		
		switch(animalChoice) {
		case 1:
			Dog breedDog = new Dog("");
			String dogType = breedDog.getDogType(animalType);
			Dog dog = new Dog(animalName,animalAge,medHist,vac,dogType);
			Owner dogOwner = new Owner(ownerName,ownerAddress,number,new Appointment(dog));
			tree.add(ownerName, dogOwner);
			break;
		case 2:
			Bird breedBird = new Bird("");
			String birdType = breedBird.getBirdType(animalType);
			Bird bird = new Bird(animalName,animalAge,medHist,vac,birdType);
			Owner birdOwner = new Owner(ownerName,ownerAddress,number,new Appointment(bird));
			tree.add(ownerName, birdOwner);
			break;
		case 3:
			Fish breedFish = new Fish("");
			String fishType = breedFish.getFishType(animalType);
			Fish fish = new Fish(animalName,animalAge,medHist,vac,fishType);
			Owner fishOwner = new Owner(ownerName,ownerAddress,number,new Appointment(fish));
			tree.add(ownerName, fishOwner);
			break;
		default:
			System.out.println("Incorrect Input. Taking you back to the main menu.");
			menu();
		}
	}
/**
 * Allows the user to check if an appointment has already been created with a particular
 * owner name.
 */
	private static void searchAppointment() {
		System.out.println("Enter Owner's Name: ");
		
		String name;
		name = sc.next();
		
		Owner tempOwner = (Owner) tree.lookup(name);
		if(tempOwner != null) {
			System.out.println("|||| Appointment Information ||||");
			System.out.println("Owner Name: " + tempOwner.getName());
			System.out.println("Owner Address: " + tempOwner.getAddress());
			System.out.println("Owner Number: " + tempOwner.getNumber());
			tempOwner.getApp().displayContent();
		} else {
			System.out.println("No appointment was found!");
		}
	}
/**
 * Allows the user to search for the medical records of a particular animal by typing
 * in the correct owner name.	
 */
	private static void searchMedicalRecord() {
		System.out.println("Enter Owner's Name: ");
		
		String name;
		name = sc.next();
		
		Owner tempOwner = (Owner) tree.lookup(name);
		if(tempOwner != null) {
			String medHist = tempOwner.getApp().getAnimal().getMedHist();
			System.out.println("|||| Medical History of Patient ||||");
			System.out.println(medHist);
		} else {
			System.out.println("No owner was found with that name!");
		}
	}
/**
 * Returns all of the appointment information for all owner objects being held in the 
 * red black tree object.
 */
	private static void seeAllAppointments() {
		String tempString = null;
		if(tree.getArrayList().size() == 0) {
			System.out.println("There are no appointments!\n");
			return;
		}
		for(int i = 0; i < tree.getArrayList().size(); i++) {
			tempString = tree.getArrayList().get(i).key;
			if(tempString != null) {
				Owner tempOwner = tree.lookup(tempString);
				System.out.println("|||| Appointment Information ||||");
				System.out.println("Owner Name: " + tempOwner.getName());
				System.out.println("Owner Address: " + tempOwner.getAddress());
				System.out.println("Owner Number: " + tempOwner.getNumber());
				tempOwner.getApp().displayContent();
			}
		}
		
		System.out.println("Press the appropriate number option for the desired task: ");
		System.out.println("\t 1. Set a particular appointment to 'Resolved'.");
		System.out.println("\t 2. Go back to the main menu.");
		
		int choice;
		choice = sc.nextInt();
		if(choice == 1) {
			removeAppointment();
		} else if(choice == 2) {
			return;
		} else {
			System.out.println("Incorrect Input. Taking you back to the main menu.");
			return;
		}
	}
/**
 * Saves the red black tree to a file for later use between runtimes.	
 * @param file
 * @param rbt
 */
	private static void exitAndSave() {
		try {
			FileOutputStream fos = new FileOutputStream("file.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tree);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
/**
 * Opens the previous red black tree object for use in the new runtime.	
 * @param file
 * @return
 */
	@SuppressWarnings("unchecked")
	private static RedBlackTree<String,Owner> openRegistry() {
		System.out.println("Opening registry...");
		RedBlackTree<String,Owner> temp = new RedBlackTree<String,Owner>();
		try {
			FileInputStream fis = new FileInputStream("file.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			temp = (RedBlackTree<String,Owner>) ois.readObject();
			ois.close();
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

/**
 * Removes an owner object from the red black tree object.
 */
	private static void removeAppointment() {
		System.out.println("Enter Owner's Name: ");
		
		String name;
		name = sc.next();
		
		Owner tempOwner = tree.lookup(name);
		if(tempOwner != null) {
			tree.remove(name);
			System.out.println("Appointment belonging to " + "'" + name + "'" + " has been resolved.");
		} else {
			System.out.println("No owner with that name has an appointment!");
		}
	}

}
