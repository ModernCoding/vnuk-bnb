package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class Room {
	private Long id;
	
	@NotNull
	private int beds;
	@NotNull
	private int roomNumber;
	@NotNull
	private double price;
	@NotNull
	private boolean isSmoking;
	@NotNull
	private Long roomTypesId;
	
	
	private RoomType roomTypes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public RoomType getRoomTypes() {
		return roomTypes;
	}
	public void setRoomTypes(RoomType roomTypes) {
		this.roomTypes = roomTypes;
	}
	public Long getRoomTypesId() {
		return roomTypesId;
	}
	public void setRoomTypesId(Long roomTypesId) {
		this.roomTypesId = roomTypesId;
	}
	
}
