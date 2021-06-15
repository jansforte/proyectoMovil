package com.example.atoda;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import com.example.atoda.Mensajes.Clientes;
import com.example.atoda.Services.ActualizarDatos;
import com.example.atoda.activityAmigos.Amigos;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class menu_activy extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment miFragment= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activy);

        if(Preferences.ObtenerPreferenceString(this,Preferences.PREFERENCES_USUARIO_LOGIN).equals("")){
            SiguienteVentana();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        String EMISOR = Preferences.ObtenerPreferenceString(this,Preferences.PREFERENCES_USUARIO_LOGIN);
        if(EMISOR.equals("admin"))
            miFragment = new Amigos();
        else miFragment = new Clientes();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmento,miFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean fragmentSeleccionado=false;
        switch (id){
            case R.id.Salir:
                Preferences.GuardarBolean(this,false,Preferences.PREFERENCES_ESTADO);
                Preferences.GuardarString(this,"",Preferences.PREFERENCES_USUARIO_LOGIN);
                SiguienteVentana();
                break;
            case R.id.ajusteCuenta:
                miFragment = new ActualizarDatos();
                fragmentSeleccionado = true;
                break;
            case R.id.chatUsuario:
                String EMISOR = Preferences.ObtenerPreferenceString(this,Preferences.PREFERENCES_USUARIO_LOGIN);
                if(EMISOR.equals("admin"))
                    miFragment = new Amigos();
                else miFragment = new Clientes();
                fragmentSeleccionado = true;
                break;
            case R.id.infContact:
                miFragment = new InfoEmpresa();
                fragmentSeleccionado = true;
                break;
        }

        if(fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmento,miFragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void SiguienteVentana(){
        Intent i = new Intent(this, Slaider.class);
        volver(i);
        startActivity(i);
        finish();
    }

    private void volver(Intent i){
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
