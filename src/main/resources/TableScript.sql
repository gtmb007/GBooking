drop table if exists hotel_vendor;
drop table if exists hotel;
drop table if exists vendor;
drop table if exists booking;
drop table if exists myuser;

create table vendor (
	vendor_id varchar(10) primary key,
	vendor_name varchar(20) not null
);

create table hotel (
	hotel_id varchar(10) primary key,
	hotel_name varchar(20) not null,
	location varchar(20) not null,
	room_available int(4) not null,
	room_charge int(8) not null,
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
	password varchar(10) not null
);

create table booking (
	booking_id int(8) auto_increment primary key,
	hotel_id varchar(10) not null,
	hotel_name varchar(20) not null,
	vendor_id varchar(10) not null,
	vendor_name varchar(10) not null,
	no_of_rooms int(4) not null,
	booked_on datetime not null,
	user_id varchar(10)
);

alter table booking add constraint fk_booking foreign key (user_id) references myuser(user_id);

alter table hotel_vendor add constraint fk_vendor_mapping foreign key (vendor_id) references vendor(vendor_id);
alter table hotel_vendor add constraint fk_hotel_mapping foreign key (hotel_id) references hotel(hotel_id);


INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('1','Plaza Hotel', 'Banglore', 100, 1000, 'TVAC', 'B');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('2','Grand Hotel', 'Banglore', 150, 1200, 'TVACWIFI', 'A');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('3','Sukanya Hotel', 'Mysore', 200, 1500, 'TVACWIFI', 'A');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('4','Grand Plaza Hotel', 'Banglore', 100, 1000, 'TVAC', 'B');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('5','Grand Marbel Hotel', 'Banglore', 150, 1200, 'TVACWIFI', 'A');
INSERT INTO hotel (hotel_id, hotel_name, location, room_available, room_charge, amenities, hotel_status) 
VALUES ('6','Sukanya Shanti Hotel', 'Mysore', 200, 1500, 'TVACWIFI', 'A');


INSERT INTO vendor (vendor_id, vendor_name) values ('1','MakeMyTrip');
INSERT INTO vendor (vendor_id, vendor_name) values ('2','Goibbo');
INSERT INTO vendor (vendor_id, vendor_name) values ('3','Yatra');
INSERT INTO vendor (vendor_id, vendor_name) values ('4','MakeEasy');
INSERT INTO vendor (vendor_id, vendor_name) values ('5','GoibboTrip');
INSERT INTO vendor (vendor_id, vendor_name) values ('6','GoYatra');


INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('1', '6');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('1', '3');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('2', '1');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('2', '4');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('3', '2');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('3', '5');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('4', '6');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('4', '3');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('5', '1');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('5', '2');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('6', '5');
INSERT INTO hotel_vendor (hotel_id, vendor_id) values ('6', '1');

commit;
select * from hotel;
select * from vendor;
select * from hotel_vendor;
select * from booking;
select * from myuser;
