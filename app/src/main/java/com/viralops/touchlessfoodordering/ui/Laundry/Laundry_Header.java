package com.viralops.touchlessfoodordering.ui.Laundry;

public class Laundry_Header {
    private Data data;

    private String message;

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
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

    public class Data
    {
        private int express_order;

        private int today_order;

        private int standard_order;

        public int getExpress_order ()
        {
            return express_order;
        }

        public void setExpress_order (int express_order)
        {
            this.express_order = express_order;
        }

        public int getToday_order ()
        {
            return today_order;
        }

        public void setToday_order (int today_order)
        {
            this.today_order = today_order;
        }

        public int getStandard_order ()
        {
            return standard_order;
        }

        public void setStandard_order (int standard_order)
        {
            this.standard_order = standard_order;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [express_order = "+express_order+", today_order = "+today_order+", standard_order = "+standard_order+"]";
        }
    }

}
