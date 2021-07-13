package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private long id;
	private long customerId;
	private Date orderDate;
	private List<Item> itemList;
	
	public Order() {
		this.itemList = new ArrayList<Item>();
	}
	public Order(long id, long customerId, Date orderDate, List<Item> itemList) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.itemList = itemList;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public void addItemToList(Item item) {
		if(this.itemList == null) {
			this.itemList = new ArrayList<Item>();
		}
		this.itemList.add(item);
	}
	
}
