package com.viralops.touchlessfoodordering.ui.model;

import java.util.ArrayList;

public class Menucategory {
    private String is_enabled;

    private String image;

    private String category_id;

    private String updated_at;

    private String name;

    private String description;

    private String created_at;

    private ArrayList<Menu.Items> items;

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

    public ArrayList<Menu.Items> getItems ()
    {
        return items;
    }

    public void setItems (ArrayList<Menu.Items> items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [is_enabled = "+is_enabled+", image = "+image+", category_id = "+category_id+", updated_at = "+updated_at+", name = "+name+", description = "+description+", created_at = "+created_at+", items = "+items+"]";
    }
}
