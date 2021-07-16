package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test 
	public void testConstructorOne() {
		Customer harry = new Customer(1L, "Harry", "Potter");
		assertTrue(harry instanceof Customer);
	}
	
	@Test 
	public void testConstructorTwo() {
		Customer harry = new Customer();
		assertTrue(harry instanceof Customer);
	}
	
	@Test 
	public void testGetters() {
		Customer harry = new Customer(1L, "Harry", "Potter");
		Long expectedId = 1L;
		String expectedFirstname = "Harry";
		String expectedSurname = "Potter";
		
		assertEquals(expectedId, harry.getId());
		assertEquals(expectedFirstname, harry.getFirstName());
		assertEquals(expectedSurname, harry.getSurname());
	}
	
	@Test
	public void testSetters() {
		Customer ron = new Customer(1L, "Harry", "Potter");
		
		ron.setId(2L);
		Long expectedId = 2L;
		ron.setFirstName("Ron");
		String expectedFirstname = "Ron";
		ron.setSurname("Weasley");
		String expectedSurname = "Weasley";
		
		assertEquals(expectedId, ron.getId());
		assertEquals(expectedFirstname, ron.getFirstName());
		assertEquals(expectedSurname, ron.getSurname());
	}
	
	@Test 
	public void testToString() {
		Customer harry = new Customer(1L, "Harry", "Potter");
		String expected = "id:" + harry.getId() + " first name:" + harry.getFirstName() + " surname:" + harry.getSurname();
		assertEquals(expected, harry.toString());
	}

	
	@Test
	public void testEqualsAndHashCode() {
		Customer harry = new Customer(1L, "Harry", "Potter");
		Customer harry2 = new Customer(1L, "Harry", "Potter");
		assertTrue(harry.equals(harry2) && harry2.equals(harry));
		assertTrue(harry.hashCode() == harry2.hashCode());
	}
	
	@Test 
	public void testNullHashCodeEquals() {
		Customer harry = new Customer(null, null, null);
		Customer harry2 = new Customer(1L, "Harry", "Potter");
		assertFalse(harry.equals(harry2) && harry2.equals(harry));
		assertFalse(harry.hashCode() == harry2.hashCode());
		
	}
	

}
