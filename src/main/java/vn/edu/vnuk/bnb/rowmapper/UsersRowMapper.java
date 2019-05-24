package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.User;
import vn.edu.vnuk.bnb.model.UserType;
import vn.edu.vnuk.bnb.model.Country;
import vn.edu.vnuk.bnb.model.IdentificationType;

public class UsersRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserType usertype = new UserType();
		User user = new User();
		IdentificationType identificationtype = new IdentificationType();
		Country country = new Country();
		
		country.setId(rs.getLong("country_id"));
		country.setLabel(rs.getString("label"));
		
		usertype.setId(rs.getLong("user_type_id"));
		usertype.setLabel(rs.getString("label"));
		
		identificationtype.setId(rs.getLong("identification_type_id"));
		identificationtype.setLabel(rs.getString("label"));
		
		user.setId(rs.getLong("id"));
		user.setUserTypesId(rs.getLong("user_type_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setMiddleName(rs.getString("middle_name"));
		user.setLastName(rs.getString("last_name"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setCreateAt(rs.getDate("create_at"));
		user.setUpdateAt(rs.getDate("update_at"));
		
		user.setUserTypes(usertype);
		user.setCountry(country);
		user.setIdentificationTypes(identificationtype);
		
		return user;
	}
	
	
	public List<User> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<User> users = new ArrayList<User>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		UserType usertype = new UserType();
    		User user = new User();
    		IdentificationType identificationtype = new IdentificationType();
    		Country country = new Country();
			
    		usertype.setId((Long) row.get("user_type_id"));
    		usertype.setLabel((String) row.get("label"));
			
    		country.setId((Long) row.get("country_id"));
    		country.setLabel((String) row.get("label"));
    		
    		identificationtype.setId((Long) row.get("identification_type_id"));
    		identificationtype.setLabel((String) row.get("label"));
    		
    		user.setId((Long) row.get("id"));
    		user.setUserTypesId((Long) row.get("user_type_id"));
    		user.setFirstName((String) row.get("first_name"));
    		user.setMiddleName((String) row.get("middle_name"));
    		user.setLastName((String) row.get("last_name"));
    		user.setAddress((String) row.get("address"));
    		user.setEmail((String) row.get("email"));
    		user.setPhone((String) row.get("phone"));
    		user.setCreateAt((java.sql.Date) row.get("create_at"));
			user.setUpdateAt((java.sql.Date) row.get("update_at"));
    		
    		user.setUserTypes(usertype);
    		user.setCountry(country);
    		user.setIdentificationTypes(identificationtype);
			
			users.add(user);
			
		}
		
    	
		return users;

	}

}