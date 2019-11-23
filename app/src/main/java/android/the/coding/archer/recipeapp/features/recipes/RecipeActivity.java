package android.the.coding.archer.recipeapp.features.recipes;

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

        dataSource = new RecipeDataSource(this);

        setupRecyclerView();
    }

    @Override
    protected void onResume () {
        super.onResume();

        dataSource.open();

        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        List<Recipe> allRecipes = getRecipes();

        /*
        // FOR UPDATING RECORDS
        Recipe updatedRecipe = allRecipes.get(0);
        updatedRecipe.setName("Yellow Cake!");
        dataSource.updateRecipe(updatedRecipe);
        */

        /*
        // FOR DELETING A SINGLE RECORD
        Recipe updatedRecipe = allRecipes.get(0);
        dataSource.deleteRecipe(updatedRecipe);
        */

        // FOR DELETING ALL RECORDS
        dataSource.deleteAllRecipes();

        getRecipes();
    }

    private List<Recipe> getRecipes() {
        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes) {
            Log.i(TAG, "the recipe: " + recipe);
        }

        adapter.setRecipes(allRecipes);

        return allRecipes;
    }

    @Override
    protected void onPause () {
        super.onPause();

        dataSource.close();
    }

    private void setupRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(adapter);
    }
}
