USE test;
drop table if exists X;
drop table if exists Y;
create table X (id Int32, x_name String) engine = CnchMergeTree ORDER BY id;
create table Y (id Int32, y_name String) engine = CnchMergeTree ORDER BY id;
insert into X (id, x_name) values (1, 'A'), (2, 'B'), (2, 'C'), (3, 'D'), (4, 'E'), (4, 'F'), (5, 'G'), (8, 'H'), (9, 'I');
insert into Y (id, y_name) values (1, 'a'), (1, 'b'), (2, 'c'), (3, 'd'), (3, 'e'), (4, 'f'), (6, 'g'), (7, 'h'), (9, 'i');
select 'inner';
select X.*, Y.* from X inner join Y using id order by X.id, X.x_name, Y.id, Y.y_name;
select 'inner subs';
select s.*, j.* from (select * from X) as s inner join (select * from Y) as j using id order by s.id, s.x_name, j.id, j.y_name;

select 'left';
select X.*, Y.* from X left join Y using id ORDER BY X.id, X.x_name, Y.id, Y.y_name;
select 'left subs';
select s.*, j.* from (select * from X) as s left join (select * from Y) as j using id ORDER BY s.id, s.x_name, j.id, j.y_name;

select 'right';
select X.*, Y.* from X right join Y using id order by X.id, X.x_name, Y.id, Y.y_name;
select 'right subs';
select s.*, j.* from (select * from X) as s right join (select * from Y) as j using id order by s.id, s.x_name, j.id, j.y_name;

select 'full';
select X.*, Y.* from X full join Y using id order by X.id, X.x_name, Y.id, Y.y_name;
select 'full subs';
select s.*, j.* from (select * from X) as s full join (select * from Y) as j using id order by s.id, s.x_name, j.id, j.y_name;

drop table X;
drop table Y;
