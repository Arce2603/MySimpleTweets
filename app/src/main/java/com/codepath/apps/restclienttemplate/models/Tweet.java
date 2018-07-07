package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {
    //list of attribute
    public String body;
    public  long uid; //database ID for the tweet
    public  String createdAt;
    public User user;
    public String retweets;
    public String favs;
    public  int tweetID;
    public String handel;

    public Tweet() {
    }


    //deserialized Data
    public static  Tweet fromJSON(JSONObject jsonObject) throws JSONException{
        Tweet tweet = new Tweet();

        //extract values from JSON
        tweet.body= jsonObject.getString("text");
        tweet.uid=jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.retweets = jsonObject.getString("retweet_count");
        tweet.favs=jsonObject.getString("favorite_count");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.handel= "@"+ tweet.user.screenName;

        return tweet;
    }

    public String getHandel() {
        return handel;
    }
}
