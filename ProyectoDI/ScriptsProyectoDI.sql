CREATE DATABASE proyecto;

CREATE TABLE proyecto( 
    idProyecto INT(5) PRIMARY KEY auto_increment,
    nombreProyecto VARCHAR(15),
    fechaI DATE,
    fechaF DATE
    );


CREATE TABLE tarea(
idTarea INT(5) PRIMARY KEY auto_increment,
nombreTare VARCHAR(15),
fechaI DATE,
fechaF DATE,
prioridad CHAR, 
estado CHAR,
idProyecto INT,
constraint pro_tarea_fk foreign key(idProyecto) references proyecto (idProyecto) on delete CASCADE on update CASCADE
);



// TRIGGER para actualizar la fecha del proyecto segun sus tareas uno para cuando insertas tablas y otro cuando actualizas
//ya que mysql no te deja hacerlo en un solo TRIGGER

DELIMITER //
CREATE TRIGGER actualizar_fecha_proyecto_update
AFTER UPDATE ON tarea
FOR EACH ROW
begin
	DECLARE proyecto_id INT;
    DECLARE max_fecha DATE;
	SELECT idProyecto INTO proyecto_id FROM tarea WHERE idTarea= NEW.idTarea;
    SELECT MAX(fechaF) INTO max_fecha FROM tarea WHERE idProyecto=proyecto_id;

    UPDATE proyecto SET fechaF=COALESCE(max_fecha, NOW()) WHERE idProyecto=proyecto_id;
END;
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER actualizar_fecha_proyecto_update
AFTER INSERT ON tarea
FOR EACH ROW
begin
	DECLARE proyecto_id INT;
    DECLARE max_fecha DATE;
	SELECT idProyecto INTO proyecto_id FROM tarea WHERE idTarea= NEW.idTarea;
    SELECT MAX(fechaF) INTO max_fecha FROM tarea WHERE idProyecto=proyecto_id;

    UPDATE proyecto SET fechaF=COALESCE(max_fecha, NOW()) WHERE idProyecto=proyecto_id;
END;
//
DELIMITER ;