package com.example.android.touristquiz;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int numOfRightAnswers = 0;
    int wrongAnswerColorId;

    ScrollView scrollView;
    CardView cardView;

    RadioButton goldenGateStrait;
    EditText londonClockTower;
    RadioButton beachAustralia;
    CheckBox libertyFrenchSculpture;
    CheckBox libertyPieces;
    CheckBox libertyYears;
    CheckBox libertyWeight;
    RadioButton eiffel600;
    RadioButton greatWallNo;
    RadioButton amusementFlorida;

    RatingBar ratingBar;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wrongAnswerColorId = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);

        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        cardView = (CardView) findViewById(R.id.bottom_card_view);

        goldenGateStrait = (RadioButton) findViewById(R.id.golden_gate_strait_radio_button);
        londonClockTower = (EditText) findViewById(R.id.london_tower_answer_edit_text);
        beachAustralia = (RadioButton) findViewById(R.id.beach_australia_radio_button);
        libertyFrenchSculpture = (CheckBox) findViewById(R.id.statue_of_liberty_french_sculptor_checkbox);
        libertyPieces = (CheckBox) findViewById(R.id.statue_of_liberty_number_of_pieces_checkbox);
        libertyYears = (CheckBox) findViewById(R.id.statue_of_liberty_completion_time_checkbox);
        libertyWeight = (CheckBox) findViewById(R.id.statue_of_liberty_weight_checkbox);
        eiffel600 = (RadioButton) findViewById(R.id.eiffel_tower_600_radio_button);
        greatWallNo = (RadioButton) findViewById(R.id.great_wall_no_radio_button);
        amusementFlorida = (RadioButton) findViewById(R.id.amusement_park_florida_radio_button);

        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        submitButton = (Button) findViewById(R.id.submit_button);
    }

    public void submitAnswers(View view) {
        checkGoldenGateAnswer();
        checkLondonTowerAnswer();
        checkBeachAnswer();
        checkLibertyStatueAnswer();
        checkEiffelTowerAnswer();
        checkGreatWallAnswer();
        checkAmusementAnswer();
        showResult();
    }

    private void showResult() {
        ratingBar.setRating(numOfRightAnswers);
        submitButton.setVisibility(View.GONE);
        ratingBar.setVisibility(View.VISIBLE);
        ratingBar.getParent().requestChildFocus(ratingBar, ratingBar);
        createToastMessage();
    }

    private void createToastMessage() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if (numOfRightAnswers == 7) {
            Toast.makeText(context, getString(R.string.result_toast_text_7, numOfRightAnswers),
                    duration).show();
        } else if (numOfRightAnswers > 3) {
            Toast.makeText(context, getString(R.string.result_toast_text_above_3, numOfRightAnswers),
                    duration).show();
        } else if (numOfRightAnswers > 1) {
            Toast.makeText(context, getString(R.string.result_toast_text_above_1, numOfRightAnswers),
                    duration).show();
        } else if (numOfRightAnswers == 1) {
            Toast.makeText(context, getString(R.string.result_toast_text_1, numOfRightAnswers),
                    duration).show();
        } else {
            Toast.makeText(context, getString(R.string.result_toast_text_0, numOfRightAnswers),
                    duration).show();
        }
    }

    private void checkGoldenGateAnswer() {
        if (goldenGateStrait.isChecked()) {
            numOfRightAnswers++;
        }
        ((RadioButton)findViewById(R.id.golden_gate_color_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton)findViewById(R.id.golden_gate_parts_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton)findViewById(R.id.golden_gate_decoration_radio_button)).setTextColor(wrongAnswerColorId);
    }

    private void checkLondonTowerAnswer() {
        String answer = londonClockTower.getText().toString().trim();
        String rightAnswer = getString(R.string.london_right_answer);

        if (answer.equalsIgnoreCase(rightAnswer)) {
            numOfRightAnswers++;
        }
        londonClockTower.setText(rightAnswer);
        londonClockTower.getBackground().setColorFilter(wrongAnswerColorId, PorterDuff.Mode.SRC_IN);
    }

    private void checkBeachAnswer() {
        if (beachAustralia.isChecked()) {
            numOfRightAnswers++;
        }
        ((RadioButton)findViewById(R.id.beach_brazil_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton)findViewById(R.id.beach_india_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton)findViewById(R.id.beach_usa_radio_button)).setTextColor(wrongAnswerColorId);
    }

    private void checkLibertyStatueAnswer() {
        if (libertyFrenchSculpture.isChecked() && libertyPieces.isChecked()
                && libertyYears.isChecked() && !libertyWeight.isChecked()) {
            numOfRightAnswers++;
        }
        libertyWeight.setTextColor(wrongAnswerColorId);
    }

    private void checkEiffelTowerAnswer() {
        if (eiffel600.isChecked()) {
            numOfRightAnswers++;
        }
        ((RadioButton) findViewById(R.id.eiffel_tower_6_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton) findViewById(R.id.eiffel_tower_60_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton) findViewById(R.id.eiffel_tower_6000_radio_button)).setTextColor(wrongAnswerColorId);
    }

    private void checkGreatWallAnswer() {
        if (greatWallNo.isChecked()) {
            numOfRightAnswers++;
        }
        ((RadioButton) findViewById(R.id.great_wall_yes_radio_button)).setTextColor(wrongAnswerColorId);
    }

    private void checkAmusementAnswer() {
        if (amusementFlorida.isChecked()) {
            numOfRightAnswers++;
        }
        ((RadioButton) findViewById(R.id.amusement_park_california_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton) findViewById(R.id.amusement_park_paris_radio_button)).setTextColor(wrongAnswerColorId);
        ((RadioButton) findViewById(R.id.amusement_park_tokyo_radio_button)).setTextColor(wrongAnswerColorId);
    }
}
