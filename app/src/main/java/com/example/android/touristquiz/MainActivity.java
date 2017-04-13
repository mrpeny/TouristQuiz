package com.example.android.touristquiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // General Global variables
    int numOfRightAnswers = 0;
    int wrongAnswerColorId;
    int rightAnswerColorId;

    // View needed for navigation, setting focus on it when needed
    CardView topCardView;

    // Content elements
    LinearLayout linearLayoutMain;
    RadioButton goldenGateStrait;
    EditText londonClockTower;
    String rightAnswer;
    RadioButton beachAustralia;
    CheckBox libertyFrenchSculpture;
    CheckBox libertyPieces;
    CheckBox libertyYears;
    CheckBox libertyWeight;
    RadioButton eiffel600;
    RadioButton greatWallNo;
    RadioButton amusementFlorida;

    // Bottom CardView elements
    RatingBar ratingBar;
    Button submitButton;
    Button showAnswersButton;
    Button restartButton;

    // List of the wrong answers
    List<Button> wrongAnswerButtonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wrongAnswerColorId = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
        rightAnswerColorId = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        topCardView = (CardView) findViewById(R.id.top_card_view);

        linearLayoutMain = (LinearLayout) findViewById(R.id.linear_layout_main);
        goldenGateStrait = (RadioButton) findViewById(R.id.golden_gate_strait_radio_button);
        londonClockTower = (EditText) findViewById(R.id.london_tower_answer_edit_text);
        rightAnswer = getString(R.string.london_right_answer);
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
        showAnswersButton = (Button) findViewById(R.id.show_answers_button);
        restartButton = (Button) findViewById(R.id.restart_button);

        Button[] wrongAnswerButtons = {(RadioButton) findViewById(R.id.golden_gate_color_radio_button)
                , (RadioButton) findViewById(R.id.golden_gate_parts_radio_button)
                , (RadioButton) findViewById(R.id.golden_gate_decoration_radio_button)
                , (RadioButton) findViewById(R.id.beach_brazil_radio_button)
                , (RadioButton) findViewById(R.id.beach_india_radio_button)
                , (RadioButton) findViewById(R.id.beach_usa_radio_button)
                , libertyWeight
                , (RadioButton) findViewById(R.id.eiffel_tower_6_radio_button)
                , (RadioButton) findViewById(R.id.eiffel_tower_60_radio_button)
                , (RadioButton) findViewById(R.id.eiffel_tower_6000_radio_button)
                , (RadioButton) findViewById(R.id.great_wall_yes_radio_button)
                , (RadioButton) findViewById(R.id.amusement_park_california_radio_button)
                , (RadioButton) findViewById(R.id.amusement_park_paris_radio_button)
                , (RadioButton) findViewById(R.id.amusement_park_tokyo_radio_button)};

        wrongAnswerButtonsList = new ArrayList<>(Arrays.asList(wrongAnswerButtons));

        // Makes the on-screen soft keyboard disappear when user touches the main LinearLayout
        linearLayoutMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) linearLayoutMain.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(linearLayoutMain.getWindowToken(), 0);
                return false;
            }
        });
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

    public void showAnswers(View view) {
        setTextColors(wrongAnswerColorId);
        londonClockTower.setText(rightAnswer);

        // Scrolling to the top CardView
        topCardView.getParent().requestChildFocus(topCardView, topCardView);
    }

    public void restartGame(View view) {
        numOfRightAnswers = 0;
        ratingBar.setRating(numOfRightAnswers);
        setTextColors(rightAnswerColorId);

        // setting the possible answers to initial state
        ((RadioGroup) findViewById(R.id.golden_gate_radio_group)).clearCheck();
        londonClockTower.setText("");
        ((RadioGroup) findViewById(R.id.beach_radio_group)).clearCheck();
        libertyFrenchSculpture.setChecked(false);
        libertyPieces.setChecked(false);
        libertyYears.setChecked(false);
        libertyWeight.setChecked(false);
        ((RadioGroup) findViewById(R.id.eiffel_tower_radio_group)).clearCheck();
        ((RadioGroup) findViewById(R.id.great_wall_radio_group)).clearCheck();
        ((RadioGroup) findViewById(R.id.amusement_park_radio_group)).clearCheck();

        // setting the layout to initial state
        ratingBar.setVisibility(View.GONE);
        showAnswersButton.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);

        // Scrolling to the top CardView
        topCardView.getParent().requestChildFocus(topCardView, topCardView);
    }

    private void showResult() {
        submitButton.setVisibility(View.GONE);
        ratingBar.setRating(numOfRightAnswers);
        ratingBar.setVisibility(View.VISIBLE);
        showAnswersButton.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.VISIBLE);

        // set the focus on the bottom CardView
        CardView bottomCardView = (CardView) findViewById(R.id.bottom_card_view);
        bottomCardView.getParent().requestChildFocus(bottomCardView, bottomCardView);
        createToastMessage();
    }

    /**
     * sets the color of the text of the buttons in batch that represent the wrong answers
     *
     * @param colorId the id of the color the button texts should be set to
     */
    private void setTextColors(int colorId) {
        for (Button button : wrongAnswerButtonsList) {
            button.setTextColor(colorId);
        }
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
    }

    private void checkLondonTowerAnswer() {
        String answer = londonClockTower.getText().toString().trim();

        if (answer.equalsIgnoreCase(rightAnswer)) {
            numOfRightAnswers++;
        }
    }

    private void checkBeachAnswer() {
        if (beachAustralia.isChecked()) {
            numOfRightAnswers++;
        }
    }

    private void checkLibertyStatueAnswer() {
        if (libertyFrenchSculpture.isChecked() && libertyPieces.isChecked()
                && libertyYears.isChecked() && !libertyWeight.isChecked()) {
            numOfRightAnswers++;
        }
    }

    private void checkEiffelTowerAnswer() {
        if (eiffel600.isChecked()) {
            numOfRightAnswers++;
        }
    }

    private void checkGreatWallAnswer() {
        if (greatWallNo.isChecked()) {
            numOfRightAnswers++;
        }
    }

    private void checkAmusementAnswer() {
        if (amusementFlorida.isChecked()) {
            numOfRightAnswers++;
        }
    }
}
