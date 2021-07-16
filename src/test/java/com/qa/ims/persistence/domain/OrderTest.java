package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test 
	public void testConstructorOne() {
		List<Item> itemList = new ArrayList<>();
		Order order = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		assertTrue(order instanceof Order);
	}
	
	@Test 
	public void testConstructorTwo() {
		Order order = new Order();
		assertTrue(order instanceof Order);
	}
	
	@Test 
	public void testGetters() {
		List<Item> itemList = new ArrayList<>();
		Order order = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		Long expectedId = 1L;
		Long expectedCustomerId = 1L;
		Date expectedDate = Date.valueOf("2020-03-20");
		List<Item> expectedList = itemList;
		
		assertTrue(expectedId == order.getId());
		assertTrue(expectedCustomerId ==order.getCustomerId());
		assertTrue(expectedDate.equals(order.getOrderDate()));
		assertTrue(expectedList == order.getItemList());
		
	}
	
	@Test
	public void testSetters() {
		List<Item> itemList = new ArrayList<>();
		Order order = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		order.setId(2L);
		Long expectedId = 2L;
		order.setCustomerId(2L);
		Long expectedCustomerId = 2L;
		order.setOrderDate(Date.valueOf("2020-04-04"));
		Date expectedDate = Date.valueOf("2020-04-04");
		order.addItemToList(new Item());
		itemList.add(new Item());
		List<Item> expectedList = itemList;
		
		
		assertTrue(expectedId == order.getId());
		assertTrue(expectedCustomerId ==order.getCustomerId());
		assertTrue(expectedDate.equals(order.getOrderDate()));
		assertTrue(expectedList == order.getItemList());
		
		order.setItemList(itemList);
		assertTrue(expectedList == order.getItemList());
	}
	
	@Test 
	public void testToString() {
		List<Item> itemList = new ArrayList<>();
		Order order = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		String expected = "Order [id=" + order.getId() + ", customerId=" + order.getCustomerId() + ", orderDate=" + order.getOrderDate() + ", itemList=" + order.getItemList()
		+ ", totalCost=" + order.getTotalCost() + "]";
		assertEquals(expected, order.toString());
	}

	
	@Test
	public void testEqualsAndHashCode() {
		List<Item> itemList = new ArrayList<>();
		Order order = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		Order order2 = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		assertTrue(order.equals(order2) && order2.equals(order));
		assertTrue(order.hashCode() == order2.hashCode());
	}
	
	@Test 
	public void testNullHashCodeEquals() {
		List<Item> itemList = new ArrayList<>();
		Order order = new Order(null, null, null, null);
		Order order2 = new Order(1L,1L, Date.valueOf("2020-03-20"),  itemList);
		assertFalse(order.equals(order2) || order2.equals(order));
		assertFalse(order.hashCode() == order2.hashCode());
		
	}
	

}
