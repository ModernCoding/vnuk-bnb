package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class RoomEquipment {
	private int id;
	
	@NotNull
	private int roomId;
	@NotNull
	private int equipmentId;
	
	private Room room;
	
	private Equipment equipment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	
}
