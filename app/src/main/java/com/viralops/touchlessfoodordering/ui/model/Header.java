package com.viralops.touchlessfoodordering.ui.model;

public class Header {
    private Data data;

    private String success;

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
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
    public class Data
    {
        private int cleared_order;

        private int dispatched_order;

        private int new_order;

        private int accepted_order;

        public int getCleared_order ()
        {
            return cleared_order;
        }

        public void setCleared_order (int cleared_order)
        {
            this.cleared_order = cleared_order;
        }

        public int getDispatched_order ()
        {
            return dispatched_order;
        }

        public void setDispatched_order (int dispatched_order)
        {
            this.dispatched_order = dispatched_order;
        }

        public int getNew_order ()
        {
            return new_order;
        }

        public void setNew_order (int new_order)
        {
            this.new_order = new_order;
        }

        public int getAccepted_order ()
        {
            return accepted_order;
        }

        public void setAccepted_order (int accepted_order)
        {
            this.accepted_order = accepted_order;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [cleared_order = "+cleared_order+", dispatched_order = "+dispatched_order+", new_order = "+new_order+", accepted_order = "+accepted_order+"]";
        }
    }




}
