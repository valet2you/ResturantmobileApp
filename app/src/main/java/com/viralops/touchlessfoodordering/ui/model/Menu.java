package com.viralops.touchlessfoodordering.ui.model;

import java.util.ArrayList;

public class Menu {
    private ArrayList<menu_data> data;

    private String success;

    public ArrayList<menu_data> getData ()
    {
        return data;
    }

    public void setData (ArrayList<menu_data> data)
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


    public static class menu_data{
        private String is_enabled;

        private String image;

        private String category_id;

        private String updated_at;

        private String name;

        private String description;

        private String created_at;

        private ArrayList<Items> items;

        public String getIs_enabled ()
        {
            return is_enabled;
        }

        public void setIs_enabled (String is_enabled)
        {
            this.is_enabled = is_enabled;
        }

        public String getImage ()
        {
            return image;
        }

        public void setImage (String image)
        {
            this.image = image;
        }

        public String getCategory_id ()
        {
            return category_id;
        }

        public void setCategory_id (String category_id)
        {
            this.category_id = category_id;
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

        public ArrayList<Items> getItems ()
        {
            return items;
        }

        public void setItems (ArrayList<Items> items)
        {
            this.items = items;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [is_enabled = "+is_enabled+", image = "+image+", category_id = "+category_id+", updated_at = "+updated_at+", name = "+name+", description = "+description+", created_at = "+created_at+", items = "+items+"]";
        }
    }
    public static class Items{
        private String is_enabled;

        private String image;

        private String updated_at;

        private String item_id;

        private String price;

        private String name;

        private String description;

        private String created_at;

        private String type;

        private String tags;

        public String getIs_enabled ()
        {
            return is_enabled;
        }

        public void setIs_enabled (String is_enabled)
        {
            this.is_enabled = is_enabled;
        }

        public String getImage ()
        {
            return image;
        }

        public void setImage (String image)
        {
            this.image = image;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getItem_id ()
        {
            return item_id;
        }

        public void setItem_id (String item_id)
        {
            this.item_id = item_id;
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
            return "ClassPojo [is_enabled = "+is_enabled+", image = "+image+", updated_at = "+updated_at+", item_id = "+item_id+", price = "+price+", name = "+name+", description = "+description+", created_at = "+created_at+", type = "+type+", tags = "+tags+"]";
        }

    }
    }

