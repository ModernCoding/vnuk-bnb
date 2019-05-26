package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class Room {
	private int id;
	
	@NotNull
	private int beds;
	@NotNull
	private int roomNumber;
	@NotNull
	private double price;
	@NotNull
	private boolean isSmoking;
	@NotNull
	private int roomTypeId;
	
	
	private RoomType roomType;
	
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

	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public int getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public boolean isSmoking() {
		return isSmoking;
	}
	public void setSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	}
	
}
