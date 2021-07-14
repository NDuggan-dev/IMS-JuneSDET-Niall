package com.qa.ims.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Money;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();
	private Utils utils;

	private ItemDAO itemDAO;


	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public HashMap<Long, Item> readAll() {
		HashMap<Long, Item> items = itemDAO.readAll();
		for (Entry<Long, Item> item : items.entrySet()) {
			LOGGER.info(item);
		}
		return items;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter item name");
		String name = utils.getString();
		LOGGER.info("Please enter value");
		Money value = utils.getValue();
		LOGGER.info("Please enter quantity");
		int quantity = utils.getInteger();
		Item item = itemDAO.create(new Item(name, value, quantity));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter item id");
		Long id = utils.getLong();
		LOGGER.info("Please enter name");
		String name = utils.getString();
		LOGGER.info("Please enter value");
		Money value = utils.getValue();
		LOGGER.info("Please enter quantity");
		Integer quantity = utils.getInteger();
		Item item = itemDAO.update(new Item(id, name, value, quantity));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}
