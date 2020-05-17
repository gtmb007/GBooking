package com.gautam.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.model.Booking;
import com.gautam.model.Customer;
import com.gautam.model.FinalHotel;
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
	public Integer bookHotel(String userId, FinalHotel fHotel, String vendorId, LocalDate date, List<Customer> customers) throws Exception {
		Integer bookingId=null;
		String hotelId=fHotel.getHotelId();
		if(hotelService.validateBooking(hotelId, vendorId, date, customers.size())) {
			Vendor vendor=hotelService.getVendor(vendorId);
			Double amount=(fHotel.getRoomCharge()-Double.parseDouble(vendor.getPromoCode().substring(4)))*customers.size();
			if(amount<0) amount=0d;
			userService.payment(userId, amount);
			try {
				bookingId=hotelService.bookHotel(hotelId, vendorId, date, customers, amount);
				userService.addBooking(userId, bookingId);
			} catch(Exception e) {
				userService.rechargeWallet(userId, amount);
				throw new Exception(e.getMessage());
			}
		} 
		return bookingId;
	}
	
	@Override
	public Integer updateBooking(String userId, Integer bookingId, List<Customer> customers) throws Exception {
		Booking booking=hotelService.getBooking(bookingId);
		Hotel hotel=hotelService.getHotel(booking.getHotelId());
		Vendor vendor=hotelService.getVendor(booking.getVendorId());
		Double baseAmount=hotel.getRoomCharge();
		Double dynamicAmount=baseAmount+baseAmount*(hotel.getTotalRooms()-hotel.getRoomMap().get(booking.getBookingDate()))/100;
		Double newAmount=(dynamicAmount-Double.parseDouble(vendor.getPromoCode().substring(4)))*customers.size();
		if(newAmount<0) newAmount=0d;
		Double oldAmount=booking.getAmount();
		userService.payment(userId, newAmount-oldAmount);
		try {
			bookingId=hotelService.updateBooking(bookingId, customers, newAmount);
		} catch(Exception e) {
			userService.rechargeWallet(userId, newAmount-oldAmount);
			throw new Exception(e.getMessage());
		}
		return bookingId;
	}
	
	@Override
	public Integer cancelBooking(String userId, Integer bookingId) throws Exception {
		Double amount=hotelService.getBooking(bookingId).getAmount();
		bookingId=hotelService.cancelBooking(bookingId);
		userService.rechargeWallet(userId, amount);
		return bookingId;
	}
	
}
