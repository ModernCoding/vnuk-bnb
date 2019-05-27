package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Booking;
import vn.edu.vnuk.bnb.model.Room;
import vn.edu.vnuk.bnb.model.User;
import vn.edu.vnuk.bnb.model.UserType;
import vn.edu.vnuk.bnb.model.Country;
import vn.edu.vnuk.bnb.model.IdentificationType;
import vn.edu.vnuk.bnb.model.RoomType;

public class BookingsRowMapper implements RowMapper<Booking> {

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Booking booking = new Booking();
		Room room = new Room();
		User user= new User();
		UserType usertype = new UserType();
		Country country = new Country();
		IdentificationType identificationtype = new IdentificationType();
		RoomType roomtype = new RoomType();
		
		country.setId(rs.getInt("country_id"));
		country.setLabel(rs.getString("label"));
		
		usertype.setId(rs.getInt("user_type_id"));
		usertype.setLabel(rs.getString("label"));
		
		identificationtype.setId(rs.getInt("identification_type_id"));
		identificationtype.setLabel(rs.getString("label"));
		
		roomtype.setId(rs.getInt("room_type_id"));
		roomtype.setLabel(rs.getString("label"));
		
		room.setId(rs.getInt("id"));
		room.setPrice(rs.getDouble("price"));
		room.setBeds(rs.getInt("beds"));
		room.setRoomTypeId(rs.getInt("room_type_id"));
		room.setRoomNumber(rs.getInt("room_number"));
		room.setSmoking(rs.getBoolean("is_smoking"));
		room.setRoomType(roomtype);
		
		user.setId(rs.getInt("id"));
		user.setUserTypesId(rs.getInt("user_type_id"));
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
		
		booking.setId(rs.getInt("id"));
		booking.setRoomId(rs.getInt("room_id"));
		booking.setUserId(rs.getInt("user_id"));
		booking.setCheckIn(rs.getDate("check_in"));
		booking.setCheckOut(rs.getDate("check_out"));
		booking.setQuanlity(rs.getInt("quanlity_of_people"));
		
		booking.setRoom(room);
		booking.setUser(user);
		
		return booking;
	}
	
	
	public List<Booking> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Booking> bookings = new ArrayList<Booking>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Booking booking = new Booking();
    		Room room = new Room();
    		User user= new User();
    		UserType usertype = new UserType();
    		Country country = new Country();
    		IdentificationType identificationtype = new IdentificationType();
    		RoomType roomtype = new RoomType();
			
    		roomtype.setId((int) row.get("room_type_id"));
    		roomtype.setLabel((String) row.get("label"));
			
    		usertype.setId((int) row.get("user_type_id"));
    		usertype.setLabel((String) row.get("label"));
			
    		identificationtype.setId((int) row.get("identification_type_id"));
    		identificationtype.setLabel((String) row.get("label"));
			
    		country.setId((int) row.get("country_id"));
    		country.setLabel((String) row.get("label"));
			
			room.setId((int) row.get("room_id"));
			room.setPrice((Double) row.get("price"));
			room.setBeds((int) row.get("rbeds"));
			room.setRoomTypeId((int) row.get("room_type_id"));
			room.setRoomNumber((int) row.get("room_number"));
			room.setSmoking((boolean) row.get("is_smoking"));
			room.setRoomType(roomtype);
			
			user.setId((int) row.get("user_id"));
			user.setUserTypesId((int) row.get("user_type_id"));
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
			
			booking.setId((int) row.get("id"));
			booking.setRoomId((int) row.get("room_id"));
			booking.setUserId((int) row.get("user_id"));
			booking.setCheckIn((java.sql.Date) row.get("check_in"));
			booking.setCheckOut((java.sql.Date) row.get("check_out"));
			booking.setQuanlity((int) row.get("quanlity_of_people"));
			
			booking.setRoom(room);
			booking.setUser(user);
			
			bookings.add(booking);
			
		}
		
    	
		return bookings;

	}

}