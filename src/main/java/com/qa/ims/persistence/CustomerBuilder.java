package com.qa.ims.persistence;

import com.qa.ims.persistence.domain.Customer;

public class CustomerBuilder {

	private Long id;
	private String firstName;
	private String surname;

	public CustomerBuilder() {}

	public Customer build() {
		return new Customer(id, firstName, surname);
	}

	public CustomerBuilder id(Long id) {
		this.id = id;
	return this;
	}

	public CustomerBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}


	public CustomerBuilder surname(String surname) {
		this.surname = surname;
		return this;
	}
}
