package com.safety;

import java.io.Serializable;

public class UserObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3334635330036439635L;
	private String firstName;
	private String lastName;
	private String customerDirectory;
	// private Image signature;
	// private Image initial;
	
	public UserObject(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCustomerDirectory() {
		return customerDirectory;
	}

	public void setCustomerDirectory(String customerDirectory) {
		this.customerDirectory = customerDirectory;
	}

	@Override
	public String toString() {
		return "UserObject [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
