insert into role(role) values('Admin'),('User');

insert into user(username,password,roleId) 
	values('admin','admin','1'),
    ('user','user','2');

insert into job(job) values('Manager'),('Ticket Seller');

insert into employee(fName,lName,gender,jobId,dob,hiredDate,address,tel,email,photo,userId,salary)
	values('admin','admin','M',1,now(),now(),'','','','',1,200),
    ('user','user','M',2,now(),now(),'','','','',2,100);
    
