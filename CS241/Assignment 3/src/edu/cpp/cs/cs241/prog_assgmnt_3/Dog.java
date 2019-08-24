package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.Serializable;

/**
 * This class is the subclass of animal in which handles what type of dog will be created.
 * @author Michael
 *
 */
public class Dog extends Animal implements Serializable {
/**
 * Version ID for serializable interface.
 */
	private static final long serialVersionUID = 6483667433000805518L;
/**
 * List of possible dog types.
 */
	private String[] dogType = {"Golden Retriever","Beagle","Bulldog","Pitbull","Boxer"};
/**
 * Field of the dog breed.
 */
	private String dogBreed;
/**
 * Constructor of a dog object in which is handled by the superclass.
 * @param dogName
 * @param dogAge
 * @param medHist
 * @param vac
 * @param dogBreed
 */
	public Dog(String dogName, int dogAge, String medHist, String vac, String dogBreed) {
		super(dogName, dogAge, medHist, vac, dogBreed);
	}
/**
 * Constructor of a dog object in which is handled by the superclass.	
 * @param dogBreed
 */
	public Dog(String dogBreed) {
		super(dogBreed);
	}
/**
 * Returns the dog breed from the list.
 * @param index
 * @return dog
 */
	public String getDogType(int index) {
		String dog = dogType[index];
		return dog;
	}
/**
 * Returns the dog breed.
 * @return dogBreed
 */
	public String getDogBreed() {
		return dogBreed;
	}
/**
 * Sets the dog breed.
 * @param dogBreed
 */
	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}

}
