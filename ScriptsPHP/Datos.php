<?php

	require 'Database.php';
	class Datos{ 
		function _construct(){}

		public static function ObtenerTodosLosDatos($id){
			$tableMensajes = "Mensajes_".$id;
				$consultar = "SELECT  D.id, D.nombre,D.direccion,D.ciudad,D.tel, M.mensaje, M.hora_del_mensaje, M.tipo_mensaje,M.fecha FROM datospersonales D LEFT JOIN $tableMensajes M on M.user = D.id  and M.id=(SELECT max(M2.id) from $tableMensajes M2 where M2.user=M.user) ORDER by M.fecha desc";

				$resultado = Database::getInstance()->getDb()->prepare($consultar);

				$resultado->execute();

				$tabla=$resultado->fetchAll(PDO::FETCH_ASSOC);

				return($tabla);
				
		}
	}
?>

