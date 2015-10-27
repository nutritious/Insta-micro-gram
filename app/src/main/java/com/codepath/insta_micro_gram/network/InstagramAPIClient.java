package com.codepath.insta_micro_gram.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.codepath.insta_micro_gram.models.InstagramPhotoModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ayegorov on 10/24/15.
 */
public class InstagramAPIClient {

    private static final String CLIENT_ID = "a50dedc0a5984edcb022df32a1bf2390";
    private static final String BASE_URL = "https://api.instagram.com/v1/";

    private Context context;

    //
    // Private Helpers
    //
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    //
    // -- Interface --
    //

    public InstagramAPIClient(Context context) {
        this.context = context;
    }

    public void getPopularPhotos(final InstagramPopularPhotosHandler handler) {
        final String path = "media/popular";

        String url = BASE_URL + path + "?client_id=" + CLIENT_ID;
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                ArrayList<InstagramPhotoModel> photoModels = new ArrayList<InstagramPhotoModel>();
                JSONArray photosJSON;
                try {
                    photosJSON = response.getJSONArray("data");

                    for (int i = 0; i < photosJSON.length(); ++i) {

                        JSONObject photoJSON = photosJSON.getJSONObject(i);

                        if (photoJSON.getString("type").equals("image")) {
                            photoModels.add(new InstagramPhotoModel(photoJSON));
                        }
                    }

                    handler.onSuccess(photoModels);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                String errorMessage;

                if (!isNetworkAvailable()) {
                    errorMessage = "Network is NOT available. Try again later.";
                } else {
                    errorMessage = errorResponse.toString();
                }

                handler.onFailure(statusCode, errorMessage);
            }
        });
    }
}
