package vn.edu.vnuk.bnb.model;

public class BookingsDishes {
	private Long id;
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
	
}
