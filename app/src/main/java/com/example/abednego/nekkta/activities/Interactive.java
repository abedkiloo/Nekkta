package com.example.abednego.nekkta.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abednego.nekkta.R;


public class Interactive extends AppCompatActivity {
    //number of pages
    private static final int num_pages = 4;

    //the page to help in navigate to and from the fragments
    private ViewPager viewPager;


    //pager adapter to provide the pages to viewpager widget
    private PagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive);

        //initiate view pager and pageradapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(new SlidePagerAdapter(getSupportFragmentManager()));

    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        }

    }

    private class SlidePagerAdapter extends FragmentPagerAdapter {
        private SlidePagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new Register();
        }

        @Override
        public int getCount() {
            return num_pages;
        }


    }
}
