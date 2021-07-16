package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Money;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtilsPool;


public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();


	@Before
	public void setup() {
		DBUtilsPool.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	


	@Test
	public void testCreate() {
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order expected = new Order(2L, 1L, itemList);
		 
		Order result = DAO.create(expected);
		//order_date is DEFAULT now() in SQL
		Date expectedDate = expected.getOrderDate();
		Date resultDate = result.getOrderDate();
		result.setOrderDate(null);
		
		assertFalse(expectedDate == resultDate);
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testReadAll() {
		final HashMap<Long, Order> expected = new HashMap<Long, Order>();
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order created = new Order(1L, 1L, itemList);
		expected.put(1L, created);
		
		final HashMap<Long, Order> result = DAO.readAll();
		//order_date is DEFAULT now() in SQL
		assertFalse(expected.get(1L).getOrderDate() == result.get(1L).getOrderDate());
		
		result.get(1L).setOrderDate(null);
		assertEquals(expected, result); 
	}
	

	@Test
	public void testReadLatest() {
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order expected = new Order(1L, 1L, itemList);
		
		final Order result = DAO.readLatest();
		result.setOrderDate(null);
		
		assertEquals(expected, result);
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order expected = new Order(1L, 1L, itemList);
		
		final Order result = DAO.read(ID);
		result.setOrderDate(null);
		
		assertEquals(expected, result);
	}

	@Test
	public void testUpdate() {
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order expected = new Order(1L, 1L, itemList);
		
		final Order result = DAO.update(expected);
		result.setOrderDate(null);
		
		assertEquals(expected, result);

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test 
	public void testDeleteItemFromOrder() {
		final List<Item> itemList = new ArrayList<Item>();
		final Order expected = new Order(1L, 1L, itemList);
		
		final Order result = DAO.deleteItemFromOrder(1L, expected);
		result.setOrderDate(null);
		
		assertEquals(expected, result);
		
	}
	
}
