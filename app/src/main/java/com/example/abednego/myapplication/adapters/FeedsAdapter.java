package com.example.abednego.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abednego.myapplication.R;
import com.example.abednego.myapplication.data_holder.FeedsDataSet;

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

    }

    @Override
    public int getItemCount() {
        return feedsDataSetList.size();
    }

    public class FeedsView extends RecyclerView.ViewHolder {
        public FeedsView(View itemView) {
            super(itemView);
        }
    }
}
