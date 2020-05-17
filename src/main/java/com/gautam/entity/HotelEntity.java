package com.gautam.entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name="hotel")
public class HotelEntity {

	@Id
	@Column(name="hotel_id")
	private String hotelId;
	
	@Column(name="hotel_name")
	private String hotelName;
	
	@Column(name="location")
	private String location;
	
	@Column(name="total_rooms")
	private Integer totalRooms;
	
	@ElementCollection
//	@CollectionTable(name = "room_map", 
//	  joinColumns = {@JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")})
	@MapKeyColumn(name = "date")
	@Column(name = "available_rooms")
	private Map<LocalDate, Integer> roomMap;
	
	@Column(name="room_charge")
	private Double roomCharge;
	
	@Column(name="amenities")
	private String amenities;
	
	@Column(name="hotel_status")
	private String hotelStatus;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="hotel_vendor",
		joinColumns=@JoinColumn(name="hotel_id"),
		inverseJoinColumns=@JoinColumn(name="vendor_id")
	)
	private Set<VendorEntity> vendors;

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

	public Integer getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}

	public Map<LocalDate, Integer> getRoomMap() {
		return roomMap;
	}

	public void setRoomMap(Map<LocalDate, Integer> roomMap) {
		this.roomMap = roomMap;
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

	public Set<VendorEntity> getVendors() {
		return vendors;
	}

	public void setVendors(Set<VendorEntity> vendors) {
		this.vendors = vendors;
	}
	
}
