package android.the.coding.archer.recipeapp.db;

import android.provider.BaseColumns;

final class RecipeContract {

    private RecipeContract() {}

    private long id;
    private String name;
    private String description;
    private int imageResourceId;

    public static class RecipeEntry implements BaseColumns {
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id";
    }

    public static class RecipeStepEntry implements BaseColumns {
        public static final String TABLE_NAME = "recipe_step";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_NUMBER = "step_number";
        public static final String COLUMN_INSTRUCTION = "instruction";
    }
}
