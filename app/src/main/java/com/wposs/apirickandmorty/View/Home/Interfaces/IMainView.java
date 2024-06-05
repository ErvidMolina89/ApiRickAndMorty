package com.wposs.apirickandmorty.View.Home.Interfaces;

import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;

public interface IMainView {
    void showGetAllCharacterSuccess(Characters characters);
    void showGetAllCharacterFilter(Characters characters);
    void showMainError(MessageResponse message);
}
