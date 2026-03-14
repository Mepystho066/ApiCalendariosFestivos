Drop DATABASE calendarioFestivosPaises WITH (FORCE);
DROP TABLE pais, tipofestivo, tipo, calendario,festivo;
CREATE DATABASE CalendarioFestivosPaises;


CREATE TABLE pais(
	Id SERIAL PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL
    );


CREATE TABLE tipofestivo(
	Id SERIAL PRIMARY KEY,
	tipo VARCHAR(100) NOT NULL
	);

CREATE TABLE tipo(
	Id SERIAL PRIMARY KEY,
	tipo VARCHAR(100) NOT NULL
	);

CREATE TABLE calendario(
	Id SERIAL PRIMARY KEY,
	fecha DATE NOT NULL,
	descripcion TEXT,
	idtipo INT NOT NULL,
    idpais INT NOT NULL,
	CONSTRAINT fkCalendario_Tipo FOREIGN KEY (idtipo) REFERENCES tipo(Id),
    CONSTRAINT fkCalendario_Pais FOREIGN KEY (idpais) REFERENCES pais(Id)
);

CREATE TABLE festivo(
	Id SERIAL PRIMARY KEY,
	idpais INT NOT NULL,
	nombre VARCHAR(100) NOT NULL,
    dias INT,
	mes INT,
	diasPascua INT,
	idtipo INT NOT NULL,
    CONSTRAINT fkFestivo_Pais FOREIGN KEY (idpais) REFERENCES pais(Id),
    CONSTRAINT fkFestivo_TipoFestivo FOREIGN KEY (idtipo) REFERENCES tipofestivo(Id)
);

INSERT INTO pais(Id, nombre) VALUES
(1, 'Colombia'),
(2, 'Ecuador'),
(3, 'Peru'),
(4, 'Venezuela');

INSERT INTO tipofestivo(Id, tipo) VALUES
(1, 'Fijo'),
(2, 'Ley Puente Festivo'),
(3, 'Basado en Pascua'),
(4, 'Basado en Pascua y Ley Puente Festivo');


INSERT INTO festivo (id,idpais,dias, mes, nombre, diaspascua, idtipo) VALUES
(1,1,1,1, 'Año nuevo',0,1),
(2,1,6,1, 'Santos Reyes',0,2),
(3,1,19,3, 'San José',0,2),
(4,1,0,0, 'Jueves Santo',-3,3),
(5,1,0,0, 'Viernes Santo',-2,3),
(6,1,0,0, 'Domingo de Pascua',0,3),
(7,1,1,5, 'Día del Trabajo',0,1),
(8,1,0,0, 'Ascensión del Señor',40,4),
(9,1,0,0, 'Corpus Christi',61,4),
(10,1,0,0, 'Sagrado Corazón de Jesús',68,4),
(11,1,29,6, 'San Pedro y San Pablo',0,2),
(12,1,20,7, 'Independencia Colombia',0,1),
(13,1,7,8, 'Batalla de Boyacá',0,1),
(14,1,15,8, 'Asunción de la Virgen',0,2),
(15,1,12,10, 'Día de la Raza',0,2),
(16,1,1,11, 'Todos los santos',0,2),
(17,1,11,11, 'Independencia de Cartagena',0,2),
(18,1,8,12, 'Inmaculada Concepción',0,1),
(19,1,25,12, 'Navidad',0,1);