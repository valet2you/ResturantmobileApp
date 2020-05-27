package com.viralops.touchlessfoodordering.ui.model;

public class IRD_Menu {
    private IRD_Data data;

    private String message;

    public IRD_Data getData ()
    {
        return data;
    }

    public void setData (IRD_Data data)
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
