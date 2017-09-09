package com.example.abednego.nekkta.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.data_holder.NekktaConfig;
import com.example.abednego.nekkta.data_holder.NekktaPreferences;

public class ProfileFragment extends Fragment {
    AppCompatTextView tv_nekkta_user_name;
    NekktaPreferences nekkta_pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, null);
        nekkta_pref = new NekktaPreferences(v.getContext());
        xml(v);
        tv_nekkta_user_name.setText(nekkta_pref.getPreferences(NekktaConfig.saved_user_name));
        return v;
    }

    private void xml(View v) {
        tv_nekkta_user_name = v.findViewById(R.id.account_names);
    }

}
