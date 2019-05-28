package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Room;
import vn.edu.vnuk.bnb.model.Equipment;
import vn.edu.vnuk.bnb.model.RoomType;
import vn.edu.vnuk.bnb.model.RoomEquipment;

public class RoomsEquipmentsRowMapper implements RowMapper<RoomEquipment> {

	@Override
	public RoomEquipment mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RoomType roomtypes = new RoomType();
		Room room = new Room();
		RoomEquipment roomequipment= new RoomEquipment();
		Equipment equipment = new Equipment();
		
		roomtypes.setId(rs.getInt("room_type_id"));
		roomtypes.setLabel(rs.getString("label"));
		
		room.setId(rs.getInt("room_id"));
		room.setPrice(rs.getDouble("price"));
		room.setBeds(rs.getInt("beds"));
		room.setRoomTypeId(rs.getInt("room_type_id"));
		room.setRoomNumber(rs.getInt("room_number"));
		room.setSmoking(rs.getBoolean("is_smoking"));
		room.setRoomType(roomtypes);
		
		equipment.setId(rs.getInt("equipment_id"));
		equipment.setLabel(rs.getString("label"));
		
		roomequipment.setId(rs.getInt("id"));
		roomequipment.setRoomId(rs.getInt("room_id"));
		roomequipment.setEquipmentId(rs.getInt("equipment_id"));
		roomequipment.setRoom(room);
		roomequipment.setEquipment(equipment);
		
		
		return roomequipment;
	}
	
	
	public List<RoomEquipment> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<RoomEquipment> roomsequipment = new ArrayList<RoomEquipment>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Equipment equipment = new Equipment();
    		RoomType roomtypes = new RoomType();
    		RoomEquipment roomequipment= new RoomEquipment();
			Room room = new Room();
			
			roomtypes.setId((int) row.get("room_type_id"));
			roomtypes.setLabel((String) row.get("room_type_label"));
			
			equipment.setId((int) row.get("equipment_id"));
			equipment.setLabel((String) row.get("equipment_label"));
			
			room.setId((int) row.get("room_id"));
			room.setPrice((Double) row.get("room_price"));
			room.setBeds((int) row.get("room_beds"));
			room.setRoomTypeId((int) row.get("room_type_id"));
			room.setRoomNumber((int) row.get("room_number"));
			room.setSmoking((boolean) row.get("is_smoking"));
			room.setRoomType(roomtypes);
			
			roomequipment.setId((int) row.get("id"));
			roomequipment.setRoomId((int) row.get("room_id"));
			roomequipment.setRoom(room);
			roomequipment.setEquipmentId((int) row.get("equipment_id"));
			roomequipment.setEquipment(equipment);
			
			roomsequipment.add(roomequipment);
			
		}
		
    	
		return roomsequipment;

	}

}