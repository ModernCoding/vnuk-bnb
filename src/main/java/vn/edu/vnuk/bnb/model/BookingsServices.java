package vn.edu.vnuk.bnb.model;

public class BookingsServices {
	private Long id;
	private double price;
	private Bookings bookings;
	private Services services;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Bookings getBookings() {
		return bookings;
	}
	public void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}
	public Services getServices() {
		return services;
	}
	public void setServices(Services services) {
		this.services = services;
	}
	
}
