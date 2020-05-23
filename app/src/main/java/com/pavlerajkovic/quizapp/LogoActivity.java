package com.pavlerajkovic.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class LogoActivity extends AppCompatActivity {
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
    }

    public void checkQuestionOne() {
        EditText txtFirst = findViewById(R.id.txtLogo1);
        EditText txtSecond = findViewById(R.id.txtLogo2);

        EditText imgFirst = findViewById(R.id.imgLogo1);
        EditText imgSecond = findViewById(R.id.imgLogo2);
        EditText imgThird = findViewById(R.id.imgLogo3);

        EditText combFirst = findViewById(R.id.combLogo1);
        EditText combSecond = findViewById(R.id.combLogo2);

        // Check answers from 1st category.
        if (checkAnswers(txtFirst, 1))
            score++;
        if (!isCheating(txtFirst, txtSecond) && (checkAnswers(txtSecond, 1)))
            score++;

        // Check answers from 2nd category.
        if (checkAnswers(imgFirst, 2))
            score++;
        if (!isCheating(imgFirst, imgSecond) && (checkAnswers(imgSecond, 2)))
            score++;
        if (!isCheating(imgThird, imgFirst, imgSecond) && (checkAnswers(imgThird, 2)))
            score++;

        // Check answers from 3rd category.
        if (checkAnswers(combFirst, 3))
            score++;
        if (!isCheating(combFirst, combSecond) && (checkAnswers(combSecond, 3)))
            score++;
    }

    public void checkQuestionTwo() {
        RadioGroup radioGroup = findViewById(R.id.radioGroupTones);
        if (radioGroup.getCheckedRadioButtonId() == R.id.textual)
            score++;
    }

    public void checkQuestionThree() {
        CheckBox attention = findViewById(R.id.attention);
        CheckBox response = findViewById(R.id.response);
        CheckBox meaning = findViewById(R.id.meaning);
        CheckBox memory = findViewById(R.id.memory);

        if (attention.isChecked()) score++;
        if (response.isChecked()) score++;
        if (meaning.isChecked()) score++;
        if (memory.isChecked()) score++;
    }

    public boolean checkAnswer(EditText et1, EditText et2) {
        return et1.getText().toString().equalsIgnoreCase(et2.getText().toString());
    }

    public boolean checkAnswer(EditText editText, String answer) {
        return editText.getText().toString().equalsIgnoreCase(answer);
    }

    public boolean checkAnswers(EditText input, int category) {
        boolean b = false;

        if (category == 1)
            b = checkAnswers(input, "wordmark", "lettermark");
        else if (category == 2)
            b = checkAnswers(input, "brandmark", "abstract", "mascot");
        else if (category == 3)
            b = checkAnswers(input, "combination", "emblem");

        return b;
    }

    public boolean checkAnswers(EditText editText, String answer1, String answer2) {
        return checkAnswer(editText, answer1) || checkAnswer(editText, answer2);
    }

    public boolean checkAnswers(EditText editText, String answer1, String answer2, String answer3) {
        return checkAnswer(editText, answer1) ||
                checkAnswer(editText, answer2) ||
                checkAnswer(editText, answer3);
    }

    public boolean isCheating(EditText editText1, EditText editText2) {
        return checkAnswer(editText1, editText2);
    }

    public boolean isCheating(EditText editText1, EditText editText2, EditText editText3) {
        return isCheating(editText1, editText2) || isCheating(editText1, editText3);
    }

    public void submitAnswers(View view) {
        score = 0;

        checkQuestionOne();
        checkQuestionTwo();
        checkQuestionThree();

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(score);
    }
}
