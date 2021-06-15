package com.example.atoda.Mensajes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.atoda.Links;
import com.example.atoda.Preferences;
import com.example.atoda.R;
import com.example.atoda.VolleyRP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Clientes extends Fragment {

    public static final String MENSAJE = "MENSAJE";

    private BroadcastReceiver bR;

    private RecyclerView rv;
    private LinearLayout linearLayout;
    private ImageView bTEnviarMensaje;
    private EditText eTEscribirMensaje;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajeriaAdapter adapter;

    private String MENSAJE_ENVIAR = "";
    private String EMISOR = "";
    private String RECEPTOR = "admin";

    private static final String IP_MENSAJE = Links.IP_MENSAJE;

    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.mensaje_cliente,container,false);

        mensajeDeTextos = new ArrayList<>();

        EMISOR = Preferences.ObtenerPreferenceString(getContext(),Preferences.PREFERENCES_USUARIO_LOGIN);

        volley = VolleyRP.getInstance(getContext());
        mRequest = volley.getRequestQueue();

        linearLayout = (LinearLayout) v.findViewById(R.id.m_pedido);

        bTEnviarMensaje = (ImageView) v.findViewById(R.id.bTenviarMensaje);
        eTEscribirMensaje = (EditText) v.findViewById(R.id.eTEsribirMensaje);

        rv = (RecyclerView) v.findViewById(R.id.rvMensajes);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);

        adapter = new MensajeriaAdapter(mensajeDeTextos,getContext());
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

        setScrollbarChat();

        bR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String mensaje = intent.getStringExtra("key_mensaje");
                String hora = intent.getStringExtra("key_hora");
               // String horaParametros[]=hora.split("\\,");
                recibirMensaje();
                String emisor = intent.getStringExtra("key_emisor_PHP");
                //Toast.makeText(getContext(),mensaje+horaParametros[0]+emisor+RECEPTOR,Toast.LENGTH_SHORT).show();
                /*if(emisor.equals(RECEPTOR)){
                    CreateMensaje(mensaje,2,horaParametros[0]);}*/
            }
        };
        recibirMensaje();
        validarPedido();
        return v;

    }

    private void validarPedido(){
        if(mensajeDeTextos.isEmpty()){
            linearLayout.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }else{
            linearLayout.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }

    private void recibirMensaje(){
        HashMap<String,String> h = new HashMap<>();
        h.put("emisor",EMISOR);
        h.put("receptor",RECEPTOR);
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,Links.IP_SMSALL,new JSONObject(h), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject j){
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
                   // Toast.makeText(getContext(),"Ocurrio un error al descomprimir los mensajes "+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Ocurrio un error, por favor contactese con el administrador",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,getContext(),volley);
    }

    private void MandarMensaje(){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("emisor",EMISOR);
        hashMapToken.put("receptor",RECEPTOR);
        hashMapToken.put("mensaje",MENSAJE_ENVIAR);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,Links.IP_MENSAJE,new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject j){
                try {
                    Toast.makeText(getContext(),j.getString("resultado"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e){
                    Toast.makeText(getContext(),"linea 153 "+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Ocurrio un error",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,getContext(),volley);

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
        validarPedido();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(bR);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(bR,new IntentFilter(MENSAJE));
    }

    public void setScrollbarChat(){rv.scrollToPosition(adapter.getItemCount()-1);}

}
