INSERT INTO authority
	(role)
VALUES
	('ROLE_ADMIN'),
	('ROLE_USER');
	
INSERT INTO webuser
	(username, password_encrypted, enabled, firstname, lastname)
VALUES
	('admin', 'fa6822aa5a27648bc2baf8a52ed9ae3f807c2387acb9829e90943de2cad6c4570bd7d92cb20c8d67', 1, 'Ada', 'Admin'), --adapass
	('user1', '04a257924b303d21c683d3458b700088db582d40748498bade9be4ff04fbc203e6cf1f1b75d9174f', 1, 'Una', 'User'), --unapass
	('user2', '26f51e7ba147a61a9755a67e38e7ef49c88baf7955f99541a3641cf5ef1f221d63e4ecce978b309f', 1, 'Duo', 'User'); --duopass
	
INSERT INTO webuser_authority
	(webuser_id, authority_id)
VALUES
	(1,1),
	(1,2),
	(2,2),
	(3,2);
	
INSERT INTO workHours
	(username, hours, workDate)
VALUES
	("admin", 7.5, "2016-10-01"),
	("admin", 7.5, "2016-10-08"),
	("user1", 7.5, "2016-10-01"),
	("user1", 7.25, "2016-10-05"),
	("user1", 7.75, "2016-10-07"),
	("user2", 7.0, "2016-10-01"),
	("user2", 8.0, "2016-10-02"),
	("user2", 7.5, "2016-10-03");