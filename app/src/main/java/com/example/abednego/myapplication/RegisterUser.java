package com.example.abednego.myapplication;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PatternMatcher;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    HashMap<String, String> user_responses_hash_map;
    String user_names_from_hash_map;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        //remove action bar
        getSupportActionBar().hide();


        //intialze empty hash map
        user_responses_hash_map = new HashMap<>();

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

        //get reference to date picker
        datePicker = findViewById(R.id.date_picker_dialog);
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
        register_reRecyclerView.setVisibility(View.GONE);
        questioner_tv.setText(R.string.first_name_ask);
        typed_response_edit_text.setVisibility(View.VISIBLE);
        btn_done.setVisibility(View.VISIBLE);


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question_value = questioner_tv.getText().toString();
                final String answer_value = typed_response_edit_text.getText().toString();
                if (check_input_typed(answer_value)) {
                    if (question_value.equals(getResources().getString(R.string.first_name_ask))) {
                        storage_answer(getResources().getString(R.string.first_name_placeholder), answer_value);
                        reset_button();
                        questioner_tv.setText(R.string.second_name_ask);
                    } else if (question_value.equals(getResources().getString(R.string.second_name_ask))) {

                        storage_answer(getResources().getString(R.string.second_name_placeholder), answer_value);
                        typed_response_edit_text.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        questioner_tv.setText(R.string.email_ask);
                        reset_button();
                    } else if (question_value.equals(getResources().getString(R.string.email_ask))) {
                        if (verify_input(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, answer_value)) {
                            storage_answer(getResources().getString(R.string.email_placeholder), answer_value);

                            //changing values inserting names from the hash map
                            user_names_from_hash_map = "Okay " +
                                    user_responses_hash_map.get(getResources().getString(R.string.first_name_placeholder)) +
                                    " " +
                                    user_responses_hash_map.get(getResources().getString(R.string.second_name_placeholder)) +
                                    ", set up Password. \n Make sure it has at least" +
                                    " one lower case letter and upper case " +
                                    "and a number";

                            questioner_tv.setText(user_names_from_hash_map);
                            typed_response_edit_text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            reset_button();
                        }
                    } else if (question_value.equals(user_names_from_hash_map)) {
//                        if (verify_input(InputType.TYPE_TEXT_VARIATION_PASSWORD, answer_value)) {
                        storage_answer(getResources().getString(R.string.password_placeholder), answer_value);
                        questioner_tv.setText(R.string.phone_ask);
                        typed_response_edit_text.setInputType(InputType.TYPE_CLASS_PHONE);
                        reset_button();
                    } else if (question_value.equals(getResources().getString(R.string.phone_ask))) {

                        storage_answer(getResources().getString(R.string.phone_number_placeholder), answer_value);
                        reset_button();
                        questioner_tv.setText(R.string.age_ask);
                        datePicker.setVisibility(View.VISIBLE);

                        create_calendar();


                    } else if (question_value.equals(getResources().getString(R.string.age_ask))) {
                        datePicker.setVisibility(View.GONE);
                        storage_answer(getResources().getString(R.string.age), answer_value);

                    }
                }
//                }

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
                    btn_done.setTextColor(getResources().getColor(R.color.white));
                } else {
                    btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views_no_fill));

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views));
                    btn_done.setTextColor(getResources().getColor(R.color.white));
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

    private void create_calendar() {
        // Use the current date as the default date in the picker
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                        typed_response_edit_text.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                });
    }

    private boolean verify_input(int typeText, String answer_value) {
        boolean bool_value = false;
        switch (typeText) {
            case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                if (!Patterns.EMAIL_ADDRESS.matcher(answer_value).matches()) {
                    typed_response_edit_text.setError(getResources().getString(R.string.email_error));
                    bool_value = false;
                } else {
                    bool_value = true;
                }
                break;
            case InputType.TYPE_TEXT_VARIATION_PASSWORD:
                String regex_password = ("[a-zA-Z]+[A-Z]+[0-9]");
//                Pattern.compile("[a-zA-Z0-9]*[0-9]*[a-z]");
                if (!Pattern.compile(answer_value).matcher(regex_password).matches()) {
                    typed_response_edit_text.setError(getResources().getString(R.string.password_error));
                    bool_value = false;
                } else {
                    bool_value = true;
                }
                break;
        }

        return bool_value;
    }

    private boolean check_input_typed(String answer_value) {
        if (answer_value.equals("")) {
            typed_response_edit_text.setError("Cannot be empty");
            return false;
        }
        return true;
    }

    private void reset_button() {
        typed_response_edit_text.setText("");
        btn_done.setBackground(getResources().getDrawable(R.drawable.custom_text_views_no_fill));
        btn_done.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void storage_answer(String answer_key, String user_response) {
        user_responses_hash_map.put(answer_key, user_response);
        Log.e("USER_RESPONSE", user_responses_hash_map.get(answer_key));

    }


}
