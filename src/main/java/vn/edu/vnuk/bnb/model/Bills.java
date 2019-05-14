package vn.edu.vnuk.bnb.model;

import java.util.Calendar;


import org.springframework.format.annotation.DateTimeFormat;

public class Bills {
	private Long id;
	private double totalPrice;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar created;
	private Calendar updated;
	private Bookings booking;
	private Users user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Calendar getCreated() {
		return created;
	}
	public void setCreated(Calendar created) {
		this.created = created;
	}
	public Calendar getUpdated() {
		return updated;
	}
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}
	public Bookings getBooking() {
		return booking;
	}
	public void setBooking(Bookings booking) {
		this.booking = booking;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
