package android.the.coding.archer.recipeapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.the.coding.archer.recipeapp.model.RecipeStep;

@Database(entities = { Recipe.class, RecipeStep.class }, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "recipes";
    private static RecipeDatabase INSTANCE = null;

    public static RecipeDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                RecipeDatabase.class,
                                                DATABASE_NAME)
                            .build();
            }
        }

        return INSTANCE;
    }
}
