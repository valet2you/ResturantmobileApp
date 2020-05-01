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
        private int accepted_orders;

        private int dispatched_orders;

        private int new_orders;

        public int getAccepted_orders ()
        {
            return accepted_orders;
        }

        public void setAccepted_orders (int accepted_orders)
        {
            this.accepted_orders = accepted_orders;
        }

        public int getDispatched_orders ()
        {
            return dispatched_orders;
        }

        public void setDispatched_orders (int dispatched_orders)
        {
            this.dispatched_orders = dispatched_orders;
        }

        public int getNew_orders ()
        {
            return new_orders;
        }

        public void setNew_orders (int new_orders)
        {
            this.new_orders = new_orders;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [accepted_orders = "+accepted_orders+", dispatched_orders = "+dispatched_orders+", new_orders = "+new_orders+"]";
        }
    }


}
