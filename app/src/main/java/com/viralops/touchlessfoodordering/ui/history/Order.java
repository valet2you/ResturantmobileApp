package com.viralops.touchlessfoodordering.ui.history;

public class Order {
    private String roomno;
    private String guests;
    private String orderreceivedat;
    private String deliverytime;
    private String status;

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

    public String getOrderreceivedat() {
        return orderreceivedat;
    }

    public void setOrderreceivedat(String orderreceivedat) {
        this.orderreceivedat = orderreceivedat;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
