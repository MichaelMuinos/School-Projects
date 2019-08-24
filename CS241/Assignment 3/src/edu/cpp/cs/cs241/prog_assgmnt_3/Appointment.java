package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.Serializable;

/**
 * The following class handles the type of animal object being created inside the appointment
 * object.
 * @author Michael
 *
 */
public class Appointment implements Serializable {
/**
 * Version ID for serializable interface.
 */
	private static final long serialVersionUID = -5188433237218339789L;
/**
 * Field in which represents an animal object.
 */
	private Animal animal;
/**
 * Constructor in which handles the animal type instantiation.	
 * @param animal
 */
	public Appointment(Animal animal) {
		this.animal = animal;
	}
/**
 * Returns the animal object.
 * @return animal
 */
	public Animal getAnimal() {
		return animal;
	}
/**
 * Displays the animal information.
 */
	public void displayContent() {
		System.out.println("Animal Name: " + animal.getAnimalName());
		System.out.println("Age: " + animal.getAnimalAge());
		System.out.println("Medical History: " + animal.getMedHist());
		System.out.println("Vaccinations: " + animal.getVac());
		System.out.println("Breed Type: " + animal.getBreed() + "\n");
	}
	
}
