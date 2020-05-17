package com.gautam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.gautam.model.Booking;
import com.gautam.model.Customer;
import com.gautam.model.FinalHotel;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

public interface HotelService {
	
	public String addVendor(Vendor vendor) throws Exception;
	
	public Vendor getVendor(String vendorId) throws Exception;
	
	public String addHotel(Hotel hotel) throws Exception;
	
	public Hotel getHotel(String hotelId) throws Exception;
	
	public Set<Hotel> getHotels() throws Exception;
	
	public String removeHotel(String hotelId) throws Exception;
	
	public String removeVendorFromHotel(String hotelId, String vendorId) throws Exception;
	
	public String addVendorToHotel(String hotelId, String vendorId) throws Exception;
	
	public Set<FinalHotel> toFinalHotels(Set<Hotel> hotels, LocalDate date) throws Exception ;
	
	public Set<FinalHotel> searchHotelByNameKey(String key, LocalDate date) throws Exception;
	
	public Set<FinalHotel> searchHotelByLocationKey(String key, LocalDate date) throws Exception;
	
	public Booking getBooking(Integer bookingId) throws Exception;
	
	public Boolean validateBooking(String hotelId, String vendorId, LocalDate date, Integer noOfCustomers) throws Exception;
	
	public Integer bookHotel(String hotelId, String vendorId, LocalDate date, List<Customer> customers, Double amount) throws Exception;
	
	public Integer updateBooking(Integer bookingId, List<Customer> customers, Double amount) throws Exception;
	
	public Integer cancelBooking(Integer bookingId) throws Exception;
	
}
