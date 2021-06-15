<?php
	
	require 'Database.php';

	class Registro{
		function _construct(){
		}

		public static function ActualizarDatos($id,$tel,$password,$nombre,$direccion,$ciudad){
			$consutlar = "UPDATE datospersonales SET Password=?, direccion=?, ciudad=?, tel=? WHERE id=?";
				$resultado = Database::getInstance()->getDb()->prepare($consutlar);
				return $resultado->execute(array($password,$direccion,$ciudad,$tel,$id));
		}

		public static function datosactualizarlogin($id,$password){
			$consutlar = "UPDATE login SET Password=? WHERE id=?";
			$resultado = Database::getInstance()->getDb()->prepare($consutlar);
				return $resultado->execute(array($password,$id));
		}


		public static function ObtenerTodosLosUsuarios($id){
			$consultar = "SELECT * FROM datospersonales WHERE id=?";

			$resultado = Database::getInstance()->getDb()->prepare($consultar);

			$resultado->execute(array($id));

			$tabla=$resultado->fetchAll(PDO::FETCH_ASSOC);

			return($tabla);
		}

		public static function InsertarNuevoDato($id,$tel,$password,$nombre,$direccion,$ciudad){
			$consultar = "INSERT INTO datospersonales(id,tel,Password,nombre,direccion,ciudad) VALUES(?,?,?,?,?,?)";
			try{
				$resultado = Database::getInstance()->getDb()->prepare($consultar);
				return $resultado->execute(array($id,$tel,$password,$nombre,$direccion,$ciudad));
			}catch(PDOException $e){
				return false;
			}
		}

		public static function InsertarEnTablaLogin($id,$password){
			$consultar = "INSERT INTO login(id,Password) VALUES(?,?)";
			try{
				$resultado = Database::getInstance()->getDb()->prepare($consultar);
				return $resultado->execute(array($id,$password));
			}catch(PDOException $e){
				return false;
			}
		}

		public static function CreateTable($id){
			$NameTable="Mensajes_".$id;
			try{
				$consultar = "CREATE TABLE $NameTable (
					id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
					user VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
					code_mensaje VARCHAR(80) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
				 	mensaje VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
				  	tipo_mensaje VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
				  	hora_del_mensaje VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
				  	fecha DATETIME NOT NULL )";
				$respuesta = Database::getInstance()->getDb()->prepare($consultar);
				$respuesta -> execute(array());
				return 200;
			}catch(PDOException $e){
				return -1;
			}
		}

		public static function InsertarPuntos($id,$puntos){
			$consultar = "INSERT INTO rpuntos(id,puntos) VALUES (?,?)";
			try{
				$resultado = Database::getInstance()->getDb()->prepare($consultar);
				return $resultado->execute(array($id,$puntos));
			}catch(PDOException $e){
				return false;
			}
		}

	}

?>