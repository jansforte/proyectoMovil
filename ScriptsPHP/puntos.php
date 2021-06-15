<?php
	require 'Registro.php';

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$datos = json_decode(file_get_contents("php://input"),true);
		$respuesta = Registro::InsertarPuntos($datos["id"],$datos["puntos"]);
		if($respuesta){
			echo json_encode(array('resultado'=>'Los puntos se registraron correctamente'));
		}else{
			echo json_encode(array('resultado'=>'No se pudo realizar el registro'));
		}
	}

?>