package com.codepath.insta_micro_gram;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ayegorov on 10/25/15.
 */
public class InstagramPopularPhotosHandler {

    public void onSuccess(ArrayList<InstagramPhotoModel> photos) {
        Log.w("DEBUG", "got the photos " + photos.toString() );
    }

    public void onFailure(int statusCode, String errorMessage) {
        Log.w("DEBUG", "request failed with code '" + statusCode + "' message '" + errorMessage + "'" );
    }
}
