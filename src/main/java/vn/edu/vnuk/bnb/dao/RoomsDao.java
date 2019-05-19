package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Rooms;
import vn.edu.vnuk.bnb.rowmapper.RoomsRowMapper;

@Repository
public class RoomsDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public RoomsDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Rooms task) throws SQLException{

        String sqlQuery = "INSERT INTO rooms (price, beds, room_type_id, room_number, is_smoking) VALUES (?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getPrice(),
            								task.getBeds(),
            								task.getRoomTypesId(),
            								task.getRoomNumber(),
            								task.isSmoking()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<Rooms> read(String roomTypesId) throws SQLException {

    	String sqlQuery = "select t01.id"
    			+ "     , t01.price"
    			+ "     , t01.beds"
    			+ "     , t02.id as room_type_id"
    			+ "     , t01.room_number"
    			+ "     , t01.is_smoking"
				+ "     , t02.label"
				+ "  from rooms t01, room_types t02"
				+ " where t02.id = t01.room_type_id"
		;

    	if (roomTypesId != null) {
    		sqlQuery += String.format("   and t02.id = %s", roomTypesId);
    		sqlQuery += " order by t01.id asc;";
    	}

    	else {
    		sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
        try {
        
        	return new RoomsRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
    			

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Rooms read(Long id) throws SQLException{
        
    	String sqlQuery = "select t01.id"
    			+ "     , t01.price"
    			+ "     , t01.beds"
    			+ "     , t02.id as room_type_id"
    			+ "     , t01.room_number"
    			+ "     , t01.is_smoking"
				+ "     , t02.label"
				+ "  from rooms t01, room_types t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.room_type_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;
    	
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new RoomsRowMapper()
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(Rooms task) throws SQLException {
        String sqlQuery = "update rooms set price=?, beds=?, room_type_id=?, room_number=?, is_smoking=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
						task.getPrice(),
						task.getBeds(),
						task.getRoomTypesId(),
						task.getRoomNumber(),
						task.isSmoking(),
						task.getId()
					}
				);
            
            
            System.out.println("Rooms successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from rooms where id=?";

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
        
    	Rooms task = this.read(id);
        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}