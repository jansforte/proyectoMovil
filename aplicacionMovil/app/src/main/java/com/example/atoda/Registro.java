package com.example.atoda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    private EditText nombre;
    private EditText direccion;
    private EditText ciudad;
    private EditText telefono;
    private EditText clave;
    private EditText cclave;
    private Button registrar;

    private String TEL="";

    private static final String IP = Links.IP_LOGIN;
    private static final String IP_REGISTRO = Links.IP_REGISTRO;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrardatos);

        nombre = findViewById(R.id.Nombre);
        direccion = findViewById(R.id.direccion);
        ciudad = findViewById(R.id.ciudad);
        telefono = findViewById(R.id.telefono);
        clave = findViewById(R.id.clave);
        cclave = findViewById(R.id.cclave);
        registrar = findViewById(R.id.registro);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=campo(nombre);
                String dir=campo(direccion);
                String ciu=campo(ciudad);
                String tel=campo(telefono);
                String cla=campo(clave);
                String ccla=campo(cclave);
                if(!nom.isEmpty() && !dir.isEmpty() && !ciu.isEmpty() &&
                    !tel.isEmpty() && !cla.isEmpty() && !ccla.isEmpty()){
                    if(cla.equals(ccla))
                    VerificarRegistro(""+telefono.getText().toString());
                    else
                    Toast.makeText(Registro.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(Registro.this,"Por favor ingrese todos los datos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void VerificarRegistro(String telefono){
        TEL = telefono;
        Links l = new Links() {
            @Override
            public void solicitudCompletada(JSONObject datos) {
                VerificarReg(datos);
            }

            @Override
            public void solicitudErronea() {
                Toast.makeText(Registro.this,"Ocurrio un error, por favor intecte mas tarde",Toast.LENGTH_SHORT).show();
            }
        };
        l.solicitudJsonGET(this,IP+TEL);
    }

    public void VerificarReg(JSONObject datos){
        try{
            String estado = datos.getString("resultado");
            if(estado.equals("CC")){
                Toast.makeText(this,"El usuario ya se encuentra registrado",Toast.LENGTH_SHORT).show();                
            }else{
                SubirRegistro();
            }
        }catch(JSONException e){}
    }

    private void SubirRegistro(){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("id",TEL);
        hashMapToken.put("password",campo(clave));
        hashMapToken.put("nombre",campo(nombre));
        hashMapToken.put("direccion",campo(direccion));
        hashMapToken.put("ciudad",campo(ciudad));

        Links l = new Links() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                Toast.makeText(Registro.this,"El registro se realizo correctamente",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Registro.this, Slaider.class);
                volver(i);
                startActivity(i);
            }

            @Override
            public void solicitudErronea() {
                Toast.makeText(Registro.this,"No se pudo realizar el registro",Toast.LENGTH_SHORT).show();
            }
        };

        l.solicitudJsonPOST(this,IP_REGISTRO,hashMapToken);
    }

    private void volver(Intent i){
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_CLEAR_TOP);
    }

    private String campo(EditText cadena){
        return cadena.getText().toString().toLowerCase().trim();
    }

}
