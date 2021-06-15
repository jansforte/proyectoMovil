package com.example.atoda;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.util.HashMap;

public abstract class Links {

    private static final String ip = "192.168.0.16";
    public static final String IP_LOGIN= "http://"+ip+"/ScriptsPHP/Login_GETID.php?id=";
    public static final String IP_TOKEN = "http://"+ip+"/ScriptsPHP/Token_INSERT.php";
    public static final String IP_REGISTRO = "http://"+ip+"/ScriptsPHP/Registro_INSERT.php";
    public static final String IP_MENSAJE = "http://"+ip+"/ScriptsPHP/Enviar_Mensajes.php";
    public static final String IP_AMIGOS = "http://"+ip+"/ScriptsPHP/Datos_GETALL.php?id=";
    public static final String IP_SMSALL = "http://"+ip+"/ScriptsPHP/Mensajes_GETID.php";
    public static final String IP_PUNTOS = "http://"+ip+"/ScriptsPHP/puntos.php";
    public static final String IP_TODO = "http://"+ip+"/ScriptsPHP/Registro_GETALL.php?id=";
    public static final String IP_ACTUALIZAR = "http://"+ip+"/ScriptsPHP/Login_UPDATE.php";

    public Links() {
    }

    public abstract void solicitudCompletada(JSONObject j);
    public abstract void solicitudErronea();

    public void solicitudJsonGET(Context c,String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos){
                solicitudCompletada(datos);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                solicitudErronea();
            }
        });
        VolleyRP.addToQueue(solicitud,VolleyRP.getInstance(c).getRequestQueue(),c,VolleyRP.getInstance(c));
    }

    public void solicitudJsonPOST(Context c,String URL, HashMap<String,String> hashMap){
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST,URL,new JSONObject(hashMap),
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject datos){
                        solicitudCompletada(datos);
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                solicitudErronea();
            }
        });
        VolleyRP.addToQueue(solicitud,VolleyRP.getInstance(c).getRequestQueue(),c,VolleyRP.getInstance(c));
    }
}
