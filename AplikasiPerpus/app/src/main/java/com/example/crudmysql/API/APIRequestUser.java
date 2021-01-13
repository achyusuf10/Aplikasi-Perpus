/**
 * Created By Achmad Yusuf
 * Github : abdullah1006
 **/
package com.example.crudmysql.API;

import com.example.crudmysql.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIRequestUser {
    //Send Request Get Ke Rest API, untuk ambil data
    @GET("users")
    Call<ResponseModel> ardGetUser();

    //Send Request POST Ke Rest API, untuk menambah data
    @FormUrlEncoded
    @POST("users/create")
    Call<ResponseModel> ardCreateUser(
            @Field("jabatan") String jabatan,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("alamat") String alamat,
            @Field("telp") String telp
    );

    //Send Request PUT Ke Rest API, untuk update data
    @FormUrlEncoded
    @PUT("users/{id}")
    Call<ResponseModel> ardUpdateUser(
            @Path("id") int id,
            @Field("jabatan") String jabatan,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("alamat") String alamat,
            @Field("telp") String telp
    );

    //Send Request DELETE Ke Rest API, untuk delete data
    @DELETE("users/{id}")
    Call<ResponseModel> ardDeleteUser(
            @Path("id") int id
    );
}
