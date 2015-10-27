package com.codepath.insta_micro_gram.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.insta_micro_gram.R;
import com.codepath.insta_micro_gram.adapters.InstagramPhotosAdapter;
import com.codepath.insta_micro_gram.models.InstagramPhotoModel;
import com.codepath.insta_micro_gram.network.InstagramAPIClient;
import com.codepath.insta_micro_gram.network.InstagramPopularPhotosHandler;

import java.util.ArrayList;

public class InstagramPhotosActivity extends AppCompatActivity {

    private InstagramAPIClient client;
    private ArrayList<InstagramPhotoModel> popularPhotos = new ArrayList<>();
    private InstagramPhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        client = new InstagramAPIClient(this);
        photosAdapter = new InstagramPhotosAdapter(this, R.id.photoItemView, popularPhotos);
        ListView photosListView = (ListView) findViewById(R.id.photosListView);
        photosListView.setAdapter(photosAdapter);

        final Context errorContext = this;

        client.getPopularPhotos( new InstagramPopularPhotosHandler() {
            @Override
            public void onSuccess(ArrayList<InstagramPhotoModel> photos) {
                super.onSuccess(photos);
                popularPhotos.clear();
                popularPhotos.addAll(photos);
                photosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, String errorMessage) {
                popularPhotos.clear();
                photosAdapter.notifyDataSetChanged();

                new AlertDialog.Builder(errorContext).setTitle("Error").setMessage(errorMessage).setCancelable(false).
                        setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing for now
                            }
                        }).create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
