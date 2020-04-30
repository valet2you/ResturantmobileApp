package com.viralops.touchlessfoodordering.ui.Support;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.viralops.touchlessfoodordering.ui.Acitities.Login_Activity;




/**
 * Created by simran on 3/25/2018.
 */
public class SessionManager {
    SharedPreferences pref;
    private static  final String recordid="recordid";
    Context context;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "touch";


    private static final String Token="Token";
    private static final String PORCH_NAME="porch_name";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String NAME = "name";
    private static final String SLOT1 = "slot1";
    private static final String SLOT2 = "slot2";
    private static final String SLOT3 = "slot3";
    private static  final String isINternet="internet";
    private  static final String notificationvalue="notifyval";



    private static final String REQUEST1 = "request1";
    private static final String REQUEST2 = "request2";
    private static final String REQUEST3= "request3";


    private static final String DRIVER_ID = "driverid";
    private static final String PORCH_ID = "porchid";
    private static final String ACCESSTOKEN = "accesstoken";
    private static final String HOTEL_ID = "hotelid";
    private static final String Header_color = "header_color";
    private static final String Design_Style = "designstyle";
    private static final String UserName = "username";
    private static final String NomalFontColor = "normalfontcolor";
    private static final String Floors = "floors";
    private static final String NomalFontSize = "normalfontsize";
    private static final String TDBackgroung = "tdbackground";
    private static final String TDfont_color = "td_font_color";
    private static final String TD_font_size = "td_font_size";
    private static final String TH_bg_color = "th_bg_color";
    private static final String TH_font_color = "th_font_color";
    private static final String LOCK_VALUE="lockvalue";

    public  String getDesign_Style() {
        return pref.getString(Design_Style,"");
    }

    public  String getNomalFontColor() {
        return pref.getString(NomalFontColor,"");
    }

    public  String getNomalFontSize() {
        return pref.getString( NomalFontSize,"");
    }

    public  String getTDBackgroung() {
        return pref.getString( TDBackgroung,"");
    }

    public  String getTDfontcolor() {
        return pref.getString( TDfont_color,"");
    }

    public  String getTDfontsize() {
        return pref.getString( TD_font_size,"");
    }

    public  String getTHbgcolor() {
        return pref.getString( TH_bg_color,"");
    }

    public  String getTHfontcolor() {
        return pref.getString( TH_font_color,"");
    }

    public  String getTHfontsize() {
        return pref.getString( TH_font_size,"");
    }

    public  String getName() {
        return pref.getString( Name,"");
    }

    public  String getRecordid() {
        return pref.getString( recordid,"");
    }
    public  void setRecordid(String token1) {
        editor.putString(recordid,token1);
        editor.commit();
    }
    public  String getID() {
        return pref.getString( ID,"");
    }
    public  void setNomalFontColor(String token1) {
        editor.putString(NomalFontColor,token1);
        editor.commit();
    }

    public  void setNomalFontSize(String token1) {
        editor.putString(NomalFontSize,token1);
        editor.commit();
    }

    public  void setTDBackgroung(String token1) {
        editor.putString(TDBackgroung,token1);
        editor.commit();
    }

    public  void setTDfontcolor(String token1) {
        editor.putString(TDfont_color,token1);
        editor.commit();

    }

    public  void setTDfontsize(String token1) {
        editor.putString(TD_font_size,token1);
        editor.commit();
    }

    public  void setTHbgcolor(String token1) {
        editor.putString(TH_bg_color,token1);
        editor.commit();
   }

    public  void setTHfontcolor(String token1) {
        editor.putString(TH_font_color,token1);
        editor.commit();
    }

    public  void setTHfontsize(String token1) {
        editor.putString(TH_font_size,token1);
        editor.commit();
    }

    public  void setName(String token1) {
        editor.putString(Name,token1);
        editor.commit();
    }

    public  void setID(String token1 ) {
        editor.putString(ID,token1);
        editor.commit();
    }
    private static final String TH_font_size = "th_font_size";
    private static final String Name = "name";
    private static final String ID = "id";




    public  String getToken() {
        return pref.getString(Token,"");
    }
    public  void setToken(String Token1) {
         editor.putString(Token,Token1);
        editor.commit();

    }

    public  String getHeadercolor() {
        return pref.getString( Header_color,"");


    }

    public  String getUserName() {
        return pref.getString(UserName,"");
    }

    public  String getBackgroundColor() {
        return pref.getString(Background_Color,"");
    }

    public  String getSideMEnuColor() {
        return pref.getString(SideMEnu_Color,"");
    }

    public  String getFontSize() {
        return pref.getString(Font_Size,"");
    }

    public  String getFontFamily() {
        return pref.getString(Font_Family,"");
    }

    public  String getMainColor() {
        return pref.getString(Main_Color,"");
    }

    public  String getFontColor() {
        return pref.getString(Font_Color,"");
    }
    public  void setHeadercolor(String id) {
        editor.putString(Header_color,id);
        editor.commit();
    }

    public  void setBackgroundColor(String id) {
        editor.putString(Background_Color,id);
        editor.commit();
    }

    public  void setSideMEnuColor(String id) {
        editor.putString(SideMEnu_Color,id);
        editor.commit();
    }

    public  void setFontSize(String id) {
        editor.putString(Font_Size,id);
        editor.commit();
    }

    public  void setFontFamily(String id) {
        editor.putString(Font_Family,id);
        editor.commit();
    }

    public  void setMainColor(String id) {
        editor.putString(Main_Color,id);
        editor.commit();
    }

    public  void setFontColor(String id) {
        editor.putString(Font_Color,id);
        editor.commit();
    }

    private static final String Background_Color = "background_color";
    private static final String SideMEnu_Color = "sidenEnu_Color";
    private static final String Font_Size = "font_size";
    private static final String Font_Family = "font_family";
    private static final String Main_Color = "main_color";
    private static final String Font_Color = "font_color";

    public SharedPreferences getPref() {
        return pref;
    }

    public void setPref(SharedPreferences pref) {
        this.pref = pref;
    }

    public  String getHotelId() {
        return pref.getString(HOTEL_ID,null);
    }
    public  void setHotelId(String id) {
        editor.putString(HOTEL_ID,id);
        editor.commit();
    }
    public  String getACCESSTOKEN() {
        return pref.getString(ACCESSTOKEN,null);
    }
    public  void setACCESSTOKEN(String id) {
        editor.putString(ACCESSTOKEN,id);
        editor.commit();
    }
    public  String getPorchId() {
        return pref.getString(PORCH_ID,null);
    }
    public  void setPorchId(String id) {
        editor.putString(PORCH_ID,id);
        editor.commit();
    }

    public  String getDriverId() {
        return pref.getString(DRIVER_ID,null);
    }
    public  void setDriverId(String driverid) {
        editor.putString(DRIVER_ID,driverid);
        editor.commit();
    }


    public  String getNAME() {
        return pref.getString(NAME,null);
    }
    public  void setNAME(String name) {
         editor.putString(NAME,name);
        editor.commit();
    }
    public  void setPorchName(String porch) {
         editor.putString(PORCH_NAME,porch);
        editor.commit();

    } public  void setUserName(String username) {
         editor.putString(UserName,username);
        editor.commit();

    }

    public  String getPorchName() {
        return pref.getString(PORCH_NAME,null);
    }


    public  Boolean getIsLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public SessionManager(Context context) {
        this.context=context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }
    public void logoutsession(){

        Intent  intent=new Intent(context, Login_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        editor.clear();
        editor.commit();
    }



    public  String getSLOT1() {
        return pref.getString(SLOT1,null);
    }

    public  String getSLOT2() {
        return pref.getString(SLOT2,null);
    }

    public  String getSLOT3() {
        return pref.getString(SLOT3,null);
    }

    public  String getREQUEST1() {
        return pref.getString(REQUEST1,null);
    }

    public  String getREQUEST2() {
        return pref.getString(REQUEST2,null);
    }

    public  String getREQUEST3() {
        return pref.getString(REQUEST3,null);
    }
    public  void setSLOT1(String slot1) {
        editor.putString(SLOT1,slot1);
        editor.commit();
    }

    public  void setSLOT2(String slot2) {
        editor.putString(SLOT2,slot2);
        editor.commit();    }

    public  void setSLOT3(String slot1) {
        editor.putString(SLOT3,slot1);
        editor.commit();    }

    public  void setREQUEST1(String slot1) {
        editor.putString(REQUEST1,slot1);
        editor.commit();    }

    public  void setREQUEST2(String slot1) {
        editor.putString(REQUEST2,slot1);
        editor.commit();
    }

    public  void setREQUEST3(String slot1) {
        editor.putString(REQUEST3,slot1);
        editor.commit();
    }
    public void setLockValue(String lockvalue) {
        editor.putString(LOCK_VALUE,lockvalue);
        editor.commit();
    } public void setIsINternet(String internet) {
        editor.putString(isINternet,internet);
        editor.commit();
    }

    public  String getIsINternet() {
        return pref.getString(isINternet,"");
    }

    public String  getLockValue() {
        return pref.getString(LOCK_VALUE,null);
    }
    public  void setDesignStyle(String design) {
        editor.putString(Design_Style,design);
        editor.commit();

    }

    public  String getFloors() {
        return pref.getString(Floors,"");
    }
    public  void setFloors(String floors) {
        editor.putString(Floors,floors);
        editor.commit();

    }
    public  void setNotificationvalue(String notificationvalue) {
        editor.putString(notificationvalue,notificationvalue);
        editor.commit();

    }
    public  String getNotificationvalue() {
        return pref.getString(notificationvalue,"");
    }

}
