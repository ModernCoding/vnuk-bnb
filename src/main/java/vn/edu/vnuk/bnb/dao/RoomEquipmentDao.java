package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.RoomEquipment;
import vn.edu.vnuk.bnb.rowmapper.RoomsEquipmentsRowMapper;

@Repository
public class RoomEquipmentDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public RoomEquipmentDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(RoomEquipment task) throws SQLException{

        String sqlQuery = "INSERT INTO rooms_equipments (room_id, equipment_id) VALUES (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getRoomId(),
            								task.getEquipmentId()
            									}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<RoomEquipment> read(String roomId,String equipmentId) throws SQLException {

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as room_id"
    			+ "     , t02.price "
    			+ "     , t02.beds "
    			+ "     , t02.room_number "
    			+ "     , t02.is_smoking "
    			+ "     , t03.id as equipment_id"
				+ "     , t03.label "
				+ "  from rooms_equipments t01, rooms t02, equipments t03"
				//+ "join rooms_equipments t01 on t02.id = t01.room_id"
				+ " where t02.id = t01.room_id"
				+ " and t03.id = t01.equipment_id"
				+ " order by t03.id asc, t02.id asc, t01.id asc"
				+ ";"
		;

    	if (roomId != null && equipmentId != null) {
    		sqlQuery += String.format("   and t02.id = %s", roomId,"   and t03.id = %s",equipmentId);
    		sqlQuery += " order by t01.id asc;";
    	}

    	else {
    		sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
        try {
        	return new RoomsEquipmentsRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public RoomEquipment read(Long id) throws SQLException{
        
    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as room_id"
    			+ "     , t02.price "
    			+ "     , t02.beds "
    			+ "     , t02.room_number "
    			+ "     , t02.is_smoking "
    			+ "     , t03.id as equipment_id"
				+ "     , t03.label "
				+ "  from rooms_equipments t01, rooms t02, equipment t03"
				+ "where t01.id = ?"
				+ "and t02.id = t01.room_id"
				+ " and t03.id = t01.equipment_id";
    	
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new RoomsEquipmentsRowMapper()
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(RoomEquipment task) throws SQLException {
        String sqlQuery = "update rooms_equipments set room_id=?, equipment_id=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
						task.getRoomId(),
						task.getEquipmentId(),
						task.getId()
					}
				);
            
            
            System.out.println("RoomsEquipments successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from rooms_equipments where id=?";

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
        
    	RoomEquipment task = this.read(id);
//       task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}