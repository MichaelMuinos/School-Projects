package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.io.Serializable;

/**
 * This class is the object in which is stored inside the red black tree for the animal appointment
 * program. It holds the appointment and all information entirely.
 * @author Michael
 *
 */
public class Owner implements Serializable {
/**
 * Version ID for serializable interface.
 */
	private static final long serialVersionUID = 5916956710115144254L;
/**
 * Field of the owner's name.	
 */
	private String name;
/**
 * Field of the owner's address.
 */
	private String address;
/**
 * Field of owner's number.
 */
	private int number;
/**
 * Field of an appointment object.
 */
	private Appointment app;
/**
 * Constructor in which instantiates the owner name, address, number, and appointment object.
 * @param name
 * @param address
 * @param number
 * @param app
 */
	public Owner(String name, String address, int number, Appointment app) {
		this.name = name;
		this.address = address;
		this.number = number;
		this.app = app;
	}
/**
 * Returns the owner name.
 * @return name
 */
	public String getName() {
		return name;
	}
/**
 * Sets the owner name.
 * @param name
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * Returns the owner address.
 * @return address
 */
	public String getAddress() {
		return address;
	}
/**
 * Sets the owner address.
 * @param address
 */
	public void setAddress(String address) {
		this.address = address;
	}
/**
 * Returns the owner number.
 * @return number
 */
	public int getNumber() {
		return number;
	}
/**
 * Sets the owner number.
 * @param number
 */
	public void setNumber(int number) {
		this.number = number;
	}
/**
 * Returns the appointment object.
 * @return app
 */
	public Appointment getApp() {
		return app;
	}
/**
 * Sets the appointment object.
 * @param app
 */
	public void setApp(Appointment app) {
		this.app = app;
	}
	
}
