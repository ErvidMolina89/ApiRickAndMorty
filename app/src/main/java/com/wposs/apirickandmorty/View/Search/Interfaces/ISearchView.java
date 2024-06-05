package com.wposs.apirickandmorty.View.Search.Interfaces;

import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;

public interface ISearchView {
    void showGetAllCharacterNameSuccess(Characters characters);
    void showSearchError(MessageResponse message);
}
