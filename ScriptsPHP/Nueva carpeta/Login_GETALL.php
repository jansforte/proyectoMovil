<?php
	require 'Login.php';
	
	if($_SERVER['REQUEST_METHOD']=='GET'){
		try{
			$respuesta = Registro::ObtenerTodosLosUsuarios();
			echo json_encode($respuesta);
		}catch(PDOException $e){
			echo json_encode(array('resultado' => 'Ocurrio un error, intentelo mas tarde'));
		}		
	}	
?>