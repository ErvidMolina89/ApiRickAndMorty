package com.wposs.apirickandmorty.View.Search.Implementations;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wposs.apirickandmorty.Base.App;
import com.wposs.apirickandmorty.Models.Character;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Constants;
import com.wposs.apirickandmorty.Utils.Extensions;
import com.wposs.apirickandmorty.View.DetailCharacter.DetailActivity;
import com.wposs.apirickandmorty.View.Home.Interfaces.OnItemClickListenerCharacter;
import com.wposs.apirickandmorty.View.Search.Adapter.CharactersSearchAdapter;
import com.wposs.apirickandmorty.View.Search.Interfaces.ISearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends App {

    private SearchView searchView;
    private SearchPresenter presenter;
    private RecyclerView rvCharacter;
    private CharactersSearchAdapter charactersAdapter;
    private int currentPage = 1;
    private String currentName = "";
    private List<Character> listCharacter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        rvCharacter = findViewById(R.id.rvSearchPersonajes);
        presenter = new SearchPresenter(new listenerSearchView());

        charactersAdapter = new CharactersSearchAdapter(listCharacter, new listenerAdapterCharacter());
        rvCharacter.setHasFixedSize(true);
        rvCharacter.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, RecyclerView.VERTICAL, false));
        rvCharacter.setAdapter(charactersAdapter);

        searchName();
        presenter.getAllCharacterNameSuccess(currentName, currentPage);
        onListenerRecycler();
    }

    private void onListenerRecycler(){
        rvCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == charactersAdapter.getItemCount() - 1) {
                    currentPage++;
                    loadMoreCharacters();
                }
            }
        });
    }

    private void searchName(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getAllCharacterNameSuccess(query, currentPage);
                currentName = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void loadMoreCharacters() {
        presenter.getAllCharacterNameSuccess(currentName, currentPage);
    }

    private class listenerSearchView implements ISearchView{
        @Override
        public void showGetAllCharacterNameSuccess(Characters characters) {
            if(currentPage == 1) {
                charactersAdapter.updateList(characters.getResults());
                listCharacter = characters.getResults();
            }else {
                listCharacter.addAll(characters.getResults());
                charactersAdapter.updateList(listCharacter);
            }
        }

        @Override
        public void showSearchError(MessageResponse message) {
            Extensions.showErrorDialog(message.getMessage(), getApplicationContext());
        }
    }
    private class listenerAdapterCharacter implements OnItemClickListenerCharacter {
        @Override
        public void onItemClick(Character character) {
            changeActivityPutExtra(DetailActivity.class, character, Constants.Key.CHARACTER);
        }
    }
}