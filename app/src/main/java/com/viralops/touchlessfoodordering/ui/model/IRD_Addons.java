package com.viralops.touchlessfoodordering.ui.model;

import org.json.JSONArray;

public class IRD_Addons {
    
        private String menu_item_id;

        private String max_addon_per_order;

        private String updated_at;

        private JSONArray addons;

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

        public JSONArray getAddons ()
        {
            return addons;
        }

        public void setAddons (JSONArray addons)
        {
            this.addons = addons;
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
            return "ClassPojo [menu_item_id = "+menu_item_id+", max_addon_per_order = "+max_addon_per_order+", updated_at = "+updated_at+", addons = "+addons+", name = "+name+", description = "+description+", created_at = "+created_at+", id = "+id+", min_addon_per_order = "+min_addon_per_order+", type = "+type+", enabled = "+enabled+", tags = "+tags+"]";
        }
    


}
