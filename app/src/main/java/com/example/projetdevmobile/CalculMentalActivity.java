package com.example.projetdevmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private TextView textViewAnswer;
    private TextView textViewQuestion;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonValidate;
    private Button buttonErase;
    private Integer playerEntry=0;
    private Integer numeroRound;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mental_calculation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewCalcul = findViewById(R.id.textViewCalcul);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonValidate = findViewById(R.id.button_validate);
        buttonErase = findViewById(R.id.button_erase);

        button0.setOnClickListener(view->{
            appuieChiffre(0);
        });
        button1.setOnClickListener(view->{
            appuieChiffre(1);
        });
        button2.setOnClickListener(view->{
            appuieChiffre(2);
        });
        button3.setOnClickListener(view->{
            appuieChiffre(3);
        });
        button4.setOnClickListener(view->{
            appuieChiffre(4);
        });
        button5.setOnClickListener(view->{
            appuieChiffre(5);
        });
        button6.setOnClickListener(view->{
            appuieChiffre(6);
        });
        button7.setOnClickListener(view->{
            appuieChiffre(7);
        });
        button8.setOnClickListener(view->{
            appuieChiffre(8);
        });
        button9.setOnClickListener(view->{
            appuieChiffre(9);
        });
    }

    private void appuieChiffre(Integer chiffre){
        textViewCalcul.setText(textViewCalcul.getText()+chiffre.toString());
        playerEntry = 10*playerEntry+chiffre;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app,menu);

        // Set up les boutons

        MenuItem menuGoBack = menu.findItem(R.id.menu_go_back);
        menuGoBack.setOnMenuItemClickListener(view -> go_back());

        MenuItem menuReset = menu.findItem(R.id.button_erase);
        menuReset.setOnMenuItemClickListener(view -> erase());



        //MenuItem menuCalcul = menu.findItem(R.id.menu_calcul);
        //menuCalcul.setOnMenuItemClickListener(view -> {
        //    Toast.makeText(CalculatriceActivity.this, TypeOperationEnum.calcul(typeOperation, premierElement, deuxiemeElement).toString(), Toast.LENGTH_LONG).show();
        //    return true;
        //});

        return super.onCreateOptionsMenu(menu);
    }

    private boolean go_back(){
        System.out.println("negro");
        boolean negro = true;
        return negro; //david or guerby or amhed or shak il olin
    }

    private boolean erase(){
        playerEntry=0;
        textViewCalcul.setText("");
        return true;
    }


    private void getRoundCategory(Integer numeroRound) {
        if (numeroRound < 10) {
            generateCalculVeryEasyMode();
        } else if (numeroRound < 20) {
            generateCalculEasyMode();
        } else if (numeroRound < 30) {
            generateCalculMediumMode();
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
}