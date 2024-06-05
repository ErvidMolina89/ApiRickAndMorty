package com.wposs.apirickandmorty.DataAccess.Connections.Retrofit;

import androidx.annotation.NonNull;

import com.wposs.apirickandmorty.DataAccess.Connections.Interfaces.IGenericServices;
import com.wposs.apirickandmorty.DataAccess.Connections.Interfaces.IResponse;
import com.wposs.apirickandmorty.DataAccess.Resources.ObjectConverter;
import com.wposs.apirickandmorty.Models.BaseModel;
import com.wposs.apirickandmorty.Models.Characters;
import com.wposs.apirickandmorty.Models.MessageResponse;
import com.wposs.apirickandmorty.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImplementationRetrofit {
    private final Retrofit retrofit;
    private final IResponse listener;

    public ImplementationRetrofit(IResponse listener) {
        this.listener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getCharactersApi(String typeservice, String complento){
        IGenericServices service = retrofit.create(IGenericServices.class);
        Call<Characters> call = service.getGenericCharters(typeservice+complento);
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(@NonNull Call<Characters> call, @NonNull Response<Characters> response) {
                if (!response.isSuccessful()){
                    listener.onFailedResponse(new MessageResponse(response.code(),response.message()));
                    return;
                }
                        listener.onSuccessResponse(response.body(), typeservice);
            }

            @Override
            public void onFailure(@NonNull Call<Characters> call, @NonNull Throwable t) {
                listener.onFailedResponse(new MessageResponse(t.hashCode(),t.getMessage()));
            }
        });
    }

    public void getCharactersForNameApi(String name, int page){
        IGenericServices service = retrofit.create(IGenericServices.class);
        Call<Characters> call = service.getGenericChartersForName(name, page);
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(@NonNull Call<Characters> call, @NonNull Response<Characters> response) {
                if (!response.isSuccessful()){
                    listener.onFailedResponse(new MessageResponse(response.code(),response.message()));
                    return;
                }
                listener.onSuccessResponse(response.body(), Constants.Service.NAME);
            }

            @Override
            public void onFailure(@NonNull Call<Characters> call, @NonNull Throwable t) {
                listener.onFailedResponse(new MessageResponse(t.hashCode(),t.getMessage()));
            }
        });
    }
}
