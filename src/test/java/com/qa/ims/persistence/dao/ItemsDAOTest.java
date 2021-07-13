package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Money;
import com.qa.ims.utils.DBUtilsPool;


public class ItemsDAOTest {

	private final ItemDAO DAO = new ItemDAO();


	@Before
	public void setup() {
		DBUtilsPool.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		//Resources
		final Item expected = new Item(2L, "Deluminator", Money.pounds(300), 20);
		//Actions
		Item result = DAO.create(expected);
		//Assertions
		assertEquals(expected, result);
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Deluminator", Money.pounds(300), 20));
		assertEquals(expected, DAO.readAll());
	}

	@Test  
	public void testReadLatest() {
	}

	@Test
	public void testRead() {
		// Resources
		final long ID = 1L;
		final Item expected = new Item(1L, "Deluminator", Money.pounds(300), 20);
		//Actions
		Item result = DAO.read(ID);
		//Assertions
		assertEquals(expected, result);
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Deluminator", Money.pounds(300), 20);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
