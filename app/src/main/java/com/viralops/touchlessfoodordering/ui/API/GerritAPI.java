package com.viralops.touchlessfoodordering.ui.API;



import com.viralops.touchlessfoodordering.BuildConfig;
import com.viralops.touchlessfoodordering.ui.Laundry.LaundryOrderHistory1;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundry_Dashboard;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundry_Header;
import com.viralops.touchlessfoodordering.ui.Laundry.Laundrydashboard;
import com.viralops.touchlessfoodordering.ui.main.HomeViewModel;
import com.viralops.touchlessfoodordering.ui.model.Action;
import com.viralops.touchlessfoodordering.ui.model.Dashboard;
import com.viralops.touchlessfoodordering.ui.model.Login;
import com.viralops.touchlessfoodordering.ui.model.Logout;
import com.viralops.touchlessfoodordering.ui.model.Menu;
import com.viralops.touchlessfoodordering.ui.model.Order;
import com.viralops.touchlessfoodordering.ui.model.OrderHistory;

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
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GerritAPI {
    @FormUrlEncoded
    @POST(BuildConfig.login)
    Call<Login> SignIn(@Field("username") String username, @Field("password") String password,@Field("device_token") String device_token,
                       @Field("device_type") String device_type);
    @GET(BuildConfig.logout)
    Call<Action> logout(@Header("Authorization") String Authorization);

    @GET(BuildConfig.ird_menu)
    Call<Menu> getMenu(@Header("Authorization") String Authorization);
    @PUT()
    Call<Action> getItemEnabled(@Header("Authorization") String Authorization, @Url String url);
    @PUT()
    Call<Action> getItemDisabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getEnabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getdisabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getcategoryEnabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getcategorydisabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubcategoryEnabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddondisabled(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddonenable(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddonitemenable(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddonitemdisable(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubcategorydisabled(@Header("Authorization") String Authorization,@Url String url);

    @GET(BuildConfig.get_orders)
    Call<Dashboard> getOrders(@Header("Authorization") String Authorization);

    @PUT()
    Call<Action> OrderAccepted(@Url String url,@Header("Authorization") String Authorization);

    @PUT()
    Call<Action> OrderDispatched(@Url String url,@Header("Authorization") String Authorization);

    @GET(BuildConfig.get_order_header)
    Call<com.viralops.touchlessfoodordering.ui.model.Header> getHeader(@Header("Authorization") String Authorization);

    @GET(BuildConfig.get_order_history)
    Call<OrderHistory> getHistory(@Header("Authorization") String Authorization, @Query("show") String show);
    @PUT()
    Call<Action> OrderCleared(@Url String url,@Header("Authorization") String Authorization);

    //----------------Laundry----------------------------------------------//
    @GET(BuildConfig.laundry_menu)
    Call<Menu> getMenuLaundry(@Header("Authorization") String Authorization);
    @PUT()
    Call<Action> getItemEnabledLaundry(@Header("Authorization") String Authorization, @Url String url);
    @PUT()
    Call<Action> getItemDisabledLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getcategoryEnabledLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getcategorydisabledLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubcategoryEnabledLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddondisabledLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddonenableLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddonitemenableLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubaddonitemdisableLaundry(@Header("Authorization") String Authorization,@Url String url);
    @PUT()
    Call<Action> getSubcategorydisabledLaundry(@Header("Authorization") String Authorization,@Url String url);
    @GET(BuildConfig.laundry_get_orders)
    Call<Laundry_Dashboard> Laundry_getOrders(@Header("Authorization") String Authorization);
    @PUT()
    Call<Action> Laundry_OrderAccepted(@Url String url,@Header("Authorization") String Authorization);

    @PUT()
    Call<Action> Laundry_OrderDispatched(@Url String url,@Header("Authorization") String Authorization);

    @PUT()
    Call<Action> Laundry_OrderCleared(@Url String url,@Header("Authorization") String Authorization);

    @GET(BuildConfig.laundry_get_order_header)
    Call<Laundry_Header> Laundry_getHeader(@Header("Authorization") String Authorization);

    @GET(BuildConfig.laundry_get_order_history)
    Call<LaundryOrderHistory1> Laundry_getHistory(@Header("Authorization") String Authorization, @Query("show") String show);

    @GET(BuildConfig.laundry_get_order_history)
    Call<Laundry_Dashboard> Laundry_getHistory1(@Header("Authorization") String Authorization, @Query("show") String show);



    //--------------End of laundry----------------------------------------//

}
