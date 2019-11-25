package android.the.coding.archer.recipeapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.the.coding.archer.recipeapp.model.RecipeStep;

import java.util.List;

@Dao
public interface RecipeStepDao {

    @Insert
    void insertAll(List<RecipeStep> steps);
}
