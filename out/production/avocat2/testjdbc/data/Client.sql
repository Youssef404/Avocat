create table IF NOT EXISTS client (

id int auto_increment primary key,
nom varchar(50) ,
prenom varchar(50) ,
adresse varchar(100),
tel varchar(50),
dossier int default null,
constraint fk_dossier_id foreign key(dossier) references dossier(id) ON DELETE SET NULL ON UPDATE CASCADE
);

