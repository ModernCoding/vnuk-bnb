package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.BookingsDishes;

@Repository
public class BookingsDishesDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BookingsDishesDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(BookingsDishes task) throws SQLException{

        String sqlQuery = "INSERT INTO bookings_dishes (booking_id, dishe_id) VALUES (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getBookingId(),
            								task.getDishId()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<BookingsDishes> read() throws SQLException {

        try {
        
        	return this.jdbcTemplate.query(
        			"SELECT * FROM bookings_dishes",
        			new BeanPropertyRowMapper<BookingsDishes>(BookingsDishes.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public BookingsDishes read(Long id) throws SQLException{
        
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM bookings_dishes where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<BookingsDishes>(BookingsDishes.class)
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(BookingsDishes task) throws SQLException {
        String sqlQuery = "update bookings_dishes set booking_id=?, dishe_id=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							task.getBookingId(),
							task.getDishId(),
							task.getId()
					}
				);
            
            
            System.out.println("BookingsDishes successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from bookings_dishes where id=?";

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
        
    	BookingsDishes task = this.read(id);
//       task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}