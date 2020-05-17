package com.gautam.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="booking")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Integer bookingId;
	
	@Column(name="hotel_id")
	private String hotelId;
	
	@Column(name="hotel_name")
	private String hotelName;
	
	@Column(name="vendor_id")
	private String vendorId;
	
	@Column(name="vendor_name")
	private String vendorName;
	
	@Column(name="booking_date")
	private LocalDate bookingDate;
	
	@Column(name="booked_on")
	private LocalDateTime bookedOn;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="booking_id")
	private List<CustomerEntity> customers;
	
	@Column(name="amount")
	private Double amount;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDateTime getBookedOn() {
		return bookedOn;
	}

	public void setBookedOn(LocalDateTime bookedOn) {
		this.bookedOn = bookedOn;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}
	
}
