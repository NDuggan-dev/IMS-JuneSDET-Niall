package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
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


	public OrderController(OrderDAO orderDAO) {
		super();
		this.orderDAO = orderDAO;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Order order = orderDAO.create(new Order());
		LOGGER.info("Customer created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Order order = orderDAO.update(new Order());
		LOGGER.info("Customer Updated");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
