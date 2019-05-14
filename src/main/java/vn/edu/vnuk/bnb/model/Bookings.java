package vn.edu.vnuk.bnb.model;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Bookings {
	private Long id;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar checkIn;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar checkOut;
	private int quanlity;
	private Users user;
	private Rooms room;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Calendar getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Calendar checkIn) {
		this.checkIn = checkIn;
	}
	public Calendar getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Calendar checkOut) {
		this.checkOut = checkOut;
	}
	public int getQuanlity() {
		return quanlity;
	}
	public void setQuanlity(int quanlity) {
		this.quanlity = quanlity;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Rooms getRoom() {
		return room;
	}
	public void setRoom(Rooms room) {
		this.room = room;
	}
	
}
