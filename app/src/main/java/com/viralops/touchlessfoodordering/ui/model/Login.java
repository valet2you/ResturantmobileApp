package com.viralops.touchlessfoodordering.ui.model;

public class Login {

    private String success;

    private Message message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public Message getMessage ()
    {
        return message;
    }

    public void setMessage (Message message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [success = "+success+", message = "+message+"]";
    }
    public class Message
    {
        private String access_token;

        private String user_name;

        private String current_timestamp;

        private String hotel_name;

        public String getAccess_token ()
        {
            return access_token;
        }

        public void setAccess_token (String access_token)
        {
            this.access_token = access_token;
        }

        public String getUser_name ()
        {
            return user_name;
        }

        public void setUser_name (String user_name)
        {
            this.user_name = user_name;
        }

        public String getCurrent_timestamp ()
        {
            return current_timestamp;
        }

        public void setCurrent_timestamp (String current_timestamp)
        {
            this.current_timestamp = current_timestamp;
        }

        public String getHotel_name ()
        {
            return hotel_name;
        }

        public void setHotel_name (String hotel_name)
        {
            this.hotel_name = hotel_name;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [access_token = "+access_token+", user_name = "+user_name+", current_timestamp = "+current_timestamp+", hotel_name = "+hotel_name+"]";
        }
    }
}
