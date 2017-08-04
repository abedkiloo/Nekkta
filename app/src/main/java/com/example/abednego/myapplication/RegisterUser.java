package com.example.abednego.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser extends AppCompatActivity {

    RecyclerView register_reRecyclerView;
    LinearLayoutManager linearLayoutManager;
    CustomAdapter customAdapter;
    List<AnswerSet> answerSetList;
    AppCompatTextView questioner_tv;
    TextInputEditText typed_response_edit_text;
    AppCompatButton btn_done;
    LinearLayoutCompat reg_main_linear;
    Animation slide_left;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        //remove action bar
        getSupportActionBar().hide();

        //reference linear layout view
        reg_main_linear = findViewById(R.id.register_main_linear);
        //local braod cast receiver for user clicks
        LocalBroadcastManager.getInstance(getApplicationContext()).
                registerReceiver(user_response_receiver,
                        new IntentFilter(String.valueOf(R.string.custom_adapter_intent_values)));
        //load animation to slide right
        slide_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_left);

        //get reference of recycler view
        register_reRecyclerView = findViewById(R.id.recy_answers);
        register_reRecyclerView.setHasFixedSize(true);

        //get reference to question text view
        questioner_tv = findViewById(R.id.register_questioner);


        //get reference for the done button
        btn_done = findViewById(R.id.done_button);
        //get reference of user edit text for responses
        typed_response_edit_text = findViewById(R.id.typed_response);

        //layout manager
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        register_reRecyclerView.setLayoutManager(linearLayoutManager);

        //give list to adapter
        answerSetList = new ArrayList<>();
        customAdapter = new CustomAdapter(getApplicationContext(), answerSetList);
        //set adapter
        register_reRecyclerView.setAdapter(customAdapter);

        populateList();
    }


    private void populateList() {
        Log.e("DEBUG", "TRUE");
        AnswerSet answerSet;

        answerSet = new AnswerSet(getApplicationContext().getResources().getString(R.string.no_create_account));
        answerSetList.add(answerSet);

        answerSet = new AnswerSet(getApplicationContext().getResources().getString(R.string.yes_log_in));
        answerSetList.add(answerSet);

        customAdapter.notifyDataSetChanged();
    }

    public BroadcastReceiver user_response_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String user_response = intent.getStringExtra(String.valueOf(R.string.user_selection));
//            Toast.makeText(getContext(),user_response,Toast.LENGTH_SHORT).show();
            follow_up_action(user_response);
        }
    };

    private void follow_up_action(String user_response) {

        if (user_response.equals(getResources().getString(R.string.no_create_account))) {
            create_account();

        } else if (user_response.equals(getResources().getString(R.string.yes_log_in))) {
            login_account();

        }
    }

    private void login_account() {
        //hide the recyler
        register_reRecyclerView.setVisibility(View.GONE);


    }

    private void create_account() {
        //hide the recyler
        reg_main_linear.startAnimation(slide_left);
        slide_left.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                register_reRecyclerView.setVisibility(View.GONE);
                questioner_tv.setText(R.string.first_name_ask);
                typed_response_edit_text.setVisibility(View.VISIBLE);
                btn_done.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//                .animate()
//                .alpha(1.0f)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        super.onAnimationStart(animation);
//                        register_reRecyclerView.setVisibility(View.GONE);
//                        questioner_tv.setText(R.string.first_name_ask);
//                        typed_response_edit_text.setVisibility(View.VISIBLE);
//                        btn_done.setVisibility(View.VISIBLE);
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//
//                    }
//                });

        //watch user typing
        typed_response_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views));
                } else {
                    btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views_no_fill));

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views));
                } else {
                    btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views_no_fill));

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views));

            }
        });


    }
}
