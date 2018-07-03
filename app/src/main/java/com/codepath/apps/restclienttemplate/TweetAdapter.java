package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

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

        Glide.with(context)
                .load(tweet.user.profileImageUrl) . into(holder.ivProfileImage);
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


        /*
        ImageView ivProfileImage;
        TextView tvUsername;
        TextView tvBody;
        */


        public ViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

           /* ivProfileImage=(ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvBody=(TextView) itemView.findViewById(R.id.tvBody);
            tvUsername=(TextView) itemView.findViewById(R.id.tvUserName);*/

        }

    }
}