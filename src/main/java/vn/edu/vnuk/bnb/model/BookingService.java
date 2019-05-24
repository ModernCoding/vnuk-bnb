package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class BookingService {
	private Long id;
	@NotNull
	private double price;
	@NotNull
	private Long bookingId;
	@NotNull
	private Long serviceId;
	
	private Booking bookings;
	private Service services;
	
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
	public Booking getBookings() {
		return bookings;
	}
	public void setBookings(Booking bookings) {
		this.bookings = bookings;
	}
	public Service getServices() {
		return services;
	}
	public void setServices(Service services) {
		this.services = services;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	
}