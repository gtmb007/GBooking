package com.gautam.dao;

import java.util.Set;
import com.gautam.model.Booking;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

public interface HotelDAO {
	
	public String addVendor(Vendor vendor) throws Exception;
	
	public Vendor getVendor(String vendorId) throws Exception;
	
	public String addHotel(Hotel hotel) throws Exception;

	public Hotel getHotel(String hotelId) throws Exception;
	
	public String removeHotel(String hotelId) throws Exception;
	
	public String removeVendorFromHotel(String hotelId, String vendorId) throws Exception;
	
	public String addVendorToHotel(String hotelId, String vendorId) throws Exception;
	
	public Set<Hotel> searchHotelByNameKey(String key) throws Exception;
	
	public Set<Hotel> searchHotelByLocationKey(String key) throws Exception;
	
	public Booking getBooking(Integer bookingId) throws Exception;
	
	public Boolean validateBooking(String hotelId, String vendorId, Integer noOfRooms) throws Exception;
	
	public Integer bookHotel(String hotelId, String vendorId, Integer noOfRooms, Double amount) throws Exception;
	
	public Integer updateBooking(Integer bookingId, Integer noOfRooms, Double amount) throws Exception;
	
	public Integer cancelBooking(Integer bookingId) throws Exception;
	
}
