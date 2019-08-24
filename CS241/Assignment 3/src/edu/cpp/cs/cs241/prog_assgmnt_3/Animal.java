package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.Serializable;

/**
 * The following class is the superclass of all animal types including fish, dog, and bird. It handles 
 * the animal's name, age, medical history, vaccinations, and breed.
 * @author Michael
 *
 */
public class Animal implements Serializable {
/**
 * Version ID for serializable interface.
 */
	private static final long serialVersionUID = -2715429008856768784L;
/**
 * Field for the animal name.
 */
	private String animalName;
/**
 * Field for the animal age.
 */
	private int animalAge;
/**
 * Field for the animal medical history.
 */
	private String medHist;
/**
 * Field for the animal vaccinations.
 */
	private String vac;
/**
 * Field for the type of animal.
 */
	private String breed;
/**
 * Constructor in which initializes all the information for the animal.
 * @param dogName
 * @param dogAge
 * @param medHist
 * @param vac
 * @param breed
 */
	public Animal(String animalName, int animalAge, String medHist, String vac, String breed) {
		this.animalName = animalName;
		this.animalAge = animalAge;
		this.medHist = medHist;
		this.vac = vac;
		this.breed = breed;
	}
/**
 * Constructor in which handles solely what breed it is (either fish, dog, or bird).	
 * @param breed
 */
	public Animal(String breed) {
		this.breed = breed;
	}
/**
 * Returns the animal name.
 * @return animalName
 */
	public String getAnimalName() {
		return animalName;
	}
/**
 * Sets the animal name.
 * @param dogName
 */
	public void setAnimalName(String dogName) {
		this.animalName = dogName;
	}
/**
 * Returns the animal age.
 * @return animalAge
 */
	public int getAnimalAge() {
		return animalAge;
	}
/**
 * Sets the animal age.
 * @param animalAge
 */
	public void setAnimalAge(int animalAge) {
		this.animalAge = animalAge;
	}
/**
 * Returns the medical history of the animal.
 * @return medHist
 */
	public String getMedHist() {
		return medHist;
	}
/**
 * Sets the medical history of the animal.
 * @param medHist
 */
	public void setMedHist(String medHist) {
		this.medHist = medHist;
	}
/**
 * Returns the vaccinations the animal has had.
 * @return vac
 */
	public String getVac() {
		return vac;
	}
/**
 * Sets the vaccinations the animal has.
 * @param vac
 */
	public void setVac(String vac) {
		this.vac = vac;
	}
/**
 * Returns the breed of the animal.
 * @return
 */
	public String getBreed() {
		return breed;
	}
/**
 * Sets the breed of the animal.
 * @param breed
 */
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
}
