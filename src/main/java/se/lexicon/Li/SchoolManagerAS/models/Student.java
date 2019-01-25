package se.lexicon.Li.SchoolManagerAS.models;

import static se.lexicon.Li.SchoolManagerAS.util.UtilNumber.addZero;

public class Student {

	private static int sequencer = 0;
	private final int ID;
	private String name;
	private String email;
	private String address;

	@Override
	public String toString() {
		return "Student, ID: " + addZero(ID, 2) + ", Name:" + name 
				+ "\nEmail: " + email + ", Address=" + address;
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param address
	 */
	public Student(String name, String email, String address) {
		ID = sequencer++;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
