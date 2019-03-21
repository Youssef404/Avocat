create table IF NOT EXISTS dossier (
                                     id            int AUTO_INCREMENT primary key,
                                     date_creation date,
                                     titre         varchar(255),
                                     demandeur     varchar(255),
                                     defendeur     varchar(255)
);
