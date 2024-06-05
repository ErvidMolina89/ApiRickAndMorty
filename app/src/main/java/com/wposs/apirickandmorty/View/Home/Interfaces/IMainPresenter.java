package com.wposs.apirickandmorty.View.Home.Interfaces;

public interface IMainPresenter {
    void getAllCharacterSuccess();
    void getAllCharacterForStatus(String status);
    void getAllCharacterForGender(String gender);
}
