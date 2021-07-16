package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.OrderBuilder;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtilsPool;

public class OrderDAO implements Dao<Order> {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final ItemDAO itemDAO = new ItemDAO();
	//private static final DataSource dataSource = DBUtilsPool.getDataSource();
 
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long customerId = resultSet.getLong("customer_id");
		Date orderDate = resultSet.getDate("order_date");
		List<Item> itemList = itemMapToList(readItemMap(id));
		return new OrderBuilder().id(id).customerId(customerId).orderDate(orderDate).itemList(itemList).build();
	} 

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public HashMap<Long, Order> readAll(){
		try (Connection connection = DBUtilsPool.getDataSource().getConnection(); 
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			HashMap<Long, Order> orders = new HashMap<>();
			while (resultSet.next()) {
				orders.put(modelFromResultSet(resultSet).getId(), modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readLatest() {
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Order create(Order order){
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomerId());
			statement.executeUpdate();
			readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		List<Item> items = order.getItemList();
		Order thisOrder = readLatest();
		if(items != null) {
			for(Item item : items) {
				addItemToOrder(thisOrder, item);
			}
		}
		return readLatest();
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
 
	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET order_date = now() WHERE order_id = ?");) {
			statement.setLong(1, order.getId());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long id) {
        deleteFromOrderItems(id);
        try (Connection connection = DBUtilsPool.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
       
    public int deleteFromOrderItems(long id) {
        try (Connection connection = DBUtilsPool.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE order_id = ?");) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }


	public Order addItemToOrder(Order thisOrder, Item item) {
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders_items(order_id, item_id) VALUES (?, ?)");) {
				statement.setLong(1, thisOrder.getId());
			statement.setLong(2, item.getItemId());
			statement.executeUpdate();
			update(thisOrder);
			return read(thisOrder.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		return null;
	}
	
	public HashMap<Long, Item> readItemMap(Long orderId){
		HashMap<Long, Item> itemMap = new HashMap<>();
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
			PreparedStatement statement = 
			connection.prepareStatement("SELECT * FROM orders_items WHERE order_id = ?");) {
			statement.setLong(1, orderId);
				try (ResultSet resultSet = statement.executeQuery();) {
				return listItemFromResultSet(resultSet);
				}
			}catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage()); 
		}
		return itemMap;
	}
	
	
	public HashMap<Long, Item> listItemFromResultSet(ResultSet resultSet){
		HashMap<Long, Item> itemList = new HashMap<>();
		try {
			while(resultSet.next()) {
					Item item = itemDAO.read(resultSet.getLong("item_id"));
					itemList.put(resultSet.getLong("orders_items_id"), item);
			}
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return itemList;
	} 
	
	public Order deleteItemFromOrder(Long id, Order thisOrder) {
		try (Connection connection = DBUtilsPool.getDataSource().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE orders_items_id = ?");) {
				statement.setLong(1, id);
			statement.executeUpdate();
			update(thisOrder);
			return read(thisOrder.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} 
		return null;
	}
	
	public List<Item> itemMapToList(HashMap<Long, Item> itemMap){
		List<Item> itemList = new ArrayList<Item>();
		for(Entry<Long, Item> item : itemMap.entrySet()) {
			itemList.add(item.getValue());
		}	
		return itemList;
	}
}
