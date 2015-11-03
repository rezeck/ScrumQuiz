package com.example.mateuspalhares.projetodeengenhariadesoftware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by mateuspalhares on 31/10/15.
 */
public class Question extends Activity implements View.OnClickListener{


    private MySQLiteHelper dbHelper;
    private List<QuestionModel> questionsList;
    private QuestionModel question;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private TextView questionTxt;
    int rank = 0;
    Random rand = new Random();
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState,  persistentState);
        setContentView(R.layout.perguntas);

        dbHelper = new MySQLiteHelper(this);
        //collecting all questions in the database
        questionsList = dbHelper.GetQuestions();
        //linking buttons/text with respectives id's
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        questionTxt = (TextView) findViewById(R.id.txtQuestion);
        //creating listeners on the choose buttons
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        //choosing a question

        question = questionsList.get(rand.nextInt(questionsList.size()-1));
        //setting text for a question on the screen
        questionTxt.setText(question.getPergunta());

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnA:
                if(question.getCorreta() == 1)
                    NextQuestion();
                else
                    GetRank();
                break;
            case R.id.btnB:
                if(question.getCorreta() == 2)
                    NextQuestion();
                else
                    GetRank();
                break;
            case R.id.btnC:
                if(question.getCorreta() == 3)
                    NextQuestion();
                else
                    GetRank();
                break;
            case R.id.btnD:
                if(question.getCorreta() == 4)
                    NextQuestion();
                else
                    GetRank();
                break;
        }
    }

    private void NextQuestion(){
        rank++;
        questionsList.remove(question);
        question = questionsList.get(rand.nextInt(questionsList.size()-1));
        questionTxt.setText(question.getPergunta());
    }

    private void GetRank(){
        Intent intent = new Intent(this,/*Rank activity*/);
        intent.add
        startActivity(intent);
    }
}