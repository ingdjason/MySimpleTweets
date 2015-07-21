package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ing Djason(Admin) on 21-Jul-15.
 */
public class User {
    //list attributes
    private String name;
    private long uid;
    private String screenname;
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenname() {
        return screenname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    //deserialize the user json ==> User
    public static User fromJSON(JSONObject json){
        User u = new User();
        //Extract and fill the values
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenname = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Return a user
        return u;
    }
}
