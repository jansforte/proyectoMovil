			<?php
			ignore_user_abort();
			ob_start();

			$Mensaje = 'prueba';
			$hora ='23:33';
			$emisor_del_mensaje ='admin';
			$receptor_del_mensaje ='jans';
			$token ='f49Se81nSd6kkh31i5Foyb:APA91bG7UassGDMobj3xbysHV9xOqoW8omMDrToIcS3UGd7t0EZk8K9GaNyQsye1IpvsAyvSa0yJo5c-ZvvpcOK8saTD_uwazSuPEGeGg4VTlgsGJEfgQm_DwyTbRe8-gvuelE6remnf';

			$url = 'https://fcm.googleapis.com/fcm/send';

			$fields =array('to' => $token, 'notification' => array(
				'title'=>$emisor_del_mensaje,
      			'body'=>'te envio un nuevo mensaje!'),
			'data' => array('mensaje' => $Mensaje,'hora' => $hora,'cabezera' => $emisor_del_mensaje.' te envio un nuevo mensaje','cuerpo' => $Mensaje, 'receptor' => $receptor_del_mensaje));

			define('GOOGLE_API_KEY', 'AAAAS4AMr5Y:APA91bEbhYSvq20dqnv2Z_xBEP8JhBUnlvEegNSWQBbFWLGbt9Qa7eyN2BoM4_hrOKHxCd83pnidoOtudWlpYFGJHNs4f8HYXQzzxN4kbl848X_oJgouQKYl1fvRj1sVwTk8tnxwo-bG');

			$headers = array(
			        'Authorization: key='.GOOGLE_API_KEY,
			        'Content-Type: application/json'
			);      

			$ch = curl_init();
			curl_setopt($ch, CURLOPT_URL, $url);
			curl_setopt($ch, CURLOPT_POST, true);
			curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
			curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
		 	curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
		 	
			$result = curl_exec($ch);
			if($result === false)
			  die('Curl failed ' . curl_error());
			curl_close($ch);
			echo $result; ?>