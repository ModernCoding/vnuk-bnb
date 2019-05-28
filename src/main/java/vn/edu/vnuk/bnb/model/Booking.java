package vn.edu.vnuk.bnb.model;

import java.sql.Date;


import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Booking {
	private int id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date checkIn;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date checkOut;
	private int quanlity;
	
	@NotNull
	private int userId;
	@NotNull
	private int roomId;
	
	private User user;
	private Room room;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
}
