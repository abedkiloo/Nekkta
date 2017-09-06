package com.example.abednego.nekkta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.abednego.nekkta.R;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.facebook.CallbackManager;


public class SignIn extends AppCompatActivity {
    AppCompatTextView sign_up_here, fb_sign;
    // [START declare_auth]
//    FirebaseAuth firebaseAuth;

    // [END declare_auth]
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        /**
         * referencing all xml elements
         */
        xml_elements();


        //firebase authorization initialization
//        firebaseAuth = FirebaseAuth.getInstance();
        // [START initialize_fblogin]
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();

        //clicked sign up here
        fb_sign_in_details();
        sign_up_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });
    }

    /**
     * in clude all details dealing with fb sign in
     */
    private void fb_sign_in_details() {

//        fb_sign.setReadPermissions("email", "public_profile");
//        fb_sign.registerCallBack(mCallbackManager, new FacebookCallback<>() {
//            @Override
//            public void onSuccess(Object o) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        })

    }

    private void xml_elements() {
//        fb_sign = findViewById(R.id.fb_text_view_sign_in);
        sign_up_here = findViewById(R.id.text_view_sign_up_here);

    }

    public void sign_in(View view) {
        startActivity(new Intent(getApplicationContext(), Landing.class));

    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if the user is signed in (non-null)
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI();
    }

    private void updateUI() {


    }
}
