package com.viralops.touchlessfoodordering.ui.main;



import java.util.ArrayList;

public class HomeViewModel{
    private String roomno;
    private String guests;
    private String orderreceived;
    private String orderstatus;
    private String since;
    private String status;
    private String checkstatus;
    private ArrayList<Order_Item> order_itemslist;

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getOrderreceived() {
        return orderreceived;
    }

    public ArrayList<Order_Item> getOrder_itemslist() {
        return order_itemslist;
    }

    public String getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus;
    }

    public void setOrder_itemslist(ArrayList<Order_Item> order_itemslist) {
        this.order_itemslist = order_itemslist;
    }

    public void setOrderreceived(String orderreceived) {
        this.orderreceived = orderreceived;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getSince() {
        return since;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSince(String since) {
        this.since = since;
    }
}