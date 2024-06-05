package com.wposs.apirickandmorty.View.Home.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.wposs.apirickandmorty.Base.ContextApp;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.View.Home.Interfaces.OnItemClickListenerCategorias;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {

    private int selectedPosition = 0;
    private List<String> categorias;
    private final OnItemClickListenerCategorias listener;

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<String> categorias) {
        this.categorias = categorias;
        notifyDataSetChanged();
    }

    public CategoriasAdapter(List<String> categorias, OnItemClickListenerCategorias listener) {
        this.categorias = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ContextApp.getContext()).inflate(R.layout.item_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setDetailCategoria(categorias.get(position), position);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonCategorias);

        }
        void setDetailCategoria(String categoria, int position){
            button.setText(categoria);
            changeBackgroundButton(position);
            button.setOnClickListener(v -> {
                notifyItemChanged(selectedPosition);
                listener.onItemClick(categoria);
                selectedPosition = position;
                changeBackgroundButton(selectedPosition);
            });
        }

        private void changeBackgroundButton(int position){
            if (position == selectedPosition) {
                button.setBackground(ContextCompat.getDrawable(ContextApp.getContext(),R.drawable.border_button_selection));
            } else {
                button.setBackground(ContextCompat.getDrawable(ContextApp.getContext(),R.drawable.border_button));
            }
        }
    }
}

