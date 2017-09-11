package com.example.abednego.nekkta.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.activities.NewBucketActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class BucketFragment extends Fragment {

    Map<String, ArrayList<String>> buckets = new HashMap<String, ArrayList<String>>();
    ArrayList<String> group_buckets;
    ArrayList<String> trending;
    ArrayList<String> my_buckets;


    private SectionedRecyclerViewAdapter sectionAdapter;
    private FloatingActionButton new_bucketFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bucket, null);
        sectionAdapter = new SectionedRecyclerViewAdapter();


        my_buckets = new ArrayList<>();
        my_buckets.add("Laptop Project");
        my_buckets.add("Birthday Outing");

        group_buckets = new ArrayList<>();
        group_buckets.add("Road Trip");
        group_buckets.add("Mzera's Birthday");
        group_buckets.add("Launch Dinner");

        trending = new ArrayList<>();
        trending.add("#pizza fest");
        trending.add("#roadtrip");


        buckets.put("Trending", trending);
        buckets.put("My Buckets", my_buckets);
        buckets.put("Group buckets", group_buckets);


//        for (String value : list) {
//            List<String> valuees = getContactsWithPhrase(value);
//            if (valuees.size() > 0) {
//                sectionAdapter.addSection(new ContactsSection(String.valueOf(value), valuees));
//
//            }
//        }
        List<String> valuees;
        int counter = 0;
        for (Map.Entry<String, ArrayList<String>> entry : buckets.entrySet()) {
            valuees = getBucketValues(entry.getKey());
            if (valuees.size() > 0) {
                sectionAdapter.addSection(new ContactsSection(String.valueOf(entry.getKey()), valuees));
            }


        }
//for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
//            List<String> contacts = getContactsWithLetter(alphabet);
//
//            if (contacts.size() > 0) {
//                sectionAdapter.addSection(new ContactsSection(String.valueOf(alphabet), contacts));
//            }
//        }

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.buckets_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);


        new_bucketFloatingActionButton = v.findViewById(R.id.new_bucket_floating);
        new_bucketFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 *   get fragment manager & transaction
                 */
                getActivity().startActivity(new Intent(getContext(), NewBucketActivity.class));

//                fragmentManager = getActivity().getSupportFragmentManager();

//                // intialize the bucket fragment
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.bucket_frag, new NewBucketActivity());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

        ;
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(R.string.dashboard);
        }
    }

    private List<String> getBucketValues(String value) {
        List<String> got_buckets = new ArrayList<>();


        for (Map.Entry<String, ArrayList<String>> entry : buckets.entrySet()) {
            int num = 0;
            if (entry.getKey().contains(value)) {
                for (String val : entry.getValue()) {
                    got_buckets.add(val);
                }
            }
        }

        return got_buckets;
    }

    private class ContactsSection extends StatelessSection {

        String title;
        List<String> list;

        ContactsSection(String title, List<String> list) {
            super(new SectionParameters.Builder(R.layout.bucket_list_item)
                    .headerResourceId(R.layout.buckets_header)
                    .build());

            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name = list.get(position);

            itemHolder.tvItem.setText(name);
            itemHolder.tvItemNumber.setText("10%");
//            itemHolder.imgItem.setImageResource(name.hashCode() % 2 == 0 ? R.drawable.not_liked_drawable : R.drawable.unarchive_drawable);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvItem;
        private final TextView tvItemNumber;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvItem = view.findViewById(R.id.tvItem);
            tvItemNumber = view.findViewById(R.id.tvItemNumber);
        }
    }


}