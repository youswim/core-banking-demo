create table TransferTxOutbox (
    tid varchar(36) primary key,
    fromAccount varchar(14) not null,
    toAccount varchar(14) not null,
    amount bigint not null
);