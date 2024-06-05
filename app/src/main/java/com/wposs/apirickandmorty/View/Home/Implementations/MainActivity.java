package com.wposs.apirickandmorty.View.Home.Implementations;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.*;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.wposs.apirickandmorty.Base.App;
import com.wposs.apirickandmorty.Models.Character;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Constants;
import com.wposs.apirickandmorty.Utils.Extensions;
import com.wposs.apirickandmorty.View.DetailCharacter.DetailActivity;
import com.wposs.apirickandmorty.View.Home.Adapters.CategoriasAdapter;
import com.wposs.apirickandmorty.View.Home.Adapters.CharactersAdapter;
import com.wposs.apirickandmorty.View.Home.Interfaces.IMainView;
import com.wposs.apirickandmorty.View.Home.Interfaces.OnItemClickListenerCategorias;
import com.wposs.apirickandmorty.View.Home.Interfaces.OnItemClickListenerCharacter;
import com.wposs.apirickandmorty.View.Search.Implementations.SearchActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends App {

    private Button searchView;
    private MainPresenter presenter;
    private ImageSlider carousel;
    private RecyclerView rvCategorias;
    private final List<String> categorias = new ArrayList<>();
    private CharactersAdapter charactersAdapter;
    private String categoria = "Alive";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(new listenerMainView());

        carousel = findViewById(R.id.carousel);
        searchView = findViewById(R.id.btn_searchView);
        rvCategorias = findViewById(R.id.rvCategorias);
        RecyclerView rvPersonajes = findViewById(R.id.rvPersonajes);

        charactersAdapter = new CharactersAdapter(new ArrayList<>(), new listenerAdapterCharacter(), categoria);
        rvPersonajes.setHasFixedSize(true);
        rvPersonajes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3, RecyclerView.VERTICAL, false));
        rvPersonajes.setAdapter(charactersAdapter);
        callServiceCharacter(categoria);

        categoriasView();
        presenter.getAllCharacterSuccess();
        searchView.setOnClickListener(v -> {
            changeActivity(SearchActivity.class);
        });
    }

    private void carouselView(List<Character> characters){
        ArrayList<SlideModel> itemsCarousel = new ArrayList<>();

        for (Character character : characters){
            SlideModel slideModel = new SlideModel(character.getImage(), ScaleTypes.CENTER_INSIDE);
            itemsCarousel.add(slideModel);
        }

        carousel.setImageList(itemsCarousel);
    }
    private void characterlUpdate(List<Character> characters){
        charactersAdapter.updateList(characters, categoria);
    }

    private void categoriasView(){
        Collections.addAll(categorias, Constants.Categorias.ALIVE,
                Constants.Categorias.DEAD, Constants.Categorias.FEMALE,
                Constants.Categorias.MALE,Constants.Categorias.GENDERLESS);
        rvCategorias.setAdapter(null);
        rvCategorias.setHasFixedSize(true);
        rvCategorias.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(categorias, new listenerAdapterCategorias());
        rvCategorias.setAdapter(categoriasAdapter);
    }

    private class listenerMainView implements IMainView {
        @Override
        public void showGetAllCharacterSuccess(Characters characters) {
            carouselView(characters.getResults());
        }
        @Override
        public void showGetAllCharacterFilter(Characters characters) {
            characterlUpdate(characters.getResults());
        }

        @Override
        public void showMainError(MessageResponse message) {
            Extensions.showErrorDialog(message.getMessage(), getApplicationContext());
        }
    }

    private class listenerAdapterCategorias implements OnItemClickListenerCategorias {
        @Override
        public void onItemClick(String categoria) {
            callServiceCharacter(categoria);
        }
    }

    private void callServiceCharacter(String categoria){
        switch (categoria){
            case Constants.Categorias.ALIVE:
            case Constants.Categorias.DEAD: {
                presenter.getAllCharacterForStatus(categoria);
                break;
            }
            case Constants.Categorias.FEMALE:
            case Constants.Categorias.GENDERLESS:
            case Constants.Categorias.MALE: {
                presenter.getAllCharacterForGender(categoria);
                break;
            }
        }
        this.categoria = categoria;
    }

    private class listenerAdapterCharacter implements OnItemClickListenerCharacter {
        @Override
        public void onItemClick(Character character) {
            changeActivityPutExtra(DetailActivity.class, character, Constants.Key.CHARACTER);
        }
    }
}