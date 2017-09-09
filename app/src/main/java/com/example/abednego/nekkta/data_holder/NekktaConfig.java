package com.example.abednego.nekkta.data_holder;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.abednego.nekkta.R;

/**
 * Created by abedkiloo on 9/9/17.
 */

public class NekktaConfig {
    Context context;
   public static final String shared_prefs_user_details = "NEKKTA_USER";



    /**
     * registration strings
     */
    public static final String pref_default = "no_value";
    public static final String key_sign_up_user_name = "nekt_user_name";
    public static final String key_sign_up_user_password = "nekt_user_password";
    public static final String key_sign_up_user_email = "nekt_user_email";
    public static final String SIGN_UP_URL = "http://abedkiloo.com/nekkta/registerNekktaUser.php";


    /**
     * sign in details
     */
    public static final String key_sign_in_user_name_email="nekt_user_email_name";
    public static final String key_sign_in_user_password="nekt_user_password";
    public static final String SIGN_IN_URL = "http://abedkiloo.com/nekkta/getNekktaUser.php";

    /**
     * register values to shared preferences
     */
    public static final String saved_user_name = "saved_user_name";
    public static final String saved_user_email = "saved_user_email";


 /**
  * some responses got from serve
  */





}
