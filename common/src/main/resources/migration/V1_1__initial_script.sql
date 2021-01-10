create table m_user
(
    id bigserial not null
        constraint m_user_pk
            primary key,
    login varchar(100) not null,
    password varchar(100) not null,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    name varchar(100),
    surname varchar(100),
    photo_link varchar(200)
);

alter table m_user owner to postgres;

create unique index m_user_id_uindex
    on m_user (id);

create unique index m_user_login_uindex
    on m_user (login);

create table m_location
(
    id bigserial not null
        constraint location_pk
            primary key,
    location varchar(100) not null,
    created timestamp(6) not null,
    changed timestamp(6) not null
);

alter table m_location owner to postgres;

create table m_cinema
(
    id bigserial not null
        constraint cinema_pk
            primary key,
    name varchar(100) not null,
    location_id bigint not null
        constraint m_cinema_m_location_id_fk
            references m_location
            on update cascade on delete cascade,
    phone_number varchar(100) not null,
    payment_method varchar(100) not null,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    address varchar(100) not null,
    event_id bigint not null
);

alter table m_cinema owner to postgres;

create table m_ticket
(
    id bigserial not null
        constraint m_ticket_pk
            primary key,
    user_id bigint not null
        constraint m_ticket_m_user_id_fk
            references m_user
            on update cascade on delete cascade,
    place_number bigint not null,
    price double precision not null,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    event_id bigint not null
);

alter table m_ticket owner to postgres;

create unique index m_ticket_id_uindex
    on m_ticket (id);

create table m_event
(
    id bigserial not null
        constraint event_pk
            primary key,
    cinema_id bigint not null
        constraint m_event_m_cinema_id_fk
            references m_cinema
            on update cascade on delete cascade,
    date varchar(100) not null,
    time varchar(100) not null,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    tickets_count bigint not null,
    ticket_id bigint not null
        constraint m_event_m_ticket_id_fk
            references m_ticket
            on update cascade on delete cascade
);

alter table m_event owner to postgres;

alter table m_cinema
    add constraint m_cinema_m_event_id_fk
        foreign key (event_id) references m_event
            on update cascade on delete cascade;

alter table m_ticket
    add constraint m_ticket_m_event_id_fk
        foreign key (event_id) references m_event;

create table m_movie
(
    id bigserial not null
        constraint m_movie_pk
            primary key,
    title varchar(100) not null,
    genre varchar(100) not null,
    year bigint not null,
    duration bigint not null,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    cinema_id bigint not null
        constraint m_movie_m_cinema_id_fk
            references m_cinema
            on delete cascade
);

alter table m_movie owner to postgres;

create table m_role
(
    id serial not null
        constraint m_role_pk
            primary key,
    role_name varchar(100) default 'ROLE_USER'::character varying not null,
    user_id bigint not null
        constraint m_role_m_user_id_fk
            references m_user
            on delete cascade
);

alter table m_role owner to postgres;

create index m_role_role_name_index
    on m_role (role_name);


