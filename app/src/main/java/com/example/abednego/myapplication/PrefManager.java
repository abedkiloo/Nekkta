package com.example.abednego.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abednego on 8/2/17.
 */

public class PrefManager {
    //shared preference object
    public SharedPreferences sharedPreferences;
    //shared preference editor object
    public  SharedPreferences.Editor editor;
    //context of the class
    Context pref_context;

    //private mode
    int PRIVATE_MODE=0;
    //shared pref file
    private static final String PREF_NAME="nektta_welcome";

    //first time launch
    private static final String IS_FIRST_TIME_LAUNCH="first_time_launch";

    public PrefManager(Context context){
        this.pref_context=context;
        sharedPreferences=pref_context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);

    }
    public void set_first_time_launch(boolean first_time_launch){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,first_time_launch);
    }
    public boolean is_first_time_launch(){
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}
