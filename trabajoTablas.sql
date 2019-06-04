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
DNI char(9),
nombre varchar(20),
apellidos varchar(50),
pinAbonados varchar(7),
tarjetaCredito varchar(16),
email varchar(30),
tipoAbonado int(1),
matricula char(8),
fechaInicioAbono date,
fechaFinAbono date,
CONSTRAINT PK_abonados PRIMARY KEY (DNI),
CONSTRAINT FK_AbonadosVehiculos FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete set null on update cascade
);

create table if not exists plazas(
codPlazas int,
matricula char(8),
estado int,
CONSTRAINT PK_plazas PRIMARY KEY (codPlazas),
CONSTRAINT FK_PlazasVehiculos FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete set null on update cascade
);

create table if not exists tikets(
codPlazas int,
matricula char(8),
pin int(6),
precioFin double,
precioPorMin double,
fechaEntrada date,
tiempoEntrada time,
fechaSalida date,
tiempoSalida time,
CONSTRAINT PK_tiket PRIMARY KEY (codPlazas,matricula),
CONSTRAINT FK_matricula_tiket FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
    on delete no action on update cascade,
CONSTRAINT FK_plazas_tiket FOREIGN KEY (codPlazas)
    REFERENCES plazas(codPlazas)
    on delete no action on update cascade
);

insert into plazas
(codPlazas,matricula,estado)
values
(101,null,2),
(102,null,2),
(103,null,2),
(104,null,2),
(105,null,2),
(106,null,2),
(107,null,2),
(108,null,2),
(109,null,2),
(110,null,2),
(111,null,2),
(112,null,2),
(113,null,2),
(114,null,2),
(115,null,2),
(201,null,2),
(202,null,2),
(203,null,2),
(204,null,2),
(205,null,2),
(206,null,2),
(207,null,2),
(208,null,2),
(209,null,2),
(210,null,2),
(211,null,2),
(212,null,2),
(213,null,2),
(214,null,2),
(215,null,2),
(301,null,2),
(302,null,2),
(303,null,2),
(304,null,2),
(305,null,2),
(306,null,2),
(307,null,2),
(308,null,2),
(309,null,2),
(310,null,2),
(311,null,2),
(312,null,2),
(313,null,2),
(314,null,2),
(315,null,2);


