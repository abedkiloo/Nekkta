package com.example.abednego.nekkta.data_holder;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abedkiloo on 9/9/17.
 */

public class NekktaPreferences {
    public Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editorpref;
    String value_of_shared;
    public NekktaPreferences(Context c){
        this.context=c;
        sharedPreferences = c.getSharedPreferences(
                NekktaConfig.shared_prefs_user_details, Context.MODE_PRIVATE);


    }




    public final void putValuetoShared(String _key, String _value) {
        editorpref = sharedPreferences.edit();
        editorpref.putString(_key, _value);
        editorpref.apply();
    }


    public String getPreferences(String _key) {
         value_of_shared = sharedPreferences.getString(_key, NekktaConfig.pref_default);

        return value_of_shared;

    }

}
