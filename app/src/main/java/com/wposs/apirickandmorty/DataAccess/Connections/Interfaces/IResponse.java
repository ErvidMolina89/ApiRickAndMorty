package com.wposs.apirickandmorty.DataAccess.Connections.Interfaces;

import com.wposs.apirickandmorty.Models.BaseModel;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.Utils.Constants;

public interface IResponse {

    void onSuccessResponse(BaseModel objectResponse, String service);
    void onFailedResponse(MessageResponse response);
}
