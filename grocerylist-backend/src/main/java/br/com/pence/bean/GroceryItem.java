package br.com.pence.bean;

import java.util.Date;

public class GroceryItem {

	private Integer id;
	private Boolean completed;
	private String itemName;
	private Date date;

	public GroceryItem() {
		super();
	}

	public GroceryItem(Integer id, Boolean completed, String itemName, Date date) {
		super();
		this.id = id;
		this.completed = completed;
		this.itemName = itemName;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
