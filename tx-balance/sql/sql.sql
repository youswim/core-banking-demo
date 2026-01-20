create table balance (
    balance_id varchar(14) primary key,
    amount bigint not null
)

insert into balance
values ('myId', 5000000);

insert into balance
values ('friendId', 5000000)

select * from balance

create table last_offset (
    topic_partition varchar primary key,
    last_offset bigint not null
)