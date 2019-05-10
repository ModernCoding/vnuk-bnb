package vn.edu.vnuk.bnb.model;

public class Rooms {
	private int id;
	private int beds;
	private int roomNumber;
	private double price;
	private boolean isSmoking;
	private RoomTypes roomTypes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isSmoking() {
		return isSmoking;
	}
	public void setSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	}
	public RoomTypes getRoomTypes() {
		return roomTypes;
	}
	public void setRoomTypes(RoomTypes roomTypes) {
		this.roomTypes = roomTypes;
	}
	
}
