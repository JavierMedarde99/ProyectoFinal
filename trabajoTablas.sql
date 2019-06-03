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
on delete cascade on update cascade
);

create table if not exists plazas(
codPlazas int,
matricula char(8),
estado int,
CONSTRAINT PK_plazas PRIMARY KEY (codPlazas),
CONSTRAINT FK_PlazasVehiculos FOREIGN KEY (matricula)
    REFERENCES vehiculos(matricula)
on delete cascade on update cascade
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
(101,'',2),
(102,'',2),
(103,'',2),
(104,'',2),
(105,'',2),
(106,'',2),
(107,'',2),
(108,'',2),
(109,'',2),
(110,'',2),
(111,'',2),
(112,'',2),
(113,'',2),
(114,'',2),
(115,'',2),
(201,'',2),
(202,'',2),
(203,'',2),
(204,'',2),
(205,'',2),
(206,'',2),
(207,'',2),
(208,'',2),
(209,'',2),
(210,'',2),
(211,'',2),
(212,'',2),
(213,'',2),
(214,'',2),
(215,'',2),
(301,'',2),
(302,'',2),
(303,'',2),
(304,'',2),
(305,'',2),
(306,'',2),
(307,'',2),
(308,'',2),
(309,'',2),
(310,'',2),
(311,'',2),
(312,'',2),
(313,'',2),
(314,'',2),
(315,'',2);


