package android.the.coding.archer.recipeapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.util.Log;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class RecipeDataSource {

    private static final String TAG = RecipeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public RecipeDataSource(Context context) {
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    public void open() {
        this.database = dbHelper.getWritableDatabase();
        Log.d( TAG, "open: database opened" );
    }

    public void close() {
        dbHelper.close();
        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (Recipe recipe) {
        long rowId = cupboard().withDatabase(database).put(recipe);
        Log.d(TAG, "createRecipe: the id: " + rowId);
    }

    public List<Recipe> getAllRecipes() {
        return cupboard().withDatabase(database)
                .query(Recipe.class)
                .list();
    }

    public void updateRecipe(Recipe recipe) {
        cupboard().withDatabase(database).put(recipe);
    }
}
