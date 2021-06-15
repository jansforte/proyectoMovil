package com.example.atoda.activityAmigos;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.atoda.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Amigos extends Fragment {

    private BroadcastReceiver bR;

    public static final String MENSAJE = "MENSAJE";

    private RecyclerView rv;
    private List<AmigosAtributos> atributosList;
    private AmigosAdapter adapter;
    private String nuestroUsuario="";
    private static final String URL_AMIGOS = Links.IP_AMIGOS;

    private int cont=0;

    private VolleyRP volley;
    private RequestQueue mRequest;
    private LinearLayout nohay;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_amigos,container,false);

        volley = VolleyRP.getInstance(getContext());
        mRequest = volley.getRequestQueue();

        atributosList = new ArrayList<>();
        nohay = (LinearLayout) v.findViewById(R.id.nohay);

        rv = (RecyclerView) v.findViewById(R.id.amigosRecicler);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);

        adapter = new AmigosAdapter(atributosList,getContext());
        rv.setAdapter(adapter);
        Toast.makeText(getContext(),"espere a que cargue porfavor",Toast.LENGTH_SHORT).show();
        if(cont==0)
            SolicitudJSON();

        bR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SolicitudJSON();
            }
        };


        verificarLista();
        return v;
    }

    public void verificarLista(){
        if(atributosList.isEmpty()){
            nohay.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }else{
            nohay.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }



    public void agregarAmigo(int fotoPerfil, String nombre, String ultimoMensaje,
                             String hora, String id, String tipoMensaje,
                             String direccion, String ciudad){
        AmigosAtributos amigosAtributos = new AmigosAtributos();
        amigosAtributos.setFotoPerfil(fotoPerfil);
        amigosAtributos.setNombre(nombre);
        amigosAtributos.setUltimoMensaje(ultimoMensaje);
        amigosAtributos.setHora(hora);
        amigosAtributos.setId(id);
        amigosAtributos.setTipoDato(tipoMensaje);
        amigosAtributos.setDireccion(direccion);
        amigosAtributos.setCiudad(ciudad);
        atributosList.add(amigosAtributos);
        adapter.notifyDataSetChanged();
        verificarLista();
    }

    public void SolicitudJSON(){
        nuestroUsuario = Preferences.ObtenerPreferenceString(getContext(),Preferences.PREFERENCES_USUARIO_LOGIN);
        JsonObjectRequest solicitud = new JsonObjectRequest(URL_AMIGOS+nuestroUsuario,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos){
                try {
                    atributosList.clear();
                    String TodosLosDatos = datos.getString("resultado");
                    JSONArray jsonArray = new JSONArray(TodosLosDatos);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject js=jsonArray.getJSONObject(i);
                        if(!nuestroUsuario.equals(js.getString("id"))){
                            if(!js.getString("mensaje").equals("null")){
                            String hora[]=js.getString("hora_del_mensaje").split("\\,");
                            agregarAmigo(R.drawable.ic_action_user,js.getString("nombre"),
                                    js.getString("mensaje"),hora[0],js.getString("id"),
                                    js.getString("tipo_mensaje"),js.getString("direccion"),
                                    js.getString("ciudad"));
                            }
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Ocurrio un error al descomponer json "+e.toString(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(bR);
    }

    @Override
    public void onResume() {
        super.onResume();
        cont=1;
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(bR,new IntentFilter(MENSAJE));
    }

}
