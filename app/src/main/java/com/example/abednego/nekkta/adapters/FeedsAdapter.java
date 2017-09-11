package com.example.abednego.nekkta.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.data_holder.FeedsDataSet;

import java.util.List;

/**
 * Created by abedkiloo on 8/29/17.
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsView> {
    List<FeedsDataSet> feedsDataSetList;
    Context context;

    public FeedsAdapter(Context g_context, List<FeedsDataSet> g_feedsDataSetslist) {
        this.feedsDataSetList = g_feedsDataSetslist;
        this.context = g_context;
    }

    @Override
    public FeedsView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_feeds, null);

        FeedsView feedsView = new FeedsView(v);

        return feedsView;
    }

    @Override
    public void onBindViewHolder(FeedsView holder, int position) {
        FeedsDataSet feedsDataSet = feedsDataSetList.get(position);
        holder.feeds_header_tv.setText(feedsDataSet.getFeed_header_text());

        holder.header_icon.setImageResource(feedsDataSet.getHeader_icon() != 0 ? feedsDataSet.getHeader_icon() : R.mipmap.nekkta_icon);
        holder.feeds_header_time_counter.setText(feedsDataSet.getFeed_timer());
        holder.feeds_description_header_tv.setText(feedsDataSet.getFeed_header_description());
//        holder.feeds_tv_comments_counter.setText(feedsDataSet.getFeed_comments_counter());
//        holder.feeds_tv_likes_counter.setText(feedsDataSet.getFeed_like_counter());
        holder.feed_image_view.setImageResource(feedsDataSet.getFeed_image());
    }

    @Override
    public int getItemCount() {
        return feedsDataSetList.size();
    }

    public class FeedsView extends RecyclerView.ViewHolder {
        AppCompatTextView feeds_tv_comments_counter, feeds_tv_likes_counter,
                feeds_description_header_tv, feeds_header_tv, feeds_header_time_counter;
        AppCompatImageView feed_image_view, header_icon;

        public FeedsView(View itemView) {
            super(itemView);
            header_icon = itemView.findViewById(R.id.header_icon);
            feeds_header_time_counter = itemView.findViewById(R.id.feeds_timer);
            feeds_description_header_tv = itemView.findViewById(R.id.feeds_description_header);
            feeds_header_tv = itemView.findViewById(R.id.feeds_heading);
            feed_image_view = itemView.findViewById(R.id.feeds_image_view);
//            feeds_tv_comments_counter = itemView.findViewById(R.id.feeds_comment_tv_counter);
//            feeds_tv_likes_counter = itemView.findViewById(R.id.feeds_like_tv_counter);
        }
    }
}
