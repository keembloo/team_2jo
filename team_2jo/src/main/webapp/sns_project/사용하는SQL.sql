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
    blike int default 0,
    bdislike int default 0,
    primary key (bno)
);

drop table if exists comment;
create table comment(
	cno int auto_increment,
    ccontent varchar(30) not null,
	cdate datetime default now(),
    cpwd varchar(20) not null,
    bno int,
    primary key(cno),
    foreign key(bno) references board(bno) on delete cascade
);

drop table if exists recommend;
create table recommend(
	rno int auto_increment,
    rip varchar(100) unique not null,
    rliked boolean default false,
    rdisliked boolean default false,
    bno int,
    primary key(rno),
    foreign key(bno) references board(bno) on delete cascade
);





insert into board (bfile,bcontent,bpwd) values ('elemental.png','고연진짱','1234');
insert into board (bfile,bcontent,bpwd) values ('manbo.png','고연진짱짱짱짱','1234');
insert into board (bfile,bcontent,bpwd) values ('park.png','고연진최고최고최고','1234');

insert into comment(ccontent,cpwd,bno) values('테스트1','12345678',1);
insert into comment(ccontent,cpwd,bno) values('테스트2','12345678',2);
insert into comment(ccontent,cpwd,bno) values('테스트3','12345678',2);
insert into comment(ccontent,cpwd,bno) values('테스트4','12345678',1);

insert into recommend(rip,rliked,rdisliked,bno) values('uid1',true,true,1);
insert into recommend(rip,rliked,rdisliked,bno) values('uid2',false,true,2);

select * from comment;
select * from board;
select * from recommend;


