package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.Serializable;

/**
 * This class is the subclass of animal in which handles what type of bird will be created.
 * @author Michael
 *
 */
public class Bird extends Animal implements Serializable {
/**
 * Version ID for serializable interface.
 */
	private static final long serialVersionUID = 4404780489141081244L;
/**
 * List of possible bird breeds.
 */
	private String[] birdType = {"Parakeet","Canary","Parrot","Cockatoo","Lory"};
/**
 * Field of the particular bird breed.
 */
	private String birdBreed;
/**
 * Constructor of a bird object in which is handled by the superclass.
 * @param birdName
 * @param birdAge
 * @param medHist
 * @param vac
 * @param birdBreed
 */
	public Bird(String birdName, int birdAge, String medHist, String vac, String birdBreed) {
		super(birdName, birdAge, medHist, vac, birdBreed);
	}
/**
 * Constructor of a bird object in which is handled by the superclass.	
 * @param birdBreed
 */
	public Bird(String birdBreed) {
		super(birdBreed);
	}
/**
 * Returns the bird breed from the list.
 * @param index
 * @return bird
 */
	public String getBirdType(int index) {
		String bird = birdType[index];
		return bird;
	}
/**
 * Returns the bird breed.
 * @return birdBreed
 */
	public String getBirdBreed() {
		return birdBreed;
	}
/**
 * Sets the bird breed.
 * @param birdBreed
 */
	public void setBirdBreed(String birdBreed) {
		this.birdBreed = birdBreed;
	}

}
