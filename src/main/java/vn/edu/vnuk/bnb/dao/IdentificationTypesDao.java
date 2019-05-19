package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.IdentificationTypes;

@Repository
public class IdentificationTypesDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public IdentificationTypesDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(IdentificationTypes task) throws SQLException{

        String sqlQuery = "INSERT INTO identification_types (label) VALUES (?)";

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
    public List<IdentificationTypes> read() throws SQLException {

        try {
        
        	return this.jdbcTemplate.query(
        			"SELECT * FROM identification_types",
        			new BeanPropertyRowMapper<IdentificationTypes>(IdentificationTypes.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public IdentificationTypes read(Long id) throws SQLException{
        
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM identification_types where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<IdentificationTypes>(IdentificationTypes.class)
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(IdentificationTypes task) throws SQLException {
        String sqlQuery = "update identification_types set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
						task.getLabel(),
						task.getId()
					}
				);
            
            
            System.out.println("IdentificationTypes successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from identification_types where id=?";

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
        
    	IdentificationTypes task = this.read(id);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(task);
        
    }
    
}