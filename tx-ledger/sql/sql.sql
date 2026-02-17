create table ledger (
                        id varchar primary key,
                        tid varchar not null,
                        account_id varchar not null,
                        direction varchar not null,
                        amount bigint not null
)

drop table ledger

select * from ledger;

create sequence ledger_seq start 1;
ALTER SEQUENCE ledger_seq INCREMENT BY 100;
select * from ledger_seq

create table ledger_last_offset (
                                    topic_partition varchar primary key,
                                    last_offset bigint not null,
                                    tid varchar not null
)

drop table ledger_last_offset

select * from ledger_last_offset