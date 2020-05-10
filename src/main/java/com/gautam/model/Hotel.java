package com.gautam.model;

import java.util.Set;

public class Hotel {
	
	private String hotelId;
	private String hotelName;
	private String location;
	private Integer roomsAvailable;
	private Double roomCharge;
	private String amenities;
	private String hotelStatus;
	private Set<Vendor> vendors;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getRoomsAvailable() {
		return roomsAvailable;
	}
	public void setRoomsAvailable(Integer roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
	public Double getRoomCharge() {
		return roomCharge;
	}
	public void setRoomCharge(Double roomCharge) {
		this.roomCharge = roomCharge;
	}
	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public String getHotelStatus() {
		return hotelStatus;
	}
	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
	}
	public Set<Vendor> getVendors() {
		return vendors;
	}
	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}
	
}
