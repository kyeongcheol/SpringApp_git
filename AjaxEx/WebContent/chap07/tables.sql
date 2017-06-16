create table COMMENT2 (
    ID        number PRIMARY KEY,
    NAME      VARCHAR2(20) NOT NULL,
    CONTENT   VARCHAR2(4000) NOT NULL
);

create table ID_REPOSITORY (
    NAME      VARCHAR2(20) PRIMARY KEY,
    VALUE     number
);

insert into ID_REPOSITORY values ('COMMENT2', 0);