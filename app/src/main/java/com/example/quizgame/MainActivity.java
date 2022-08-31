package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.models.Answer;
import com.example.quizgame.models.Question;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int TOTAL_TIME = 30000;
    public static final int COUNT_DOWN_INTERVAL = 1000;
    private TextView timeLeftTextView, questionTextView, pointsTextView;
    private Button answerButton1, answerButton2, answerButton3;
    private ProgressBar timeProgressBar;
    private int questionIndex = 0;
    private int points = 0;
    private List<Question> allQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeLeftTextView = findViewById(R.id.timeLeftTextView);
        questionTextView = findViewById(R.id.questionTextView);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        pointsTextView = findViewById(R.id.pointsTextView);

        allQuestions = Question.getAllQuestions(); //Database.get for example Server.get
        displayQuestion(allQuestions.get(questionIndex));
        
        startTimer();
    }

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinish) {
                int progress = (int) (millisUntilFinish * 100.0/TOTAL_TIME);
                timeProgressBar.setProgress(progress);
                timeLeftTextView.setText(getString(R.string.time_with_placeholder, millisUntilFinish/1000));
            }

            @Override
            public void onFinish() {
                gameOver();
            }
        }; countDownTimer.start();
    }

    private void displayQuestion(Question question) {
        questionTextView.setText(question.getText());
        answerButton1.setText(question.getAnswer1().getText());
        answerButton2.setText(question.getAnswer2().getText());
        answerButton3.setText(question.getAnswer3().getText());

        answerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrect(question.getAnswer1(), question.getPoints());
            }
        });

        answerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrect(question.getAnswer2(), question.getPoints());
            }
        });

        answerButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrect(question.getAnswer3(), question.getPoints());
            }
        });

    }

    private void checkAnswerCorrect(Answer answer, int point) {
        if (answer.isCorrect()) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        questionIndex++;

        if (questionIndex == allQuestions.size()) {
            gameOver();
        } else{
            displayQuestion(allQuestions.get(questionIndex));
        }
    }

    private void gameOver() {
        Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
    }

}