package com.viralops.touchlessfoodordering.ui.Laundry;

public class Laundry_Model {
    private Laundry_Data data;

    private String message;

    public Laundry_Data getData ()
    {
        return data;
    }

    public void setData (Laundry_Data data)
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
}
