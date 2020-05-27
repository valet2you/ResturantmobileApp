package com.viralops.touchlessfoodordering.ui.model;

public class Login {
    private String access_token;

    private String user_type;

    private String expires_at;

    private Hotel hotel;

    private String message;

    private String token_type;

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token (String access_token)
    {
        this.access_token = access_token;
    }

    public String getUser_type ()
    {
        return user_type;
    }

    public void setUser_type (String user_type)
    {
        this.user_type = user_type;
    }

    public String getExpires_at ()
    {
        return expires_at;
    }

    public void setExpires_at (String expires_at)
    {
        this.expires_at = expires_at;
    }

    public Hotel getHotel ()
    {
        return hotel;
    }

    public void setHotel (Hotel hotel)
    {
        this.hotel = hotel;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getToken_type ()
    {
        return token_type;
    }

    public void setToken_type (String token_type)
    {
        this.token_type = token_type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [access_token = "+access_token+", user_type = "+user_type+", expires_at = "+expires_at+", hotel = "+hotel+", message = "+message+", token_type = "+token_type+"]";
    }


    public class Hotel
    {
        private String zip;

        private Country country;

        private String address;

        private String city;

        private String encrypted_id;

        private String mobile;

        private String created_at;

        private String updated_at;

        private String name;

        private String id;

        private String state;

        private String email;

        private String country_id;

        public String getZip ()
        {
            return zip;
        }

        public void setZip (String zip)
        {
            this.zip = zip;
        }

        public Country getCountry ()
        {
            return country;
        }

        public void setCountry (Country country)
        {
            this.country = country;
        }

        public String getAddress ()
        {
            return address;
        }

        public void setAddress (String address)
        {
            this.address = address;
        }

        public String getCity ()
        {
            return city;
        }

        public void setCity (String city)
        {
            this.city = city;
        }

        public String getEncrypted_id ()
        {
            return encrypted_id;
        }

        public void setEncrypted_id (String encrypted_id)
        {
            this.encrypted_id = encrypted_id;
        }

        public String getMobile ()
        {
            return mobile;
        }

        public void setMobile (String mobile)
        {
            this.mobile = mobile;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
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

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getState ()
        {
            return state;
        }

        public void setState (String state)
        {
            this.state = state;
        }

        public String getEmail ()
        {
            return email;
        }

        public void setEmail (String email)
        {
            this.email = email;
        }

        public String getCountry_id ()
        {
            return country_id;
        }

        public void setCountry_id (String country_id)
        {
            this.country_id = country_id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [zip = "+zip+", country = "+country+", address = "+address+", city = "+city+", encrypted_id = "+encrypted_id+", mobile = "+mobile+", created_at = "+created_at+", updated_at = "+updated_at+", name = "+name+", id = "+id+", state = "+state+", email = "+email+", country_id = "+country_id+"]";
        }
    }

    public class Country
    {
        private String iso;

        private String updated_at;

        private String timezone;

        private String name;

        private String created_at;

        private String id;

        private String currency_code;

        private String iso3;

        private String phone_code;

        public String getIso ()
        {
            return iso;
        }

        public void setIso (String iso)
        {
            this.iso = iso;
        }

        public String getUpdated_at ()
        {
            return updated_at;
        }

        public void setUpdated_at (String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getTimezone ()
        {
            return timezone;
        }

        public void setTimezone (String timezone)
        {
            this.timezone = timezone;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
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

        public String getCurrency_code ()
        {
            return currency_code;
        }

        public void setCurrency_code (String currency_code)
        {
            this.currency_code = currency_code;
        }

        public String getIso3 ()
        {
            return iso3;
        }

        public void setIso3 (String iso3)
        {
            this.iso3 = iso3;
        }

        public String getPhone_code ()
        {
            return phone_code;
        }

        public void setPhone_code (String phone_code)
        {
            this.phone_code = phone_code;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [iso = "+iso+", updated_at = "+updated_at+", timezone = "+timezone+", name = "+name+", created_at = "+created_at+", id = "+id+", currency_code = "+currency_code+", iso3 = "+iso3+", phone_code = "+phone_code+"]";
        }
    }

}
