package com.qa.ims.persistence;

import java.util.Objects;

import com.qa.ims.persistence.domain.Item;

public class ItemBuilder {
	private Long itemId;
	private String name;
	private Money value;
	private Integer quantity;
	
	public ItemBuilder() {
	}

	public Item build() {
		return new Item(itemId, name, value, quantity);
	}

	public ItemBuilder itemId(long itemId) {
		this.itemId = itemId;
		return this;
	}

	public ItemBuilder name(String name) {
		this.name = name;
		return this;
	}

	public ItemBuilder value(Money value) {
		this.value = value;
		return this;
	}

	public ItemBuilder quanity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
}
