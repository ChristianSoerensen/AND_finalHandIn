package com.example.hamburgerhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarMain;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = (Toolbar) findViewById(R.id.le_toolbar);
        setSupportActionBar(toolbarMain);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.recipesList:
                intent = new Intent(MainActivity.this, RecipeList.class);
                startActivity(intent);
                return true;
            case R.id.ingredientsList:
                intent = new Intent(MainActivity.this, IngredientsList.class);
                startActivity(intent);
                return true;

                default: return super.onOptionsItemSelected(item);
        }
    };

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
