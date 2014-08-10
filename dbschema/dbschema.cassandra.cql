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

-- table to store tag-counters mapping
CREATE TABLE tsc_tag_counter (
    "t"             text,
    "c"             text,
    "v"             int,
    PRIMARY KEY ("t", "c")
) WITH COMPACT STORAGE;

-- table to store "all" stuff (e.g. all existing tags, all existing counters, etc)
CREATE TABLE tsc_all_list (
    "n"             text,
    "k"             text,
    "v"             int,
    PRIMARY KEY ("n", "k")
) WITH COMPACT STORAGE;
