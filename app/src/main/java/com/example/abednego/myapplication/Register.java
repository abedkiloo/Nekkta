package com.example.abednego.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Register extends Fragment {
    RecyclerView register_reRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CustomAdapter customAdapter;
    List<AnswerSet> answerSetList;
    AppCompatTextView questioner_tv;
    TextInputEditText typed_response_edit_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //remove action bar
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        //local braod cast receiver for user clicks
        LocalBroadcastManager.getInstance(getContext()).
                registerReceiver(user_response_receiver,
                        new IntentFilter(String.valueOf(R.string.custom_adapter_intent_values)));

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.register_fragment, container, false);

        //get reference of recycler view
        register_reRecyclerView = rootView.findViewById(R.id.recy_answers);
        register_reRecyclerView.setHasFixedSize(true);

        //get reference to question text view
        questioner_tv=rootView.findViewById(R.id.register_questioner);

        //get reference of user edit text for responses
        typed_response_edit_text=rootView.findViewById(R.id.typed_response);

        //layout manager
        linearLayoutManager = new LinearLayoutManager(getContext());
        register_reRecyclerView.setLayoutManager(linearLayoutManager);

        //give list to adapter
        answerSetList=new ArrayList<>();
        customAdapter=new CustomAdapter(getContext(),answerSetList);
        //set adapter
        register_reRecyclerView.setAdapter(customAdapter);

        populateList();

        return rootView;
    }

    private void populateList() {
        Log.e("DEBUG","TRUE");
        AnswerSet answerSet;

        answerSet=new AnswerSet(getContext().getResources().getString(R.string.no_create_account));
        answerSetList.add(answerSet);

        answerSet=new AnswerSet(getContext().getResources().getString(R.string.yes_log_in));
        answerSetList.add(answerSet);

        customAdapter.notifyDataSetChanged();
    }
    public BroadcastReceiver user_response_receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String user_response=intent.getStringExtra(String.valueOf(R.string.user_selection));
//            Toast.makeText(getContext(),user_response,Toast.LENGTH_SHORT).show();
            follow_up_action(user_response);
        }
    };

    private void follow_up_action(String user_response) {
        
        if(user_response.equals(getContext().getResources().getString(R.string.no_create_account))){
            create_account();

        }else if(user_response.equals(getContext().getResources().getString(R.string.yes_log_in))){
            login_account();

        }
    }
    private void login_account() {
        //hide the recyler
        register_reRecyclerView.setVisibility(View.GONE);


    }

    private void create_account() {
        //hide the recyler
        register_reRecyclerView.setVisibility(View.GONE);
        questioner_tv.setText(R.string.first_name_ask);
        typed_response_edit_text.setVisibility(View.VISIBLE);


    }
}

