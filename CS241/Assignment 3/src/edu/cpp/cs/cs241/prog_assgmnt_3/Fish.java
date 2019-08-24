package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.Serializable;

/**
 * This class is the subclass of animal in which handles what type of fish will be created.
 * @author Michael
 *
 */
public class Fish extends Animal implements Serializable {
/**
 * Version ID for serializable interface.
 */
	private static final long serialVersionUID = -143422890158176750L;
/**
 * List of possible fish breeds.
 */
	private String[] fishType = {"Catfish","Clownfish","Pufferfish","Sunfish","Eel"};
/**
 * Field of the particular fish breed.
 */
	private String fishBreed;
/**
 * Constructor of a fish object in which is handled by the superclass.
 * @param fishName
 * @param fishAge
 * @param medHist
 * @param vac
 * @param fishBreed
 */
	public Fish(String fishName, int fishAge, String medHist, String vac, String fishBreed) {
		super(fishName, fishAge, medHist, vac, fishBreed);
	}
/**
 * Constructor of a fish object in which is handled by the superclass.	
 * @param fishBreed
 */
	public Fish(String fishBreed) {
		super(fishBreed);
	}
/**
 * Returns the fish breed from the list.
 * @param index
 * @return fish
 */
	public String getFishType(int index) {
		String fish = fishType[index];
		return fish;
	}
/**
 * Returns the fish breed type.
 * @return fishBreed
 */
	public String getFishBreed() {
		return fishBreed;
	}
/**
 * Sets the fish breed.
 * @param fishBreed
 */
	public void setFishBreed(String fishBreed) {
		this.fishBreed = fishBreed;
	}

}
