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



insert into league (league,photo) 
	values('La Liga','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\laliga.jpg'),
    ('Series-A','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\series-a.png'),
    ('Barclays Premier League','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\barclays.jpg');
    
insert into club (club,nickname,photo,leagueId)
	values('Futbol Club Barcelona','Barça','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\bacelona.png',1),
    ('Real Madrid Club de Fútbol','Real','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\real madrid.png',1),
    ('Juventus Football Club S.p.A.','JUV','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\juventus.jpg',2),
    ('Manchester United Football Club','MUFC','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\manunited.png',3),
    ('Manchester City Football Club','MCFC','E:\\Year 3\\Java\\staduim\\project\\src\\project\\Picture\\mancity.png',3);
    
