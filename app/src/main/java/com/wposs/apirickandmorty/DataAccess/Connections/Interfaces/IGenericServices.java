package com.wposs.apirickandmorty.DataAccess.Connections.Interfaces;

import com.wposs.apirickandmorty.Models.BaseModel;
import com.wposs.apirickandmorty.Models.Characters;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IGenericServices {
    @GET
    Call<Characters> getGenericCharters(@Url String url);

    @GET("character")
    Call<Characters> getGenericChartersForName(@Query("name") String name, @Query("page") int page);

    @POST
    Call postGeneric(@Url String url);

    @PUT
    Call putGeneric(@Url String url);

    @DELETE
    Call deleteGeneric(@Url String url);
}
