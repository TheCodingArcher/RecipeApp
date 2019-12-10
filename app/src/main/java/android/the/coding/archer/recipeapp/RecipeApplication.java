package android.the.coding.archer.recipeapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RecipeApplication extends Application {

    private static final int SCHEMA_VERSION = 2;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration configuration =
                new RealmConfiguration.Builder()
                        .schemaVersion(SCHEMA_VERSION)
                        .migration(new Migration())
                        .name("recipe.realm")
                        .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
