insert into role(role) values('Admin'),('User');

insert into user(username,password,roleId) 
	values('admin','admin','1'),
    ('user','user','2');

insert into employee(fName,lName,gender,dob,hiredDate,address,tel,photo,userId)
	values('admin','admin','M',now(),now(),'','','',1),
    ('user','user','M',now(),now(),'','','',2);
    
