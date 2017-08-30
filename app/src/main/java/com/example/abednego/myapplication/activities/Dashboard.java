package com.example.abednego.myapplication.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abednego.myapplication.Fragments.ProfileFragment;
import com.example.abednego.myapplication.Fragments.BucketFragment;
import com.example.abednego.myapplication.Fragments.CommunityFragment;
import com.example.abednego.myapplication.R;

public class Dashboard extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        /**
         *   get fragment manager & transaction
         */

        fragmentManager=getSupportFragmentManager();

        // intialize the bucket fragment
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dashcontent_content,new BucketFragment());
        fragmentTransaction.commit();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bucket:
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.dashcontent_content,new BucketFragment()).commit();
                    return true;
                case R.id.navigation_community:
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.dashcontent_content,new CommunityFragment()).commit();
//                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_account:
                    fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.dashcontent_content,new ProfileFragment()).commit();
//                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };
}
