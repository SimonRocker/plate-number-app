package simon.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by admin on 11.10.2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Number_plate_app.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_ENTRIES = "entries";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PLATE_NUMBER = "plate_number";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_HISTORY_NUMBER = "history_number";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ENTRIES +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PLATE_NUMBER + " TEXT NOT NULL, " +
                    COLUMN_RESULT + " TEXT NOT NULL, " +
                    COLUMN_HISTORY_NUMBER + " INTEGER NOT NULL); ";

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try {
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
