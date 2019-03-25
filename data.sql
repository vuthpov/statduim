use staduim;

insert into role(role) values('Admin'),('User');



insert into job(job) values('Manager'),('Ticket Seller');

insert into employee(fName,lName,gender,jobId,dob,hiredDate,address,tel,email,photo,salary)
	values('admin','admin','Female',1,'1998-1-1',now(),'PP','012','hello','',200),
    ('user','user','Male',2,'1998-1-1',now(),'PP','012','hello','',100),
    ('test','test','Male',2,'1998-1-1',now(),'PP','012','hello','',100)
    ;

insert into user(username,password,roleId,is_active,empId) 
	values('admin','admin','1',1,1),
    ('user','user','2',0,2);




update employee set photo='E:\\Year 3\\Java\\staduim\\project\\src\\project\\Icon\\Employee.png';
update employee set photo='E:\\Year 3\\Java\\staduim\\project\\src\\project\\Icon\\close-hover.png' where empid=1;



