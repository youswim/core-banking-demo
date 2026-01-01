create table transfer_tx_outbox (
    tid varchar(36) primary key,
    from_account_id varchar(14) not null,
    to_account_id varchar(14) not null,
    amount bigint not null,
    status char(10) not null,
    created_at timestamp not null,
    expire_at timestamp not null
);
