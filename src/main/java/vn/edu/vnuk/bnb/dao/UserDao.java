package vn.edu.vnuk.bnb.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Users;
import vn.edu.vnuk.bnb.rowmapper.UsersRowMapper;

@Repository
public class UserDao {
	
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Users task) throws SQLException{

        String sqlQuery = "INSERT INTO users (user_type_id, first_name, middle_name, last_name, address, email, phone, identification_number, create_at, update_at, identification_type_id, country_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new record in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								task.getUserTypesId(),
            								task.getFirstName(),
            								task.getMiddleName(),
            								task.getLastName(),
            								task.getAddress(),
            								task.getEmail(),
            								task.getPhone(),
            								task.getIdentificationNumber(),
            								task.getCreateAt(),
            								task.getUpdateAt(),
            								task.getIdentificationTypesId(),
            								task.getCountryId()
            								}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Tasks)
    public List<Users> read(String userTypesId,String identificationTypesId, String countryId) throws SQLException {

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as user_type_id"
    			+ "     , t01.first_name "
    			+ "     , t01.middle_name "
    			+ "     , t01.last_name "
    			+ "     , t01.address "
    			+ "     , t01.email "
    			+ "     , t01.phone "
    			+ "     , t02.label "
    			+ "     , t03.id as identification_type_id"
				+ "     , t03.label "
				+ "     , t04.id as country_id"
				+ "     , t04.label "
				+ "  from users t01, user_types t02, identification_types t03, countries t04"
				//+ "join rooms_equipments t01 on t02.id = t01.room_id"
				+ " where t02.id = t01.user_type_id"
				+ " and t03.id = t01.identification_type_id"
				+ " and t04.id = t01.country_id"
				;
    	if (userTypesId != null && identificationTypesId != null && countryId != null ) {
    		sqlQuery += String.format("   and t02.id = %s", userTypesId,"   and t03.id = %s",identificationTypesId ,"   and t04.id = %s",countryId );
    		sqlQuery += " order by t01.id asc;";
    	}

    	else {
    		sqlQuery += " order by t04.id asc, t03.id asc, t02.id asc, t01.id asc;";
    	}
    	
        try {
        
        	return new UsersRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));        	
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single Task)
    public Users read(Long id) throws SQLException{
        
    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as user_type_id"
    			+ "     , t01.first_name "
    			+ "     , t01.middle_name "
    			+ "     , t01.last_name "
    			+ "     , t01.address "
    			+ "     , t01.email "
    			+ "     , t01.phone "
    			+ "     , t02.label "
    			+ "     , t03.id as identification_type_id"
				+ "     , t03.label "
				+ "     , t04.id as country_id"
				+ "     , t04.label "
				+ "  from users t01, user_types t02, identification_types t03, countries t04"
				+ "	 where t01.id = ?"
				+ "  and t02.id = t01.user_type_id"
				+ "  and t03.id = t01.identification_type_id"
				+ "  and t04.id = t01.country_id";
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new UsersRowMapper()
        	);
    	
    
    }  

    
    //  UPDATE
    public void update(Users task) throws SQLException {
        String sqlQuery = "update users set user_type_id=?, first_name=?, middle_name=?, last_name=?, address=?, email=?, phone=?, identification_number=?, create_at=?, update_at=?, identification_type_id=?, country_id=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							task.getUserTypesId(),
							task.getFirstName(),
							task.getMiddleName(),
							task.getLastName(),
							task.getAddress(),
							task.getEmail(),
							task.getPhone(),
							task.getIdentificationNumber(),
							task.getCreateAt(),
							task.getUpdateAt(),
							task.getIdentificationTypesId(),
							task.getCountryId(),
							task.getId()
					}
				);
            
            
            System.out.println("Users successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from users where id=?";

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
        
    	Users task = this.read(id);
//        task.setIsComplete(true);
        task.setCreateAt(new Date(System.currentTimeMillis()));
        task.setUpdateAt(new Date(System.currentTimeMillis()));
        this.update(task);
        
    }
    
}