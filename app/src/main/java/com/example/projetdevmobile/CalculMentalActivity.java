package com.example.projetdevmobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class CalculMentalActivity extends AppCompatActivity {
    private TextView textViewCalcul;
    private TextView textViewResult;
    private Button bouton1;
    private Button bouton2;
    private Button bouton3;
    private Button bouton4;
    private Button bouton5;
    private Button bouton6;
    private Button bouton7;
    private Button bouton8;
    private Button bouton9;
    private Button bouton0;
    private Button boutonValidate;
    private Button boutonErase;
    private Integer playerEntry=0;
    private Integer numeroRound;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculmental);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewCalcul = findViewById(R.id.textViewCalcul);
        bouton0 = findViewById(R.id.bouton0);
        bouton1 = findViewById(R.id.bouton1);
        bouton2 = findViewById(R.id.bouton2);
        bouton3 = findViewById(R.id.bouton3);
        bouton4 = findViewById(R.id.bouton4);
        bouton5 = findViewById(R.id.bouton5);
        bouton6 = findViewById(R.id.bouton6);
        bouton7 = findViewById(R.id.bouton7);
        bouton8 = findViewById(R.id.bouton8);
        bouton9 = findViewById(R.id.bouton9);
        bouton0.setOnClickListener(view->{
            appuieChiffre(0);
        });
        bouton1.setOnClickListener(view->{
            appuieChiffre(1);
        });
        bouton2.setOnClickListener(view->{
            appuieChiffre(2);
        });
        bouton3.setOnClickListener(view->{
            appuieChiffre(3);
        });
        bouton4.setOnClickListener(view->{
            appuieChiffre(4);
        });
        bouton5.setOnClickListener(view->{
            appuieChiffre(5);
        });
        bouton6.setOnClickListener(view->{
            appuieChiffre(6);
        });
        bouton7.setOnClickListener(view->{
            appuieChiffre(7);
        });
        bouton8.setOnClickListener(view->{
            appuieChiffre(8);
        });
        bouton9.setOnClickListener(view->{
            appuieChiffre(9);
        });
    }

    private void appuieChiffre(Integer chiffre){
        textViewCalcul.setText(textViewCalcul.getText()+chiffre.toString());
        playerEntry = 10*playerEntry+chiffre;
    }

    private boolean reset(){
        playerEntry=0;
        textViewCalcul.setText("");
        return true;
    }

    private void getRoundCategory(Integer numeroRound) {
        switch (true) {
            case numeroRound < 10:
                generateCalculVeryEasyMode();
                break;
            case numeroRound >= 10 && numeroRound < 20:
                generateCalculEasyMode();
                break;
            case numeroRound >= 20 && numeroRound < 30:
                generateCalculMediumMode();
                break;
            case numeroRound >= 30 && numeroRound < 40:
                generateCalculHardMode();
                break;
            case numeroRound >= 40 && numeroRound < 50:
                generateCalculExpertMode();
                break;
            case numeroRound==50:
                generateCalculFinalMode();
                break;
            default:
                return;
        }
    }

    public int RandomGenerator(int max, int min){
        Random random = new Random();
        int nombreAleatoire = random.nextInt(max-min+1) + min;
        return nombreAleatoire;
    }

    private Integer generateCalculVeryEasyMode(){
        int nombre1=RandomGenerator(50, 1);
        int nombre2=RandomGenerator(50, 1);
        int operation=RandomGenerator(2, 1);
        int resultatAttendu;
        switch(operation){
            case 1:
                resultatAttendu=nombre1+nombre2;
                textViewCalcul.setText(nombre1 + " + " + nombre2 +" = ?");
                break;
            case 2:
                resultatAttendu=nombre1-nombre2;
                textViewCalcul.setText(nombre1 + " - " + nombre2 +" = ?");
                break;
            default:
                resultatAttendu=nombre1+nombre2;
                textViewCalcul.setText(nombre1 + " + " + nombre2 +" = ?");
                break;
        }
        return resultatAttendu;
    }

    private Integer generateCalculEasyMode(){
        int nombre1;
        int nombre2;
        int nombre3;
        int operation=RandomGenerator(5, 1);
        int resultatAttendu;
        switch(operation){
            case 1:
                nombre1=RandomGenerator(100, 1);
                nombre2=RandomGenerator(100, 1);
                nombre3=RandomGenerator(100, 1);
                resultatAttendu=nombre1+nombre2+nombre3;
                textViewCalcul.setText(nombre1 + " + " + nombre2 + " + " + nombre3 +" = ?");
                break;
            case 2:
                nombre1=RandomGenerator(100, 1);
                nombre2=RandomGenerator(100, 1);
                nombre3=RandomGenerator(100, 1);
                resultatAttendu=nombre1-nombre2-nombre3;
                textViewCalcul.setText(nombre1 + " - " + nombre2 + " - " + nombre3 +" = ?");
                break;
            case 3:
                nombre1=RandomGenerator(100, 1);
                nombre2=RandomGenerator(100, 1);
                nombre3=RandomGenerator(100, 1);
                resultatAttendu=nombre1+nombre2-nombre3;
                textViewCalcul.setText(nombre1 + " + " + nombre2 + " - " + nombre3 +" = ?");
                break;
            case 4:
                nombre1=RandomGenerator(100, 1);
                nombre2=RandomGenerator(100, 1);
                nombre3=RandomGenerator(100, 1);
                resultatAttendu=nombre1-nombre2+nombre3;
                textViewCalcul.setText(nombre1 + " - " + nombre2 + " + " + nombre3 + " = ?");
                break;
            case 5:
                nombre1=RandomGenerator(100, 1);
                nombre2=RandomGenerator(10, 1);
                resultatAttendu=nombre1*nombre2;
                textViewCalcul.setText(nombre1 + " x " + nombre2 + " = ?");
                break;
            default:
                nombre1=RandomGenerator(100, 1);
                nombre2=RandomGenerator(10, 1);
                resultatAttendu=nombre1*nombre2;
                textViewCalcul.setText(nombre1 + " x " + nombre2 + " = ?");
                break;
        }
        return resultatAttendu;
    }

    private Integer generateCalculMediumMode(){
        int nombre1=RandomGenerator(100, 1);
        int nombre2=RandomGenerator(100, 1);
        int nombre3=RandomGenerator(100, 1);
        int operation=RandomGenerator(4, 1);
        int resultatAttendu;
        switch(operation){
            case 1:
                resultatAttendu=nombre1+nombre2+nombre3;
                textViewCalcul.setText(nombre1 + " + " + nombre2 + " + " + nombre3 +" = ?");
                break;
            case 2:
                resultatAttendu=nombre1-nombre2-nombre3;
                textViewCalcul.setText(nombre1 + " - " + nombre2 + " - " + nombre3 +" = ?");
                break;
            case 3:
                resultatAttendu=nombre1+nombre2-nombre3;
                textViewCalcul.setText(nombre1 + " + " + nombre2 + " - " + nombre3 +" = ?");
                break;
            case 4:
                resultatAttendu=nombre1-nombre2+nombre3;
                textViewCalcul.setText(nombre1 + " - " + nombre2 + " + " + nombre3 + " = ?");
                break;
            default:
                resultatAttendu=nombre1+nombre2+nombre3;
                textViewCalcul.setText(nombre1 + " + " + nombre2 + " + " + nombre3 +" = ?");
                break;
        }
        return resultatAttendu;
    }

    private Integer generateCalculHardMode(){

    }

    private Integer generateCalculExpertMode(){

    }

    private Integer generateCalculFinalMode(){

    }
}
