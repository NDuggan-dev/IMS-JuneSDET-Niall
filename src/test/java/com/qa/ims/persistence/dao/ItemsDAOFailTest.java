package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.ItemBuilder;
import com.qa.ims.persistence.Money;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtilsPool;
 
public class ItemsDAOFailTest {
	private final ItemDAO DAO = new ItemDAO();


	@Before
	public void setup() {
		DBUtilsPool.getInstance().init("src/test/resources/sql-schema2fail.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		//Resources
		final Item expected = 
				new ItemBuilder().itemId(2L).name("Deluminator").value(Money.pounds(300)).quanity(20).build(); 
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
		final Item updated = 
				new ItemBuilder().itemId(1L).name("Deluminator").value(Money.pounds(300)).quanity(20).build();
		assertNull(DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}

}
