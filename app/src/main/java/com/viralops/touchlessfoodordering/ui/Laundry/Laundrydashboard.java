package com.viralops.touchlessfoodordering.ui.Laundry;

import java.util.ArrayList;

public class Laundrydashboard {
    private String roomno;
    private String orderid;
    private String time;
    private ArrayList<LaudryModel> laudryModels;
    private String price;
    private String orderstatus;

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<LaudryModel> getLaudryModels() {
        return laudryModels;
    }

    public void setLaudryModels(ArrayList<LaudryModel> laudryModels) {
        this.laudryModels = laudryModels;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }
}
