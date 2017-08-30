package com.example.abednego.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.example.abednego.myapplication.R;

public class SignUp extends AppCompatActivity {
    AppCompatTextView sign_in_here;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        /**
         * referencing all xml elements
         */
        xml_elements();

        //clicked sign up here
        sign_in_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });
    }
    private void xml_elements() {

        sign_in_here= (AppCompatTextView) findViewById(R.id.text_view_sign_in_here);
    }
}
