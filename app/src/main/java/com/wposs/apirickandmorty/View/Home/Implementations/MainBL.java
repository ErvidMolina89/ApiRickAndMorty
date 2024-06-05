package com.wposs.apirickandmorty.View.Home.Implementations;

import android.content.Context;

import com.wposs.apirickandmorty.Base.ContextApp;
import com.wposs.apirickandmorty.DataAccess.Connections.Interfaces.IResponse;
import com.wposs.apirickandmorty.DataAccess.Connections.Retrofit.ImplementationRetrofit;
import com.wposs.apirickandmorty.DataAccess.Resources.ObjectConverter;
import com.wposs.apirickandmorty.Models.BaseModel;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Constants;
import com.wposs.apirickandmorty.View.Home.Interfaces.IMainBL;
import com.wposs.apirickandmorty.View.Home.Interfaces.IMainListener;

public class MainBL implements IMainBL {
    private final IMainListener listener;
    private final ImplementationRetrofit service;
    public MainBL(IMainListener listener) {
        this.listener = listener;
        service = new ImplementationRetrofit(new listenerRetrofitGetCharacters());

    }

    @Override
    public void getAllCharacterSuccess() {
        service.getCharactersApi(Constants.Service.CHARACTERS, new String());
    }

    @Override
    public void getAllCharacterForStatus(String status) {service.getCharactersApi(Constants.Service.STATUS, status);}

    @Override
    public void getAllCharacterForGender(String gender) {service.getCharactersApi(Constants.Service.GENDER, gender);}

    private class listenerRetrofitGetCharacters implements IResponse {

        @Override
        public void onSuccessResponse(BaseModel objectResponse, String service) {
            try {
                switch (service){
                    case Constants.Service.CHARACTERS: {
                        Characters characters = ObjectConverter.convertObjectToCharacters(objectResponse);
                        listener.showGetAllCharacterSuccess(characters);
                        break;
                    }
                    case Constants.Service.GENDER:
                    case Constants.Service.STATUS: {
                        Characters characters = ObjectConverter.convertObjectToCharacters(objectResponse);
                        listener.showGetAllCharacterFilter(characters);
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                listener.showMainError(new MessageResponse(0, e.getMessage()));
            }
        }

        @Override
        public void onFailedResponse(MessageResponse response) {
            listener.showMainError(response);
        }
    }
}
