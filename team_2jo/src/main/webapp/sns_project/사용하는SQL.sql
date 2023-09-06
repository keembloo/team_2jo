drop database if exists sns;
create database sns;
use sns;
drop table if exists board;
create table board (
	bno int auto_increment,
    bfile longtext not null,
    bdate datetime default now(),
	bcontent varchar(30) not null,
    bpwd varchar(20) not null,
    primary key (bno)
);
drop table if exists comment;

delete from board where bno=3;
select*from board;
insert into board (bfile,bcontent,bpwd) values ('사진1','고연진짱','1234');
insert into board (bfile,bcontent,bpwd) values ('사진2','고연진짱짱짱짱','1234');
insert into board (bfile,bcontent,bpwd) values ('사진3','고연진최고최고최고','1234');
update board set bfile = 'elemental.png' where bno =1;
update board set bfile = 'manbo.png' where bno =2;
update board set bfile = 'park.png' where bno =3;


create table comment(
	cno int auto_increment,
    ccontent varchar(30) not null,
	cdate datetime default now(),
    cpwd varchar(20) not null,
    bno int,
    primary key(cno),
    foreign key(bno) references board(bno) on delete cascade
);
select * from comment;

insert into comment(ccontent,cpwd,bno) values('테스트1','12345678',1);
insert into comment(ccontent,cpwd,bno) values('테스트2','12345678',2);
insert into comment(ccontent,cpwd,bno) values('테스트3','12345678',2);
insert into comment(ccontent,cpwd,bno) values('테스트4','12345678',1);


