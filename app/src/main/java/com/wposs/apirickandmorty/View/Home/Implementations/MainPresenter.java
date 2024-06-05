package com.wposs.apirickandmorty.View.Home.Implementations;

import android.content.Context;

import com.wposs.apirickandmorty.Base.ContextApp;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Extensions;
import com.wposs.apirickandmorty.View.Home.Interfaces.IMainListener;
import com.wposs.apirickandmorty.View.Home.Interfaces.IMainPresenter;
import com.wposs.apirickandmorty.View.Home.Interfaces.IMainView;

public class MainPresenter implements IMainPresenter {
    private final IMainView view;
    private final MainBL bl;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.bl = new MainBL(new listenerMainBL());
    }

    @Override
    public void getAllCharacterSuccess() {
        if (!Extensions.isNetworkAvailable(ContextApp.getContext())){
            view.showMainError(new MessageResponse(2, ContextApp.getContext().getString(R.string.error_internet)));
            return;
        }
        bl.getAllCharacterSuccess();
    }

    @Override
    public void getAllCharacterForStatus(String status) {
        bl.getAllCharacterForStatus(status);
    }

    @Override
    public void getAllCharacterForGender(String gender) {
        bl.getAllCharacterForGender(gender);
    }

    private class listenerMainBL implements IMainListener {

        @Override
        public void showGetAllCharacterSuccess(Characters characters) {
            view.showGetAllCharacterSuccess(characters);
        }

        @Override
        public void showGetAllCharacterFilter(Characters characters) {
            view.showGetAllCharacterFilter(characters);
        }

        @Override
        public void showMainError(MessageResponse message) {
            view.showMainError(message);
        }
    }
}
