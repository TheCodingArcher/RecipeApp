package android.the.coding.archer.recipeapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.the.coding.archer.recipeapp.model.Recipe;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "recipes.db";
    public static final int VERSION_NUMBER = 1;

    public DatabaseSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    static {
        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations().build());
        cupboard().register(Recipe.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).dropAllTables();
        cupboard().withDatabase(db).createTables();
    }
}
