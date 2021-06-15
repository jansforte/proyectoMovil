<?php 
	require 'Database.php';
	class Token{ 
		function _construct(){}

		public static function ObtenerTodosLosUsuarios(){
			$consultar = "SELECT * FROM token";

			$resultado = Database::getInstance()->getDb()->prepare($consultar);

			$resultado->execute();

			$tabla=$resultado->fetchAll(PDO::FETCH_ASSOC);

			return($tabla);
		}

		public static function ObtenerDatosPorId($id){
			$consultar = "SELECT * FROM token WHERE id = ?";
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
			$consutlar = "INSERT INTO token(id,token) VALUES(?,?)";
			try{
				$resultado = Database::getInstance()->getDb()->prepare($consutlar);
				return $resultado->execute(array($id,$password));
			}catch(PDOException $e){
				return false;
			}
		}

		public static function ActualizarDatos($id,$password){
			$consutlar = "UPDATE token SET token=? WHERE id=?";
			if(self::ObtenerDatosPorId($id)){
				$resultado = Database::getInstance()->getDb()->prepare($consutlar);
				return $resultado->execute(array($password,$id));
			}else{
				return false;
			}
		}
	
	}
?>