package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Room;
import vn.edu.vnuk.bnb.model.RoomType;

public class RoomsRowMapper implements RowMapper<Room> {

	@Override
	public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Room room = new Room();
		RoomType roomtypes = new RoomType();
		
		roomtypes.setId(rs.getInt("room_type_id"));
		roomtypes.setLabel(rs.getString("label"));
		
		room.setId(rs.getInt("id"));
		room.setPrice(rs.getDouble("price"));
		room.setBeds(rs.getInt("beds"));
		room.setRoomTypeId(rs.getInt("room_type_id"));
		room.setRoomNumber(rs.getInt("room_number"));
		room.setSmoking(rs.getBoolean("is_smoking"));
		room.setRoomType(roomtypes);
		
		return room;
	}
	
	
	public List<Room> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Room> rooms = new ArrayList<Room>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		RoomType roomtypes = new RoomType();
			Room room = new Room();
			
			roomtypes.setId((int) row.get("room_type_id"));
			roomtypes.setLabel((String) row.get("label"));
			
			room.setId((int) row.get("id"));
			room.setPrice((Double) row.get("price"));
			room.setBeds((int) row.get("beds"));
			room.setRoomTypeId((int) row.get("room_type_id"));
			room.setRoomNumber((int) row.get("room_number"));
			room.setSmoking((boolean) row.get("is_smoking"));
			room.setRoomType(roomtypes);
			
			rooms.add(room);
			
		}
		
    	
		return rooms;

	}

}