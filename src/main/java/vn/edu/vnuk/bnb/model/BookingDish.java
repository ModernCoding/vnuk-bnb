package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class BookingDish {
	private Long id;
	
	@NotNull
	private Long bookingId;
	@NotNull
	private Long dishId;
	
	private Booking bookings;
	private Dish dishes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Booking getBookings() {
		return bookings;
	}
	public void setBookings(Booking bookings) {
		this.bookings = bookings;
	}
	public Dish getDishes() {
		return dishes;
	}
	public void setDishes(Dish dishes) {
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
