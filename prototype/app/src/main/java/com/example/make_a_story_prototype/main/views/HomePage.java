package com.example.make_a_story_prototype.main.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.make_a_story_prototype.R;
import com.example.make_a_story_prototype.main.Categories.view.CategoriesActivity;
import com.example.make_a_story_prototype.main.Characters.view.CharacterActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
* Class for Home page activities, including redirection to word bank and story screen.
*/
public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_home);
    }

    /**
     * goes to the word bank screen
     * @param v view
     */
    public void wordBankButtonOnClick(View v) {
        Intent intent = new Intent(this, CategoriesActivity.class);
        this.startActivity(intent);
    }

    /**
     * goes to the story page screen
     * @param v view
     */
    public void storyPageButtonOnClick(View v) {
        Toast.makeText(this, "Todo: story page button", Toast.LENGTH_SHORT).show();
    }

    /**
     * temp button to go to characters listing for demoing
     * @param v view
     */
    public void characterPageButtonOnClick(View v) {
        Intent intent = new Intent(this, CharacterActivity.class);
        this.startActivity(intent);    }
}