package com.example.abednego.nekkta.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.adapters.FeedsAdapter;
import com.example.abednego.nekkta.data_holder.FeedsDataSet;

import java.util.ArrayList;
import java.util.List;

public class FeedsFragment extends Fragment {
    RecyclerView feedRecyclerView;
    LinearLayoutManager linearLayoutManager;
    List<FeedsDataSet> feedsDataSetList;
    FeedsAdapter feedsAdapter;
    FeedsDataSet feedsDataSet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_feeds, null);
        xml(v);
        populate_list();
        return v;
    }

    private void populate_list() {
        feedsDataSet = new FeedsDataSet();
        feedsDataSet.setHeader_icon(R.mipmap.nekkta_icon);
        feedsDataSet.setFeed_header_text("Nekkta Community Party");
        feedsDataSet.setFeed_header_description("Come and be the first in the lauching of the nekkta community where " +
                "elegance meets fruits of hard with saving");
        feedsDataSet.setFeed_timer("2d");
        feedsDataSet.setFeed_image(R.mipmap.welcome_screen);
        feedsDataSet.setFeed_like_counter("23 Likes");
        feedsDataSet.setFeed_comments_counter("103 Comments");
        feedsDataSetList.add(feedsDataSet);

        feedsDataSet = new FeedsDataSet();
        feedsDataSet.setHeader_icon(R.mipmap.pic1);
        feedsDataSet.setFeed_header_text("Ololua Walk");
        feedsDataSet.setFeed_header_description("idden away in the up market suburb of Karen is 250 hectares " +
                "of the indigenous tropical dry Oloolua forest that is home to the Institute of Primate Research (IPR");
        feedsDataSet.setFeed_timer("2d");
        feedsDataSet.setFeed_image(R.mipmap.ololua);
        feedsDataSet.setFeed_like_counter("23 Likes");
        feedsDataSet.setFeed_comments_counter("103 Comments");
        feedsDataSetList.add(feedsDataSet);

        feedsDataSet = new FeedsDataSet();
        feedsDataSet.setHeader_icon(R.mipmap.mtkenya);
        feedsDataSet.setFeed_header_text("Mt Kenya Hike");
        feedsDataSet.setFeed_header_description("Mount Kenya is a stratovolcano created approximately 3 million years after the opening of the East African rift." +
                "Before glaciation, it was 7,000 m (23,000 ft) high. It was covered by an ice cap for thousands of years.");
        feedsDataSet.setFeed_timer("7d");
        feedsDataSet.setFeed_image(R.mipmap.mtkenya);
        feedsDataSet.setFeed_like_counter("76 Likes");
        feedsDataSet.setFeed_comments_counter("112 Comments");
        feedsDataSetList.add(feedsDataSet);

        feedsAdapter.notifyDataSetChanged();
    }

    private void xml(View v) {

        feedRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_feeds);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        feedRecyclerView.setLayoutManager(linearLayoutManager);

        feedsDataSetList = new ArrayList<>();
        feedsAdapter = new FeedsAdapter(getActivity().getApplicationContext(), feedsDataSetList);
        feedRecyclerView.setAdapter(feedsAdapter);


    }


}
