package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Dishes;
import vn.edu.vnuk.bnb.model.DishTypes;

public class DishesRowMapper implements RowMapper<Dishes> {

	@Override
	public Dishes mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Dishes dish = new Dishes();
		DishTypes dishtypes = new DishTypes();
		
		dishtypes.setId(rs.getLong("dish_type_id"));
		dishtypes.setLabel(rs.getString("label"));
		
		dish.setId(rs.getLong("id"));
		dish.setPrice(rs.getDouble("price"));
		dish.setDishTypesId(rs.getLong("dish_type_id"));
		dish.setLabel(rs.getString("label"));
		dish.setLabel(rs.getString("description"));
		
		dish.setDishType(dishtypes);
		
		return dish;
	}
	
	
	public List<Dishes> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Dishes> dishes = new ArrayList<Dishes>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		DishTypes dishtypes = new DishTypes();
    		Dishes dish = new Dishes();
			
    		dishtypes.setId((Long) row.get("dish_type_id"));
    		dishtypes.setLabel((String) row.get("label"));
			
			dish.setId((Long) row.get("id"));
			dish.setPrice((Double) row.get("price"));
			dish.setDishTypesId((Long) row.get("dish_type_id"));
			dish.setLabel((String) row.get("label"));
			dish.setLabel((String) row.get("description"));
			dish.setDishType(dishtypes);;
			
			dishes.add(dish);
			
		}
		
    	
		return dishes;

	}

}