package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Bookings;

@Repository
public class BookingsDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BookingsDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Bookings task) throws SQLException{

        String sqlQuery = "INSERT INTO bookings (room_id, user_id, check_in, check_out, quanlity_of_people) VALUES (?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getRoomId(),
            								task.getUserId(),
            								task.getCheckIn(),
            								task.getCheckOut(),
            								task.getQuanlity()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<Bookings> read() throws SQLException {

        try {
        
        	return this.jdbcTemplate.query(
        			"SELECT * FROM bookings",
        			new BeanPropertyRowMapper<Bookings>(Bookings.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Bookings read(Long id) throws SQLException{
        
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM bookings where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Bookings>(Bookings.class)
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(Bookings task) throws SQLException {
        String sqlQuery = "update bookings set room_id=?, user_id=?, check_in=?, check_out=?, quanlity_of_people=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							task.getRoomId(),
							task.getUserId(),
							task.getCheckIn(),
							task.getCheckOut(),
							task.getQuanlity(),
						task.getId()
					}
				);
            
            
            System.out.println("Bookings successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from bookings where id=?";

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
        
    	Bookings task = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}