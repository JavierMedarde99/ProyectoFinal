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
pinAbonados int(6),
tarjetaCredito varchar(15),
email varchar(30),
tipoAbonado int(1),
matricula char(8),
fechaInicioAbono date,
fechaFinAbono date,
CONSTRAINT PK_abonados PRIMARY KEY (DNI),
CONSTRAINT FK_AbonadosVehiculos FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete no action on update cascade
);

create table if not exists plazas(
codPlazas int,
numPlazas int,
matricula char(8),
estado int,
CONSTRAINT PK_plazas PRIMARY KEY (codPlazas),
CONSTRAINT FK_plazasMatricula FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete no action on update cascade
);

create table if not exists tikets(
codPlazas int,
matricula char(8),
pin int(6),
precioFin int,
precioPorMin int,
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
(101,null,false),
(102,null,false),
(103,null,false),
(104,null,false),
(105,null,false),
(106,null,false),
(107,null,false),
(108,null,false),
(109,null,false),
(110,null,false),
(111,null,false),
(112,null,false),
(113,null,false),
(114,null,false),
(115,null,false),
(201,null,false),
(202,null,false),
(203,null,false),
(204,null,false),
(205,null,false),
(206,null,false),
(207,null,false),
(208,null,false),
(209,null,false),
(210,null,false),
(211,null,false),
(212,null,false),
(213,null,false),
(214,null,false),
(215,null,false),
(301,null,false),
(302,null,false),
(303,null,false),
(304,null,false),
(305,null,false),
(306,null,false),
(307,null,false),
(308,null,false),
(309,null,false),
(310,null,false),
(311,null,false),
(312,null,false),
(313,null,false),
(314,null,false),
(315,null,false)


