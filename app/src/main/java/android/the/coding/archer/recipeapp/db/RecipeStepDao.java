package android.the.coding.archer.recipeapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.the.coding.archer.recipeapp.model.RecipeStep;

import java.util.List;

@Dao
public interface RecipeStepDao {

    @Insert
    void insertAll(List<RecipeStep> steps);

    @Query("SELECT * FROM recipe_steps where recipe_id = :recipeId")
    List<RecipeStep> getAllRecipeStepsById(long recipeId);
}
