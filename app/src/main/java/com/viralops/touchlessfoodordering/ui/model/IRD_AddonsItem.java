package com.viralops.touchlessfoodordering.ui.model;

public class IRD_AddonsItem {
    private String menu_item_id;

    private String thumbnail;

    private String description;

    private String created_at;

    private String type;

    private String enabled;

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
        return "ClassPojo [menu_item_id = "+menu_item_id+", thumbnail = "+thumbnail+", description = "+description+", created_at = "+created_at+", type = "+type+", enabled = "+enabled+", tags = "+tags+", updated_at = "+updated_at+", price = "+price+", name = "+name+", max_per_order = "+max_per_order+", min_per_order = "+min_per_order+", id = "+id+", menu_item_subaddon_id = "+menu_item_subaddon_id+"]";
    }
}
