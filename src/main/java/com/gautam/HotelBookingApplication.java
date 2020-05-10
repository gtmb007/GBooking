package com.gautam;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.gautam.model.Booking;
import com.gautam.model.Hotel;
import com.gautam.model.User;
import com.gautam.model.Vendor;
import com.gautam.service.AdminService;
import com.gautam.service.BookingService;
import com.gautam.service.HotelService;
import com.gautam.service.UserService;

@SpringBootApplication
public class HotelBookingApplication implements CommandLineRunner {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private Environment environment;
	
	private Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		while(true) {
			System.out.println("\nPlease Enter...\n1.) Admin Login\n2.) User Login\n0.) Exit");
			int opt=sc.nextInt();
			if(opt==0) return;
			else if(opt==1) adminLogin();
			else if(opt==2) userLogin();
			else System.out.println("\n"+environment.getProperty("UI.INVALID_OPTION"));
		}
	}
	
	public void adminLogin() {
		System.out.print("\nEnter Admin Id: ");
		String id=sc.next();
		System.out.print("Enter Password: ");
		String password=sc.next();
		try {
			id=adminService.validateAdmin(id, password);
			System.out.println("\n"+environment.getProperty("API.ADMIN_LOGIN_SUCCESS")+id);
			while(true) {
				System.out.println("\nPlease Enter...\n1.) Add Vendor\n2.) Get Vendor\n3.) Add Hotel\n4.) Get Hotel\n5.) Delete Hotel\n6.) Remove Vendor from Hotel\n7.) Add Vendor to Hotel\n0.) Log Out");
				int opt=sc.nextInt();
				if(opt==0) return;
				else if(opt==1) addVendor();
				else if(opt==2) getVendor();
				else if(opt==3) addHotel();
				else if(opt==4) getHotel();
				else if(opt==5) deleteHotel();
				else if(opt==6) removeVendorFromHotel();
				else if(opt==7) addVendorToHotel();
				else System.out.println("\n"+environment.getProperty("UI.INVALID_OPTION"));
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void addVendor() {
		Vendor vendor=new Vendor();
		System.out.print("\nEnter Vendor Id: ");
		vendor.setVendorId(sc.next());
		System.out.print("Enter Vendor Name: ");
		vendor.setVendorName(sc.next());
		System.out.print("Enter Promo Code: ");
		vendor.setPromoCode(sc.next());
		try {
			String vendorId=hotelService.addVendor(vendor);
			System.out.println("\n"+environment.getProperty("API.VENDOR_ADDED")+vendorId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void getVendor() {
		try {
			System.out.print("\nEnter Vendor Id: ");
			Vendor vendor=hotelService.getVendor(sc.next());
			System.out.println("\nVendor Details...");
			System.out.println("Vendor Id: "+vendor.getVendorId());
			System.out.println("Vendor Name: "+vendor.getVendorName());
			System.out.println("Promo Code: "+vendor.getPromoCode());
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void addHotel() {
		Hotel hotel=new Hotel();
		System.out.print("\nEnter Hotel Id: ");
		hotel.setHotelId(sc.next());
		System.out.print("Enter Hotel Name: ");
		hotel.setHotelName(sc.next());
		System.out.print("Enter Location: ");
		hotel.setLocation(sc.next());
		System.out.print("Enter Available Rooms: ");
		hotel.setRoomsAvailable(sc.nextInt());
		System.out.print("Enter Room Charge: ");
		hotel.setRoomCharge(sc.nextDouble());
		System.out.print("Enter Amenities: ");
		hotel.setAmenities(sc.next());
		System.out.print("Enter Status: ");
		hotel.setHotelStatus(sc.next());
		Set<Vendor> vendors=new LinkedHashSet<Vendor>();
		System.out.print("Enter number of vendors: ");
		int n=sc.nextInt();
		for(int i=1;i<=n;i++) {
			Vendor vendor=new Vendor();
			System.out.print("Enter Vendor"+i+" Id: ");
			vendor.setVendorId(sc.next());
			vendors.add(vendor);
		}
		hotel.setVendors(vendors);
		try {
			String hotelId=hotelService.addHotel(hotel);
			System.out.println("\n"+environment.getProperty("API.HOTEL_ADDED")+hotelId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void getHotel() {
		try {
			System.out.print("\nEnter Hotel Id: ");
			Hotel hotel=hotelService.getHotel(sc.next());
			System.out.println("\nHotel Details...");
			System.out.println("Hotel Id: "+hotel.getHotelId());
			System.out.println("Hotel Name: "+hotel.getHotelName());
			System.out.println("Location: "+hotel.getLocation());
			System.out.println("Available Rooms: "+hotel.getRoomsAvailable());
			System.out.println("Room Charge: "+hotel.getRoomCharge());
			System.out.println("Amenities: "+hotel.getAmenities());
			System.out.println("Status: "+hotel.getHotelStatus());
			Set<Vendor> vendors=hotel.getVendors();
			int i=1;
			for(Vendor vendor : vendors) {
				System.out.println("\nVendor"+i+"...");
				System.out.println("Vendor Id: "+vendor.getVendorId());
				System.out.println("Vendor Name: "+vendor.getVendorName());
				System.out.println("Promo Code: "+vendor.getPromoCode());
				i++;
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void deleteHotel() {
		System.out.print("\nEnter Hotel Id: ");
		String hotelId=sc.next();
		try {
			hotelId=hotelService.removeHotel(hotelId);
			System.out.println("\n"+environment.getProperty("API.HOTEL_DELETED")+hotelId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void removeVendorFromHotel() {
		System.out.print("\nEnter Hotel Id: ");
		String hotelId=sc.next();
		System.out.print("Enter Vendor Id: ");
		String vendorId=sc.next();
		try {
			vendorId=hotelService.removeVendorFromHotel(hotelId, vendorId);
			System.out.println("\n"+environment.getProperty("API.VENDOR_REMOVED_FROM_HOTEL")+vendorId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void addVendorToHotel() {
		System.out.print("\nEnter Hotel Id: ");
		String hotelId=sc.next();
		System.out.print("Enter Vendor Id: ");
		String vendorId=sc.next();
		try {
			vendorId=hotelService.addVendorToHotel(hotelId, vendorId);
			System.out.println("\n"+environment.getProperty("API.VENDOR_ADDED_TO_HOTEL")+vendorId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void userLogin() {
		while(true) {
			System.out.println("\nPlease Enter...\n1.) User Sign Up\n2.) User Sign In\n0.) Exit as User");
			int opt=sc.nextInt();
			if(opt==0) return;
			else if(opt==1) signUp();
			else if(opt==2) signIn();
			else System.out.println("\n"+environment.getProperty("UI.INVALID_OPTION"));
		}
	}
	
	public void signUp() {
		User user=new User();
		System.out.print("\nEnter User Id: ");
		user.setUserId(sc.next());
		System.out.print("Enter First Name: ");
		user.setFirstName(sc.next());
		System.out.print("Enter Last Name: ");
		user.setLastName(sc.next());
		System.out.print("Enter Password: ");
		user.setPassword(sc.next());
		try {
			String userId=userService.addUser(user);
			System.out.println("\n"+environment.getProperty("API.USER_SIGNUP_SUCCESS")+userId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void signIn() {
		System.out.print("\nEnter User Id: ");
		String id=sc.next();
		System.out.print("Enter Password: ");
		String password=sc.next();
		try {
			id=userService.validateUser(id, password);
			System.out.println("\n"+environment.getProperty("API.USER_LOGIN_SUCCESS")+id);
			while(true) {
				System.out.println("\nPlease Enter...\n1.) User Details\n2.) Update Name\n3.) Update Password\n4.) Recharge Wallet\n5.) Search Hotel By Name\n6.) Search Hotel By Location\n7.) Book Hotel\n8.) Update Booking\n9.) Cancel Booking\n0.) Log Out");
				int opt=sc.nextInt();
				if(opt==0) return;
				else if(opt==1) userDetails(id);
				else if(opt==2) updateName(id);
				else if(opt==3) updatePassword(id);
				else if(opt==4) rechargeWallet(id);
				else if(opt==5) searchHotelByName();
				else if(opt==6) searchHotelByLocation();
				else if(opt==7) bookHotel(id);
				else if(opt==8) updateBooking(id);
				else if(opt==9) cancelBooking(id);
				else System.out.println("\n"+environment.getProperty("UI.INVALID_OPTION"));
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void userDetails(String id) {
		try {
			User user=userService.getUser(id);
			System.out.println("\nUser Details...");
			System.out.println("Id: "+user.getUserId());
			System.out.println("Name: "+user.getFirstName()+" "+user.getLastName());
			System.out.println("Wallet Balance: "+user.getWalletBalance());
			Set<Booking> bookings=user.getBookings();
			int i=1;
			for(Booking booking : bookings) {
				System.out.println("\nBooking"+i+"...");
				System.out.println("Booking Id: "+booking.getBookingId());
				System.out.println("Hotel Id: "+booking.getHotelId());
				System.out.println("Hotel Name: "+booking.getHotelName());
				System.out.println("Vendor Id: "+booking.getVendorId());
				System.out.println("Vendor Name: "+booking.getVendorName());
				System.out.println("Number of Rooms: "+booking.getNoOfRooms());
				System.out.println("Booking Date: "+booking.getBookedOn());
				System.out.println("Booking Amount: "+booking.getAmount());
				i++;
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void updateName(String id) {
		System.out.print("\nEnter First Name: ");
		String fName=sc.next();
		System.out.print("Enter Last Name: ");
		String lName=sc.next();
		try {
			id=userService.updateUserName(id, fName, lName);
			System.out.println("\n"+environment.getProperty("API.USER_NAME_UPDATED")+id);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void updatePassword(String id) {
		System.out.print("\nEnter New Password: ");
		String password=sc.next();
		try {
			id=userService.updatePassword(id, password);
			System.out.println("\n"+environment.getProperty("API.USER_PASSWORD_UPDATED")+id);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void rechargeWallet(String id) {
		System.out.print("\nEnter amount: ");
		Double amount=sc.nextDouble();
		try {
			userService.rechargeWallet(id, amount);
			System.out.println("\n"+environment.getProperty("API.WALLET_RECHARGE_SUCCESS"));
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void searchHotelByName() {
		try {
			System.out.print("\nEnter Name Key of Hotel: ");
			String key=sc.next();
			Set<Hotel> hotels=hotelService.searchHotelByNameKey(key);
			int j=1;
			for(Hotel hotel : hotels) {
				System.out.println("\n\nHotel"+j+" Details...");
				System.out.println("Hotel Id: "+hotel.getHotelId());
				System.out.println("Hotel Name: "+hotel.getHotelName());
				System.out.println("Location: "+hotel.getLocation());
				System.out.println("Available Rooms: "+hotel.getRoomsAvailable());
				System.out.println("Room Charge: "+hotel.getRoomCharge());
				System.out.println("Amenities: "+hotel.getAmenities());
				System.out.println("Status: "+hotel.getHotelStatus());
				Set<Vendor> vendors=hotel.getVendors();
				int i=1;
				for(Vendor vendor : vendors) {
					System.out.println("\nVendor"+i+"...");
					System.out.println("Vendor Id: "+vendor.getVendorId());
					System.out.println("Vendor Name: "+vendor.getVendorName());
					System.out.println("Promo Code: "+vendor.getPromoCode());
					i++;
				}
				j++;
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void searchHotelByLocation() {
		try {
			System.out.print("\nEnter Location Key of Hotel: ");
			String key=sc.next();
			Set<Hotel> hotels=hotelService.searchHotelByLocationKey(key);
			int j=1;
			for(Hotel hotel : hotels) {
				System.out.println("\n\nHotel"+j+" Details...");
				System.out.println("Hotel Id: "+hotel.getHotelId());
				System.out.println("Hotel Name: "+hotel.getHotelName());
				System.out.println("Location: "+hotel.getLocation());
				System.out.println("Available Rooms: "+hotel.getRoomsAvailable());
				System.out.println("Room Charge: "+hotel.getRoomCharge());
				System.out.println("Amenities: "+hotel.getAmenities());
				System.out.println("Status: "+hotel.getHotelStatus());
				Set<Vendor> vendors=hotel.getVendors();
				int i=1;
				for(Vendor vendor : vendors) {
					System.out.println("\nVendor"+i+"...");
					System.out.println("Vendor Id: "+vendor.getVendorId());
					System.out.println("Vendor Name: "+vendor.getVendorName());
					System.out.println("Promo Code: "+vendor.getPromoCode());
					i++;
				}
				j++;
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void bookHotel(String userId) {
		System.out.print("\nEnter Hotel Id: ");
		String hotelId=sc.next();
		System.out.print("Enter Vendor Id: ");
		String vendorId=sc.next();
		System.out.print("Enter Number of Rooms: ");
		Integer noOfRooms=sc.nextInt();
		try {
			Integer bookingId=bookingService.bookHotel(userId, hotelId, vendorId, noOfRooms);
			System.out.println("\n"+environment.getProperty("API.BOOKING_SUCCESS")+bookingId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void updateBooking(String userId) {
		System.out.print("\nEnter Booking Id: ");
		Integer bookingId=sc.nextInt();
		System.out.print("Enter Number of Rooms: ");
		Integer noOfRooms=sc.nextInt();
		try {
			bookingId=bookingService.updateBooking(userId, bookingId, noOfRooms);
			System.out.println("\n"+environment.getProperty("API.BOOKING_UPDATED")+bookingId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void cancelBooking(String userId) {
		System.out.print("\nEnter Booking Id: ");
		Integer bookingId=sc.nextInt();
		try {
			bookingId=bookingService.cancelBooking(userId, bookingId);
			System.out.println("\n"+environment.getProperty("API.BOOKING_CANCELLED")+bookingId);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}

}
