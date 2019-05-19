package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Bills;
import vn.edu.vnuk.bnb.rowmapper.BillsRowMapper;

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
    public List<Bills> read(String bookingId,String userId) throws SQLException {

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as booking_id"
    			+ "     , t02.check_in "
    			+ "     , t02.check_out "
    			+ "     , t02.quanlity_of_people "
    			+ "     , t01.total_price"
    			+ "     , t03.id as user_id"
    			+ "     , t03.first_name "
    			+ "     , t03.middle_name "
    			+ "     , t03.last_name "
    			+ "     , t03.address "
    			+ "     , t03.email "
    			+ "     , t03.phone "
    			+ "     , t03.identification_number "
    			+ "     , t02.create_at "
    			+ "     , t02.update_at "
    			+ "     , t01.created"
    			+ "     , t01.updated"
				+ "  from bills t01, bookings t02, users t03"
				//+ "join rooms_equipments t01 on t02.id = t01.room_id"
				+ " where t02.id = t01.booking_id"
				+ " and t03.id = t01.user_id"
		;

    	if (bookingId != null && userId != null) {
    		sqlQuery += String.format("   and t02.id = %s", bookingId,"   and t03.id = %s",userId);
    		sqlQuery += " order by t01.id asc;";
    	}

    	else {
    		sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
        try {
        
        	return new BillsRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Bills read(Long id) throws SQLException{
        
    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as booking_id"
    			+ "     , t02.check_in "
    			+ "     , t02.check_out "
    			+ "     , t02.quanlity_of_people "
    			+ "     , t01.total_price"
    			+ "     , t03.id as user_id"
    			+ "     , t03.first_name "
    			+ "     , t03.middle_name "
    			+ "     , t03.last_name "
    			+ "     , t03.address "
    			+ "     , t03.email "
    			+ "     , t03.phone "
    			+ "     , t03.identification_number "
    			+ "     , t02.create_at "
    			+ "     , t02.update_at "
    			+ "     , t01.created"
    			+ "     , t01.updated"
				+ "  from bills t01, bookings t02, users t03"
				+ "	 where t01.id = ?"
				+ "  and t02.id = t01.booking_id"
				+ "  and t03.id = t01.user_id"
				;
    	
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BillsRowMapper()
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