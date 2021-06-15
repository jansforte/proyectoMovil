package com.example.atoda.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat.Builder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.example.atoda.Mensajes.Mensajeria;
import com.example.atoda.Preferences;
import com.example.atoda.R;
import com.example.atoda.activityAmigos.Amigos;
import com.example.atoda.menu_activy;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FireBaseServiceMensajes extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String tit = remoteMessage.getNotification().getTitle();
        String cuerpo = remoteMessage.getNotification().getBody();
        String mensaje[] = cuerpo.split("\\|");
        String titulo[] = tit.split("\\|");
        //String mensaje = remoteMessage.getData().get("mensaje");
        //String hora = remoteMessage.getData().get("hora");
        //String cabezera =  remoteMessage.getData().get("cabezera");
        //String em[] = cabezera.split(" ");
        //String cuerpo =  remoteMessage.getData().get("cuerpo");
        String receptor = remoteMessage.getData().get("receptor");
        String emisor = Preferences.ObtenerPreferenceString(this,Preferences.PREFERENCES_USUARIO_LOGIN);
        //if(receptor.equals("admin")){
            if(emisor.equals(titulo[1])){
                Mensaje(mensaje[0],mensaje[1],titulo[0]);//en este caso cabezera es el emisor que esta llegando diferente al emisor receptor
                //showNotification(cabezera,cuerpo);
            }
        /*}else{
            if(emisor.equals(receptor)){
                Mensaje(mensaje,hora,em[0]);//en este caso cabezera es el emisor que esta llegando diferente al emisor receptor
                showNotification(cabezera,cuerpo);
            }
        }*/

    }

    private void Mensaje(String mensaje,String hora,String emisor){
        Intent o = new Intent(Amigos.MENSAJE);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(o);
        Intent i = new Intent(Mensajeria.MENSAJE);
        i.putExtra("key_mensaje",mensaje);
        i.putExtra("key_hora",hora);
        i.putExtra("key_emisor_PHP",emisor);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);
    }


    private void showNotification(String cabezera, String cuerpo){
        Intent i = new Intent(this, menu_activy.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        //Uri soundNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Builder builder = new Builder(this);
        builder.setAutoCancel(true);
        builder.setContentTitle(cabezera);
        builder.setContentText(cuerpo);
        //builder.setSound(soundNotification);
        builder.setSmallIcon(R.drawable.ic_launcher_round);
        builder.setTicker(cuerpo);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1,builder.build());

    }
}
