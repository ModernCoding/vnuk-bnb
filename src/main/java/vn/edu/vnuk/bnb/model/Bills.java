package vn.edu.vnuk.bnb.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Bills {
	private Long id;
	
	@NotNull
	private double totalPrice;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date created;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date updated;
	
	@NotNull
	private Long bookingId;
	@NotNull
	private Long userId;
	
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
	
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
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
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
