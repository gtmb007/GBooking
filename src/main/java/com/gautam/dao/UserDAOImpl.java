package com.gautam.dao;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.gautam.entity.BookingEntity;
import com.gautam.entity.UserEntity;
import com.gautam.model.Booking;
import com.gautam.model.User;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String addUser(User user) throws Exception {
		UserEntity userEntity=entityManager.find(UserEntity.class, user.getUserId());
		String userId=null;
		if(userEntity==null) {
			userEntity=new UserEntity();
			userEntity.setUserId(user.getUserId());
			userEntity.setPassword(user.getPassword());
			userEntity.setFirstName(user.getFirstName());
			userEntity.setLastName(user.getLastName());
			userEntity.setBookings(new LinkedHashSet<BookingEntity>());
			entityManager.persist(userEntity);
			userId=userEntity.getUserId();
		}
		return userId;
	}
	
	@Override
	public User getUser(String userId) throws Exception {
		UserEntity userEntity=entityManager.find(UserEntity.class, userId);
		User user=null;
		if(userEntity!=null) {
			user=new User();
			user.setUserId(userEntity.getUserId());
			user.setPassword(userEntity.getPassword());
			user.setFirstName(userEntity.getFirstName());
			user.setLastName(userEntity.getLastName());
			Set<BookingEntity> bookingEntities=userEntity.getBookings();
			Set<Booking> bookings=new LinkedHashSet<Booking>();
			for(BookingEntity bookingEntity : bookingEntities) {
				Booking booking=new Booking();
				booking.setBookingId(bookingEntity.getBookingId());
				booking.setHotelId(bookingEntity.getHotelId());
				booking.setHotelName(bookingEntity.getHotelName());
				booking.setVendorId(bookingEntity.getVendorId());
				booking.setVendorName(bookingEntity.getVendorName());
				booking.setNoOfRooms(bookingEntity.getNoOfRooms());
				booking.setBookedOn(bookingEntity.getBookedOn());
				bookings.add(booking);
			}
			user.setBookings(bookings);
		}
		return user;
	}
	
	@Override
	public String updateUserName(String userId, String firstName, String lastName) throws Exception {
		UserEntity userEntity=entityManager.find(UserEntity.class, userId);
		String id=null;
		if(userEntity!=null) {
			userEntity.setFirstName(firstName);
			userEntity.setLastName(lastName);
			id=userEntity.getUserId();
		}
		return id;
	}
	
	@Override
	public String updatePassword(String userId, String password) throws Exception {
		UserEntity userEntity=entityManager.find(UserEntity.class, userId);
		String id=null;
		if(userEntity!=null) {
			userEntity.setPassword(password);
			id=userEntity.getUserId();
		}
		return id;
	}

}
