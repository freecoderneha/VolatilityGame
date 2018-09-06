package hello.abc.neha.volatilitygame;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class User implements Serializable {


    public User(int id, String name, String email, String password, String mobile, String city, String ig) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.city = city;
        this.user_ig = ig;

    }

    public User(String name, String email, String password, String mobile, String city, String ig) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.city = city;
        this.user_ig = ig;
    }


    public int id;
    public String name;
    public String image;
    public String email;
    public String mobile;
    public String password;
    public String city;
    public String business_name;
    public String address;
    public int user_type;
    public int device_type;
    public String device_id;
    public String fb_link;
    public String google_link;
    public String twitter_link;
    public String linkedin_link;
    public int user_time;
    public int user_status;
    public String user_ig;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public String getAddress() {
        return address;
    }

    public int getUser_type() {
        return user_type;
    }


    public int getDevice_type() {
        return device_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getFb_link() {
        return fb_link;
    }

    public String getGoogle_link() {
        return google_link;
    }

    public String getTwitter_link() {
        return twitter_link;
    }

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public int getUser_time() {
        return user_time;
    }

    public int getUser_status() {
        return user_status;
    }

    public String getUser_ig() {
        return user_ig;
    }
}



