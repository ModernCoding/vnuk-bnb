package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class BookingsDishes {
	private Long id;
	
	@NotNull
	private Long bookingId;
	@NotNull
	private Long dishId;
	
	private Bookings bookings;
	private Dishes dishes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Bookings getBookings() {
		return bookings;
	}
	public void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}
	public Dishes getDishes() {
		return dishes;
	}
	public void setDishes(Dishes dishes) {
		this.dishes = dishes;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getDishId() {
		return dishId;
	}
	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}
	
}
