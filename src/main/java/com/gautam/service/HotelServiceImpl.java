package com.gautam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.dao.HotelDAO;
import com.gautam.model.Booking;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

@Service(value="hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelDAO hotelDAO;
	
	@Override
	public String addHotel(Hotel hotel) throws Exception {
		String hotelId=hotelDAO.addHotel(hotel);
		if(hotelId==null) throw new Exception("Service.HOTEL_ALREADY_EXIST");
		return hotelId;
	}
	
	@Override
	public Hotel searchHotelById(String hotelId) throws Exception {
		Hotel hotel=hotelDAO.searchHotelById(hotelId);
		if(hotel==null) throw new Exception("Service.HOTEL_NOT_FOUND");
		return hotel;
	}
	
	@Override
	public Set<Hotel> searchHotelByNameKey(String key) throws Exception {
		Set<Hotel> hotels=hotelDAO.searchHotelByNameKey(key);
		if(hotels.isEmpty()) throw new Exception("Service.HOTEL_NOT_FOUND");
		return hotels;
	}
	
	@Override
	public Set<Hotel> searchHotelByLocationKey(String key) throws Exception {
		Set<Hotel> hotels=hotelDAO.searchHotelByLocationKey(key);
		if(hotels.isEmpty()) throw new Exception("Service.HOTEL_NOT_FOUND");
		return hotels;
	}
	
	@Override
	public String addVendor(Vendor vendor) throws Exception {
		String vendorId=hotelDAO.addVendor(vendor);
		if(vendorId==null) throw new Exception("Service.VENDOR_ALREADY_EXIST");
		return vendorId;
	}
	
	@Override
	public Booking getBooking(Integer bookingId) throws Exception {
		Booking booking=hotelDAO.getBooking(bookingId);
		if(booking==null) throw new Exception("Service.BOOKING_NOT_FOUND");
		return booking;
	}
	
	@Override
	public Integer bookHotel(String hotelId, String vendorId, Integer noOfRooms) throws Exception {
		Integer bookingId=hotelDAO.bookHotel(hotelId, vendorId, noOfRooms);
		if(bookingId==null) throw new Exception("Service.BOOKING_FAILED");
		return bookingId;
	}
	
	@Override
	public Integer updateHotel(Integer bookingId, Integer noOfRooms) throws Exception {
		Integer bId=hotelDAO.updateHotel(bookingId, noOfRooms);
		if(bId==null) throw new Exception("Service.BOOKING_UPDATION_FAILED");
		return bookingId;
	}
	
	@Override
	public Integer cancelHotel(Integer bookingId) throws Exception {
		Integer bId=hotelDAO.cancelHotel(bookingId);
		if(bId==null) throw new Exception("Service.BOOKING_CANCELATION_FAILED");
		return bookingId;
	}
	
}
