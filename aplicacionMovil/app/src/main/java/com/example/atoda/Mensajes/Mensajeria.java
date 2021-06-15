package com.example.atoda.Mensajes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.atoda.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Mensajeria extends AppCompatActivity {

    public static final String MENSAJE = "MENSAJE";

    private BroadcastReceiver bR;

    private RecyclerView rv;
    private ImageView bTEnviarMensaje;
    private EditText eTEscribirMensaje;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajeriaAdapter adapter;

    private String MENSAJE_ENVIAR = "";
    private String EMISOR = "";

    private String ciudadC="";
    private String direccionC="";
    private String nombreC="";
    private String RECEPTOR = "";

    private static final String IP_MENSAJE = Links.IP_MENSAJE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        mensajeDeTextos = new ArrayList<>();

        EMISOR = Preferences.ObtenerPreferenceString(this,Preferences.PREFERENCES_USUARIO_LOGIN);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if(bundle!=null){
            RECEPTOR = bundle.getString("key_receptor");
            direccionC = bundle.getString("key_direccion");;
            ciudadC = bundle.getString("key_ciudad");
            nombreC = bundle.getString("key_nombre");
        }
        setTitle("Cliente "+nombreC);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bTEnviarMensaje = findViewById(R.id.bTenviarMensaje);
        eTEscribirMensaje = findViewById(R.id.eTEsribirMensaje);

        rv = findViewById(R.id.rvMensajes);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);

        adapter = new MensajeriaAdapter(mensajeDeTextos,this);
        rv.setAdapter(adapter);

        bTEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje=eTEscribirMensaje.getText().toString().trim();
                if(!mensaje.isEmpty()){
                    Calendar c = Calendar.getInstance();
                    String hora = c.get(Calendar.HOUR_OF_DAY)
                            + ":" + c.get(Calendar.MINUTE);
                    MENSAJE_ENVIAR = mensaje;
                    MandarMensaje();
                    CreateMensaje(mensaje,1,hora);
                    eTEscribirMensaje.setText("");
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mensajeria.this, menu_activy.class);
                startActivity(i);
                finish();
            }
        });

        setScrollbarChat();

        bR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                recibirMensaje();
                /*String mensaje = intent.getStringExtra("key_mensaje");
                String hora = intent.getStringExtra("key_hora");
                String horaParametros[]=hora.split("\\,");
                String emisor = intent.getStringExtra("key_emisor_PHP");
                Toast.makeText(Mensajeria.this,mensaje+horaParametros[0]+emisor+RECEPTOR,Toast.LENGTH_SHORT).show();
                if(emisor.equals(RECEPTOR)){
                CreateMensaje(mensaje,2,horaParametros[0]);}*/
            }
        };

        recibirMensaje();

    }

    public void recibirMensaje(){
        Links l = new Links() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                try {
                    JSONArray js = new JSONArray(j.getString("resultado"));
                    for(int i=0;i<js.length();i++){
                        JSONObject jo = js.getJSONObject(i);
                        String mensaje=jo.getString("mensaje");
                        String hora=jo.getString("hora_del_mensaje").split("\\,")[0];
                        int tipoMensaje=jo.getInt("tipo_mensaje");
                        CreateMensaje(mensaje,tipoMensaje,hora);
                    }
                } catch (JSONException e) {
                    Toast.makeText(Mensajeria.this,"Ocurrio un error al descomprimir los mensajes "+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void solicitudErronea() {

            }
        };
        HashMap<String,String> h = new HashMap<>();
        h.put("emisor",EMISOR);
        h.put("receptor",RECEPTOR);
        l.solicitudJsonPOST(this,Links.IP_SMSALL,h);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activityamigos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.Inf_contact){
            Intent i =  new Intent(this, infContactoCliente.class);
            i.putExtra("key_direccion",direccionC);
            i.putExtra("key_ciudad",ciudadC);
            i.putExtra("key_nombre",nombreC);
            i.putExtra("key_receptor",RECEPTOR);
            volver(i);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void MandarMensaje(){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("emisor",EMISOR);
        hashMapToken.put("receptor",RECEPTOR);
        hashMapToken.put("mensaje",MENSAJE_ENVIAR);
        
        Links l = new Links() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                try {
                    Toast.makeText(Mensajeria.this,j.getString("resultado"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e){
                    Toast.makeText(Mensajeria.this,"linea 153 "+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void solicitudErronea() {
                Toast.makeText(Mensajeria.this,"Ocurrio un error",Toast.LENGTH_SHORT).show();
            }
        };
        l.solicitudJsonPOST(this,IP_MENSAJE,hashMapToken);
    }

    public void CreateMensaje(String mensaje, int tipoDeMensaje,String hora){
        MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
        mensajeDeTextoAuxiliar.setId("0");
        mensajeDeTextoAuxiliar.setMensaje(mensaje);
        mensajeDeTextoAuxiliar.setTipoMensaje(tipoDeMensaje);
        mensajeDeTextoAuxiliar.setHoraDelMensaje(hora);
        mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        adapter.notifyDataSetChanged();
        setScrollbarChat();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bR,new IntentFilter(MENSAJE));
    }

    public void setScrollbarChat(){rv.scrollToPosition(adapter.getItemCount()-1);}

    private void volver(Intent i){
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_CLEAR_TOP);
    }

}
