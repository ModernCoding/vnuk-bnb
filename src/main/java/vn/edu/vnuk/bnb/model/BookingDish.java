package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class BookingDish {
	private int id;
	
	@NotNull
	private int bookingId;
	@NotNull
	private int dishId;
	
	private Booking bookings;
	private Dish dishes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	
}
