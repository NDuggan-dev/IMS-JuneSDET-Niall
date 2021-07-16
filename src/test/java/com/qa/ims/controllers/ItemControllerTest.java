package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.ItemBuilder;
import com.qa.ims.persistence.Money;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;
	 

	@Test
	public void testCreate() {
		final String NAME = "Owl";
		final Money VALUE = Money.pounds(34.55);
		final Integer QUANTITY = 20;
		final Item created = 
				new ItemBuilder().name(NAME).value(VALUE).quanity(QUANTITY).build();

		Mockito.when(utils.getString()).thenReturn(NAME);
		Mockito.when(utils.getValue()).thenReturn(VALUE);
		Mockito.when(utils.getInteger()).thenReturn(QUANTITY);
		
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getValue();
		Mockito.verify(utils, Mockito.times(1)).getInteger();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		HashMap<Long, Item> items = new HashMap<>();
		items.put(1L, new ItemBuilder().itemId(1L).name("Potion").value(Money.pounds(3.4)).quanity(20).build());

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Item updated = new ItemBuilder().itemId(1L).name("Potion").value(Money.pounds(3.4)).quanity(20).build();

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getName());
		Mockito.when(this.utils.getValue()).thenReturn(updated.getValue());
		Mockito.when(this.utils.getInteger()).thenReturn(updated.getQuantity());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getValue();
		Mockito.verify(this.utils, Mockito.times(1)).getInteger();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
