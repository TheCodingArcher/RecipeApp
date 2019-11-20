package android.the.coding.archer.recipeapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RecipeDataSource {

    public static final String TAG = RecipeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public RecipeDataSource(Context context) {
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    public void open() {
        this.database = dbHelper.getWritableDatabase();

        Log.d(TAG, "Database is Opened.");
    }

    public void close() {
        dbHelper.close();

        Log.d(TAG, "Database is Closed.");
    }
}
