package com.example.projetdevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import java.util.Random;

public class CalculMentalActivity extends BaseActivity  {
    private TextView editCalcul;
    private TextView textViewAnswer;
    private TextView textViewQuestion;
    private Integer resultatAttendu;
    private TextView textViewLife;
    private Integer life = 3;
    private TextView textViewScore;
    private Integer score = 0;
    private TextView textViewTimer;
    private CountDownTimer timer;

    private Integer round = 1;

    private Button buttonValidate;
    private Integer playerEntry=0;
    private Integer numberRound;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mental_calculation);

        editCalcul = findViewById(R.id.edit_calcul);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        textViewQuestion = findViewById(R.id.textViewQuestion);

        textViewTimer = findViewById(R.id.timer);
        textViewScore = findViewById(R.id.score);
        textViewLife = findViewById(R.id.life);

        buttonValidate = findViewById(R.id.button_validate);
        buttonValidate.setOnClickListener(view->{
            Validate();
        });

        // Initialiser les textView
        UpdateScore(0);
        UpdateLife(0);

        generateQuestion();

    }


    private void startTimer(long durationMillis) {
        // Si un timer est déjà actif, on l’arrête
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(durationMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long remainingSeconds = seconds % 60;
                String timeFormatted = String.format(Locale.US, "%d:%02d", minutes, seconds);
                textViewTimer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                Validate();
            }
        }.start();
    }

    private void ResetEditCalcul(){
        editCalcul.setText(" ");
    }

    private void UpdateScore(Integer scoreValue){
        score+=scoreValue;
        String scoreText = getString(R.string.score, score);
        textViewScore.setText(scoreText);
    }

    private void UpdateLife(Integer lifeValue){
        life+=lifeValue;
        String lifeText = getString(R.string.life, life);
        textViewLife.setText(lifeText);
    }

    private void Validate(){
        textViewAnswer.setText(String.valueOf(resultatAttendu));
        round++;
        String input = editCalcul.getText().toString().trim();
        boolean mauvaiseReponse = false;

        if (input.isEmpty()) {
            mauvaiseReponse = true;
        } else {
            try {
                int reponse = Integer.parseInt(input);
                if (reponse == resultatAttendu) {
                    UpdateScore(1);
                } else {
                    mauvaiseReponse = true;
                }
            } catch (NumberFormatException e) {
                mauvaiseReponse = true;
            }
        }

        if (mauvaiseReponse) {
            UpdateScore(-1);
            UpdateLife(-1);
            if (life <= 0) goBackToHome();
        }

        ResetEditCalcul();
        generateQuestion();
    }

    private void generateQuestion() {
        System.out.println(round);
        if (round < 3) {
            generateQuestionVeryEasyMode();
            startTimer(5000); // 5 secondes
        } else if (round < 6) {
            generateQuestionEasyMode();
            startTimer(10000); // 10 secondes
        } else if (round < 9) {
            generateCalculMediumMode();
            startTimer(15000); // 15 secondes
        }
        
    }

    public int RandomGenerator(int max, int min){
        Random random = new Random();
        int nombreAleatoire = random.nextInt(max-min+1) + min;
        return nombreAleatoire;
    }

    private void generateQuestionVeryEasyMode(){
        int nombre1=RandomGenerator(10, 1);
        int nombre2=RandomGenerator(10, 1);
        int operation=RandomGenerator(2, 1);
        switch(operation){
            case 1:
                resultatAttendu=nombre1+nombre2;
                textViewQuestion.setText(nombre1 + " + " + nombre2 +" = ?");
                break;
            case 2:
                resultatAttendu=nombre1-nombre2;
                textViewQuestion.setText(nombre1 + " - " + nombre2 +" = ?");
                break;
        }
    }

    private void generateQuestionEasyMode(){
        int nombre1 = RandomGenerator(100, 1);;
        int nombre2 = RandomGenerator(100, 1);;
        int operation=RandomGenerator(3, 1);
        switch(operation){
            case 1:
                resultatAttendu = nombre1 + nombre2;
                textViewQuestion.setText(nombre1 + " + " + nombre2 + " = ?");
                break;
            case 2:
                resultatAttendu = nombre1 - nombre2;
                textViewQuestion.setText(nombre1 + " - " + nombre2 + " = ?");
                break;
            case 3:
                nombre2 = RandomGenerator(10, 1);
                resultatAttendu = nombre1 * nombre2;
                textViewQuestion.setText(nombre1 + " X " + nombre2 + " = ?");
                break;
        }
    }

    private void generateCalculMediumMode(){
        int nombre1=RandomGenerator(100, 1);
        int nombre2=RandomGenerator(100, 1);
        int nombre3=RandomGenerator(100, 1);
        int operation=RandomGenerator(4, 1);
        switch(operation){
            case 1:
                resultatAttendu=nombre1+nombre2+nombre3;
                textViewQuestion.setText(nombre1 + " + " + nombre2 + " + " + nombre3 +" = ?");
                break;
            case 2:
                resultatAttendu=nombre1-nombre2-nombre3;
                textViewQuestion.setText(nombre1 + " - " + nombre2 + " - " + nombre3 +" = ?");
                break;
            case 3:
                resultatAttendu=nombre1+nombre2-nombre3;
                textViewQuestion.setText(nombre1 + " + " + nombre2 + " - " + nombre3 +" = ?");
                break;
            case 4:
                resultatAttendu=nombre1-nombre2+nombre3;
                textViewQuestion.setText(nombre1 + " - " + nombre2 + " + " + nombre3 + " = ?");
                break;
        }
    }
}