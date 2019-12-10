package android.the.coding.archer.recipeapp.db;

import android.the.coding.archer.recipeapp.model.Recipe;
import android.the.coding.archer.recipeapp.model.RecipeFields;
import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class RecipeDataSource {

    private static final String TAG = RecipeDataSource.class.getSimpleName();

    private Realm realm;

    public void open() {
        realm = Realm.getDefaultInstance();
        Log.d(TAG, "Database Open.");
    }

    public void close() {
        realm.close();
        Log.d(TAG, "Database Close.");
    }

    public void createRecipe(final Recipe recipe) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(recipe);
            }
        });

        Log.d(TAG, "createRecipe: ID: " + recipe.getId());
    }

    public List<Recipe> getAllRecipes() {
        return realm.where(Recipe.class).findAll();

        // Querying all Recipes that has Steps
        /*return realm.where(Recipe.class)
                .isNotEmpty(RecipeFields.STEPS.$)
                .findAll();*/

        // Querying all Recipes that has Steps and has "ie" in its name
        /*return realm.where(Recipe.class)
                .isNotEmpty(RecipeFields.STEPS.$)
                .or()
                .contains(RecipeFields.NAME, "ie")
                .findAll();*/
    }
}
