
drop database if exists nichanaecha;
create database nichanaecha;
use nichanaecha;

# 회원 정보 
drop table if exists member; 
create table member(
mno int auto_increment,                  #회원 번호 
mid varchar(20) not null unique,      #회원 아이디
mpw varchar(20)   not null,            #회원 비밀번호 
mphone varchar(15) not null,         #회원 전화번호
mname varchar(20) not null,            #회원 이름 
mads varchar(100) not null,            #회원 주소 
mcash bigint default 0,               #회원 보유 금액 
primary key(mno)
);

insert into member(mid,mpw,mphone,mname,mads) values('admin1111','1234','010-1111-2222','김규리','서울시 대복동');
insert into member(mid,mpw,mphone,mname,mads) values('admin2222','1234','010-1111-3333','고연진','평양시 강호동');
insert into member(mid,mpw,mphone,mname,mads) values('admin3333','1234','010-1111-4444','이성호','시흥시 대부동');
insert into member(mid,mpw,mphone,mname,mads) values('admin4444','1234','010-1111-5555','정용상','안산시 초지동');



drop table if exists car;
create table car(
cno int auto_increment,               #매물 번호 
ccompany varchar(50) not null,         #제조사 
cnum varchar(8) not null,            #차량 번호
csize char(10) not null,            #차량 종류
cc int not null,                  #배기량
coil varchar(20) not null,            #연료
cname varchar(20) not null,            #차량명
cdate varchar(20) not null,            #제조년월
ckm int not null,                     #주행거리
mno int,                        # 회원 코드 참조키
primary key(cno),
foreign key(mno) references member(mno) on delete cascade
);

drop table if exists caraddress;
create table caraddress(
   cads varchar(50) not null,            #차량 등록 주소
    calat varchar(50) not null,            # 차량 위치 위도
   calng varchar(50) not null,            # 차량 위치 경도
    cacode varchar(15) not null,         # 법적동 코드
    cacodename varchar(20) not null,      # 법적동명
    cno int,                        # 차 참조키
    foreign key(cno) references car(cno) on delete cascade on update cascade
    
);

drop table if exists carimg; 
create table carimg(
cino int auto_increment,
ciimg longtext not null,               #차량 사진
cno int,
primary key(cino),
foreign key(cno) references car(cno)
);

drop table if exists auctionInfo; 
create table auctionInfo(
ano int auto_increment,            #경매 번호 
atitle varchar(30) not null,      #경매 제목 
acontent longtext not null,            #경매 내용 
astartdate datetime default now(),   #경매 등록 날짜 
aenddate datetime not null,         #경매 종료 날짜 
aprice bigint not null,            #경매 등록 가격 
astate tinyint default 0,       #경매 상태 [ 0: 경매중, 1:거래중(낙찰 후 판매자와 거래중) 2:경매 취소 3:경매 완료
cno int,                         #매물 정보
primary key(ano),
foreign key(cno) references car(cno) on delete no action
);

drop table if exists buymember; 
create table buymember(
bno int auto_increment,                  #입찰 번호
bprice bigint not null,                  #입찰 가격 
bdate datetime default now(),            #입찰 시간 
mno int,                           #입찰자
ano int,
primary key(bno),
foreign key(mno) references member(mno),
foreign key(ano) references auctionInfo(ano) on delete cascade
);

drop table if exists wishlist; 
create table wishlist(
wno int auto_increment,               # 찜번호
mno int,                        # 회원 번호
ano int,                        # 경매 정보
primary key(wno),
foreign key(mno) references member(mno) on delete cascade, 
foreign key(ano) references auctionInfo(ano) on delete cascade 
);

# 포인트 기록
drop table if exists pointrecord;
create table pointrecord(
pointno varchar(50) ,               # 포인트기록 고유번호
mno int ,                              # 회원 번호
mpoint bigint default 0 ,              # 실제회원의 포인트
pointdate datetime default now() ,
pointhistory varchar(100) ,             #입출금내역
primary key(pointno),
foreign key(mno) references member(mno) on delete set null
);



select * from member;
select * from carimg;
select * from car;
select * from auctioninfo;





