package com.wposs.apirickandmorty.View.Search.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wposs.apirickandmorty.Base.ContextApp;
import com.wposs.apirickandmorty.Models.Character;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Extensions;
import com.wposs.apirickandmorty.View.Home.Interfaces.OnItemClickListenerCharacter;

import java.security.cert.Extension;
import java.util.List;

public class CharactersSearchAdapter extends RecyclerView.Adapter<CharactersSearchAdapter.ViewHolder> {

    private List<Character> characters;
    private final OnItemClickListenerCharacter listener;

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Character> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    public CharactersSearchAdapter(List<Character> characters, OnItemClickListenerCharacter listener) {
        this.characters = characters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ContextApp.getContext()).inflate(R.layout.item_characters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setDetailCategoria(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView name;
        private final TextView filter;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_character);
            name = itemView.findViewById(R.id.tv_nameCharacter);
            filter = itemView.findViewById(R.id.tv_filterCharacter);

        }
        @SuppressLint("ResourceAsColor")
        void setDetailCategoria(Character character){
            name.setText(character.getName());
            name.setTextColor(ContextApp.getContext().getColor(R.color.colorPrimaryDark));
            filter.setVisibility(View.INVISIBLE);
            Extensions.convertImageService(character.getImage(), imageView, 200);
            imageView.setOnClickListener(v -> {
                listener.onItemClick(character);
            });
        }

    }
}

