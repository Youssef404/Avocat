create table IF NOT EXISTS affaire (

    id int auto_increment primary key,
    motif varchar(100) ,
    date_creation date,
    honoraires double,
    rest_paiement double default 0,
    dossier int default null,
    constraint fk_affaire_dossier foreign key(dossier) references dossier(id) ON DELETE SET NULL ON UPDATE CASCADE

);