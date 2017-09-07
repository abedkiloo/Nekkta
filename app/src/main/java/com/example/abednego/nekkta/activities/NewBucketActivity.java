package com.example.abednego.nekkta.activities;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.abednego.nekkta.Fragments.DateFragment;
import com.example.abednego.nekkta.R;

public class NewBucketActivity extends AppCompatActivity {
    AppCompatButton btn_select_target_time;
    TextInputEditText text_edit_year, text_edit_month, text_edit_day;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_new_bucket);

        //get reference to date picker

        btn_select_target_time = (AppCompatButton) findViewById(R.id.btn_select_target_time);
        text_edit_year = (TextInputEditText) findViewById(R.id.edit_target_year);
        text_edit_month = (TextInputEditText) findViewById(R.id.edit_target_month);
        text_edit_day = (TextInputEditText) findViewById(R.id.edit_target_day);
        btn_select_target_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RelativeLayout) findViewById(R.id.new_bucket_layout)).removeAllViews();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.new_bucket_layout, new DateFragment());
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("RESUME", "RESUME");

        intent = getIntent();

        if (intent.hasExtra("YEAR")) {
            text_edit_year.setText(intent.getStringExtra("YEAR"));
            text_edit_month.setText(intent.getStringExtra("MONTH"));
            text_edit_day.setText(intent.getStringExtra("DAY"));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public void btnCreate(View view) {
        startActivity(new Intent(getApplicationContext(), Feeds.class));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Landing.class));
    }
}
