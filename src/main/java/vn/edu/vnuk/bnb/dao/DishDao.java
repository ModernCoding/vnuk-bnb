package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Dish;
import vn.edu.vnuk.bnb.rowmapper.DishesRowMapper;


@Repository
public class DishDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public DishDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Dish task) throws SQLException{

        String sqlQuery = "INSERT INTO dishes (price, dish_type_id, label, description) VALUES (?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getPrice(),
            								task.getDishTypeId(),
            								task.getLabel(),
            								task.getDescription()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<Dish> read(String dishTypeId) throws SQLException {

    	String sqlQuery = "select t01.id"
    			+ "     , t01.price"
    			+ "     , t02.id as dish_type_id"
    			+ "     , t01.label"
    			+ "     , t01.description"
				+ "     , t02.label"
				+ "  from dishes t01, dish_types t02"
				+ " where t02.id = t01.dish_type_id"
		;

    	if (dishTypeId != null) {
    		sqlQuery += String.format("   and t02.id = %s", dishTypeId);
    		sqlQuery += " order by t01.id asc;";
    	}

    	else {
    		sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
        try {
        
        	return new DishesRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
    			

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Dish read(Long id) throws SQLException{
        
    	String sqlQuery = "select t01.id"
    			+ "     , t01.price"
    			+ "     , t02.id as dish_type_id"
    			+ "     , t01.label"
    			+ "     , t01.description"
				+ "     , t02.label "
				+ "  from dishes t01, dish_types t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.dish_type_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
			;
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new DishesRowMapper()
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(Dish task) throws SQLException {
        String sqlQuery = "update dishes set price=?, dish_type_id=?, label=?, description=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
						task.getPrice(),
						task.getDishTypeId(),
						task.getLabel(),
						task.getDescription(),
						task.getId()
					}
				);
            
            
            System.out.println("Dishes successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from dishes where id=?";

        try {

            System.out.println(
            		String.format(
            				"%s record successfully removed from DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {id}
        						)
        				)
        		);

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    //  OTHERS
    
    public void complete(Long id) throws SQLException{
        
    	Dish task = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}