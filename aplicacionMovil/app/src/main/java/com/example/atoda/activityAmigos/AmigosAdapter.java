package com.example.atoda.activityAmigos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.atoda.Mensajes.Mensajeria;
import com.example.atoda.R;

import java.util.List;

public class AmigosAdapter extends RecyclerView.Adapter<AmigosAdapter.HolderAmigos> {

    private List<AmigosAtributos> atributosList;
    private Context context;

    public AmigosAdapter(List<AmigosAtributos> atributosList, Context context){
        this.atributosList = atributosList;
        this.context = context;
    }

    @Override
    public HolderAmigos onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_amigos,parent,false);
        return new AmigosAdapter.HolderAmigos(v);
    }

    @Override
    public void onBindViewHolder(HolderAmigos holder, final int position) {
        holder.imageView.setImageResource(R.drawable.ic_contact);
        holder.nombre.setText(atributosList.get(position).getNombre()+" - "+atributosList.get(position).getId());
        holder.mensaje.setText(atributosList.get(position).getUltimoMensaje());
        holder.hora.setText(atributosList.get(position).getHora());

        if(atributosList.get(position).getTipoDato().equals("1")){
            holder.mensaje.setTextColor(ContextCompat.getColor(context,R.color.colorNegro));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(context, Mensajeria.class);
                i.putExtra("key_direccion",atributosList.get(position).getDireccion());
                i.putExtra("key_ciudad",atributosList.get(position).getCiudad());
                i.putExtra("key_nombre",atributosList.get(position).getNombre());
                i.putExtra("key_receptor",atributosList.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return atributosList.size();
    }

    static class HolderAmigos extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView nombre;
        TextView mensaje;
        TextView hora;

        public HolderAmigos(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvAmigos);
            imageView = (ImageView) itemView.findViewById(R.id.fotoP);
            nombre = (TextView) itemView.findViewById(R.id.nombreAmigo);
            mensaje = (TextView) itemView.findViewById(R.id.mensajeAmigo);
            hora = (TextView) itemView.findViewById(R.id.horaAmigo);
        }
    }
}
