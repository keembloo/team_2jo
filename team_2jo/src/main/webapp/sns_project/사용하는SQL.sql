drop database if exists 2jo;
create database 2jo;
use 2jo;
drop table if exists board;
create table board (
	bno int auto_increment,
    bfile longtext not null,
    bdate datetime default now(),
	bcontent varchar(30) not null,
    bpwd varchar(20) not null,
    primary key (bno)
);

select*from board;
insert into board (bfile,bcontent,bpwd) values ('사진1','고연진짱','1234');
insert into board (bfile,bcontent,bpwd) values ('사진2','고연진짱짱짱짱','1234');
insert into board (bfile,bcontent,bpwd) values ('사진3','고연진최고최고최고','1234');
insert into board (bfile,bcontent,bpwd) values ('사진4','김규리바보바보바보','1234');