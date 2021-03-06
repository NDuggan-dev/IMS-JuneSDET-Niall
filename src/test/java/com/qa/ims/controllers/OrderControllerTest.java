package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.ItemBuilder;
import com.qa.ims.persistence.Money;
import com.qa.ims.persistence.OrderBuilder;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;
	
	@Mock
	private ItemDAO itemDao;
	
	@Mock
	private OrderController orderController;
	
	@Mock 
	private CustomerController customerController;

	@InjectMocks
	private OrderController controller;
	


	@Test
	public void testCreate() {
		final HashMap<Long, Item> CATALOGUE = new HashMap<>();
		final Long ID = 1L;
		final Item ITEM = new ItemBuilder().itemId(1L).name("Deluminator").value(Money.pounds(300)).quanity(20).build();
				
		final List<Item> BASKET = new ArrayList<Item>();
		BASKET.add(ITEM);
		final Order created = new OrderBuilder().customerId(ID).itemList(BASKET).build();
		CATALOGUE.put(1L, ITEM); 

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(controller.catalogue()).thenReturn(CATALOGUE);
		Mockito.when(utils.getYesNo()).thenReturn(false);
		Mockito.when(dao.create(created)).thenReturn(created);
		
		final Order result = controller.create();

		assertEquals(created, result);

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getYesNo();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		final HashMap<Long, Order> MAP = new HashMap<>();
		final Long ID = 1L;
		final Item ITEM = new ItemBuilder().itemId(1L).name("Deluminator").value(Money.pounds(300)).quanity(20).build();
		final List<Item> BASKET = new ArrayList<Item>();
		BASKET.add(ITEM);
		final Order CREATED =  new OrderBuilder().id(ID).addItemToList(ITEM).build();
		MAP.put(1L, CREATED);
		
		Mockito.when(dao.readAll()).thenReturn(MAP);

		assertEquals(MAP, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		final Long ID = 1L;
		final Item ITEM =  new ItemBuilder().itemId(1L).name("Deluminator").value(Money.pounds(300)).quanity(20).build();
		final List<Item> BASKET = new ArrayList<Item>();
		BASKET.add(ITEM);
		final Order EXPECTED =  new OrderBuilder().id(ID).addItemToList(ITEM).build();

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.read(1L)).thenReturn(EXPECTED);
		
		assertEquals(EXPECTED, controller.update());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).read(1L);
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
	
	@Test
	public void testListItems() {
		final HashMap<Long, Item> expected = new HashMap<>();
		final Long ID = 1L;
		final Item ITEM =  new ItemBuilder().itemId(1L).name("Deluminator").value(Money.pounds(300)).quanity(20).build();
		expected.put(1L, ITEM);


		HashMap<Long, Item> result = controller.listItems(expected);

		assertEquals(expected, result);
	}
	
	@Test
	public void testAddItemsToOrder() {
		//when null
		Order NULLORDER = null;
		assertNull(controller.addItemsToOrder(NULLORDER));
		
		final long ID = 1L;
		final List<Item> ITEMS = new ArrayList<>();
		final Item ITEM =  new ItemBuilder().itemId(1L).name("Deluminator").value(Money.pounds(300)).quanity(20).build();
		ITEMS.add(ITEM);
		Order ORDER =  new OrderBuilder().id(ID).addItemToList(ITEM).build();
		final HashMap<Long, Item> HASH = new HashMap<>();
		HASH.put(1L, ITEM);
		
		Mockito.when(utils.getYesNo()).thenReturn(true);
		
		assertEquals(ORDER, controller.addItemsToOrder(ORDER));

		Mockito.verify(utils, Mockito.times(1)).getYesNo();
	}
	

}
