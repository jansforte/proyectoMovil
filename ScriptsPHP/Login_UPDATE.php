<?php
	require 'Registro.php';

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$datos = json_decode(file_get_contents("php://input"),true);
		$respuesta = Registro::ActualizarDatos($datos["id"],$datos["tel"],$datos["password"],$datos["nombre"]
		,$datos["direccion"],$datos["ciudad"]);
		$r2 = Registro::datosactualizarlogin($datos["id"],$datos["password"]);
		if($respuesta && $r2){
			echo json_encode(array('resultado' => 'Datos actualizados correctamente'));
		}else{
			echo json_encode(array('resultado' => 'Error al actualizar los datos'));
		}
	}

?>