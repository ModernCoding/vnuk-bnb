package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.BookingsServices;
import vn.edu.vnuk.bnb.rowmapper.BookingsServicesRowMapper;

@Repository
public class BookingsServicesDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BookingsServicesDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(BookingsServices task) throws SQLException{

        String sqlQuery = "INSERT INTO bookings_services (booking_id, service_id,price) VALUES (?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getBookingId(),
            								task.getServiceId(),
            								task.getPrice()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<BookingsServices> read(String bookingId,String serviceId) throws SQLException {

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as booking_id"
    			+ "     , t02.check_in "
    			+ "     , t02.check_out "
    			+ "     , t02.quanlity_of_people "
    			+ "     , t03.id as service_id"
				+ "     , t03.label "
				+ "     , t01.price "
				+ "  from bookings_services t01, bookings t02, services t03"
				//+ "join rooms_equipments t01 on t02.id = t01.room_id"
				+ " where t02.id = t01.booking_id"
				+ " and t03.id = t01.service_id"
		;

    	if (bookingId != null && serviceId != null) {
    		sqlQuery += String.format("   and t02.id = %s", bookingId,"   and t03.id = %s",serviceId);
    		sqlQuery += " order by t01.id asc;";
    	}

    	else {
    		sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
        try {
        
        	return new BookingsServicesRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public BookingsServices read(Long id) throws SQLException{
        
    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as booking_id"
    			+ "     , t02.check_in "
    			+ "     , t02.check_out "
    			+ "     , t02.quanlity_of_people "
    			+ "     , t03.id as service_id"
				+ "     , t03.label "
				+ "     , t01.price "
				+ "  from bookings_services t01, bookings t02, services t03"
				+ "	 where t01.id = ?"
				+ "  and t02.id = t01.booking_id"
				+ "  and t03.id = t01.service_id"
				+ " order by t03.id asc, t02.id asc, t01.id asc"
				+ ";"
				;
    	
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingsServicesRowMapper()
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(BookingsServices task) throws SQLException {
        String sqlQuery = "update bookings_services set booking_id=?, service_id=?,price=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							task.getBookingId(),
							task.getServiceId(),
							task.getPrice(),
						task.getId()
					}
				);
            
            
            System.out.println("BookingsServices successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from bookings_services where id=?";

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
        
    	BookingsServices task = this.read(id);
//       task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}