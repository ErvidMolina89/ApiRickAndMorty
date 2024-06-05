package com.wposs.apirickandmorty.View.Home.Interfaces;

public interface IMainBL {
    void getAllCharacterSuccess();
    void getAllCharacterForStatus(String status);
    void getAllCharacterForGender(String gender);
}
