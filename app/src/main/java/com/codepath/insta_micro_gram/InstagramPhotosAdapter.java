package com.codepath.insta_micro_gram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ayegorov on 10/26/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhotoModel> {
    public InstagramPhotosAdapter(Context context, int resource, List<InstagramPhotoModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhotoModel photoModel = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_item, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView captionTextView = (TextView) convertView.findViewById(R.id.captionTextView);
        TextView usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        TextView likeCountTextView = (TextView) convertView.findViewById(R.id.likeCountTextView);

        captionTextView.setText(photoModel.caption);
        usernameTextView.setText(photoModel.username);

        String likesString = ""; // If there are zero likes, it will be an empty string

        if (photoModel.likeCount == 1) {
            likesString = photoModel.likeCount + " Like";
        } else if (photoModel.likeCount > 1) {
            likesString = photoModel.likeCount + " Likes";
        }

        likeCountTextView.setText(likesString);

        photoImageView.setImageResource(0);

        Picasso.with(getContext()).load(photoModel.imageUrl).into(photoImageView);

        return convertView;
    }
}
