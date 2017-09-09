package com.example.abednego.nekkta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
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

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    AppCompatTextView sign_in_here;
    TextInputEditText edit_text_signup_email, edit_text_signup_user_name, edit_text_signup_user_paasword;
    AppCompatButton button_sign_up;
    String string_user_email, string_user_password, string_user_name;
    NekktaPreferences nekktaPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        /**
         * referencing all xml elements
         */
        xml_elements();
        nekktaPreferences = new NekktaPreferences(getApplicationContext());

        //clicked sign up here
        button_sign_up.setOnClickListener(this);
        sign_in_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });
    }

    private void xml_elements() {

        sign_in_here = findViewById(R.id.text_view_sign_in_here);
        edit_text_signup_email = findViewById(R.id.text_input_signup_email);
        edit_text_signup_user_name = findViewById(R.id.text_input_signup_user_name);
        edit_text_signup_user_paasword = findViewById(R.id.text_input_signup_user_password);
        button_sign_up = findViewById(R.id.btn_sign_up);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                normal_sign_up();
                break;

        }

    }

    private void normal_sign_up() {

        string_user_email = edit_text_signup_email.getText().toString();
        string_user_name = edit_text_signup_user_name.getText().toString();
        string_user_password = edit_text_signup_user_paasword.getText().toString();
        if (check_empty(string_user_email, edit_text_signup_email) ||
                check_empty(string_user_name, edit_text_signup_user_name) ||
                check_empty(string_user_password, edit_text_signup_user_paasword)) {
            make_server_request();
        }
    }

    private void make_server_request() {

        StringRequest sign_up_req = new StringRequest(Request.Method.POST, NekktaConfig.SIGN_UP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jresponse = new JSONObject(response);
                    JSONArray jarray = jresponse.getJSONArray("register_user");
                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject server_objects = jarray.getJSONObject(i);
                        if (server_objects.get("error").toString().equals("0")) {
                            nekktaPreferences.putValuetoShared(NekktaConfig.saved_user_name,
                                    string_user_name);
                            nekktaPreferences.putValuetoShared(NekktaConfig.saved_user_email,
                                    string_user_email);

                            startActivity(new Intent(getApplicationContext(), Landing.class));
                        } else {
                            Toast.makeText(SignUp.this, "Failed SignUp Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUp.this, "Failed SignUp Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(NekktaConfig.key_sign_up_user_name, string_user_name);
                params.put(NekktaConfig.key_sign_up_user_password, string_user_password);
                params.put(NekktaConfig.key_sign_up_user_email, string_user_email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(sign_up_req);
    }

    private boolean check_empty(String text_, TextInputEditText _editText) {
        if (text_.equals("")) {
            _editText.setError("Cannot be empty");
            return false;
        }
        return true;
    }


}
