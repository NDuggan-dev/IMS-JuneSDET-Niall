package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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


public class OrderDAOFailTest {

	private final OrderDAO DAO = new OrderDAO();


	@Before
	public void setup() {
		DBUtilsPool.getInstance().init("src/test/resources/sql-schema2fail.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order expected = new Order(2L, 1L, itemList);
		 
		assertNull(DAO.create(expected));
	}
	
	
	@Test
	public void testReadAll() {
		assertNull(DAO.readAll());
	}
	

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}

	@Test
	public void testRead() {
		assertNull(DAO.read(1L));
	}

	@Test
	public void testUpdate() {
		final List<Item> itemList = new ArrayList<Item>();
		final Item item1 = new Item(1L, "Deluminator", Money.pounds(300), 20);
		itemList.add(item1);
		final Order order = new Order(1L, 1L, itemList);
		
		assertNull(DAO.update(order));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
	
	@Test 
	public void testDeleteItemFromOrder() {
		final List<Item> itemList = new ArrayList<Item>();
		final Order expected = new Order(1L, 1L, itemList);
		
		final Order result = DAO.deleteItemFromOrder(1L, expected);
		assertNull(result);
	}
	
}
