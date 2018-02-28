package br.com.pence.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pence.bean.GroceryItem;
import br.com.pence.jdbc.database.DbUtil;

/**
 * 
 * @author Spencer Chedid
 * @date 03/12/2017
 */
public class GroceryListDAO extends BaseDAO {
	
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	public GroceryListDAO() {
	}

	/**
	 * Get a list of all grocery items
	 * @return
	 */
	public List<GroceryItem> getAllGroceryItems() {
		List<GroceryItem> resultList = new ArrayList<GroceryItem>();

		try {
			String sqlQuery = ("SELECT * FROM tb_grocery_item;");
			preparedStatement = getConnection().prepareStatement(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				GroceryItem groceryItem = new GroceryItem();
				groceryItem.setId(resultSet.getInt("id"));
				groceryItem.setCompleted(resultSet.getBoolean("completed"));
				groceryItem.setItemName(resultSet.getString("name"));
				groceryItem.setDate(resultSet.getDate("date"));
				resultList.add(groceryItem);
			}

			DbUtil.close(resultSet);
			DbUtil.close(preparedStatement);

			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get a scpecific grocery item by id
	 * @param id
	 * @return
	 */
	public GroceryItem getGroceryItem(Integer id) {
		GroceryItem groceryItem = null;
		
		try {
			preparedStatement = getConnection().prepareStatement("SELECT * FROM tb_grocery_item WHERE id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				groceryItem = new GroceryItem();
				groceryItem.setId(resultSet.getInt("id"));
				groceryItem.setCompleted(resultSet.getBoolean("completed"));
				groceryItem.setItemName(resultSet.getString("name"));
				groceryItem.setDate(resultSet.getDate("date"));
			}
			
			DbUtil.close(resultSet);
			DbUtil.close(preparedStatement);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return groceryItem;
	}
	
	/**
	 * Adds a grocery item
	 * @param groceryItem
	 */
	public void addGroceryItem (GroceryItem groceryItem) {
		try {
			preparedStatement = getConnection().prepareStatement("INSERT INTO tb_grocery_item (completed, name, date) VALUES (?, ?, ?)");
			preparedStatement.setBoolean(1, groceryItem.getCompleted());
			preparedStatement.setString(2, groceryItem.getItemName());
			preparedStatement.setDate(3, new Date(groceryItem.getDate().getTime()));
			
			preparedStatement.execute();
			DbUtil.close(preparedStatement);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a grocery item
	 * @param groceryItem
	 */
	public void updateGroceryItem (GroceryItem groceryItem) {
		try {
			preparedStatement = getConnection().prepareStatement("UPDATE tb_grocery_item SET completed = ?, name = ?, date = ? WHERE id = ?");
			preparedStatement.setBoolean(1, groceryItem.getCompleted());
			preparedStatement.setString(2, groceryItem.getItemName());
			preparedStatement.setDate(3, new Date(groceryItem.getDate().getTime()));
			preparedStatement.setInt(4, groceryItem.getId());
			
			preparedStatement.executeUpdate();
			DbUtil.close(preparedStatement);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a grocery item
	 * @param groceryItem
	 */
	public void deleteGroceryItem(Integer id) {
		try {
			preparedStatement = getConnection().prepareStatement("DELETE tb_grocery_item WHERE id = ?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			DbUtil.close(preparedStatement);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
