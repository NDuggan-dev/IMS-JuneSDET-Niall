package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
	private Long itemId ;
	private String name;
	private Money value;
	private Integer quantity;
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Money getValue() {
		return value;
	}
	public void setValue(Money value) {
		this.value = value;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(itemId, name, quantity, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return itemId == other.itemId && Objects.equals(name, other.name) && quantity == other.quantity
				&& Objects.equals(value, other.value);
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", value=" + value + ", quantity=" + quantity + "]";
	}
	public Item(Long itemId, String name, Money value, Integer quantity) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.value = value;
		this.quantity = quantity;
	}
	
	public Item(String name, Money value, int quantity) {
		super();
		this.name = name;
		this.value = value;
		this.quantity = quantity;
	}
	public Item() {
		
	}
	
	


}
