package com.gautam.service;

import java.time.LocalDate;
import java.util.List;

import com.gautam.model.Customer;
import com.gautam.model.FinalHotel;

public interface BookingService {
	
	public Integer bookHotel(String userId, FinalHotel fHotel, String vendorId, LocalDate date, List<Customer> customers) throws Exception;
	
	public Integer updateBooking(String userId, Integer bookingId, List<Customer> customers) throws Exception;
	
	public Integer cancelBooking(String userId, Integer bookingId) throws Exception;
	
}
