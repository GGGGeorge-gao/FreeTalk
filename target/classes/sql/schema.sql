create table FreeTalk.user
(
    id         int primary key auto_increment,
    name       varchar(255) not null unique,
    password   varchar(255),
    created_at timestamp    not null default CURRENT_TIMESTAMP
) CHARSET = utf8mb4
  ENGINE = innoDB;

create table FreeTalk.roles
(
    id      int primary key auto_increment,
    role    varchar(64),
    comment varchar(255),
    index (role)
) CHARSET = utf8mb4;

create table FreeTalk.user_roles
(
    user_id int not null,
    roles   int not null,
    foreign key (user_id) REFERENCES user (id) on delete cascade,
    foreign key (roles) REFERENCES roles (id) on delete cascade
) CHARSET = utf8mb4;

create table FreeTalk.message
(
    id           int primary key auto_increment,
    user_id      int,
    user_name    varchar(255)  not null,
    content      varchar(1000) not null,
    message_type tinyint       not null,
    created_at   timestamp default CURRENT_TIMESTAMP,
    foreign key (user_id) references user (id) on delete no action
) CHARSET = utf8mb4;

create table FreeTalk.login_info
(
    id          int primary key auto_increment,
    user_id     int          not null,
    user_name   varchar(255) not null,
    status      TINYINT      not null comment '登录为1,退出为2',
    handle_time timestamp,
    foreign key (user_id) references user (id) on delete no action
) CHARSET = utf8mb4;