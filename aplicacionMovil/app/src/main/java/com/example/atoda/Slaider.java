package com.example.atoda;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Slaider extends AppCompatActivity {

    ViewFlipper v_flipper;


    private EditText eTusuario;
    private EditText eTcontrasena;
    private Button bTingresar;

    private Button registro;
    private CheckBox noCerrar;
    private static final String IP = Links.IP_LOGIN;
    private static final String IP_TOKEN = Links.IP_TOKEN;

    private String USER = "";
    private String PASSWORD = "";
    private boolean isActivateCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slaider);

        int images[] = {R.drawable.logan,R.drawable.img1_opt,R.drawable.img2_opt,R.drawable.img3_opt,R.drawable.img4_opt,R.drawable.img5_opt};


        v_flipper = findViewById(R.id.v_flipper);

        //for loop
        for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }

        //
        for (int image: images){
            flipperImages(image);
        }

        //login

        if(Preferences.ObtenerPreferenceBoolean(this,Preferences.PREFERENCES_ESTADO)){
            SiguienteVentana();
        }

        eTusuario = findViewById(R.id.eTusuario);
        eTcontrasena = findViewById(R.id.eTcontrasena);
        bTingresar = findViewById(R.id.bTingresar);
        registro =findViewById(R.id.registro);
        noCerrar = findViewById(R.id.no_cerrar);

        isActivateCheckbox = noCerrar.isChecked();

        noCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActivateCheckbox) noCerrar.setChecked(false);
                isActivateCheckbox = noCerrar.isChecked();
            }
        });

        bTingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Login.this,"Ingresando...",Toast.LENGTH_SHORT).show();
                VerificarLogin(""+eTusuario.getText().toString(),eTcontrasena.getText().toString().toLowerCase());
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Slaider.this,Registro.class);
                volver(i);
                startActivity(i);
            }
        });
    }


    //slider
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);


        //animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }

    //login
    public void VerificarLogin(String user, String password){
        USER = user;
        PASSWORD = password;
        Links l = new Links() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                VerificarPassword(j);
            }

            @Override
            public void solicitudErronea() {
                Toast.makeText(Slaider.this,"Ocurrio un error, por favor contactese con el administrador",Toast.LENGTH_SHORT).show();
            }
        };
        l.solicitudJsonGET(this,IP+USER);
    }

    public void VerificarPassword(JSONObject datos){
        try{
            String estado = datos.getString("resultado");
            if(estado.equals("CC")){
                JSONObject Jsondatos = new JSONObject(datos.getString("datos"));
                String usuario = Jsondatos.getString("Id").toLowerCase();
                String clave = Jsondatos.getString("Password").toLowerCase();
                if(USER.equals(usuario) && clave.equals(PASSWORD)){
                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                return;
                            }
                            // Get new FCM registration token
                            String token = task.getResult();
                            if(token !=null)SubirToken(token);
                            else Toast.makeText(getApplicationContext(),"El token es nulo",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else Toast.makeText(this,"Contrasena incorrecta",Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this,"El usuario no existe",Toast.LENGTH_SHORT).show();
        }catch(JSONException e){}
    }

    private void SubirToken(String token){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("id",USER);
        hashMapToken.put("token",token);

        Links l = new Links() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                Preferences.GuardarBolean(Slaider.this,noCerrar.isChecked(),Preferences.PREFERENCES_ESTADO);
                Preferences.GuardarString(Slaider.this,USER,Preferences.PREFERENCES_USUARIO_LOGIN);
                //Toast.makeText(Slaider.this,"El token se pudo subir",Toast.LENGTH_SHORT).show();
                SiguienteVentana();
            }

            @Override
            public void solicitudErronea() {
                Toast.makeText(Slaider.this,"Problema con el servidor",Toast.LENGTH_SHORT).show();
            }
        };

        l.solicitudJsonPOST(this,IP_TOKEN,hashMapToken);

    }

    private void SiguienteVentana(){
        Intent i = new Intent(this, menu_activy.class);
        volver(i);
        startActivity(i);
        finish();
    }
    private void volver(Intent i){
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
