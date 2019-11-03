create table authorities
(
    id        bigserial    not null
        constraint authorities_pkey
            primary key,
    role_name varchar(255) not null
);

create table category
(
    id   bigserial not null
        constraint category_pkey
            primary key,
    name varchar(255)
);

create table users
(
    id                bigserial                           not null
        constraint users_pkey
            primary key,
    activated_account boolean   default false             not null,
    country           varchar(255)                        not null,
    date_of_birth     date                                not null,
    email             varchar(255)                        not null,
    faculty           varchar(255)                        not null,
    first_name        varchar(255)                        not null,
    /*gender            varchar(255)                        not null,*/
    last_name         varchar(255)                        not null,
    need_visa         boolean   default false             not null,
    passport_number   varchar(255),
    password          varchar(255)                        not null,
    phone_number      varchar(9),
    registration_date timestamp default CURRENT_TIMESTAMP not null,
    title             varchar(255)                        not null,
    university        varchar(255)                        not null,
    year_of_study     integer                             not null
);

create table users_authorities
(
    user_id        bigint not null
        constraint fkq3lq694rr66e6kpo2h84ad92q
            references users,
    authorities_id bigint not null
        constraint fkmfxncv8ke1jjgna64c8kclry5
            references authorities,
    constraint users_authorities_pkey
        primary key (user_id, authorities_id)
);

create table case_abstracts
(
    id          bigserial    not null
        constraint case_abstracts_pkey
            primary key,
    authors     text[]       not null,
    status      varchar(255) not null,
    title       varchar(255) not null,
    tutors      varchar(255) not null,
    type        varchar(255),
    background  varchar(255) not null,
    case_report varchar(255) not null,
    conclusions varchar(255) not null,
    category_id bigint       not null
        constraint fkbmf5y76o099f6kodbr12lxyfv
            references category,
    user_id     bigint
        constraint fklhkmcwlrwapajh4mv0960oqvl
            references users
);

create table research_abstracts
(
    id                   bigserial    not null
        constraint research_abstracts_pkey
            primary key,
    authors              text[]       not null,
    status               varchar(255) not null,
    title                varchar(255) not null,
    tutors               varchar(255) not null,
    type                 varchar(255),
    aim_of_the_study     varchar(255) not null,
    conclusions          varchar(255) not null,
    introduction         varchar(255) not null,
    material_and_methods varchar(255) not null,
    results              varchar(255) not null,
    category_id          bigint       not null
        constraint fk78lmiqcd8omaqj3q5i2woits4
            references category,
    user_id              bigint
        constraint fkc5pad4udvsop678mdt3uc9r0e
            references users
);

