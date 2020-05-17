package com.gautam.dao;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.gautam.entity.BookingEntity;
import com.gautam.entity.CustomerEntity;
import com.gautam.entity.HotelEntity;
import com.gautam.entity.VendorEntity;
import com.gautam.model.Booking;
import com.gautam.model.Customer;
import com.gautam.model.Hotel;
import com.gautam.model.Vendor;

@Repository(value="hotelDAO")
public class HotelDAOImpl implements HotelDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String addVendor(Vendor vendor) throws Exception {
		VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendor.getVendorId());
		String vendorId=null;
		if(vendorEntity==null) {
			vendorEntity=new VendorEntity();
			vendorEntity.setVendorId(vendor.getVendorId());
			vendorEntity.setVendorName(vendor.getVendorName());
			vendorEntity.setPromoCode(vendor.getPromoCode());
			entityManager.persist(vendorEntity);
			vendorId=vendorEntity.getVendorId();
		}
		return vendorId;
	}
	
	@Override
	public Vendor getVendor(String vendorId) throws Exception {
		VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendorId);
		Vendor vendor=null;
		if(vendorEntity!=null) {
			vendor=new Vendor();
			vendor.setVendorId(vendorEntity.getVendorId());
			vendor.setVendorName(vendorEntity.getVendorName());
			vendor.setPromoCode(vendorEntity.getPromoCode());
		}
		return vendor;
	}
	
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
			hotelEntity.setTotalRooms(hotel.getTotalRooms());
			hotelEntity.setRoomMap(hotel.getRoomMap());
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
	public Hotel getHotel(String hotelId) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		Hotel hotel=null;
		if(hotelEntity!=null) {
			hotel=new Hotel();
			hotel.setHotelId(hotelEntity.getHotelId());
			hotel.setHotelName(hotelEntity.getHotelName());
			hotel.setLocation(hotelEntity.getLocation());
			hotel.setRoomCharge(hotelEntity.getRoomCharge());
			hotel.setTotalRooms(hotelEntity.getTotalRooms());
			Map<LocalDate, Integer> roomMap1=hotelEntity.getRoomMap();
			Map<LocalDate, Integer> roomMap=new LinkedHashMap<LocalDate, Integer>();
			for(LocalDate key : roomMap1.keySet()) {
				roomMap.put(key, roomMap1.get(key));
			}
			hotel.setRoomMap(roomMap);
			hotel.setAmenities(hotelEntity.getAmenities());
			hotel.setHotelStatus(hotelEntity.getHotelStatus());
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			Set<Vendor> vendors=new LinkedHashSet<Vendor>();
			for(VendorEntity vendorEntity : vendorEntities) {
				Vendor vendor=new Vendor();
				vendor.setVendorId(vendorEntity.getVendorId());
				vendor.setVendorName(vendorEntity.getVendorName());
				vendor.setPromoCode(vendorEntity.getPromoCode());
				vendors.add(vendor);
			}
			hotel.setVendors(vendors);
		}
		return hotel;
	}
	
	@Override
	public Set<Hotel> toHotels(List<HotelEntity> hotelEntities) throws Exception {
		Set<Hotel> hotels=new LinkedHashSet<Hotel>();
		for(HotelEntity hotelEntity : hotelEntities) {
			Hotel hotel=new Hotel();
			hotel.setHotelId(hotelEntity.getHotelId());
			hotel.setHotelName(hotelEntity.getHotelName());
			hotel.setLocation(hotelEntity.getLocation());
			hotel.setRoomCharge(hotelEntity.getRoomCharge());
			hotel.setTotalRooms(hotelEntity.getTotalRooms());
			Map<LocalDate, Integer> roomMap1=hotelEntity.getRoomMap();
			Map<LocalDate, Integer> roomMap=new LinkedHashMap<LocalDate, Integer>();
			for(LocalDate key : roomMap1.keySet()) {
				roomMap.put(key, roomMap1.get(key));
			}
			hotel.setRoomMap(roomMap);
			hotel.setAmenities(hotelEntity.getAmenities());
			hotel.setHotelStatus(hotelEntity.getHotelStatus());
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			Set<Vendor> vendors=new LinkedHashSet<Vendor>();
			for(VendorEntity vendorEntity : vendorEntities) {
				Vendor vendor=new Vendor();
				vendor.setVendorId(vendorEntity.getVendorId());
				vendor.setVendorName(vendorEntity.getVendorName());
				vendor.setPromoCode(vendorEntity.getPromoCode());
				vendors.add(vendor);
			}
			hotel.setVendors(vendors);
			hotels.add(hotel);
		}
		return hotels;
	}
	
	@Override
	public Set<Hotel> getHotels() throws Exception {
		String queryString="SELECT h FROM HotelEntity h";
		Query query=entityManager.createQuery(queryString);
		List<HotelEntity> hotelEntities=query.getResultList();
		return toHotels(hotelEntities);
	}
	
	@Override
	public String removeHotel(String hotelId) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		String hId=null;
		if(hotelEntity!=null) {
			hotelEntity.setVendors(null);
			entityManager.remove(hotelEntity);
			hId=hotelEntity.getHotelId();
		}
		return hId;
	}
	
	@Override
	public String removeVendorFromHotel(String hotelId, String vendorId) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendorId);
		String vId=null;
		if(hotelEntity!=null && vendorEntity!=null) {
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			vendorEntities.remove(vendorEntity);
			hotelEntity.setVendors(vendorEntities);
			vId=vendorEntity.getVendorId();
		}
		return vId;
	}
	
	@Override
	public String addVendorToHotel(String hotelId, String vendorId) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendorId);
		String vId=null;
		if(hotelEntity!=null && vendorEntity!=null) {
			Set<VendorEntity> vendorEntities=hotelEntity.getVendors();
			vendorEntities.add(vendorEntity);
			hotelEntity.setVendors(vendorEntities);
			vId=vendorEntity.getVendorId();
		}
		return vId;
	}
	
	
	
	@Override
	public Set<Hotel> searchHotelByNameKey(String key) throws Exception {     
		String queryString="SELECT h FROM HotelEntity h WHERE h.hotelName LIKE :searchKey";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("searchKey", "%"+key+"%");
		List<HotelEntity> hotelEntities=query.getResultList();
		return toHotels(hotelEntities);
	}
	
	@Override
	public Set<Hotel> searchHotelByLocationKey(String key) throws Exception {     
		String queryString="SELECT h FROM HotelEntity h WHERE h.location LIKE :searchKey";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("searchKey", "%"+key+"%");
		List<HotelEntity> hotelEntities=query.getResultList();
		return toHotels(hotelEntities);
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
			booking.setBookingDate(bookingEntity.getBookingDate());
			booking.setBookedOn(bookingEntity.getBookedOn());
			booking.setAmount(bookingEntity.getAmount());
			List<Customer> customers=new ArrayList<Customer>();
			for(CustomerEntity cEntity : bookingEntity.getCustomers()) {
				Customer c=new Customer();
				c.setCustId(cEntity.getCustId());
				c.setfName(cEntity.getfName());
				c.setlName(cEntity.getlName());
				customers.add(c);
			}
			booking.setCustomers(customers);
		}
		return booking;
	}
	
	@Override
	public Boolean validateBooking(String hotelId, String vendorId, LocalDate date, Integer noOfCustomers) throws Exception {
		Boolean isValid=false;
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		if(hotelEntity!=null && hotelEntity.getRoomMap().get(date)>=noOfCustomers) {
			VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendorId);
			if(vendorEntity!=null && hotelEntity.getVendors().contains(vendorEntity)) {
				isValid=true;
			}
		}
		return isValid;
	}
	
	@Override
	public Integer bookHotel(String hotelId, String vendorId, LocalDate date, List<Customer> customers, Double amount) throws Exception {
		HotelEntity hotelEntity=entityManager.find(HotelEntity.class, hotelId);
		VendorEntity vendorEntity=entityManager.find(VendorEntity.class, vendorId);
		BookingEntity bookingEntity=new BookingEntity();
		bookingEntity.setHotelId(hotelEntity.getHotelId());
		bookingEntity.setHotelName(hotelEntity.getHotelName());
		bookingEntity.setVendorId(vendorEntity.getVendorId());
		bookingEntity.setVendorName(vendorEntity.getVendorName());
		bookingEntity.setBookingDate(date);
		bookingEntity.setBookedOn(LocalDateTime.now());
		bookingEntity.setAmount(amount);
		List<CustomerEntity> cEntities=new ArrayList<CustomerEntity>();
		for(Customer c : customers) {
			CustomerEntity cEntity=new CustomerEntity();
			cEntity.setfName(c.getfName());
			cEntity.setlName(c.getlName());
			cEntities.add(cEntity);
		}
		bookingEntity.setCustomers(cEntities);
		entityManager.persist(bookingEntity);
		Map<LocalDate, Integer> roomMap=hotelEntity.getRoomMap();
		roomMap.put(date, roomMap.get(date)-customers.size());
		hotelEntity.setRoomMap(roomMap);
		return bookingEntity.getBookingId();
	}
	
	@Override
	public Integer updateBooking(Integer bookingId, List<Customer> customers, Double amount) throws Exception {
		BookingEntity bookingEntity=entityManager.find(BookingEntity.class, bookingId);
		Integer bId=null;
		if(bookingEntity!=null) {
			HotelEntity hotelEntity=entityManager.find(HotelEntity.class, bookingEntity.getHotelId());
			Map<LocalDate, Integer> roomMap=hotelEntity.getRoomMap();
			LocalDate date=bookingEntity.getBookingDate();
			roomMap.put(date, roomMap.get(date)+bookingEntity.getCustomers().size()-customers.size());
			hotelEntity.setRoomMap(roomMap);
			List<CustomerEntity> cEntities=new ArrayList<CustomerEntity>();
			for(Customer c : customers) {
				CustomerEntity cEntity=new CustomerEntity();
				cEntity.setfName(c.getfName());
				cEntity.setlName(c.getlName());
				cEntities.add(cEntity);
			}
			bookingEntity.setCustomers(cEntities);
			bookingEntity.setAmount(amount);
			bId=bookingEntity.getBookingId();
		}
		return bId;
	}
	
	@Override
	public Integer cancelBooking(Integer bookingId) throws Exception {
		BookingEntity bookingEntity=entityManager.find(BookingEntity.class, bookingId);
		Integer bId=null;
		if(bookingEntity!=null) {
			HotelEntity hotelEntity=entityManager.find(HotelEntity.class, bookingEntity.getHotelId());
			Map<LocalDate, Integer> roomMap=hotelEntity.getRoomMap();
			LocalDate date=bookingEntity.getBookingDate();
			roomMap.put(date, roomMap.get(date)+bookingEntity.getCustomers().size());
			hotelEntity.setRoomMap(roomMap);
			entityManager.remove(bookingEntity);
			bId=bookingEntity.getBookingId();
		}
		return bId;
	}
	
}
