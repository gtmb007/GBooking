package com.gautam.dao;

import java.util.Set;
import com.gautam.model.Booking;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

public interface HotelDAO {
	
	public String addHotel(Hotel hotel) throws Exception;

	public Hotel searchHotelById(String hotelId) throws Exception;
	
	public Set<Hotel> searchHotelByNameKey(String key) throws Exception;
	
	public Set<Hotel> searchHotelByLocationKey(String key) throws Exception;
	
	public String addVendor(Vendor vendor) throws Exception;
	
	public Booking getBooking(Integer bookingId) throws Exception;
	
	public Integer bookHotel(String hotelId, String vendorId, Integer noOfRooms) throws Exception;
	
	public Integer updateHotel(Integer bookingId, Integer noOfRooms) throws Exception;
	
	public Integer cancelHotel(Integer bookingId) throws Exception;
	
}
