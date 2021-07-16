package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.qa.ims.persistence.Money;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	@Test 
	public void testConstructorOne() {
		Item item = new Item(1L, "Deluminator", Money.pounds(30), 20);
		assertTrue(item instanceof Item);
	}
	
	@Test 
	public void testConstructorTwo() {
		Item item = new Item();
		assertTrue(item instanceof Item);
	}
	
	@Test 
	public void testGetters() {
		Item item = new Item(1L, "Deluminator", Money.pounds(30), 20);
		Long expectedId = 1L;
		String expectedName = "Deluminator";
		Money expectedValue = Money.pounds(30);
		Integer expectedQuantity = 20;
		
		assertTrue(expectedId == item.getItemId());
		assertTrue(expectedName == item.getName());
		assertTrue(expectedValue.equals(item.getValue()));
		assertTrue(expectedQuantity == item.getQuantity());
	}
	
	@Test
	public void testSetters() {
		Item item = new Item(1L, "Deluminator", Money.pounds(30), 20);
		item.setItemId(2L);
		Long expectedId = 2L;
		item.setName("invisibility cloak");
		String expectedName = "invisibility cloak";
		item.setValue(Money.pounds(50));
		Money expectedValue = Money.pounds(50);
		item.setQuantity(30);
		Integer expectedQuantity = 30;
		
		assertTrue(expectedId == item.getItemId());
		assertTrue(expectedName == item.getName());
		assertTrue(expectedValue.equals(item.getValue()));
		assertTrue(expectedQuantity == item.getQuantity());
	}
	
	@Test 
	public void testToString() {
		Item item = new Item(1L, "Deluminator", Money.pounds(30), 20);
		String expected = "Item [itemId=" + item.getItemId() + ", name=" + item.getName() + ", value=" + item.getValue() + ", quantity=" + item.getQuantity() + "]";
		assertEquals(expected, item.toString());
	}

	
	@Test
	public void testEqualsAndHashCode() {
		Item item = new Item(1L, "Deluminator", Money.pounds(30), 20);
		Item item2 = new Item(1L, "Deluminator", Money.pounds(30), 20);
	
		assertTrue(item.equals(item2) && item2.equals(item));
		assertTrue(item.hashCode() == item2.hashCode());
	}
	
	@Test 
	public void testNullHashCodeEquals() {
		Item item = new Item(null, null, null, null);
		Item item2 = new Item(1L, "Deluminator", Money.pounds(30), 20);
		assertFalse(item.equals(item2) && item2.equals(item));
		assertFalse(item.hashCode() == item2.hashCode());
		
	}
	

}
