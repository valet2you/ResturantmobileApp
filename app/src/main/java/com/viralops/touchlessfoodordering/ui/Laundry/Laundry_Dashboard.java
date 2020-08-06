package com.viralops.touchlessfoodordering.ui.Laundry;

import java.util.ArrayList;

public class Laundry_Dashboard {
    private ArrayList<Data> data;

    private String message;

    public ArrayList<Data> getData ()
    {
        return data;
    }

    public void setData (ArrayList<Data> data)
    {
        this.data = data;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", message = "+message+"]";
    }
    public class Data
    {
        private String hotel_id;

        private String payment_status;

        private String created_at;

        private String description;

        private String type;

        private Order_detail order_detail;

        private String no_of_guest;

        private String updated_at;

        private ArrayList<Order_laundry_items> order_laundry_items;

        private Primises primises;

        private String primises_id;

        private String id;

        private String status;

        public String getHotel_id ()
        {
            return hotel_id;
        }

        public void setHotel_id (String hotel_id)
        {
            this.hotel_id = hotel_id;
        }

        public String getPayment_status ()
        {
            return payment_status;
        }

        public void setPayment_status (String payment_status)
        {
            this.payment_status = payment_status;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getType ()
        {
            return type;
        }

        public void setType (String type)
        {
            this.type = type;
        }

        public Order_detail getOrder_detail ()
        {
            return order_detail;
        }

        public void setOrder_detail (Order_detail order_detail)
        {
            this.order_detail = order_detail;
        }

        public String getNo_of_guest ()
        {
            return no_of_guest;
        }

        public void setNo_of_guest (String no_of_guest)
        {
            this.no_of_guest = no_of_guest;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public ArrayList<Order_laundry_items> getOrder_laundry_items ()
        {
            return order_laundry_items;
        }

        public void setOrder_laundry_items (ArrayList<Order_laundry_items> order_laundry_items)
        {
            this.order_laundry_items = order_laundry_items;
        }

        public Primises getPrimises ()
        {
            return primises;
        }

        public void setPrimises (Primises primises)
        {
            this.primises = primises;
        }

        public String getPrimises_id ()
        {
            return primises_id;
        }

        public void setPrimises_id (String primises_id)
        {
            this.primises_id = primises_id;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
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
            return "ClassPojo [hotel_id = "+hotel_id+", payment_status = "+payment_status+", created_at = "+created_at+", description = "+description+", type = "+type+", order_detail = "+order_detail+", no_of_guest = "+no_of_guest+", updated_at = "+updated_at+", order_laundry_items = "+order_laundry_items+", primises = "+primises+", primises_id = "+primises_id+", id = "+id+", status = "+status+"]";
        }
    }
    public class Order_detail
    {
        private String updated_at;

        private String dispatched_at;

        private String requested_pickup_at;

        private String created_at;

        private String cleared_at;

        private String canceled_on;

        private String id;

        private String order_id;

        private String accepted_at;

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getDispatched_at ()
    {
        return dispatched_at;
    }

        public void setDispatched_at (String dispatched_at)
        {
            this.dispatched_at = dispatched_at;
        }

        public String getRequested_pickup_at ()
        {
            return requested_pickup_at;
        }

        public void setRequested_pickup_at (String requested_pickup_at)
        {
            this.requested_pickup_at = requested_pickup_at;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }

        public String getCleared_at ()
    {
        return cleared_at;
    }

        public void setCleared_at (String cleared_at)
        {
            this.cleared_at = cleared_at;
        }

        public String getCanceled_on ()
    {
        return canceled_on;
    }

        public void setCanceled_on (String canceled_on)
        {
            this.canceled_on = canceled_on;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getOrder_id ()
        {
            return order_id;
        }

        public void setOrder_id (String order_id)
        {
            this.order_id = order_id;
        }

        public String getAccepted_at ()
    {
        return accepted_at;
    }

        public void setAccepted_at (String accepted_at)
        {
            this.accepted_at = accepted_at;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [updated_at = "+updated_at+", dispatched_at = "+dispatched_at+", requested_pickup_at = "+requested_pickup_at+", created_at = "+created_at+", cleared_at = "+cleared_at+", canceled_on = "+canceled_on+", id = "+id+", order_id = "+order_id+", accepted_at = "+accepted_at+"]";
        }
    }

    public static class Order_laundry_items
    {
        private Item item;

        private String laundry_item_id;

        private String quantity;

        private String updated_at;

        private String price;

        private String description;

        private String created_at;

        private String id;

        private String order_id;

        private String is_express_delivery;

        public Item getItem ()
        {
            return item;
        }

        public void setItem (Item item)
        {
            this.item = item;
        }

        public String getLaundry_item_id ()
        {
            return laundry_item_id;
        }

        public void setLaundry_item_id (String laundry_item_id)
        {
            this.laundry_item_id = laundry_item_id;
        }

        public String getQuantity ()
        {
            return quantity;
        }

        public void setQuantity (String quantity)
        {
            this.quantity = quantity;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getPrice ()
        {
            return price;
        }

        public void setPrice (String price)
        {
            this.price = price;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getOrder_id ()
        {
            return order_id;
        }

        public void setOrder_id (String order_id)
        {
            this.order_id = order_id;
        }

        public String getIs_express_delivery ()
        {
            return is_express_delivery;
        }

        public void setIs_express_delivery (String is_express_delivery)
        {
            this.is_express_delivery = is_express_delivery;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [item = "+item+", laundry_item_id = "+laundry_item_id+", quantity = "+quantity+", updated_at = "+updated_at+", price = "+price+", description = "+description+", created_at = "+created_at+", id = "+id+", order_id = "+order_id+", is_express_delivery = "+is_express_delivery+"]";
        }
    }


    public class Item
    {
        private String thumbnail;

        private String description;

        private String created_at;

        private String laundry_category_id;

        private String enabled;

        private String tags;

        private String updated_at;

        public Laundry_category getLaundry_category() {
            return laundry_category;
        }

        public void setLaundry_category(Laundry_category laundry_category) {
            this.laundry_category = laundry_category;
        }

        private String price;
        private Laundry_category laundry_category;

        private String name;

        private String laundry_id;

        private String express_delivery_price;

        private String id;

        private String laundry_sub_category_id;

        private String is_express_delivery;

        public String getThumbnail ()
    {
        return thumbnail;
    }

        public void setThumbnail (String thumbnail)
        {
            this.thumbnail = thumbnail;
        }

        public String getDescription ()
    {
        return description;
    }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }

        public String getLaundry_category_id ()
        {
            return laundry_category_id;
        }

        public void setLaundry_category_id (String laundry_category_id)
        {
            this.laundry_category_id = laundry_category_id;
        }

        public String getEnabled ()
        {
            return enabled;
        }

        public void setEnabled (String enabled)
        {
            this.enabled = enabled;
        }

        public String getTags ()
    {
        return tags;
    }

        public void setTags (String tags)
        {
            this.tags = tags;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getPrice ()
        {
            return price;
        }

        public void setPrice (String price)
        {
            this.price = price;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getLaundry_id ()
        {
            return laundry_id;
        }

        public void setLaundry_id (String laundry_id)
        {
            this.laundry_id = laundry_id;
        }

        public String getExpress_delivery_price ()
        {
            return express_delivery_price;
        }

        public void setExpress_delivery_price (String express_delivery_price)
        {
            this.express_delivery_price = express_delivery_price;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getLaundry_sub_category_id ()
    {
        return laundry_sub_category_id;
    }

        public void setLaundry_sub_category_id (String laundry_sub_category_id)
        {
            this.laundry_sub_category_id = laundry_sub_category_id;
        }

        public String getIs_express_delivery ()
        {
            return is_express_delivery;
        }

        public void setIs_express_delivery (String is_express_delivery)
        {
            this.is_express_delivery = is_express_delivery;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [thumbnail = "+thumbnail+", description = "+description+", created_at = "+created_at+", laundry_category_id = "+laundry_category_id+", enabled = "+enabled+", tags = "+tags+", updated_at = "+updated_at+", price = "+price+", name = "+name+", laundry_id = "+laundry_id+", express_delivery_price = "+express_delivery_price+", id = "+id+", laundry_sub_category_id = "+laundry_sub_category_id+", is_express_delivery = "+is_express_delivery+"]";
        }
    }



    public class Primises
    {
        private String hotel_floor_id;

        private String updated_at;

        private String premise_no;

        private String hotel_id;

        private String name;

        private String created_at;

        private String id;

        public String getHotel_floor_id ()
        {
            return hotel_floor_id;
        }

        public void setHotel_floor_id (String hotel_floor_id)
        {
            this.hotel_floor_id = hotel_floor_id;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getPremise_no ()
        {
            return premise_no;
        }

        public void setPremise_no (String premise_no)
        {
            this.premise_no = premise_no;
        }

        public String getHotel_id ()
        {
            return hotel_id;
        }

        public void setHotel_id (String hotel_id)
        {
            this.hotel_id = hotel_id;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [hotel_floor_id = "+hotel_floor_id+", updated_at = "+updated_at+", premise_no = "+premise_no+", hotel_id = "+hotel_id+", name = "+name+", created_at = "+created_at+", id = "+id+"]";
        }
    }

    public class Laundry_category
    {
        private String updated_at;

        private String name;

        private String laundry_id;

        private String description;

        private String created_at;

        private String id;

        private String enabled;

        private String tags;

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getLaundry_id ()
        {
            return laundry_id;
        }

        public void setLaundry_id (String laundry_id)
        {
            this.laundry_id = laundry_id;
        }

        public String getDescription ()
    {
        return description;
    }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getEnabled ()
        {
            return enabled;
        }

        public void setEnabled (String enabled)
        {
            this.enabled = enabled;
        }

        public String getTags ()
    {
        return tags;
    }

        public void setTags (String tags)
        {
            this.tags = tags;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [updated_at = "+updated_at+", name = "+name+", laundry_id = "+laundry_id+", description = "+description+", created_at = "+created_at+", id = "+id+", enabled = "+enabled+", tags = "+tags+"]";
        }
    }


}
