package com.example.atoda.Mensajes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.atoda.Links;
import com.example.atoda.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class infContactoCliente extends AppCompatActivity {

    private TextView nombre;
    private TextView direccion;
    private TextView ciudad;
    private TextView telefono;
    private EditText puntos;
    private Button registro;

    private String ciudadC="";
    private String direccionC="";
    private String nombreC="";
    private String RECEPTOR = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infocliente);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if(bundle!=null){
            RECEPTOR = bundle.getString("key_receptor");
            direccionC = bundle.getString("key_direccion");;
            ciudadC = bundle.getString("key_ciudad");
            nombreC = bundle.getString("key_nombre");
        }

        nombre = findViewById(R.id.Nombre);
        direccion = findViewById(R.id.direccion);
        ciudad = findViewById(R.id.ciudad);
        telefono = findViewById(R.id.telefono);
        puntos = findViewById(R.id.venta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        registro = findViewById(R.id.registroP);

        nombre.setText("Nombre: "+nombreC);
        direccion.setText("Direccion: "+ direccionC);
        ciudad.setText("Ciudad: "+ciudadC);
        telefono.setText("Tel: "+RECEPTOR);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(infContactoCliente.this, Mensajeria.class);
                i.putExtra("key_direccion",direccionC);
                i.putExtra("key_ciudad",ciudadC);
                i.putExtra("key_nombre",nombreC);
                i.putExtra("key_receptor",RECEPTOR);
                volver(i);
                startActivity(i);
                finish();
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int punt = Integer.parseInt(puntos.getText().toString());
                int total = punt/10000;
                puntos.setText("");

                Links l = new Links() {
                    @Override
                    public void solicitudCompletada(JSONObject j) {
                        try {
                            Toast.makeText(infContactoCliente.this,j.getString("resultado"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(infContactoCliente.this,":Error al registrar los puntos",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void solicitudErronea() {
                        Toast.makeText(infContactoCliente.this,"-Error al registrar los puntos",Toast.LENGTH_SHORT).show();
                    }
                };
                HashMap<String,String> datos = new HashMap<>();
                datos.put("id",RECEPTOR);
                datos.put("puntos",""+total);
                l.solicitudJsonPOST(infContactoCliente.this,Links.IP_PUNTOS,datos);

            }
        });

    }
    private void volver(Intent i){
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
