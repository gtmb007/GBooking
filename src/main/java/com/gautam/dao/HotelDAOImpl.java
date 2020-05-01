package com.gautam.dao;


import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.gautam.entity.BookingEntity;
import com.gautam.entity.HotelEntity;
import com.gautam.entity.VendorEntity;
import com.gautam.model.Booking;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

@Repository(value="hotelDAO")
public class HotelDAOImpl implements HotelDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String addHotel(Hotel hotel) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotel.getHotelId());
		String hotelId=null;
		if(hotelEntity==null) {
			hotelEntity=new HotelEntity();
			hotelEntity.setHotelId(hotel.getHotelId());
			hotelEntity.setHotelName(hotel.getHotelName());
			hotelEntity.setHotelStatus(hotel.getHotelStatus());
			hotelEntity.setLocation(hotel.getLocation());
			hotelEntity.setRoomCharge(hotel.getRoomCharge());
			hotelEntity.setRoomsAvailable(hotel.getRoomsAvailable());
			hotelEntity.setAmenities(hotel.getAmenities());
			Set<Vendor> vendors=hotel.getVendors();
			Set<VendorEntity> vendorEntities=new LinkedHashSet<VendorEntity>();
			for(Vendor vendor : vendors) {
				VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendor.getVendorId());
				if(vendorEntity!=null) vendorEntities.add(vendorEntity);
			}
			hotelEntity.setVendors(vendorEntities);
			entityManager.persist(hotelEntity);
			hotelId=hotelEntity.getHotelId();
		}
		return hotelId;
	}
	
	@Override
	public Hotel searchHotelById(String hotelId) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		Hotel hotel=null;
		if(hotelEntity!=null) {
			hotel=new Hotel();
			hotel.setHotelId(hotelEntity.getHotelId());
			hotel.setHotelName(hotelEntity.getHotelName());
			hotel.setLocation(hotelEntity.getLocation());
			hotel.setRoomCharge(hotelEntity.getRoomCharge());
			hotel.setRoomsAvailable(hotelEntity.getRoomsAvailable());
			hotel.setAmenities(hotelEntity.getAmenities());
			hotel.setHotelStatus(hotelEntity.getHotelStatus());
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			Set<Vendor> vendors=new LinkedHashSet<Vendor>();
			for(VendorEntity vendorEntity : vendorEntities) {
				Vendor vendor=new Vendor();
				vendor.setVendorId(vendorEntity.getVendorId());
				vendor.setVendorName(vendorEntity.getVendorName());
				vendors.add(vendor);
			}
			hotel.setVendors(vendors);
		}
		return hotel;
	}
	
	@Override
	public Set<Hotel> searchHotelByNameKey(String key) throws Exception {     
		String queryString="SELECT h FROM HotelEntity h WHERE h.hotelName LIKE :searchKey";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("searchKey", "%"+key+"%");
		List<HotelEntity> hotelEntities=query.getResultList();
		Set<Hotel> hotels=new LinkedHashSet<Hotel>();
		for(HotelEntity hotelEntity : hotelEntities) {
			Hotel hotel=new Hotel();
			hotel.setHotelId(hotelEntity.getHotelId());
			hotel.setHotelName(hotelEntity.getHotelName());
			hotel.setLocation(hotelEntity.getLocation());
			hotel.setRoomCharge(hotelEntity.getRoomCharge());
			hotel.setRoomsAvailable(hotelEntity.getRoomsAvailable());
			hotel.setAmenities(hotelEntity.getAmenities());
			hotel.setHotelStatus(hotelEntity.getHotelStatus());
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			Set<Vendor> vendors=new LinkedHashSet<Vendor>();
			for(VendorEntity vendorEntity : vendorEntities) {
				Vendor vendor=new Vendor();
				vendor.setVendorId(vendorEntity.getVendorId());
				vendor.setVendorName(vendorEntity.getVendorName());
				vendors.add(vendor);
			}
			hotel.setVendors(vendors);
			hotels.add(hotel);
		}
		return hotels;
	}
	
	@Override
	public Set<Hotel> searchHotelByLocationKey(String key) throws Exception {     
		String queryString="SELECT h FROM HotelEntity h WHERE h.location LIKE :searchKey";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("searchKey", "%"+key+"%");
		List<HotelEntity> hotelEntities=query.getResultList();
		Set<Hotel> hotels=new LinkedHashSet<Hotel>();
		for(HotelEntity hotelEntity : hotelEntities) {
			Hotel hotel=new Hotel();
			hotel.setHotelId(hotelEntity.getHotelId());
			hotel.setHotelName(hotelEntity.getHotelName());
			hotel.setLocation(hotelEntity.getLocation());
			hotel.setRoomCharge(hotelEntity.getRoomCharge());
			hotel.setRoomsAvailable(hotelEntity.getRoomsAvailable());
			hotel.setAmenities(hotelEntity.getAmenities());
			hotel.setHotelStatus(hotelEntity.getHotelStatus());
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			Set<Vendor> vendors=new LinkedHashSet<Vendor>();
			for(VendorEntity vendorEntity : vendorEntities) {
				Vendor vendor=new Vendor();
				vendor.setVendorId(vendorEntity.getVendorId());
				vendor.setVendorName(vendorEntity.getVendorName());
				vendors.add(vendor);
			}
			hotel.setVendors(vendors);
			hotels.add(hotel);
		}
		return hotels;
	}
	
	@Override
	public String addVendor(Vendor vendor) throws Exception {
		VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendor.getVendorId());
		String vendorId=null;
		if(vendorEntity==null) {
			vendorEntity=new VendorEntity();
			vendorEntity.setVendorId(vendor.getVendorId());
			vendorEntity.setVendorName(vendor.getVendorName());
			entityManager.persist(vendorEntity);
			vendorId=vendorEntity.getVendorId();
		}
		return vendorId;
	}
	
	@Override
	public Booking getBooking(Integer bookingId) throws Exception {
		BookingEntity bookingEntity=entityManager.find(BookingEntity.class, bookingId);
		Booking booking=null;
		if(bookingEntity!=null) {
			booking=new Booking();
			booking.setBookingId(bookingEntity.getBookingId());
			booking.setHotelId(bookingEntity.getHotelId());
			booking.setHotelName(bookingEntity.getHotelName());
			booking.setVendorId(bookingEntity.getVendorId());
			booking.setVendorName(bookingEntity.getVendorName());
			booking.setNoOfRooms(bookingEntity.getNoOfRooms());
			booking.setBookedOn(bookingEntity.getBookedOn());
		}
		return booking;
	}
	
	@Override
	public Integer bookHotel(String hotelId, String vendorId, Integer noOfRooms) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		Integer bookingId=null;
		if(hotelEntity!=null && hotelEntity.getRoomsAvailable()>=noOfRooms) {
			VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendorId);
			if(vendorEntity!=null && hotelEntity.getVendors().contains(vendorEntity)) {
				hotelEntity.setRoomsAvailable(hotelEntity.getRoomsAvailable()-noOfRooms);
				BookingEntity bookingEntity=new BookingEntity();
				bookingEntity.setHotelId(hotelEntity.getHotelId());
				bookingEntity.setHotelName(hotelEntity.getHotelName());
				bookingEntity.setNoOfRooms(noOfRooms);
				bookingEntity.setVendorId(vendorEntity.getVendorId());
				bookingEntity.setVendorName(vendorEntity.getVendorName());
				bookingEntity.setBookedOn(LocalDateTime.now());
				entityManager.persist(bookingEntity);
				bookingId=bookingEntity.getBookingId();
			}
		}
		return bookingId;
	}
	
	@Override
	public Integer updateHotel(Integer bookingId, Integer noOfRooms) throws Exception {
		BookingEntity bookingEntity=entityManager.find(BookingEntity.class, bookingId);
		Integer bId=null;
		if(bookingEntity!=null) {
			HotelEntity hotelEntity=entityManager.find(HotelEntity.class, bookingEntity.getHotelId());
			hotelEntity.setRoomsAvailable(hotelEntity.getRoomsAvailable()+bookingEntity.getNoOfRooms()-noOfRooms);
			bookingEntity.setNoOfRooms(noOfRooms);
			bId=bookingEntity.getBookingId();
		}
		return bId;
	}
	
	@Override
	public Integer cancelHotel(Integer bookingId) throws Exception {
		BookingEntity bookingEntity=entityManager.find(BookingEntity.class, bookingId);
		Integer bId=null;
		if(bookingEntity!=null) {
			HotelEntity hotelEntity=entityManager.find(HotelEntity.class, bookingEntity.getHotelId());
			hotelEntity.setRoomsAvailable(hotelEntity.getRoomsAvailable()+bookingEntity.getNoOfRooms());
			entityManager.remove(bookingEntity);
			bId=bookingEntity.getBookingId();
		}
		return bId;
	}
	
}
