package com.example.abednego.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abednego.myapplication.R;
import com.example.abednego.myapplication.adapters.FeedsAdapter;
import com.example.abednego.myapplication.data_holder.FeedsDataSet;

import java.util.ArrayList;
import java.util.List;

public class Feeds extends AppCompatActivity {
    RecyclerView feedRecyclerView;
    LinearLayoutManager linearLayoutManager;
    List<FeedsDataSet> feedsDataSetList;
    FeedsAdapter feedsAdapter;
    FeedsDataSet feedsDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        xml();

        populate_list();
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

    private void xml() {

        feedRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_feeds);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        feedRecyclerView.setLayoutManager(linearLayoutManager);

        feedsDataSetList = new ArrayList<>();
        feedsAdapter = new FeedsAdapter(getApplicationContext(), feedsDataSetList);
        feedRecyclerView.setAdapter(feedsAdapter);


    }


}
