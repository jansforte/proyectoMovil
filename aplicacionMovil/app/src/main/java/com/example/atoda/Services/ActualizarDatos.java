package com.example.atoda.Services;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.atoda.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ActualizarDatos extends Fragment {

    private VolleyRP volley;
    private RequestQueue mRequest;

    private EditText nombre;
    private EditText direccion;
    private EditText ciudad;
    private EditText telefono;
    private EditText clave;
    private EditText cclave;
    private Button registrar;

    private String TEL="";

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.actualizardatos,container,false);

        volley = VolleyRP.getInstance(getContext());
        mRequest = volley.getRequestQueue();

        nombre = (EditText) v.findViewById(R.id.Nombre);
        direccion = (EditText) v.findViewById(R.id.direccion);
        ciudad = (EditText) v.findViewById(R.id.ciudad);
        telefono = (EditText) v.findViewById(R.id.telefono);
        clave = (EditText) v.findViewById(R.id.clave);
        cclave = (EditText) v.findViewById(R.id.cclave);
        registrar = (Button) v.findViewById(R.id.registro);

        VerificarRegistro(Preferences.ObtenerPreferenceString(getContext(),Preferences.PREFERENCES_USUARIO_LOGIN));

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
                        SubirRegistro();
                    else
                        Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(),"Por favor ingrese todos los datos",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void VerificarRegistro(final String tel){
        TEL = ""+tel;
        JsonObjectRequest solicitud = new JsonObjectRequest(Links.IP_TODO+TEL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos){
                try {
                    String TodosLosDatos = datos.getString("resultado");
                    JSONArray jsonArray = new JSONArray(TodosLosDatos);
                    JSONObject js=jsonArray.getJSONObject(0);
                    nombre.setText(js.getString("nombre"));
                    direccion.setText(js.getString("direccion"));
                    ciudad.setText(js.getString("ciudad"));
                    telefono.setText(TEL);
                    clave.setText(js.getString("Password"));
                    cclave.setText(js.getString("Password"));

                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Ocurrio: "+e.toString(),Toast.LENGTH_SHORT).show();
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

    private void SubirRegistro(){
        HashMap<String,String> hash = new HashMap<>();
        hash.put("password",campo(clave));
        hash.put("direccion",campo(direccion));
        hash.put("id",TEL);
        hash.put("nombre",campo(nombre));
        hash.put("ciudad",campo(ciudad));

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,Links.IP_ACTUALIZAR,new JSONObject(hash),
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject datos){
                        try {
                            Toast.makeText(getContext(),datos.getString("resultado"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(),"Ocurrio: "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se pudo actualizar los datos",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,getContext(),volley);
    }

    private String campo(EditText cadena){
        return cadena.getText().toString().toLowerCase().trim();
    }

}
