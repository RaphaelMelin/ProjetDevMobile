package com.example.projetdevmobile;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;


public class MainActivity extends BaseActivity  {
    private Button buttonPlay;
    private Button buttonQuit;
    private Button languageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageView flagImageView = findViewById(R.id.flag_img);
        Locale current = Locale.getDefault();

        if (current.getLanguage().equals("fr")) {
            flagImageView.setImageResource(R.drawable.ic_flag_fr);
        } else if (current.getLanguage().equals("en")) {
            flagImageView.setImageResource(R.drawable.ic_flag_eng);
        }


        buttonPlay = findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalculMentalActivity.class);
            startActivity(intent);
        });


        buttonQuit = findViewById(R.id.quit_button);
        buttonQuit.setOnClickListener(v -> {
            finishAffinity();
            //System.exit(0);
        });


        languageButton = findViewById(R.id.select_language_btn);
        languageButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String[] languages = {"Français", "English"};

            builder.setTitle(getString(R.string.select_language_btn))
                    .setItems(languages, (dialog, which) -> {
                        if (which == 0) {
                            setLocale("fr");
                        } else {
                            setLocale("en");
                        }
                    }).show();
        });
    }
    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Redémarrer l'activité pour appliquer la langue
        recreate();
    }
}