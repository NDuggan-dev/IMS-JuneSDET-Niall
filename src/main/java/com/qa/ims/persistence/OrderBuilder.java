package com.qa.ims.persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.qa.ims.persistence.Money;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

public class OrderBuilder {
	private Long id;
	private Long customerId;
	private Date orderDate;
	private List<Item> itemList;
	
	public OrderBuilder() {
		this.itemList = new ArrayList<Item>();
	}
	public Order build() {
	 return new Order(id, customerId, orderDate, itemList);
	}
	
	public OrderBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public OrderBuilder customerId(long customerId) {
		this.customerId = customerId;
		return this;
	}

	public OrderBuilder orderDate(Date orderDate) {
		this.orderDate = orderDate;
		return this;
	}

	public OrderBuilder itemList(List<Item> itemList) {
		this.itemList = itemList;
		return this;
	}

	public OrderBuilder addItemToList(Item item) {
		if(this.itemList == null) {
			this.itemList = new ArrayList<Item>();
		}
		this.itemList.add(item);
		return this;
	}
}
