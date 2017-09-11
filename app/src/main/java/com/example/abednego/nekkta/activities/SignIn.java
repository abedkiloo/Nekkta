package com.example.abednego.nekkta.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.data_holder.NekktaConfig;
import com.example.abednego.nekkta.data_holder.NekktaPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//import com.google.firebase.auth.FirebaseAuth;

//import com.facebook.CallbackManager;


public class SignIn extends AppCompatActivity implements View.OnClickListener {
    AppCompatTextView sign_up_here, fb_sign;
    AppCompatButton sign_in_AppCompatButton;
    TextInputEditText edt_user_name_email, edt_user_password;
    String string_user_name, string_user_password;
    ProgressBar sign_in_progressBar;


    NekktaPreferences nekktaPreferences;
    // [START declare_auth]
//    FirebaseAuth firebaseAuth;

    // [END declare_auth]
//    private CallbackManager mCallbackManager
// ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        /**
         * referencing all xml elements
         */
        xml_elements();
        nekktaPreferences = new NekktaPreferences(getApplicationContext());


        //firebase authorization initialization
//        firebaseAuth = FirebaseAuth.getInstance();
        // [START initialize_fblogin]
        // Initialize Facebook Login button
//        mCallbackManager = CallbackManager.Factory.create();

        //clicked sign up here
        fb_sign_in_details();
        sign_up_here.setOnClickListener(this);
        sign_in_AppCompatButton.setOnClickListener(this);
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
        edt_user_name_email = findViewById(R.id.sign_in_user_name_email);
        edt_user_password = findViewById(R.id.sign_in_password);
        sign_in_AppCompatButton = findViewById(R.id.sign_in_btn_sign_in);
        sign_in_progressBar = findViewById(R.id.sign_in_progress);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_sign_up_here:
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                break;
            case R.id.sign_in_btn_sign_in:
                sign_in_user();
                break;
        }

    }

    private void sign_in_user() {
        sign_in_progressBar.setIndeterminate(true);
        string_user_name = edt_user_name_email.getText().toString();
        string_user_password = edt_user_password.getText().toString();
        if (check_empty(string_user_name, edt_user_name_email) || check_empty(string_user_password, edt_user_password)) {
            make_server_request();
        }

    }

    private void make_server_request() {
        sign_in_progressBar.setVisibility(View.VISIBLE);
        StringRequest sign_in_req = new StringRequest(Request.Method.POST, NekktaConfig.SIGN_IN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jresponse = new JSONObject(response);
                    JSONArray jarray = jresponse.getJSONArray("get_user");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject server_objects = jarray.getJSONObject(i);
                        Log.e("HERE", "YES");
                        Log.e("HERE", response);
                        if (server_objects.get("error").toString().equals("0")) {
                            nekktaPreferences.putValuetoShared(NekktaConfig.saved_user_name,
                                    server_objects.get("user_name").toString());
                            nekktaPreferences.putValuetoShared(NekktaConfig.saved_user_email,
                                    server_objects.get("user_email").toString());
                            sign_in_progressBar.setVisibility(View.GONE);

                            startActivity(new Intent(getApplicationContext(), Landing.class));
                        } else {
                            Toast.makeText(SignIn.this, "Failed SignIn Please Try Again with correct values", Toast.LENGTH_SHORT).show();
                            sign_in_progressBar.setVisibility(View.GONE);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sign_in_progressBar.setVisibility(View.GONE);
                Toast.makeText(SignIn.this, "Failed SignIn Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(NekktaConfig.key_sign_in_user_name_email, string_user_name);
                params.put(NekktaConfig.key_sign_in_user_password, string_user_password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(sign_in_req);
    }

    private boolean check_empty(String text_, TextInputEditText _editText) {
        if (text_.equals("")) {
            _editText.setError("Cannot be empty");
            return false;
        }
        return true;
    }
}
