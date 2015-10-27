package com.codepath.insta_micro_gram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.insta_micro_gram.R;
import com.codepath.insta_micro_gram.models.InstagramPhotoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ayegorov on 10/26/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhotoModel> {

    // View lookup cache
    private static class ViewHolder {
        ImageView photoImageView;
        TextView captionTextView;
        TextView usernameTextView;
        TextView likeCountTextView;
    };

    public InstagramPhotosAdapter(Context context, int resource, List<InstagramPhotoModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhotoModel photoModel = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_item, parent, false);

            viewHolder.photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
            viewHolder.captionTextView = (TextView) convertView.findViewById(R.id.captionTextView);
            viewHolder.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
            viewHolder.likeCountTextView = (TextView) convertView.findViewById(R.id.likeCountTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.captionTextView.setText(photoModel.caption);
        viewHolder.usernameTextView.setText(photoModel.username);

        String likesString = ""; // If there are zero likes, it will be an empty string

        if (photoModel.likeCount == 1) {
            likesString = photoModel.likeCount + " Like";
        } else if (photoModel.likeCount > 1) {
            likesString = photoModel.likeCount + " Likes";
        }

        viewHolder.likeCountTextView.setText(likesString);

        viewHolder.photoImageView.setImageResource(0);

        Picasso.with(getContext()).load(photoModel.imageUrl).into(viewHolder.photoImageView);

        return convertView;
    }
}
