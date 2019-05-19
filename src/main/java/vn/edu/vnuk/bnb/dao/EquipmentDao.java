package vn.edu.vnuk.bnb.dao;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Equipment;

@Repository
public class EquipmentDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public EquipmentDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Equipment task) throws SQLException{

        String sqlQuery = "INSERT INTO equipments (label) VALUES (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {task.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<Equipment> read() throws SQLException {

        try {
        
        	return this.jdbcTemplate.query(
        			"SELECT * FROM equipments",
        			new BeanPropertyRowMapper<Equipment>(Equipment.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Equipment read(Long id) throws SQLException{
        
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM equipments where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Equipment>(Equipment.class)
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(Equipment task) throws SQLException {
        String sqlQuery = "update equipments set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
						task.getLabel(),
						task.getId()
					}
				);
            
            
            System.out.println("Equipment successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from equipments where id=?";

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
        
    	Equipment task = this.read(id);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}