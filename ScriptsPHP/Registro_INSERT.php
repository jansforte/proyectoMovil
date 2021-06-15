<?php
	require 'Registro.php';

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$datos = json_decode(file_get_contents("php://input"),true);
		$respuesta = Registro::InsertarNuevoDato($datos["id"],$datos["id"],$datos["password"],$datos["nombre"],$datos["direccion"],$datos["ciudad"]);
		$r2 = Registro::InsertarEnTablaLogin($datos["id"],$datos["password"]);
		$r3 = Registro::CreateTable($datos["id"]);
		if($respuesta && $r2 && $r3=200){
			echo json_encode(array('resultado' => 'El usuario se registro correctamente'));
		}else{
			echo json_encode(array('resultado' => 'El usuario ya existe, por favor inserte otro usuario'));
		}
	}

?>