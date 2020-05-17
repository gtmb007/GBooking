package com.gautam.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.dao.HotelDAO;
import com.gautam.model.Booking;
import com.gautam.model.Customer;
import com.gautam.model.FinalHotel;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

@Service(value="hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelDAO hotelDAO;
	
	@Override
	public String addVendor(Vendor vendor) throws Exception {
		String promoCode=vendor.getPromoCode();
		if(promoCode!=null && !promoCode.matches("[A-Za-z]{4}[0-9]{3}")) throw new Exception("Service.INVAILD_PROMOCODE");
		String vendorId=hotelDAO.addVendor(vendor);
		if(vendorId==null) throw new Exception("Service.VENDOR_ALREADY_EXIST");
		return vendorId;
	}
	
	@Override
	public Vendor getVendor(String vendorId) throws Exception {
		Vendor vendor=hotelDAO.getVendor(vendorId);
		if(vendor==null) throw new Exception("Service.VENDOR_NOT_FOUND");
		return vendor;
	}
	
	@Override
	public String addHotel(Hotel hotel) throws Exception {
		String hotelId=hotelDAO.addHotel(hotel);
		if(hotelId==null) throw new Exception("Service.HOTEL_ALREADY_EXIST");
		return hotelId;
	}
	
	@Override
	public Hotel getHotel(String hotelId) throws Exception {
		Hotel hotel=hotelDAO.getHotel(hotelId);
		if(hotel==null) throw new Exception("Service.HOTEL_NOT_FOUND");
		return hotel;
	}
	
	@Override
	public Set<Hotel> getHotels() throws Exception {
		Set<Hotel> hotels=hotelDAO.getHotels();
		if(hotels.isEmpty()) throw new Exception("Service.HOTEL_NOT_FOUND");
		return hotels;
	}
	
	@Override
	public String removeHotel(String hotelId) throws Exception {
		String hId=hotelDAO.removeHotel(hotelId);
		if(hId==null) throw new Exception("Service.HOTEL_NOT_FOUND");
		return hId;
	}
	
	@Override
	public String removeVendorFromHotel(String hotelId, String vendorId) throws Exception {
		String vId=hotelDAO.removeVendorFromHotel(hotelId, vendorId);
		if(vId==null) throw new Exception("Service.WRONG_DETAILS");
		return vId;
	}
	
	@Override
	public String addVendorToHotel(String hotelId, String vendorId) throws Exception {
		String vId=hotelDAO.addVendorToHotel(hotelId, vendorId);
		if(vId==null) throw new Exception("Service.WRONG_DETAILS");
		return vId;
	}
	
	@Override
	public Set<FinalHotel> toFinalHotels(Set<Hotel> hotels, LocalDate date) throws Exception {
		Set<FinalHotel> fHotels=new HashSet<FinalHotel>(); 
		for(Hotel hotel : hotels) {
			FinalHotel fHotel=new FinalHotel();
			fHotel.setHotelId(hotel.getHotelId());
			fHotel.setHotelName(hotel.getHotelName());
			fHotel.setLocation(hotel.getLocation());
			fHotel.setAmenities(hotel.getAmenities());
			fHotel.setHotelStatus(hotel.getHotelStatus());
			fHotel.setVendors(hotel.getVendors());
			Integer availableRooms=hotel.getRoomMap().get(date);
			fHotel.setAvailableRooms(availableRooms);
			Double baseAmount=hotel.getRoomCharge();
			Double dynamicAmount=baseAmount+baseAmount*(hotel.getTotalRooms()-availableRooms)/100;
			fHotel.setRoomCharge(dynamicAmount);
			fHotels.add(fHotel);
		}
		return fHotels;
	}
	
	@Override
	public Set<FinalHotel> searchHotelByNameKey(String key, LocalDate date) throws Exception {
		Set<Hotel> hotels=hotelDAO.searchHotelByNameKey(key);
		if(hotels.isEmpty()) throw new Exception("Service.HOTEL_NOT_FOUND");
		return toFinalHotels(hotels, date);
	}
	
	@Override
	public Set<FinalHotel> searchHotelByLocationKey(String key, LocalDate date) throws Exception {
		Set<Hotel> hotels=hotelDAO.searchHotelByLocationKey(key);
		if(hotels.isEmpty()) throw new Exception("Service.HOTEL_NOT_FOUND");
		return toFinalHotels(hotels, date);
	}
	
	@Override
	public Booking getBooking(Integer bookingId) throws Exception {
		Booking booking=hotelDAO.getBooking(bookingId);
		if(booking==null) throw new Exception("Service.BOOKING_NOT_FOUND");
		return booking;
	}
	
	@Override
	public Boolean validateBooking(String hotelId, String vendorId, LocalDate date, Integer noOfCustomers) throws Exception {
		Boolean message=hotelDAO.validateBooking(hotelId, vendorId, date, noOfCustomers);
		if(!message) throw new Exception("Service.INVALID_BOOKING_DETAILS");
		return message;
	}
	
	@Override
	public Integer bookHotel(String hotelId, String vendorId, LocalDate date, List<Customer> customers, Double amount) throws Exception {
		return hotelDAO.bookHotel(hotelId, vendorId, date, customers, amount);
	}
	
	@Override
	public Integer updateBooking(Integer bookingId, List<Customer> customers, Double amount) throws Exception {
		Integer bId=hotelDAO.updateBooking(bookingId, customers, amount);
		if(bId==null) throw new Exception("Service.BOOKING_UPDATION_FAILED");
		return bookingId;
	}
	
	@Override
	public Integer cancelBooking(Integer bookingId) throws Exception {
		Integer bId=hotelDAO.cancelBooking(bookingId);
		if(bId==null) throw new Exception("Service.BOOKING_CANCELATION_FAILED");
		return bookingId;
	}
	
}
