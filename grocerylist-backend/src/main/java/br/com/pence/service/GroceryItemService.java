package br.com.pence.service;

import java.util.HashMap;
import java.util.List;

import br.com.pence.bean.GroceryItem;
import br.com.pence.jdbc.dao.GroceryListDAO;

public class GroceryItemService {
	static HashMap<Integer, GroceryItem> groceryItemIdMap = getGroceryItemIdMap();

	private GroceryListDAO groceryListDAO;
	
	public GroceryItemService() {
		super();
		groceryListDAO = new GroceryListDAO();
	}

	public List<GroceryItem> getAllGroceryItems() {
		return groceryListDAO.getAllGroceryItems();
	}

	public GroceryItem getGroceryItem(Integer id) {
		return groceryListDAO.getGroceryItem(id);
	}

	public void addGroceryItem(GroceryItem groceryItem) {
		groceryListDAO.addGroceryItem(groceryItem);
	}

	public void updateGroceryItem(GroceryItem groceryItem) {
		groceryListDAO.updateGroceryItem(groceryItem);
		/*
		if (groceryItem.getId() <= 0)
			return null;
		groceryItemIdMap.put(groceryItem.getId(), groceryItem);
		return groceryItem;
		*/
	}

	public void deleteGroceryItem(Integer id) {
		// groceryItemIdMap.remove(id);
		groceryListDAO.deleteGroceryItem(id);
		
	}

	public static HashMap<Integer, GroceryItem> getGroceryItemIdMap() {
		return groceryItemIdMap;
	}
}
