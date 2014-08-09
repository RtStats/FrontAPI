-- table to store metadata
CREATE TABLE tsc_metadata (
    c        varchar,
    o        text,
    PRIMARY KEY (c)
) WITH COMPACT STORAGE;

UPDATE tsc_metadata SET o='[
    {"pattern":"^.*$","table":"tsc_counter","counter_column":true}
]' WHERE c='*';

-- main table to store all unconfigured counters
CREATE TABLE tsc_counter (
    "c"             text,
    "ym"            int,
    "d"             int,
    "t"             bigint,
    "v"             counter,
    PRIMARY KEY(("c","ym","d"),"t")
) WITH COMPACT STORAGE;
