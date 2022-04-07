SET FOREIGN_KEY_CHECKS = 0;
truncate table sender;
truncate table package_model;

insert into sender(sender_id, first_name, last_name, password, sender_address, date_created, sender_email, sender_phone)
values(102, 'kelechi', 'madu', '123kele','sabo yaba lagos',current_timestamp, 'kele@madu@gmail.com', '08045639995'),
       (103, 'gidion', 'udoh', '123gidi','sabo yaba lagos',current_timestamp, 'gidiudo@gmail.com', '080456567585'),
       (104, 'solomon', 'laz', '123sol','sabo yaba lagos',current_timestamp, 'sollaz@gmail.com', '08047772585');

insert into package_model(package_id, package_name, shipping_address, receiver_phone_number,receiver_name, package_description, date_created, weight)
values (1, 'Mobile phone', 'surulere', '08034566545','Chris', 'A black samsung mobile phone', current_timestamp, '2.00'),
       (2, 'handbook', 'yaba', '08034544355','mergan', 'a note book', current_timestamp , '1.00');


SET FOREIGN_KEY_CHECKS = 1;


