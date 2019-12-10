package android.the.coding.archer.recipeapp.features.recipes;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.the.coding.archer.recipeapp.R;
import android.the.coding.archer.recipeapp.db.RecipeDataSource;
import android.the.coding.archer.recipeapp.db.RecipesDataProvider;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.util.Log;

import java.util.List;

import io.realm.OrderedRealmCollection;

public class RecipeActivity extends AppCompatActivity {

    private static final String TAG = RecipeActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;

    private RecipeDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = findViewById(R.id.recipes_recycler_view);

        dataSource = new RecipeDataSource();
        dataSource.open();

        setupRecyclerView();
    }

    @Override
    protected void onResume () {
        super.onResume();

        /*dataSource.createRecipe(RecipesDataProvider.recipesList.get(0));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataSource.createRecipe(RecipesDataProvider.recipesList.get(1));
            }
        },3_000);*/

        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes) {
            Log.i(TAG, "recipe: " + recipe);
        }

        dataSource.modifyDescription();
    }

    @Override
    protected void onPause () {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        dataSource.close();
        super.onDestroy();
    }

    private void setupRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter((OrderedRealmCollection<Recipe>) dataSource.getAllRecipes(), true);
        recipesRecyclerView.setAdapter(adapter);
    }
}
