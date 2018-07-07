package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetAdapter extends  RecyclerView.Adapter<TweetAdapter.ViewHolder>{

    private  List<Tweet> mTweets;
    Context context;
    //pass in the Tweet array
    public TweetAdapter(List<Tweet> tweets){
        mTweets = tweets;
    }

    //for each row inflate the Layout and pass them to the ViewHolder

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet,parent,false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return  viewHolder;
    }


    //bind the values based on the position of the elemtent

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get data according to position
        Tweet tweet = mTweets.get(position);

        //populate the view according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvTime.setText(getRelativeTimeAgo(tweet.createdAt));
        holder.tvFav.setText(tweet.favs);
        holder.tvScreen.setText(tweet.user.screenName);
        holder.tvRet.setText(tweet.retweets);

        Glide.with(context)
                .load(tweet.user.profileImageUrl).into(holder.ivProfileImage);

    }


    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //create ViewHolder class
    public  static  class ViewHolder extends RecyclerView.ViewHolder {
        //perform findViewById lookups using butterknife

        @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
        @BindView(R.id.tvUserName) TextView tvUsername;
        @BindView(R.id.tvBody)  TextView tvBody;
        @BindView(R.id.tvTime)  TextView tvTime;
        @BindView(R.id.tvFav)  TextView tvFav;
        @BindView(R.id.tvRet)  TextView tvRet;
        @BindView(R.id.tvScreen)  TextView tvScreen;

        /* alternative version without butterknife
        ImageView ivProfileImage;
        TextView tvUsername;
        TextView tvBody;
        */

        //@OnClick()

        public ViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}