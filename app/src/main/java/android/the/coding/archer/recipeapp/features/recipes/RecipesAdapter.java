package android.the.coding.archer.recipeapp.features.recipes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.the.coding.archer.recipeapp.R;
import android.the.coding.archer.recipeapp.model.Recipe;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class RecipesAdapter extends RealmRecyclerViewAdapter<Recipe, RecipesAdapter.ViewHolder> {

    public RecipesAdapter(@Nullable OrderedRealmCollection<Recipe> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @NonNull
    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.ViewHolder viewHolder, int position) {
        Recipe recipe = getData().get( position );

        viewHolder.recipeName.setText(recipe.getName());
        viewHolder.recipeDescription.setText(recipe.getDescription());

        Picasso.with(viewHolder.recipeImage.getContext())
                .load(recipe.getImageResourceId())
                .resize(340, 200)
                .centerCrop()
                .into(viewHolder.recipeImage);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView recipeName;
        TextView recipeDescription;

        public ViewHolder (View v) {
            super(v);

            recipeImage = v.findViewById(R.id.recipe_image);
            recipeName = v.findViewById(R.id.recipe_name);
            recipeDescription = v.findViewById(R.id.recipe_description);
        }
    }
}
