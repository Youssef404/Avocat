create table IF NOT EXISTS audience (
    id int auto_increment primary key,
    date_audience date,
    verdict varchar(100) default null,
    ville varchar(100),
    instance varchar(100),
    affaire int default null,
    constraint fk_audience_audience foreign key(affaire) references affaire(id) ON DELETE SET NULL ON UPDATE CASCADE
)