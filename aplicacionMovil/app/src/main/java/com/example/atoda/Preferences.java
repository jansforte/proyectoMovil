package com.example.atoda;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String PREFERENCES = "atoda.Mensajes.Mensajeria";
    public static final String PREFERENCES_ESTADO = "estado.check.sesion";
    public static final String PREFERENCES_USUARIO_LOGIN = "usuario.login";

    public static void GuardarBolean(Context c, boolean b, String key){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREFERENCES,c.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,b).apply();
    }

    public static void GuardarString(Context c, String s, String key){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREFERENCES,c.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,s).apply();
    }

    public static boolean ObtenerPreferenceBoolean(Context c, String key){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREFERENCES,c.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public static String ObtenerPreferenceString(Context c, String key){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREFERENCES,c.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
