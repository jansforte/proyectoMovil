<?php
	require 'Login.php';

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$datos = json_decode(file_get_contents("php://input"),true);
		$respuesta = Registro::InsertarNuevoDato($datos["id"],$datos["password"]);
		if($respuesta){
			echo json_encode(array('resultado'=>'El token se subio correctamente'));
		}else{
			echo json_encode(array('resultado'=>'El token no se subio correctamente'));
		}
	}

?>