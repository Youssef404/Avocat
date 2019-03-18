create table IF NOT EXISTS piece (
    id int auto_increment primary key,
    chemin_img varchar(200),
    affaire int default null,
    constraint fk_audience_piece foreign key(affaire) references affaire(id) ON DELETE SET NULL ON UPDATE CASCADE
)