package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Dish;
import vn.edu.vnuk.bnb.model.DishType;

public class DishesRowMapper implements RowMapper<Dish> {

	@Override
	public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Dish dish = new Dish();
		DishType dishtypes = new DishType();
		
		dishtypes.setId(rs.getInt("dish_type_id"));
		dishtypes.setLabel(rs.getString("label"));
		
		dish.setId(rs.getInt("id"));
		dish.setPrice(rs.getDouble("price"));
		dish.setDishTypeId(rs.getInt("dish_type_id"));
		dish.setLabel(rs.getString("label"));
		dish.setLabel(rs.getString("description"));
		
		dish.setDishType(dishtypes);
		
		return dish;
	}
	
	
	public List<Dish> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Dish> dishes = new ArrayList<Dish>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		DishType dishtypes = new DishType();
    		Dish dish = new Dish();
			
    		dishtypes.setId((int) row.get("dish_type_id"));
    		dishtypes.setLabel((String) row.get("label"));
			
			dish.setId((int) row.get("id"));
			dish.setPrice((Double) row.get("price"));
			dish.setDishTypeId((int) row.get("dish_type_id"));
			dish.setLabel((String) row.get("label"));
			dish.setLabel((String) row.get("description"));
			dish.setDishType(dishtypes);;
			
			dishes.add(dish);
			
		}
		
    	
		return dishes;

	}

}