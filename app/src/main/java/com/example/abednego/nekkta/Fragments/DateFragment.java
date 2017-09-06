package com.example.abednego.nekkta.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.abednego.nekkta.R;
import com.example.abednego.nekkta.activities.NewBucketActivity;

import java.util.Calendar;

public class DateFragment extends Fragment {
    DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.date_picker, null);
        datePicker = v.findViewById(R.id.date_picker_dialog);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Intent dateIntent = new Intent(getContext(), NewBucketActivity.class);
                        dateIntent.putExtra("YEAR", String.valueOf(year));
                        dateIntent.putExtra("MONTH", String.valueOf(month));
                        dateIntent.putExtra("DAY", String.valueOf(dayOfMonth));
                        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getActivity().startActivity(dateIntent);
//                        typed_response_edit_text.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                });
        return v;
    }

}
