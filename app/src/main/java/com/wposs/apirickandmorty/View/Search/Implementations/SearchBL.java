package com.wposs.apirickandmorty.View.Search.Implementations;

import com.wposs.apirickandmorty.DataAccess.Connections.Interfaces.IResponse;
import com.wposs.apirickandmorty.DataAccess.Connections.Retrofit.ImplementationRetrofit;
import com.wposs.apirickandmorty.DataAccess.Resources.ObjectConverter;
import com.wposs.apirickandmorty.Models.BaseModel;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.Utils.Constants;
import com.wposs.apirickandmorty.View.Search.Interfaces.ISearchBL;
import com.wposs.apirickandmorty.View.Search.Interfaces.ISearchListener;

public class SearchBL implements ISearchBL {
    private final ISearchListener listener;
    private final ImplementationRetrofit service;
    public SearchBL(ISearchListener listener) {
        this.listener = listener;
        service = new ImplementationRetrofit(new listenerRetrofitGetCharacters());

    }

    @Override
    public void getAllCharacterNameSuccess(String name, int page) {
        service.getCharactersForNameApi(name, page);
    }

    private class listenerRetrofitGetCharacters implements IResponse {

        @Override
        public void onSuccessResponse(BaseModel objectResponse, String service) {
            try {
                Characters characters = ObjectConverter.convertObjectToCharacters(objectResponse);
                listener.showGetAllCharacterNameSuccess(characters);
            } catch (IllegalArgumentException e) {
                listener.showSearchError(new MessageResponse(0, e.getMessage()));
            }
        }

        @Override
        public void onFailedResponse(MessageResponse response) {
            listener.showSearchError(response);
        }
    }
}
