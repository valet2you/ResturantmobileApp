package com.viralops.touchlessfoodordering.ui.Laundry;

public class Laundry_item {
    private String thumbnail;

    private String description;

    private String created_at;

    private String laundry_category_id;

    private String enabled;

    private String tags;

    private String updated_at;

    private String price;

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
