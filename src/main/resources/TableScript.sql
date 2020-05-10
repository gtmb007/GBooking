drop table if exists hotel_vendor;
drop table if exists hotel;
drop table if exists vendor;
drop table if exists booking;
drop table if exists myuser;

create table vendor (
	vendor_id varchar(10) primary key,
	vendor_name varchar(20) not null,
	promo_code varchar(20)
);

create table hotel (
	hotel_id varchar(10) primary key,
	hotel_name varchar(20) not null,
	location varchar(20) not null,
	room_available int(4) not null,
	room_charge double(8,2) not null,
	amenities varchar(20),
	hotel_status varchar(10)
);

create table hotel_vendor (
	hotel_id varchar(10) not null,
	vendor_id varchar(10) not null
);

create table myuser (
	user_id varchar(10) primary key,
	first_name varchar(20) not null,
	last_name varchar(20),
	password varchar(10) not null,
	wallet_balance double(8,2) not null
);

create table booking (
	booking_id int(8) auto_increment primary key,
	hotel_id varchar(10) not null,
	hotel_name varchar(20) not null,
	vendor_id varchar(10) not null,
	vendor_name varchar(10) not null,
	no_of_rooms int(4) not null,
	booked_on datetime not null,
	amount double(8,2) not null,
	user_id varchar(10)
);

alter table booking add constraint fk_booking foreign key (user_id) references myuser(user_id);

alter table hotel_vendor add constraint fk_vendor_mapping foreign key (vendor_id) references vendor(vendor_id);
alter table hotel_vendor add constraint fk_hotel_mapping foreign key (hotel_id) references hotel(hotel_id);


INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('H101','Plaza Hotel', 'Banglore', 100, 1000, 'TVAC', 'B');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('H102','Grand Hotel', 'Banglore', 150, 1200, 'TVACWIFI', 'A');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('H103','Sukanya Hotel', 'Mysore', 200, 1500, 'TVACWIFI', 'A');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('H104','Grand Plaza Hotel', 'Banglore', 100, 1000, 'TVAC', 'B');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('H105','Grand Marbel Hotel', 'Banglore', 150, 1200, 'TVACWIFI', 'A');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('H106','Sukanya Shanti Hotel', 'Mysore', 200, 1500, 'TVACWIFI', 'A');


INSERT INTO vendor (vendor_id, vendor_name, promo_code) values ('V101','MakeMyTrip', 'BOOK100');
INSERT INTO vendor (vendor_id, vendor_name, promo_code) values ('V102','Goibbo', 'BOOK200');
INSERT INTO vendor (vendor_id, vendor_name, promo_code) values ('V103','Yatra', 'BOOK250');
INSERT INTO vendor (vendor_id, vendor_name, promo_code) values ('V104','MakeEasy', null);
INSERT INTO vendor (vendor_id, vendor_name, promo_code) values ('V105','GoibboTrip', 'BOOK150');
INSERT INTO vendor (vendor_id, vendor_name, promo_code) values ('V106','GoYatra', null);


INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H101', 'V106');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H101', 'V103');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H102', 'V101');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H102', 'V104');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H103', 'V102');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H103', 'V105');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H104', 'V106');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H104', 'V103');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H105', 'V101');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H105', 'V102');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H106', 'V105');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('H106', 'V101');

commit;
select * from hotel;
select * from vendor;
select * from hotel_vendor;
select * from booking;
select * from myuser;
