package android.the.coding.archer.recipeapp.db;

import android.the.coding.archer.recipeapp.model.Recipe;
import android.util.Log;

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
                realm.insert(recipe);
            }
        });

        Log.d(TAG, "createRecipe: ID: " + recipe.getId());
    }
}
