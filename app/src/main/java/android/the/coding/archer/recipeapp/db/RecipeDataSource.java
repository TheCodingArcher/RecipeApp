package android.the.coding.archer.recipeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.the.coding.archer.recipeapp.model.RecipeStep;
import android.util.Log;

import java.util.List;

public class RecipeDataSource {

    public static final String TAG = RecipeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public RecipeDataSource(Context context) {
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    public void open() {
        this.database = dbHelper.getWritableDatabase();

        Log.d(TAG, "Database is Opened.");
    }

    public void close() {
        dbHelper.close();

        Log.d(TAG, "Database is Closed.");
    }

    public void createRecipe(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        long rowId = database.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);

        Log.d(TAG, "createRecipe with ID: " + rowId);

        List<RecipeStep> steps = recipe.getSteps();
        if (steps != null && steps.size() > 0) {
            for (RecipeStep step : steps) {
                createRecipeStep(step, rowId);
            }
        }
    }

    public void createRecipeStep(RecipeStep recipeStep, long recipeId) {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeStepEntry.COLUMN_RECIPE_ID, recipeId);
        values.put(RecipeContract.RecipeStepEntry.COLUMN_INSTRUCTION, recipeStep.getInstruction());
        values.put(RecipeContract.RecipeStepEntry.COLUMN_STEP_NUMBER, recipeStep.getStepNumber());

        long rowId = database.insert(RecipeContract.RecipeStepEntry.TABLE_NAME, null, values);

        Log.d(TAG, "createRecipeStep with ID: " + rowId);
    }
}
