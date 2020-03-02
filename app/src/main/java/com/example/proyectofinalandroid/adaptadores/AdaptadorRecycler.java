package com.example.proyectofinalandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.utils.Persona;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.HolderAdapter> {

    Context context;
    ArrayList<Persona> listaPersonas;

    public AdaptadorRecycler(Context context, ArrayList<Persona> listaPersonas) {
        this.context = context;
        this.listaPersonas = listaPersonas;
    }

    @NonNull
    @Override
    public HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        HolderAdapter miHolder = new HolderAdapter(view);

        return miHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapter holder, int position) {
        Persona persona = listaPersonas.get(position);
        holder.getText_nombre().setText(persona.getNombre());
        holder.getText_apellidos().setText(persona.getApellidos());
        holder.getText_edad().setText(persona.getEdad());
        String sexo = persona.getSexo();

        if (sexo.equals("hombre")) {
            holder.getImagen_recycler().setImageResource(R.drawable.hombre);
        } else if (sexo.equals("mujer")) {
            holder.getImagen_recycler().setImageResource(R.drawable.mujer);
        }
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class HolderAdapter extends RecyclerView.ViewHolder {

        ImageView imagen_recycler;
        TextView text_nombre, text_apellidos, text_edad;

        public HolderAdapter(View itemView) {
            super(itemView);
            imagen_recycler = itemView.findViewById(R.id.item_imagen);
            text_nombre = itemView.findViewById(R.id.item_nombre);
            text_apellidos = itemView.findViewById(R.id.item_apellidos);
            text_edad = itemView.findViewById(R.id.item_edad);
        }

        public ImageView getImagen_recycler() {
            return imagen_recycler;
        }

        public void setImagen_recycler(ImageView imagen_recycler) {
            this.imagen_recycler = imagen_recycler;
        }

        public TextView getText_nombre() {
            return text_nombre;
        }

        public void setText_nombre(TextView text_nombre) {
            this.text_nombre = text_nombre;
        }

        public TextView getText_apellidos() {
            return text_apellidos;
        }

        public void setText_apellidos(TextView text_apellidos) {
            this.text_apellidos = text_apellidos;
        }

        public TextView getText_edad() {
            return text_edad;
        }

        public void setText_edad(TextView text_edad) {
            this.text_edad = text_edad;
        }
    }
}
