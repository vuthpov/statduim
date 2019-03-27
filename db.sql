SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 0;
SET default_storage_engine=InnoDB;

drop database if exists staduim;
create database staduim ;

use staduim;

create table role(roleId int primary key auto_increment,
role nvarchar(30));




create table job(jobId int primary key auto_increment,
job nvarchar(25)

);

create table employee(EmpId int primary key auto_increment,
	fName nvarchar(30),
    lName nvarchar(30),
    gender nvarchar(6),
    jobId int,
    dob date,
    hiredDate date,
    address nvarchar(200),
    tel nvarchar(20),
    email nvarchar(30) default '',
    photo nvarchar(200),
    salary int,
    foreign key (jobId) references job(jobId),
    unique key(fName,lName,dob)
    );

create table user(userId int primary key auto_increment,
	username nvarchar(30),
	password nvarchar(30),
	roleId int,
	is_active smallint,
	empid int,
	foreign key(empId) references employee(empId) on delete cascade,
	foreign key(roleId) references role(roleId),
	unique key(username));


create table league(leagueId int primary key auto_increment,
	league nvarchar(30) unique,
    photo nvarchar(200)
    );

create table club(clubId int primary key auto_increment,
	club nvarchar(50) unique,
    nickname nvarchar(10) unique,
    photo nvarchar(200),
    leagueId int,
    foreign key (leagueId) references league(leagueId)
    );
    
create table section(sectionId int primary key auto_increment,
	section nvarchar(30));

create table chairType(chairTypeId int primary key auto_increment,
	chairType nvarchar(30));
    
create table chair(chairId int primary key auto_increment,
	chairTypeId int,
    sectionId int,
    chairNum nvarchar(30),
    foreign key (chairtypeId) references chairtype(chairTypeId) on delete cascade,
    foreign key (sectionId) references section(sectionId) on delete cascade);
    
create table competition(competitionId int primary key auto_increment,
	dateTimeStart timestamp,
    dateTimeEnd timeStamp,
    createdBy int,
    description nvarchar(200),
    status int,
    foreign key(createdBy) references employee(empId));
    
create table competitionClub(competitionClubId int primary key auto_increment,
	competitionId int,
    club1 int,
    club2 int,
    description nvarchar(200),
    foreign key (competitionId) references competition(competitionId) on delete cascade,
    foreign key (club1) references club(clubId),
    foreign key (club2) references club(clubId)
    );


    
create table competitionDetail(competitionDetailid int primary key auto_increment,
	competitionId int,
    chairTypeId int,
    price float,
    foreign key (competitionId) references competition(competitionid) on delete cascade,
    foreign key (chairTypeId) references chairtype(chairtypeid)
    );
    

create table book(bookId int primary key auto_increment,
	dateTimeCreated timestamp,
    empId int,
    total float,
    phone nvarchar(20),
    chairTypeId int,
    competitionId int,
    foreign key (empId) references employee(empid),
    foreign key (competitionId) references competition(competitionid) on delete cascade
    );

create table bookDetail(bookDetailId int primary key auto_increment,
	bookId int,
    chairTypeId int,
    qty int,
    subTotal float,
    foreign key (chairTypeId) references chairType(chairTypeid),
    foreign key (bookId) references book(bookid) on delete cascade
    );
    
create table bookChair(bookChairId int primary key auto_increment,
	bookDetailId int,
    chairId int,
    ticketNum nvarchar(30),
    foreign key (bookDetailid) references bookdetail(bookdetailid) on delete cascade,
    foreign key (chairId) references chair(chairid));

create table expense(expenseId int primary key auto_increment,
	dateCreated timestamp,
    total float,
    empId int,
    foreign key (empId) references employee(empid));
    
create table expenseDetail(expenseDetailId int primary key auto_increment,
	expenseid int,
    description nvarchar(200),
    amount float,
    foreign key (expenseId) references expense(expenseid) on delete cascade);
    

    

    
