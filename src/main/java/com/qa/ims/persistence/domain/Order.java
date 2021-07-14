package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private Long id;
	private Long customerId;
	private Date orderDate;
	private List<Item> itemList;
	
	public Order() {
		this.itemList = new ArrayList<Item>();
	}
	public Order(Long id, Long customerId, Date orderDate, List<Item> itemList) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.itemList = itemList;
	}
	public Order(Long customerId, List<Item> itemList) {
		super();
		this.customerId = customerId;
		this.itemList = itemList;
	}

	public long getId() {
		return id;
	}
	public void setId(Long id) {
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
	@Override
	public int hashCode() {
		return Objects.hash(customerId, id, itemList, orderDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(id, other.id)
				&& Objects.equals(itemList, other.itemList) && Objects.equals(orderDate, other.orderDate);
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", orderDate=" + orderDate + ", itemList=" + itemList
				+ "]";
	}
	
	
}
