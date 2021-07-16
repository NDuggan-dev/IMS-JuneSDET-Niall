package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private Utils utils;

	private OrderDAO orderDAO;
	private ItemDAO itemDAO;
 

	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.itemDAO = itemDAO;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public HashMap<Long, Order> readAll() {
		HashMap<Long, Order> orders = orderDAO.readAll();
		for(Entry<Long, Order> order : orders.entrySet()) {
			LOGGER.info(order.getValue());
		}
		return orders;
	} 

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerId = utils.getLong();
		HashMap<Long, Item> catalogue = catalogue();
		List<Item> basket = createBasket(catalogue); 
		Order order = orderDAO.create(new Order(customerId, basket));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() { 
		boolean add = false;
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		Order order = orderDAO.read(id);
		order = addItemsToOrder(order);
		order = deleteItemsFromOrder(order);

		
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}
	
	public List<Item> createBasket(HashMap<Long, Item> catalogue){
		boolean moreItems = true;
		List<Item> basket = new ArrayList<Item>();
		
		do {
			LOGGER.info("Please select item by ID");
			Long selectedItemID =  utils.getLong();
			Item selectedItem = catalogue.get(selectedItemID);
			basket.add(selectedItem);
			LOGGER.info("Would you like to add another item? (Yes/No)");
			moreItems = utils.getYesNo();
		}while(moreItems);
		
		return basket;
	}
	
	public HashMap<Long, Item> catalogue(){
		HashMap<Long, Item> catalogue = itemDAO.readAll();
		for(Entry<Long, Item> item : catalogue.entrySet()) {
			LOGGER.info(item.getValue().toString());
		}
		return catalogue;
	}
	
	public HashMap<Long, Item> listItems(HashMap<Long, Item> itemList) {
		for(Entry<Long, Item> item : itemList.entrySet()) {
			LOGGER.info("Item Ref: " + item.getKey() + " " + item.getValue().toString());
		}
		return itemList;	
	}
	
	public Order addItemsToOrder(Order order) {
		if(order == null) {
			return order;
		}
		LOGGER.info("Would you like to add items (yes/no)");
		boolean addItem = utils.getYesNo();
		List<Item> basket = new ArrayList<>(); 
		
		if(addItem) {
			HashMap<Long, Item> cat = catalogue();
			if(cat.size() > 0) {
				basket = createBasket(cat);
				for(Item item : basket) {
					order = orderDAO.addItemToOrder(order, item);
				}
			}
		}
		return order;
	}
	
	public Order deleteItemsFromOrder(Order order) {
		LOGGER.info("Would you like to delete items(yes/no)");
		boolean deleteItems = utils.getYesNo();
		while(deleteItems) {
			HashMap<Long, Item> listItems = listItems(orderDAO.readItemMap(order.getId()));
			LOGGER.info("Please enter item ref of item you would like to delete");
			Long deleteId = utils.getLong();
			order = orderDAO.deleteItemFromOrder(deleteId, order);
			if(listItems.size() > 1) {
				LOGGER.info("Would you like to delete more items(yes/no)");
				deleteItems = utils.getYesNo();
			} else {
				deleteItems = false;
			}
		}
		return order;
	}
}
