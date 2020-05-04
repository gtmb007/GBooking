package com.gautam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.model.Booking;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

@Service(value="bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Integer bookHotel(String userId, String hotelId, String vendorId, Integer noOfRooms) throws Exception {
		Integer bookingId=null;
		if(hotelService.validateBooking(hotelId, vendorId, noOfRooms)) {
			Hotel hotel=hotelService.getHotel(hotelId);
			Vendor vendor=hotelService.getVendor(vendorId);
			Double amount=(hotel.getRoomCharge()-Double.parseDouble(vendor.getPromoCode().substring(4)))*noOfRooms;
			userService.payment(userId, amount);
			try {
				bookingId=hotelService.bookHotel(hotelId, vendorId, noOfRooms, amount);
				userService.addBooking(userId, bookingId);
			} catch(Exception e) {
				userService.rechargeWallet(userId, amount);
				throw new Exception(e.getMessage());
			}
		} 
		return bookingId;
	}
	
	@Override
	public Integer updateBooking(String userId, Integer bookingId, Integer noOfRooms) throws Exception {
		Booking booking=hotelService.getBooking(bookingId);
		Hotel hotel=hotelService.getHotel(booking.getHotelId());
		Vendor vendor=hotelService.getVendor(booking.getVendorId());
		Double baseAmount=hotel.getRoomCharge()-Double.parseDouble(vendor.getPromoCode().substring(4));
		Double newAmount=baseAmount*noOfRooms;
		Double oldAmount=baseAmount*booking.getNoOfRooms();
		userService.payment(userId, newAmount-oldAmount);
		try {
			bookingId=hotelService.updateBooking(bookingId, noOfRooms, newAmount);
		} catch(Exception e) {
			userService.rechargeWallet(userId, newAmount-oldAmount);
			throw new Exception(e.getMessage());
		}
		return bookingId;
	}
	
	@Override
	public Integer cancelBooking(String userId, Integer bookingId) throws Exception {
		Booking booking=hotelService.getBooking(bookingId);
		Hotel hotel=hotelService.getHotel(booking.getHotelId());
		Vendor vendor=hotelService.getVendor(booking.getVendorId());
		Double amount=(hotel.getRoomCharge()-Double.parseDouble(vendor.getPromoCode().substring(4)))*booking.getNoOfRooms();
		bookingId=hotelService.cancelBooking(bookingId);
		userService.rechargeWallet(userId, amount);
		return bookingId;
	}
	
}
