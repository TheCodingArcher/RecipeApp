package android.the.coding.archer.recipeapp.features.recipes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.the.coding.archer.recipeapp.R;
import android.the.coding.archer.recipeapp.db.RecipeDataSource;
import android.the.coding.archer.recipeapp.db.RecipesDataProvider;
import android.the.coding.archer.recipeapp.model.Recipe;

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

        new AsyncTask<Void, Void, List<Recipe>>() {

            @Override
            protected List<Recipe> doInBackground(Void... voids) {
                for (Recipe recipe : RecipesDataProvider.recipesList) {
                    dataSource.createRecipe(recipe);
                }

                return dataSource.getAllRecipes();
            }

            @Override
            protected void onPostExecute(List<Recipe> recipes) {
                adapter.setRecipes(recipes);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    protected void onPause () {
        super.onPause();
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
