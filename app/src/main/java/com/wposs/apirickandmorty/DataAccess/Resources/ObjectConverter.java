package com.wposs.apirickandmorty.DataAccess.Resources;

import com.wposs.apirickandmorty.Base.ContextApp;
import com.wposs.apirickandmorty.Models.BaseModel;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.R;

public class ObjectConverter {
    public static Characters convertObjectToCharacters(BaseModel objectResponse) {
        if (objectResponse instanceof Characters) {
            return (Characters) objectResponse;
        } else {
            throw new IllegalArgumentException(ContextApp.getContext().getString(R.string.object_no_cast_characters));
        }
    }
}
