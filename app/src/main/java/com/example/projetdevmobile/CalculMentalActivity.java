package com.example.projetdevmobile;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class CalculMentalActivity extends BaseActivity  {
    private TextView editCalcul;
    private TextView textViewAnswer;
    private TextView textViewQuestion;
    private Integer resultatAttendu;
    private Integer life = 3;
    private ImageView heart3;
    private ImageView heart2;
    private ImageView heart1;


    private TextView textViewScore;
    private Integer score = 0;
    private TextView textViewTimer;
    private CountDownTimer timer;

    private Button buttonValidate;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mental_calculation);

        // Récupérer les textViews et les imageViews
        editCalcul = findViewById(R.id.edit_calcul);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        textViewQuestion = findViewById(R.id.textViewQuestion);

        textViewTimer = findViewById(R.id.timer);
        textViewScore = findViewById(R.id.score);

        heart3 = findViewById(R.id.heart3);
        heart2 = findViewById(R.id.heart2);
        heart1 = findViewById(R.id.heart1);

        buttonValidate = findViewById(R.id.button_validate);
        buttonValidate.setOnClickListener(view->{
            Validate();
        });

        // Initialiser l'affichage
        UpdateScore(0);
        UpdateLife(0);
        generateQuestion();
        textViewAnswer.setVisibility(View.GONE);
    }


    private void stopTimer(){
        if (timer != null) {
            timer.cancel();
        }
    }
    private void startTimer(long durationMillis) {
        System.out.println("Timer : starTimer");
        stopTimer();

        timer = new CountDownTimer(durationMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                String timeFormatted = String.format(Locale.US, "%d:%02d", minutes, seconds);
                textViewTimer.setText(timeFormatted);
            }

            @Override
            public void onFinish() {
                System.out.println("Timer : onFinish");
                Validate();
            }
        }.start();
    }

    private void ResetEditCalcul(){
        editCalcul.setText(" ");
    }

    private void UpdateScore(Integer scoreValue){
        score+=scoreValue;
        textViewScore.setText(getString(R.string.score, score));
    }

    private void UpdateLife(Integer lifeValue){
        life+=lifeValue;
        if (life <= 2) heart3.setVisibility(View.GONE);
        if (life <= 1) heart2.setVisibility(View.GONE);
        if (life <= 0) heart1.setVisibility(View.GONE);
    }

    private void showGameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Partie terminée");

        final EditText input = new EditText(this);
        input.setHint("Entrez votre nom");
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String playerName = input.getText().toString().trim();
            if (!playerName.isEmpty()) {
                saveScore(playerName, score);
            }
            goBackToHome();
        });

        builder.setCancelable(false);
        builder.show();
    }

    private void saveScore(String name, int score) {
        try {
            FileOutputStream fos = openFileOutput("scores.txt", MODE_APPEND);
            String entry = name + ":" + score + "\n";
            fos.write(entry.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private void Validate(){
        System.out.println("Validate");
        textViewAnswer.setVisibility(View.VISIBLE);

        String input = editCalcul.getText().toString().trim();
        boolean mauvaiseReponse = false;

        if (input.isEmpty()) {
            mauvaiseReponse = true;
        } else {
            try {
                int reponse = Integer.parseInt(input);
                if (reponse == resultatAttendu) {
                    textViewAnswer.setText(getString(R.string.correct_answer, resultatAttendu));
                    textViewAnswer.setTextColor(Color.GREEN);
                    UpdateScore(1);
                } else {
                    mauvaiseReponse = true;
                }
            } catch (NumberFormatException e) {
                mauvaiseReponse = true;
            }
        }

        boolean keepPlaying = true;
        if (mauvaiseReponse) {
            textViewAnswer.setText(getString(R.string.wrong_answer, resultatAttendu));
            textViewAnswer.setTextColor(Color.RED);

            UpdateLife(-1);
            if (life <= 0){
                keepPlaying = false;
                stopTimer();
                showGameOverDialog();
            }
        }
        if (keepPlaying){
            ResetEditCalcul();
            generateQuestion();
        }

    }

    private void generateQuestion() {
        System.out.println("generateQuestion");
        if (score < 3) {
            generateQuestionEasyMode();
            startTimer(10000); // 10 secondes
        }
        else if (score < 6) {
            generateQuestionEasyMode();
            startTimer(5000); // 5 secondes
        }

        else if (score < 9) {
            generateQuestionMediumMode();
            startTimer(15000); // 15 secondes
        } else if (score < 12) {
            generateQuestionHardMode();
            startTimer(20000); // 20 secondes
        }
        else if (score < 15) {
            generateQuestionHardMode();
            startTimer(15000); // 15 secondes
        }
        else {
            generateQuestionHardMode();
            startTimer(10000); // 10 secondes
        }
    }

    public int RandomGenerator(int max, int min){
        Random random = new Random();
        return random.nextInt(max-min+1) + min;
    }

    private void generateQuestionEasyMode(){
        System.out.println("generateQuestionEasyMode");
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


    private void generateMultiplication(){
        int nombre1 = RandomGenerator(10, 1);
        int nombre2 = RandomGenerator(10, 1);
        resultatAttendu = nombre1 * nombre2;
        textViewQuestion.setText(nombre1 + " X " + nombre2 + " = ?");
    }

    private void generateDivision() {
        int a = RandomGenerator(10, 1); // nombre entre 1 et 10
        int b = RandomGenerator(10, 1); // nombre entre 1 et 50
        int c = a * b; // on crée un produit pour que la division tombe juste

        resultatAttendu = a; // car c / b = a
        textViewQuestion.setText(c + " ÷ " + b + " = ?");
    }


    private void generateQuestionMediumMode(){
        System.out.println("generateQuestionMediumMode");
        int nombre1 = RandomGenerator(100, 1);
        int nombre2 = RandomGenerator(100, 1);
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
                generateMultiplication();
                break;
        }
    }

    private void generateQuestionHardMode(){
        int nombre1=RandomGenerator(100, 1);
        int nombre2=RandomGenerator(50, 1);
        int nombre3=RandomGenerator(10, 1);
        int operation=RandomGenerator(6, 1);
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
            case 5:
                generateMultiplication();
            case 6:
                generateDivision();


        }
    }
}