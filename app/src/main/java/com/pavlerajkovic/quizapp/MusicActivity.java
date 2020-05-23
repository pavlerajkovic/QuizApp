package com.pavlerajkovic.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }

    public void checkAnswers(View view) {

        double score = 0;

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int chosenButtonId = radioGroup.getCheckedRadioButtonId();
        if (chosenButtonId != -1) {
            RadioButton chosenButton = (RadioButton) findViewById(chosenButtonId);
            if (chosenButton.getText().toString().equals("Semitone"))
                score++;
        }

        EditText entryOne = (EditText) findViewById(R.id.edit_one);
        EditText entryTwo = (EditText) findViewById(R.id.edit_two);

        CheckBox notes = (CheckBox) findViewById(R.id.notes_checkbox);
        CheckBox pitch = (CheckBox) findViewById(R.id.pitch_checkbox);
        CheckBox rhythm = (CheckBox) findViewById(R.id.rhythm_checkbox);
        CheckBox harmony = (CheckBox) findViewById(R.id.harmony_checkbox);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress_bar);
        Switch trueFalse = (Switch) findViewById(R.id.true_false);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        if (entryOne.getText().toString().equalsIgnoreCase("bass")) score += 0.5;
        if (entryTwo.getText().toString().equalsIgnoreCase("treble")) score += 0.5;

        if (notes.isChecked()) score += 0.25;
        if (pitch.isChecked()) score += 0.25;
        if (rhythm.isChecked()) score += 0.25;
        if (harmony.isChecked()) score += 0.25;

        if (spinner.getSelectedItem().toString().equals("Semibreve")) score++;
        if (trueFalse.isChecked()) score++;
        if (ratingBar.getRating() == 5) score++;

        progress.setProgress((int) score);
        Toast.makeText(this, "You have " + score + " points.", Toast.LENGTH_LONG).show();
    }
}