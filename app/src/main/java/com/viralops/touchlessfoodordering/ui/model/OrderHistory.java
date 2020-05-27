package com.viralops.touchlessfoodordering.ui.model;

import java.util.ArrayList;

public class OrderHistory {
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
    public class Order_detail
    {
        private String updated_at;

        private String dispatched_at;

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
            return "ClassPojo [updated_at = "+updated_at+", dispatched_at = "+dispatched_at+", created_at = "+created_at+", cleared_at = "+cleared_at+", canceled_on = "+canceled_on+", id = "+id+", order_id = "+order_id+", accepted_at = "+accepted_at+"]";
        }
}
public class Order_menu_items
{
    private String menu_item_id;

    private Item item;

    private String quantity;

    private String updated_at;

    private String price;

    private String description;

    private String created_at;

    private String id;

    private String order_id;

    private ArrayList<Order_addons> order_addons;

    public String getMenu_item_id ()
    {
        return menu_item_id;
    }

    public void setMenu_item_id (String menu_item_id)
    {
        this.menu_item_id = menu_item_id;
    }

    public Item getItem ()
    {
        return item;
    }

    public void setItem (Item item)
    {
        this.item = item;
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

    public ArrayList<Order_addons> getOrder_addons ()
    {
        return order_addons;
    }

    public void setOrder_addons (ArrayList<Order_addons> order_addons)
    {
        this.order_addons = order_addons;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [menu_item_id = "+menu_item_id+", item = "+item+", quantity = "+quantity+", updated_at = "+updated_at+", price = "+price+", description = "+description+", created_at = "+created_at+", id = "+id+", order_id = "+order_id+", order_addons = "+order_addons+"]";
    }
}

    public class Data
    {
        private String hotel_id;

        private String payment_status;

        private String created_at;

        private String description;

        private String type;

        private Order_detail order_detail;

        private ArrayList<Order_menu_items> order_menu_items;

        private String no_of_guest;

        private String updated_at;

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

        public ArrayList<Order_menu_items> getOrder_menu_items ()
        {
            return order_menu_items;
        }

        public void setOrder_menu_items (ArrayList<Order_menu_items> order_menu_items)
        {
            this.order_menu_items = order_menu_items;
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
            return "ClassPojo [hotel_id = "+hotel_id+", payment_status = "+payment_status+", created_at = "+created_at+", description = "+description+", type = "+type+", order_detail = "+order_detail+", order_menu_items = "+order_menu_items+", no_of_guest = "+no_of_guest+", updated_at = "+updated_at+", primises = "+primises+", primises_id = "+primises_id+", id = "+id+", status = "+status+"]";
        }
    }

    public class Item
    {
        private String menu_sub_category_id;

        private String thumbnail;

        private String description;

        private String created_at;

        private String menu_category_id;

        private String type;

        private String enabled;

        private String tags;

        private String updated_at;

        private String price;

        private String name;

        private String max_per_order;

        private String min_per_order;

        private String id;

        public String getMenu_sub_category_id ()
    {
        return menu_sub_category_id;
    }

        public void setMenu_sub_category_id (String menu_sub_category_id)
        {
            this.menu_sub_category_id = menu_sub_category_id;
        }

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

        public String getMenu_category_id ()
        {
            return menu_category_id;
        }

        public void setMenu_category_id (String menu_category_id)
        {
            this.menu_category_id = menu_category_id;
        }

        public String getType ()
        {
            return type;
        }

        public void setType (String type)
        {
            this.type = type;
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

        public String getMax_per_order ()
        {
            return max_per_order;
        }

        public void setMax_per_order (String max_per_order)
        {
            this.max_per_order = max_per_order;
        }

        public String getMin_per_order ()
        {
            return min_per_order;
        }

        public void setMin_per_order (String min_per_order)
        {
            this.min_per_order = min_per_order;
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
            return "ClassPojo [menu_sub_category_id = "+menu_sub_category_id+", thumbnail = "+thumbnail+", description = "+description+", created_at = "+created_at+", menu_category_id = "+menu_category_id+", type = "+type+", enabled = "+enabled+", tags = "+tags+", updated_at = "+updated_at+", price = "+price+", name = "+name+", max_per_order = "+max_per_order+", min_per_order = "+min_per_order+", id = "+id+"]";
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

    public class Order_addons
    {
        private String order_menu_item_id;

        private MenuItem item;

        private String quantity;

        private String updated_at;

        private String price;

        private String description;

        private String created_at;

        private String id;

        private String order_id;

        private String menu_item_addon_id;

        public String getOrder_menu_item_id ()
        {
            return order_menu_item_id;
        }

        public void setOrder_menu_item_id (String order_menu_item_id)
        {
            this.order_menu_item_id = order_menu_item_id;
        }

        public MenuItem getItem ()
        {
            return item;
        }

        public void setItem (MenuItem item)
        {
            this.item = item;
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

        public String getMenu_item_addon_id ()
        {
            return menu_item_addon_id;
        }

        public void setMenu_item_addon_id (String menu_item_addon_id)
        {
            this.menu_item_addon_id = menu_item_addon_id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [order_menu_item_id = "+order_menu_item_id+", item = "+item+", quantity = "+quantity+", updated_at = "+updated_at+", price = "+price+", description = "+description+", created_at = "+created_at+", id = "+id+", order_id = "+order_id+", menu_item_addon_id = "+menu_item_addon_id+"]";
        }
    }


    public class Item_subaddon
    {
        private String menu_item_id;

        private String max_addon_per_order;

        private String updated_at;

        private String name;

        private String description;

        private String created_at;

        private String id;

        private String min_addon_per_order;

        private String type;

        private String enabled;

        private String tags;

        public String getMenu_item_id ()
        {
            return menu_item_id;
        }

        public void setMenu_item_id (String menu_item_id)
        {
            this.menu_item_id = menu_item_id;
        }

        public String getMax_addon_per_order ()
        {
            return max_addon_per_order;
        }

        public void setMax_addon_per_order (String max_addon_per_order)
        {
            this.max_addon_per_order = max_addon_per_order;
        }

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

        public String getMin_addon_per_order ()
        {
            return min_addon_per_order;
        }

        public void setMin_addon_per_order (String min_addon_per_order)
        {
            this.min_addon_per_order = min_addon_per_order;
        }

        public String getType ()
        {
            return type;
        }

        public void setType (String type)
        {
            this.type = type;
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
            return "ClassPojo [menu_item_id = "+menu_item_id+", max_addon_per_order = "+max_addon_per_order+", updated_at = "+updated_at+", name = "+name+", description = "+description+", created_at = "+created_at+", id = "+id+", min_addon_per_order = "+min_addon_per_order+", type = "+type+", enabled = "+enabled+", tags = "+tags+"]";
        }
    }

    public class MenuItem
    {
        private String menu_item_id;

        private String thumbnail;

        private String description;

        private String created_at;

        private String type;

        private String enabled;

        private Item_subaddon item_subaddon;

        private String tags;

        private String updated_at;

        private String price;

        private String name;

        private String max_per_order;

        private String min_per_order;

        private String id;

        private String menu_item_subaddon_id;

        public String getMenu_item_id ()
        {
            return menu_item_id;
        }

        public void setMenu_item_id (String menu_item_id)
        {
            this.menu_item_id = menu_item_id;
        }

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

        public String getType ()
        {
            return type;
        }

        public void setType (String type)
        {
            this.type = type;
        }

        public String getEnabled ()
        {
            return enabled;
        }

        public void setEnabled (String enabled)
        {
            this.enabled = enabled;
        }

        public Item_subaddon getItem_subaddon ()
    {
        return item_subaddon;
    }

        public void setItem_subaddon (Item_subaddon item_subaddon)
        {
            this.item_subaddon = item_subaddon;
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

        public String getMax_per_order ()
        {
            return max_per_order;
        }

        public void setMax_per_order (String max_per_order)
        {
            this.max_per_order = max_per_order;
        }

        public String getMin_per_order ()
        {
            return min_per_order;
        }

        public void setMin_per_order (String min_per_order)
        {
            this.min_per_order = min_per_order;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getMenu_item_subaddon_id ()
    {
        return menu_item_subaddon_id;
    }

        public void setMenu_item_subaddon_id (String menu_item_subaddon_id)
        {
            this.menu_item_subaddon_id = menu_item_subaddon_id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [menu_item_id = "+menu_item_id+", thumbnail = "+thumbnail+", description = "+description+", created_at = "+created_at+", type = "+type+", enabled = "+enabled+", item_subaddon = "+item_subaddon+", tags = "+tags+", updated_at = "+updated_at+", price = "+price+", name = "+name+", max_per_order = "+max_per_order+", min_per_order = "+min_per_order+", id = "+id+", menu_item_subaddon_id = "+menu_item_subaddon_id+"]";
        }
    }



}
