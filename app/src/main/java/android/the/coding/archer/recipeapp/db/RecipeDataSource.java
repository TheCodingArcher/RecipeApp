package android.the.coding.archer.recipeapp.db;

import android.content.Context;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.the.coding.archer.recipeapp.model.RecipeStep;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecipeDataSource {

    private static final String TAG = RecipeDataSource.class.getSimpleName();
    private final RecipeDao recipeDao;
    private final RecipeStepDao recipeStepDao;

    public RecipeDataSource(Context context) {
        RecipeDatabase database = RecipeDatabase.getInstance(context);
        recipeDao = database.recipeDao();
        recipeStepDao = database.recipeStepDao();
    }

    public void createRecipe(Recipe recipe) {
        long rowId = recipeDao.createRecipe(recipe);
        List<RecipeStep> steps = recipe.getSteps();
        if (steps != null) {
            for (RecipeStep step : steps) {
                step.setRecipeId(rowId);
            }

            recipeStepDao.insertAll(steps);
        }

        Log.d(TAG, "createRecipe: ID: " + rowId);
    }

    public List<Recipe> getAllRecipes () {
        List<Recipe> recipes = new ArrayList<>();

        return recipes;
    }
}
