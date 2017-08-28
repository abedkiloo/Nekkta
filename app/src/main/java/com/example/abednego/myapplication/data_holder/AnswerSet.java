package com.example.abednego.myapplication.data_holder;

/**
 * Created by abednego on 8/3/17.
 */

public class AnswerSet {
    public AnswerSet(){}
    public AnswerSet(String answer){
        this.string_answer=answer;
    }
    public String getString_answer() {
        return string_answer;
    }

    public void setString_answer(String string_answer) {
        this.string_answer = string_answer;
    }

    public String string_answer;
}
