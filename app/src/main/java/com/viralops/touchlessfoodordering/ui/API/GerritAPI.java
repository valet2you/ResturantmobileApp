package com.viralops.touchlessfoodordering.ui.API;



import com.viralops.touchlessfoodordering.BuildConfig;
import com.viralops.touchlessfoodordering.ui.model.Action;
import com.viralops.touchlessfoodordering.ui.model.Login;
import com.viralops.touchlessfoodordering.ui.model.Logout;
import com.viralops.touchlessfoodordering.ui.model.Menu;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface GerritAPI {
    @FormUrlEncoded
    @POST(BuildConfig.login)
    Call<Login> SignIn(@Field("userid") String username, @Field("password") String password,@Field("device_token") String device_token,
                       @Field("device_type") String device_type);
    @GET(BuildConfig.logout)
    Call<Action> logout(@Header("Authorization") String Authorization);


    @GET(BuildConfig.hotel_menu)
    Call<Menu> getMenu(@Header("Authorization") String Authorization);
    @PUT()
    Call<Action> getItemEnabled(@Header("Authorization") String Authorization, @Url String url);
    @PUT()
    Call<Action> getItemDisabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getcategoryEnabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getcategorydisabled(@Header("Authorization") String Authorization,@Url String url);






}
