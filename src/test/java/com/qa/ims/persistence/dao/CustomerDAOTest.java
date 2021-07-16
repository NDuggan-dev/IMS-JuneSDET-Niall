package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.CustomerBuilder;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtilsPool;


public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO(); 


	@Before
	public void setup() {
		DBUtilsPool.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new CustomerBuilder().id(2L).firstName("chris").surname("perrins").build();
		assertEquals(created, DAO.create(created));
	}
 
	@Test
	public void testReadAll() {
		HashMap<Long, Customer> expected = new HashMap<>();
		expected.put(1L, new CustomerBuilder().id(1L).firstName("jordan").surname("harrison").build());
		assertEquals(expected, DAO.readAll()); 
	}
 
	@Test
	public void testReadLatest() {
		Customer expected = new CustomerBuilder().id(1L).firstName("jordan").surname("harrison").build();
		assertEquals(expected, DAO.readLatest()); 
		
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new CustomerBuilder().id(1L).firstName("jordan").surname("harrison").build(), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated =  new CustomerBuilder().id(1L).firstName("chris").surname("perrins").build();
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
}
