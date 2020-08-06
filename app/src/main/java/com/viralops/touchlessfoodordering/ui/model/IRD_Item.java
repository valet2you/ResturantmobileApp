package com.viralops.touchlessfoodordering.ui.model;

import org.json.JSONArray;

public class IRD_Item {
   
        private String menu_sub_category_id;

        private String thumbnail;

        private JSONArray without_subaddon_addons;

        private String description;

        private String created_at;

        private String menu_category_id;

        private String type;

        private JSONArray sub_addons;

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

        public JSONArray getWithout_subaddon_addons ()
        {
            return without_subaddon_addons;
        }

        public void setWithout_subaddon_addons (JSONArray without_subaddon_addons)
        {
            this.without_subaddon_addons = without_subaddon_addons;
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

        public JSONArray getSub_addons ()
        {
            return sub_addons;
        }

        public void setSub_addons (JSONArray sub_addons)
        {
            this.sub_addons = sub_addons;
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
            return "ClassPojo [menu_sub_category_id = "+menu_sub_category_id+", thumbnail = "+thumbnail+", without_subaddon_addons = "+without_subaddon_addons+", description = "+description+", created_at = "+created_at+", menu_category_id = "+menu_category_id+", type = "+type+", sub_addons = "+sub_addons+", enabled = "+enabled+", tags = "+tags+", updated_at = "+updated_at+", price = "+price+", name = "+name+", max_per_order = "+max_per_order+", min_per_order = "+min_per_order+", id = "+id+"]";
        }
    }



