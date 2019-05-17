-- set global time_zone='+1:00';
drop database if exists parkingDaw;
create database if not exists parkingDaw;
use parkingDaw;

create table if not exists vehiculos(
matricula char(8),
tipoVehiculo int(3),
CONSTRAINT PK_vehiculos PRIMARY KEY (matricula)
);
create table if not exists abonados(
codAbonados int,
nombre varchar(20),
apellidos varchar(50),
DNI char(9),
pinAbonados int(6),
tarjetaCredito varchar(15),
email varchar(30),
tipoAbonado int(3),
matricula char(8),
CONSTRAINT PK_abonados PRIMARY KEY (codAbonados),
CONSTRAINT FK_AbonadosVehiculos FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete no action on update cascade
);

create table if not exists plazas(
codPlazas int,
numPlazas int,
matricula char(8),
CONSTRAINT PK_plazas PRIMARY KEY (codPlazas),
CONSTRAINT FK_plazasMatricula FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete no action on update cascade
);

create table if not exists tikets(
codPlazas int,
matricula char(8),
pin int(6),
precio int,
CONSTRAINT PK_tiket PRIMARY KEY (codPlazas,matricula),
CONSTRAINT FK_matricula_tiket FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
    on delete no action on update cascade,
CONSTRAINT FK_plazas_tiket FOREIGN KEY (codPlazas)
    REFERENCES plazas(codPlazas)
    on delete no action on update cascade
);


