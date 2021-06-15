<?php 
	require 'Database.php';
	class Registro{ 
		function _construct(){}

		public static function ObtenerTodosLosUsuarios(){
			$consultar = "SELECT * FROM login";

			$resultado = Database::getInstance()->getDb()->prepare($consultar);

			$resultado->execute();

			$tabla=$resultado->fetchAll(PDO::FETCH_ASSOC);

			return($tabla);
		}

		public static function ObtenerDatosPorId($id){
			$consultar = "SELECT * FROM login WHERE id =?";
			try{
				$resultado = Database::getInstance()->getDb()->prepare($consultar);
				$resultado->execute(array($id));
				$tabla=$resultado->fetch(PDO::FETCH_ASSOC);
				return $tabla;
			}
			catch(PDOException $e){
				return false;
			}
		}

		public static function InsertarNuevoDato($id,$password){
			$consutlar = "INSERT INTO login(id,Password) VALUES(?,?)";
			try{
				$resultado = Database::getInstance()->getDb()->prepare($consutlar);
				return $resultado->execute(array($id,$password));
			}catch(PDOException $e){
				return false;
			}
		}

		public static function ActualizarDatos($id,$password,$direccion,$ciudad){
			$consutlar = "UPDATE login SET Password=?, direccion=?, ciudad=? WHERE id='?'";
			if(self::ObtenerDatosPorId($id)){
				$resultado = Database::getInstance()->getDb()->prepare($consutlar);
				return $resultado->execute(array($password,$direccion,$ciudad,$id));
			}else{
				return false;
			}
		}
	
	}
?>