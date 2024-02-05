insert into vendor (id, name, phone, mobile, email, status, business_type, location_type, business_address, city, state, country, zipcode, registration_date, qr_code_text) 
values (1, 'FinishLineLauf', null, '012542511243', 'info@finishline.de', 'Active', 'Sports', 'Store', 'Bahnhofstr. 26', 'Nuremberg', 'Bavaria', 'Germany', '90402', current_date(), null);

insert into post(id, description, vendor_id) values (1, 'Post from FinishLine', 1);
insert into post(id, description, vendor_id) values (2, 'Advertisement from finishline, 50% discount', 1);
insert into post(id, description, vendor_id) values (3, '70% off on winter drinks', null);