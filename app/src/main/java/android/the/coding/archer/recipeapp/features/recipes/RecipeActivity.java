package android.the.coding.archer.recipeapp.features.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.the.coding.archer.recipeapp.R;
import android.the.coding.archer.recipeapp.db.RecipeDataSource;

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
