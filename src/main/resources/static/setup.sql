create database if not exists logistics_db;

create user if not exists 'logistics_user'@'localhost' identified by 'pass_123';
grant all privileges on logistics_db.* to 'logistics_user'@'localhost';
flush privileges ;