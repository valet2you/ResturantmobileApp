package com.viralops.touchlessfoodordering.ui.model;

import java.util.ArrayList;

public class Order {
    private ArrayList<Data> data;

    private String success;

    public ArrayList<Data> getData ()
    {
        return data;
    }

    public void setData (ArrayList<Data> data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", success = "+success+"]";
    }
    public class Data
    {
        private String updated_at;

        private long confirm_at;

        private long dispatched_at;

        private String no_guest;

        private long created_at;

        private String room_no;

        private String details;

        private String order_id;

        private ArrayList<Items> items;

        private String status;

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public long getConfirm_at ()
        {
            return confirm_at;
        }

        public void setConfirm_at (long confirm_at)
        {
            this.confirm_at = confirm_at;
        }

        public long getDispatched_at ()
        {
            return dispatched_at;
        }

        public void setDispatched_at (long dispatched_at)
        {
            this.dispatched_at = dispatched_at;
        }

        public String getNo_guest ()
        {
            return no_guest;
        }

        public void setNo_guest (String no_guest)
        {
            this.no_guest = no_guest;
        }

        public long getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (long created_at)
        {
            this.created_at = created_at;
        }

        public String getRoom_no ()
        {
            return room_no;
        }

        public void setRoom_no (String room_no)
        {
            this.room_no = room_no;
        }

        public String getDetails ()
    {
        return details;
    }

        public void setDetails (String details)
        {
            this.details = details;
        }

        public String getOrder_id ()
        {
            return order_id;
        }

        public void setOrder_id (String order_id)
        {
            this.order_id = order_id;
        }

        public ArrayList<Items> getItems ()
        {
            return items;
        }

        public void setItems (ArrayList<Items> items)
        {
            this.items = items;
        }

        public String getStatus ()
        {
            return status;
        }

        public void setStatus (String status)
        {
            this.status = status;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [updated_at = "+updated_at+", confirm_at = "+confirm_at+", dispatched_at = "+dispatched_at+", no_guest = "+no_guest+", created_at = "+created_at+", room_no = "+room_no+", details = "+details+", order_id = "+order_id+", items = "+items+", status = "+status+"]";
        }
    }

    public class Items
    {
        private String item_id;

        private String count;
        private String name;

        private String details;

        public String getItem_id ()
        {
            return item_id;
        }

        public void setItem_id (String item_id)
        {
            this.item_id = item_id;
        }

        public String getCount ()
        {
            return count;
        }

        public void setCount (String count)
        {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetails ()
    {
        return details;
    }

        public void setDetails (String details)
        {
            this.details = details;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [item_id = "+item_id+", count = "+count+", details = "+details+", name = "+name+"]";
        }
    }


}
