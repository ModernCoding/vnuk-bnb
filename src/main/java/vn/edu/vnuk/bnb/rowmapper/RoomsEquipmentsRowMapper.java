package vn.edu.vnuk.bnb.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.bnb.model.Rooms;
import vn.edu.vnuk.bnb.model.Equipment;
import vn.edu.vnuk.bnb.model.RoomTypes;
import vn.edu.vnuk.bnb.model.RoomsEquipments;

public class RoomsEquipmentsRowMapper implements RowMapper<RoomsEquipments> {

	@Override
	public RoomsEquipments mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RoomTypes roomtypes = new RoomTypes();
		Rooms room = new Rooms();
		RoomsEquipments roomequipment= new RoomsEquipments();
		Equipment equipment = new Equipment();
		
		roomtypes.setId(rs.getLong("room_type_id"));
		roomtypes.setLabel(rs.getString("label"));
		
		room.setId(rs.getLong("room_id"));
		room.setPrice(rs.getDouble("price"));
		room.setBeds(rs.getInt("beds"));
		room.setRoomTypesId(rs.getLong("room_type_id"));
		room.setRoomNumber(rs.getInt("room_number"));
		room.setSmoking(rs.getBoolean("is_smoking"));
		room.setRoomTypes(roomtypes);
		
		equipment.setId(rs.getLong("equipment_id"));
		equipment.setLabel(rs.getString("label"));
		
		roomequipment.setId(rs.getLong("id"));
		roomequipment.setRoomId(rs.getLong("room_id"));
		roomequipment.setEquipmentId(rs.getLong("equipment_id"));
		roomequipment.setRoom(room);
		roomequipment.setEquipment(equipment);
		
		
		return roomequipment;
	}
	
	
	public List<RoomsEquipments> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<RoomsEquipments> roomsequipment = new ArrayList<RoomsEquipments>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Equipment equipment = new Equipment();
    		RoomTypes roomtypes = new RoomTypes();
    		RoomsEquipments roomequipment= new RoomsEquipments();
			Rooms room = new Rooms();
			
			roomtypes.setId((Long) row.get("room_type_id"));
			roomtypes.setLabel((String) row.get("room_type_label"));
			
			equipment.setId((Long) row.get("equipment_id"));
			equipment.setLabel((String) row.get("equipment_label"));
			
			room.setId((Long) row.get("room_id"));
			room.setPrice((Double) row.get("room_price"));
			room.setBeds((int) row.get("room_beds"));
			room.setRoomTypesId((Long) row.get("room_type_id"));
			room.setRoomNumber((int) row.get("room_number"));
			room.setSmoking((boolean) row.get("is_smoking"));
			room.setRoomTypes(roomtypes);
			
			roomequipment.setId((Long) row.get("id"));
			roomequipment.setRoomId((Long) row.get("room_id"));
			roomequipment.setRoom(room);
			roomequipment.setEquipmentId((Long) row.get("equipment_id"));
			roomequipment.setEquipment(equipment);
			
			roomsequipment.add(roomequipment);
			
		}
		
    	
		return roomsequipment;

	}

}