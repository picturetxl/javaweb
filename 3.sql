-- 供货商表
create table supplier(
 sup_id int primary key,
 sup_addr varchar(50) comment '供货商的地址',
 sup_man varchar(20) comment '联系人',
 sup_tel varchar(20) comment '联系人电话',
 sup_name varchar(30) comment '供货商的名称'
)charset utf8;
-- 供货商1 提供 computer    供货商2 提供 projector
insert into supplier values(1,'南京','王二','13211112222','南京电脑科技公司');
insert into supplier values(2,'合肥','白球','13311112222','合肥投影仪集团');

-- 设备类型表
create table type(
     type_id int primary key,
     type_name varchar(30) not null,
     sup_id int,
     foreign key(sup_id) references supplier(sup_id)
)charset utf8;
-- 0代表电脑     1 代表课堂用品 后面的1 2代表的是供货商id
insert into type values(0,'computer',1);
insert into type values(1,'class_supplies',2);

-- 设备表
create table equipment(
     equ_id int primary key,
     equ_name varchar(20) not null,
     equ_price decimal not null,
     type_id int,
     foreign key(type_id) references type(type_id)
)charset utf8;
insert into equipment values('0001','lenovo-520-24IKU','3999',0);
insert into equipment values('0002','lenovo-520-24IKU','3999',0);
insert into equipment values('0003','lenovo-520-24IKU','3999',0);
insert into equipment values('1004','acer-x118h','3999',1);
insert into equipment values('1005','acer-x118h','3999',1);
insert into equipment values('1006','acer-x118h','3999',1);


-- 订单信息表
create table order_info(
     order_id int primary key auto_increment,
     sup_id int,
     equ_id int,
     foreign key(equ_id) references equipment(equ_id),
     foreign key(sup_id) references supplier(sup_id)
)charset utf8;
insert into order_info(order_id,sup_id,equ_id) values(null,1,'0001');
insert into order_info(order_id,sup_id,equ_id) values(null,1,'0002');
insert into order_info(order_id,sup_id,equ_id) values(null,1,'0003');
insert into order_info(order_id,sup_id,equ_id) values(null,2,'1004');
insert into order_info(order_id,sup_id,equ_id) values(null,2,'1005');
insert into order_info(order_id,sup_id,equ_id) values(null,2,'1006');


-- 教师表
create table techer(
     tech_id int primary key not null,
     tech_name varchar(20) not null,
     tech_password varchar(20) not null,
     tech_phone varchar(20) not null
)charset utf8;
insert into techer values(201,'龚金晶','123456','13094572871');
insert into techer values(202,'费秋乐','123456','13393372871');


-- 借还表
create table rent(
     rent_id int primary key auto_increment,
     equ_id int,
     tech_id int,
     foreign key(equ_id) references equipment(equ_id),
     foreign key(tech_id) references techer(tech_id)
)charset utf8;


-- 管理员表
create table adminstrator(
     adm_id int primary key ,
     adm_name varchar(20) not null,
     adm_password varchar(20) not null,
     adm_tel varchar(20) not null,
     adm_sex enum('男','女')
)charset utf8;
insert into adminstrator values(101,'邰亮','thll1tl2dl','19234567832','男');


-- 采购员信息表
create table buyer(
     buy_id int primary key,
     buy_name varchar(20) not null,
     buy_tel varchar(20) not null,
     buy_sex enum('男','女') not null,
     adm_id int,
     foreign key(adm_id) references adminstrator(adm_id)
)charset utf8;
insert into buyer values(301,'王水仙','12037836276','男',101);

-- 采购设备表
create table buyequipment(
     be_id int primary key auto_increment,
     buy_id int,
     equ_id int,
     index(equ_id),
     foreign key(buy_id) references buyer(buy_id),
     foreign key(equ_id) references equipment(equ_id) on delete cascade
)charset utf8;

insert into buyequipment values(null,301,0001);
insert into buyequipment values(null,301,0002);
insert into buyequipment values(null,301,0003);
insert into buyequipment values(null,301,1004);
insert into buyequipment values(null,301,1005);
insert into buyequipment values(null,301,1006);


-- 留言板信息
create table message(
    mid int primary key auto_increment,
    username varchar(20) not null,
    title varchar(20) ,
    message text
)charset utf8;