package com.viralops.touchlessfoodordering.ui.model;

import org.json.JSONArray;

public class IRd_SubCategory {
    private String updated_at;

    private String name;

    private String description;

    private String created_at;

    private String id;

    private String menu_category_id;

    private JSONArray items;

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

    public String getMenu_category_id ()
    {
        return menu_category_id;
    }

    public void setMenu_category_id (String menu_category_id)
    {
        this.menu_category_id = menu_category_id;
    }

    public JSONArray getItems ()
    {
        return items;
    }

    public void setItems (JSONArray items)
    {
        this.items = items;
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
        return "ClassPojo [updated_at = "+updated_at+", name = "+name+", description = "+description+", created_at = "+created_at+", id = "+id+", menu_category_id = "+menu_category_id+", items = "+items+", enabled = "+enabled+", tags = "+tags+"]";
    }
}
