package com.example.abednego.nekkta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abednego.nekkta.Fragments.BucketFragment;
import com.example.abednego.nekkta.Fragments.CommunityFragment;
import com.example.abednego.nekkta.Fragments.FeedsFragment;
import com.example.abednego.nekkta.Fragments.ProfileFragment;
import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.data_holder.NekktaConfig;
import com.example.abednego.nekkta.data_holder.NekktaPreferences;

import java.util.ArrayList;
import java.util.List;

public class Landing extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NekktaPreferences nekktaPreferences;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        nekktaPreferences = new NekktaPreferences(getApplicationContext());
        if (nekktaPreferences.getPreferences(NekktaConfig.saved_user_name).equals("no_value")) {
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        actionBar.setTitle("Dashboard");
                        break;
                    case 1:
                        actionBar.setTitle("Buckets");
                        break;
                    case 2:
                        actionBar.setTitle("Community");
                        break;
                    case 3:
                        actionBar.setTitle("Profile");
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

                actionBar.setTitle(getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setTabIcons();
    }

    public void setTabIcons() {
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.feeds_drawable));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.buckets_drawable));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.community_drawable));
        tabLayout.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.account_drawable));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FeedsFragment(), getResources().getString(R.string.trending_suggestions));
        adapter.addFragment(new BucketFragment(), getResources().getString(R.string.title_bucket));
        adapter.addFragment(new CommunityFragment(), getResources().getString(R.string.title_community));
        adapter.addFragment(new ProfileFragment(), getResources().getString(R.string.title_profile));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
//            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
