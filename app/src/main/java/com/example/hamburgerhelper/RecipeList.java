package com.example.hamburgerhelper;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

import static java.lang.Integer.parseInt;

public class RecipeList extends AppCompatActivity {

    Toolbar toolbarRL;

    RecyclerView mRecipeList;
    RecyclerView.Adapter mRecipeListAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("recipes");

    static ArrayList<Recipes> recipesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        //code for Toolbar
        toolbarRL = (Toolbar) findViewById(R.id.recipesList_toolbar);
        setSupportActionBar(toolbarRL);

        toolbarRL.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(RecipeList.this, MainActivity.class);
                startActivity(backIntent);
            }
        });


        //code for RecyclerView


                mRecipeList = findViewById(R.id.rv);
                mRecipeList.hasFixedSize();
                mRecipeList.setLayoutManager(new LinearLayoutManager(this));

                getDataFromDb();


                /*
                recipes.add(new Recipes("Burger", ".", R.drawable.p1));
                recipes.add(new Recipes("FishDick",".", R.drawable.p2));
                recipes.add(new Recipes("hand",".", R.drawable.p2));
                recipes.add(new Recipes("swup",".", R.drawable.p2));
                recipes.add(new Recipes("woop",".", R.drawable.p2));
                recipes.add(new Recipes("kkk",".", R.drawable.p2));
                recipes.add(new Recipes("Fish",".", R.drawable.p2));
                recipes.add(new Recipes("asd",".", R.drawable.p2));
                recipes.add(new Recipes("dss",".", R.drawable.p2));
                recipes.add(new Recipes("aasd",".", R.drawable.p2));



                mRecipeListAdapter = new RecipeListAdapter(recipesArrayList);
                mRecipeList.setAdapter(mRecipeListAdapter);
                */


        //getDataFromDB();

        };
    //code for going to n
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.sweetCheese:
                Intent intent = new Intent(RecipeList.this, RecipeWriter.class);
                startActivity(intent);

            default: return super.onOptionsItemSelected(item);
        }
    }
    private void getDataFromDb(){
    myRef.addValueEventListener(new ValueEventListener() {

        private static final String TAG = "";

        @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //This method is called once with the initial value and again
                //whenever data at this location is updated,
                /*
                Recipes recipe = dataSnapshot.getValue(Recipes.class);
                recipesArrayList.add(recipe);
                mRecipeListAdapter.notifyDataSetChanged();
                */
                recipesArrayList = new ArrayList<>();

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Recipes re = snapshot.getValue(Recipes.class);
                recipesArrayList.add(re);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Failed to read the value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        mRecipeListAdapter = new RecipeListAdapter(recipesArrayList);
        mRecipeListAdapter.notifyDataSetChanged();
        mRecipeList.setAdapter(mRecipeListAdapter);

    }






    //Inflates the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_list_menu, menu);
        return true;
    };


}
