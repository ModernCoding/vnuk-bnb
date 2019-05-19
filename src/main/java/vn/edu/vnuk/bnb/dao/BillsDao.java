package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Bills;

@Repository
public class BillsDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BillsDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Bills task) throws SQLException{

        String sqlQuery = "INSERT INTO bills (booking_id, user_id, total_price, created, updated) VALUES (?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getBookingId(),
            								task.getUserId(),
            								task.getTotalPrice(),
            								task.getCreated(),
            								task.getUpdated()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<Bills> read() throws SQLException {

        try {
        
        	return this.jdbcTemplate.query(
        			"SELECT * FROM bills",
        			new BeanPropertyRowMapper<Bills>(Bills.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Bills read(Long id) throws SQLException{
        
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM bills where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Bills>(Bills.class)
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(Bills task) throws SQLException {
        String sqlQuery = "update bills set booking_id=?, user_id=?, total_price=?, created=?, updated=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							task.getBookingId(),
							task.getUserId(),
							task.getTotalPrice(),
							task.getCreated(),
							task.getUpdated(),
							task.getId()
					}
				);
            
            
            System.out.println("Bills successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from bills where id=?";

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
        
    	Bills task = this.read(id);
//       task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}