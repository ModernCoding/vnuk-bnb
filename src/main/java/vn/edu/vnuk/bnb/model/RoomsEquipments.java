package vn.edu.vnuk.bnb.model;

public class RoomsEquipments {
	private int id;
	private Rooms room;
	private Equipment equipment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
}
