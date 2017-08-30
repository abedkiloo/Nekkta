package com.example.abednego.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.abednego.myapplication.R;

public class Feeds extends AppCompatActivity {
RecyclerView feedRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        xml();

    }

    private void xml() {

        feedRecyclerView=findViewById(R.id.recycler_view_feeds);
    }


}
