 --create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
delete from user_details where id > 6100;
delete from user_table where id > 6350;
commit;