package com.codepath.insta_micro_gram.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ayegorov on 10/24/15.
 */

public class InstagramPhotoModel {

    // { "data" => [x] => "type" } ("image" or "video")
    public String imageUrl; // { "data" => [x] => "images" => "standard_resolution" => "url" }
    public String username; // { "data" => [x] => "user" => "username" }
    public String caption;  // { "data" => [x] => "caption" => "text" }
    public int likeCount;   // { "data" => [x] => "likes" => "count"}

    public InstagramPhotoModel(JSONObject jsonObject) {

        try {
            imageUrl = jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            username = jsonObject.getJSONObject("user").getString("username");
            caption = jsonObject.getJSONObject("caption").getString("text");
            likeCount = jsonObject.getJSONObject("likes").getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
