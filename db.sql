SET FOREIGN_KEY_CHECKS = 1;
SET default_storage_engine=InnoDB;

create database IF NOT EXISTS staduim ;

use staduim;

create table role(id int primary key auto_increment,
role nvarchar(30));


create table user(id int primary key auto_increment,
username nvarchar(30),
password nvarchar(30),
roleId int,
foreign key(roleId) references role(id));


create table employee(id int primary key auto_increment,
	fName nvarchar(30),
    lName nvarchar(30),
    gender char,
    dob date,
    hiredDated date,
    address nvarchar(200),
    tel nvarchar(20),
    photo nvarchar(200),
    userId int,
    foreign key (userId) references user(id) on delete cascade);

    
create table club(id int primary key auto_increment,
	club nvarchar(30));
    
create table section(id int primary key auto_increment,
	section nvarchar(30));

create table chairType(id int primary key auto_increment,
	chairType nvarchar(30));
    
create table chair(id int primary key auto_increment,
	chairTypeId int,
    sectionId int,
    chairNum nvarchar(30),
    foreign key (chairtypeId) references chairtype(id) on delete cascade,
    foreign key (sectionId) references section(id) on delete cascade);
    
create table competition(id int primary key auto_increment,
	dateTimeStart timestamp,
    dateTimeEnd timeStamp,
    createdBy int,
    description nvarchar(200),
    status int,
    foreign key(createdBy) references employee(id));
    
create table competitionClub(id int primary key auto_increment,
	competitionId int,
    club1 int,
    club2 int,
    description nvarchar(200),
    foreign key (competitionId) references competition(id) on delete cascade,
    foreign key (club1) references club(id),
    foreign key (club2) references club(id)
    );


    
create table competitionDetail(id int primary key auto_increment,
	competitionId int,
    chairTypeId int,
    price float,
    foreign key (competitionId) references competition(id) on delete cascade,
    foreign key (chairTypeId) references chairtype(id)
    );
    

create table book(id int primary key auto_increment,
	dateTimeCreated timestamp,
    empId int,
    total float,
    phone nvarchar(20),
    chairTypeId int,
    competitionId int,
    foreign key (empId) references employee(id),
    foreign key (competitionId) references competition(id) on delete cascade
    );

create table bookDetail(id int primary key auto_increment,
	bookId int,
    chairTypeId int,
    qty int,
    subTotal float,
    foreign key (chairTypeId) references chairType(id),
    foreign key (bookId) references book(id) on delete cascade
    );
    
create table bookChair(id int primary key auto_increment,
	bookDetailId int,
    chairId int,
    ticketNum nvarchar(30),
    foreign key (bookDetailid) references bookdetail(id) on delete cascade,
    foreign key (chairId) references chair(id));

create table expense(id int primary key auto_increment,
	dateCreated timestamp,
    total float,
    empId int,
    foreign key (empId) references employee(id));
    
create table expenseDetail(id int primary key auto_increment,
	expenseid int,
    description nvarchar(200),
    amount float,
    foreign key (expenseId) references expense(id) on delete cascade);
    

    

    
