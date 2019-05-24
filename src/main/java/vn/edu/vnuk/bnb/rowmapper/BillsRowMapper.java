package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Booking;
import vn.edu.vnuk.bnb.model.Bill;
import vn.edu.vnuk.bnb.model.User;
import vn.edu.vnuk.bnb.model.UserType;
import vn.edu.vnuk.bnb.model.Country;
import vn.edu.vnuk.bnb.model.IdentificationType;
import vn.edu.vnuk.bnb.model.Room;
import vn.edu.vnuk.bnb.model.RoomType;


public class BillsRowMapper implements RowMapper<Bill> {

	@Override
	public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Booking booking = new Booking();
		Room room = new Room();
		User user= new User();
		RoomType roomtype = new RoomType();
		UserType usertype = new UserType();
		Country country = new Country();
		IdentificationType identificationtype = new IdentificationType();
		Bill bill = new Bill();
		
		
		country.setId(rs.getLong("country_id"));
		country.setLabel(rs.getString("label"));
		
		usertype.setId(rs.getLong("user_type_id"));
		usertype.setLabel(rs.getString("label"));
		
		identificationtype.setId(rs.getLong("identification_type_id"));
		identificationtype.setLabel(rs.getString("label"));
		
		roomtype.setId(rs.getLong("room_type_id"));
		roomtype.setLabel(rs.getString("label"));
		
		room.setId(rs.getLong("room_id"));
		room.setPrice(rs.getDouble("price"));
		room.setBeds(rs.getInt("beds"));
		room.setRoomTypesId(rs.getLong("room_type_id"));
		room.setRoomNumber(rs.getInt("room_number"));
		room.setSmoking(rs.getBoolean("is_smoking"));
		room.setRoomTypes(roomtype);
		
		user.setId(rs.getLong("user_id"));
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
		
		booking.setId(rs.getLong("booking_id"));
		booking.setRoomId(rs.getLong("room_id"));
		booking.setUserId(rs.getLong("user_id"));
		booking.setCheckIn(rs.getDate("check_in"));
		booking.setCheckOut(rs.getDate("check_out"));
		booking.setQuanlity(rs.getInt("quanlity_of_people"));
		
		booking.setRoom(room);
		booking.setUser(user);
		
		bill.setId(rs.getLong("id"));
		bill.setBookingId(rs.getLong("booking_id"));
		bill.setUserId(rs.getLong("user_id"));
		bill.setTotalPrice(rs.getDouble("total_price"));
		bill.setCreated(rs.getDate("created"));
		bill.setUpdated(rs.getDate("updated"));
		
		bill.setBooking(booking);
		bill.setUser(user);
	
		return bill;
	}
	
	
	public List<Bill> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Bill> bills = new ArrayList<Bill>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Booking booking = new Booking();
    		Room room = new Room();
    		User user= new User();
    		RoomType roomtype = new RoomType();
    		UserType usertype = new UserType();
    		Country country = new Country();
    		IdentificationType identificationtype = new IdentificationType();
    		Bill bill = new Bill();
    		
			
    		roomtype.setId((Long) row.get("room_type_id"));
    		roomtype.setLabel((String) row.get("label"));
		
    		usertype.setId((Long) row.get("user_type_id"));
    		usertype.setLabel((String) row.get("label"));
			
    		identificationtype.setId((Long) row.get("identification_type_id"));
    		identificationtype.setLabel((String) row.get("label"));
			
    		country.setId((Long) row.get("country_id"));
    		country.setLabel((String) row.get("label"));
			
			room.setId((Long) row.get("room_id"));
			room.setPrice((Double) row.get("room_price"));
			room.setBeds((int) row.get("room_beds"));
			room.setRoomTypesId((Long) row.get("room_type_id"));
			room.setRoomNumber((int) row.get("room_number"));
			room.setSmoking((boolean) row.get("is_smoking"));
			room.setRoomTypes(roomtype);
			
			user.setId((Long) row.get("user_id"));
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
			
			booking.setId((Long) row.get("booking_id"));
			booking.setRoomId((Long) row.get("room_id"));
			booking.setUserId((Long) row.get("user_id"));
			booking.setCheckIn((java.sql.Date) row.get("check_in"));
			booking.setCheckOut((java.sql.Date) row.get("check_out"));
			booking.setQuanlity((int) row.get("quanlity_of_people"));
			
			booking.setRoom(room);
			booking.setUser(user);
			
			bill.setId((Long) row.get("id"));
			bill.setBookingId((Long) row.get("booking_id"));
			bill.setUserId((Long) row.get("user_id"));
			bill.setTotalPrice((double) row.get("total_price"));
			bill.setCreated((java.sql.Date) row.get("created"));
			bill.setUpdated((java.sql.Date) row.get("updated"));
			
			bill.setBooking(booking);
			bill.setUser(user);
			
			
			
			
			bills.add(bill);
			
		}
		
    	
		return bills;

	}

}