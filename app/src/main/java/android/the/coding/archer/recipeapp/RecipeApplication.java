package android.the.coding.archer.recipeapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RecipeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration configuration =
                new RealmConfiguration.Builder()
                        .name("recipe.realm")
                        .deleteRealmIfMigrationNeeded()
                        .build();

        Realm.deleteRealm(configuration);

        Realm.setDefaultConfiguration(configuration);
    }
}
