create table USER (
    ID integer primary key auto_increment,
    NAME varchar(400)
);

create table TASK_LIST (
    ID integer primary key auto_increment,
    NAME varchar(400),
    USER_ID integer,
    foreign key (USER_ID) references USER(ID)
);

create table TASK (
    ID integer primary key auto_increment,
    TITLE varchar(400),
    CONTENTS text,
    DUE_DATE date,
    LAST_MODIFIED_DATE date,
    TASK_LIST_ID integer,
    foreign key (TASK_LIST_ID) references TASK_LIST(ID)
);
