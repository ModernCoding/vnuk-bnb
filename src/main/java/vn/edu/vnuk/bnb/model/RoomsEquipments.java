package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class RoomsEquipments {
	private Long id;
	
	@NotNull
	private Long roomId;
	@NotNull
	private Long equipmentId;
	
	private Rooms room;
	
	private Equipment equipment;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Rooms getRoom() {
		return room;
	}
	public void setRoom(Rooms room) {
		this.room = room;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	
}
