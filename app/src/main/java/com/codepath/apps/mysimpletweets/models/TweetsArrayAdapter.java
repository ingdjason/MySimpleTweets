package com.codepath.apps.mysimpletweets.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ing Djason(Admin) on 21-Jul-15.
 */

// taking in tweet objects and turning them into views display in the list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context,0, tweets);
    }

    //override and custome template
    //view older pattern
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //1.Get the tweet
        Tweet tweet = getItem(position);
        //2.find or inflate the template
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        //3.find the subviews to fill with data in the template
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        //4.populate data into subviews
        tvUserName.setText(tweet.getUser().getScreenname());
        tvBody.setText(tweet.getBody());
        ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for a ...
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);

        //5.return the view to be inserted into the list
        return convertView;
    }

}
