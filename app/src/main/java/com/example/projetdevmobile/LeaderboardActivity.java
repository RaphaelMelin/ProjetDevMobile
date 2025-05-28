package com.example.projetdevmobile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardActivity extends BaseActivity {

    TextView leaderboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardView = findViewById(R.id.textViewLeaderboard);
        loadScores();
    }

    private void loadScores() {
        try {
            FileInputStream fis = openFileInput("scores.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            List<String> scores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }

            // Trie les scores décroissants
            Collections.sort(scores, (a, b) -> {
                int scoreA = Integer.parseInt(a.split(":")[1]);
                int scoreB = Integer.parseInt(b.split(":")[1]);
                return Integer.compare(scoreB, scoreA);
            });

            // Récuperer les string
            String rank = getString(R.string.leaderboard_rank);
            String name = getString(R.string.leaderboard_name);
            String score = getString(R.string.leaderboard_score);

            StringBuilder display = new StringBuilder();
            display.append(String.format("%-4s %-15s %s\n", rank, name, score));
            display.append("-------------------------------\n");

            // Formattage des lignes pour afficher proprement en colonnes
            for (int i = 0; i < Math.min(10, scores.size()); i++) {
                String[] parts = scores.get(i).split(":");
                String nom = parts[0];
                String sc = parts[1];
                display.append(String.format("%-4d %-15s %s\n", i + 1, nom, sc));
            }

            leaderboardView.setTypeface(Typeface.MONOSPACE); // pour alignement clair
            leaderboardView.setTextSize(16); // un peu plus grand
            leaderboardView.setText(display.toString());

            reader.close();
        } catch (IOException e) {
            leaderboardView.setText("Aucun score disponible.");
        }
    }

}
