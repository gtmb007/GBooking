package com.gautam.service;

public interface BookingService {
	
	public Integer bookHotel(String userId, String hotelId, String vendorId, Integer noOfRooms) throws Exception;
	
	public Integer updateBooking(String userId, Integer bookingId, Integer noOfRooms) throws Exception;
	
	public Integer cancelBooking(String userId, Integer bookingId) throws Exception;
	
}
