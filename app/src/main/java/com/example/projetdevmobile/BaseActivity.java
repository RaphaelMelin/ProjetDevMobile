package com.example.projetdevmobile;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app,menu);

        MenuItem goHomeMenuItem = menu.findItem(R.id.home_menu_item);
        goHomeMenuItem.setOnMenuItemClickListener(view -> goBackToHome());

        MenuItem goToAboutSectionItem = menu.findItem(R.id.about_menu_item);
        goToAboutSectionItem.setOnMenuItemClickListener(view -> goToAboutSection());

        return super.onCreateOptionsMenu(menu);
    }

    protected boolean goBackToHome() {
        if (this.getClass() == MainActivity.class) return false;
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish(); // Termine l’activité actuelle
        return true;
    }

    private boolean goToAboutSection() {
        if (this.getClass() == AboutActivity.class) return false;
        Intent intent = new Intent(this, AboutActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish(); // Termine l’activité actuelle
        return true;
    }
}