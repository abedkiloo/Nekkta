package com.example.abednego.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abednego.myapplication.R;
import com.example.abednego.myapplication.data_holder.AnswerSet;

import java.util.List;

/**
 * Created by abednego on 8/3/17.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.AnswerHolder> {
    public Context custom_adapter_context;
    public List<AnswerSet> custom_adapter_sanswer_set_list;

    public CustomAdapter(Context context,List<AnswerSet> answerSetList){
        this.custom_adapter_context=context;
        this.custom_adapter_sanswer_set_list=answerSetList;

    }
    @Override
    public AnswerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_answer_view,null);


        AnswerHolder answerHolder=new AnswerHolder(v);

        return answerHolder;
    }

    @Override
    public void onBindViewHolder(AnswerHolder holder, int position) {

        AnswerSet answerSet=custom_adapter_sanswer_set_list.get(position);

        holder.answer_holder_tv.setText(answerSet.getString_answer());
    }

    @Override
    public int getItemCount() {
        return custom_adapter_sanswer_set_list.size();
    }

    public class AnswerHolder extends RecyclerView.ViewHolder{
        public AppCompatTextView answer_holder_tv;
        public AnswerHolder(View itemView) {
            super(itemView);
            answer_holder_tv=itemView.findViewById(R.id.custom_recycler_answer_tv);
            answer_holder_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user_answer=answer_holder_tv.getText().toString();
//                    Toast.makeText(view.getContext(),answer_holder_tv.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent click_response=new Intent(String.valueOf(R.string.custom_adapter_intent_values));
                    click_response.putExtra(String.valueOf(R.string.user_selection),user_answer);
                    LocalBroadcastManager.getInstance(custom_adapter_context).sendBroadcast(click_response);
                }
            });
        }
    }
}
