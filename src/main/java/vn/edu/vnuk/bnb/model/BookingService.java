package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;

public class BookingService {
	private int id;
	@NotNull
	private double price;
	@NotNull
	private int bookingId;
	@NotNull
	private int serviceId;
	
	private Booking bookings;
	private Service services;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
}
