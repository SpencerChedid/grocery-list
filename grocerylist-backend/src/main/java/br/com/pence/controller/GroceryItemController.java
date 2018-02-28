package br.com.pence.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.pence.bean.GroceryItem;
import br.com.pence.service.GroceryItemService;

@Path("/groceryItems")
public class GroceryItemController {

	GroceryItemService groceryItemService = new GroceryItemService();

	@SuppressWarnings("rawtypes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List getGroceryItems() {
		List listOfItems = groceryItemService.getAllGroceryItems();
		return listOfItems;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GroceryItem getGroceryItemById(@PathParam("id") Integer id) {
		return groceryItemService.getGroceryItem(id);
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public void addGroceryItem(GroceryItem groceryItem) {
		groceryItemService.addGroceryItem(groceryItem);
	}

	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateGroceryItem(GroceryItem groceryItem) {
		groceryItemService.updateGroceryItem(groceryItem);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteGroceryItem(@PathParam("id") Integer id) {
		groceryItemService.deleteGroceryItem(id);

	}

}
