package com.wposs.apirickandmorty.View.Search.Implementations;

import com.wposs.apirickandmorty.Base.ContextApp;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Extensions;
import com.wposs.apirickandmorty.View.Search.Interfaces.ISearchListener;
import com.wposs.apirickandmorty.View.Search.Interfaces.ISearchPresenter;
import com.wposs.apirickandmorty.View.Search.Interfaces.ISearchView;

public class SearchPresenter implements ISearchPresenter {
    private final ISearchView view;
    private final SearchBL bl;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        this.bl = new SearchBL(new listenerSearchBL());
    }

    @Override
    public void getAllCharacterNameSuccess(String name, int page) {
        if (!Extensions.isNetworkAvailable(ContextApp.getContext())){
            view.showSearchError(new MessageResponse(2, ContextApp.getContext().getString(R.string.error_internet)));
            return;
        }
        bl.getAllCharacterNameSuccess(name, page);
    }

    private class listenerSearchBL implements ISearchListener {

        @Override
        public void showGetAllCharacterNameSuccess(Characters characters) {
            view.showGetAllCharacterNameSuccess(characters);
        }

        @Override
        public void showSearchError(MessageResponse message) {
            view.showSearchError(message);
        }
    }
}
