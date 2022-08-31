package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizgame.util.Tags;

public class EndGameActivity extends AppCompatActivity {

    private TextView highScore_1;
    private TextView highScore_2;
    private TextView highScore_3;
    private TextView newHighScoreTextView;
    int points;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        points = intent.getIntExtra(Tags.POINTS, 0);
        initViews();
        saveHighScore(points);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });


    }

    private void resetGame() {
        //TODO apply logic
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViews() {
        TextView pointsTextView = findViewById(R.id.pointsTextView);
        highScore_1 = findViewById(R.id.highScore_1);
        highScore_2 = findViewById(R.id.highScore_2);
        highScore_3 = findViewById(R.id.highScore_3);

        newHighScoreTextView = findViewById(R.id.newHighScoreTextView);
        pointsTextView.setText(getString(R.string.points_with_placeholder, points));
        resetButton = findViewById(R.id.resetButton);
    }

    private void saveHighScore(int points) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        int highScore_1 = sharedPreferences.getInt(Tags.HIGH_SCORE_1, 0);
        int highScore_2 = sharedPreferences.getInt(Tags.HIGH_SCORE_2, 0);
        int highScore_3 = sharedPreferences.getInt(Tags.HIGH_SCORE_3, 0);

        this.highScore_1.setText(getString(R.string.high_score_1_with_placeholder, highScore_1));
        this.highScore_2.setText(getString(R.string.high_score_2_with_placeholder, highScore_2));
        this.highScore_3.setText(getString(R.string.high_score_3_with_placeholder, highScore_3));


        if (points > highScore_1) {
            newHighScoreTextView.setVisibility(View.VISIBLE);
            newHighScoreTextView.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int old2Score = sharedPreferences.getInt(Tags.HIGH_SCORE_2, 0);
            int old1Score = sharedPreferences.getInt(Tags.HIGH_SCORE_1, 0);
            editor.putInt(Tags.HIGH_SCORE_3, old2Score);
            editor.putInt(Tags.HIGH_SCORE_2, old1Score);
            editor.putInt(Tags.HIGH_SCORE_1, points);
            editor.apply();
        } else if (points > highScore_2 && points < highScore_1) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int old2Score = sharedPreferences.getInt(Tags.HIGH_SCORE_2, 0);
            editor.putInt(Tags.HIGH_SCORE_3, old2Score);
            editor.putInt(Tags.HIGH_SCORE_2, points);
            editor.apply();
        } else if (points > highScore_3 && points < highScore_2) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(Tags.HIGH_SCORE_3, points);
            editor.apply();
        }
    }
}