package android.the.coding.archer.recipeapp.db;

import android.the.coding.archer.recipeapp.R;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.the.coding.archer.recipeapp.model.RecipeStep;

import java.util.ArrayList;
import java.util.List;

public class RecipesDataProvider {

    public static List<Recipe> recipesList;

    static {
        recipesList = new ArrayList<>();

        addRecipe(new Recipe("Cake", "Wonderful cake", R.drawable.cake_wonderful));

        addRecipe(new Recipe("Pie", "Delicious Pie", R.drawable.pie_delicious));

        addRecipe(new Recipe("Pound Cake", "Fluffy cake", R.drawable.cake_fluffy),
                new RecipeStep(1, "mix all the ingredients"),
                new RecipeStep(2, "preheat the oven"),
                new RecipeStep(3, "stir"),
                new RecipeStep(4, "bake"));
    }

    private static void addRecipe(Recipe recipe, RecipeStep... steps) {
        recipesList.add(recipe);
    }
}
