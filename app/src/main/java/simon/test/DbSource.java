package simon.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11.10.2017.
 */

public class DbSource {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] columns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_PLATE_NUMBER,
            DbHelper.COLUMN_RESULT,
            DbHelper.COLUMN_HISTORY_NUMBER
    };


    public DbSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private DbElement cursorToDbElement(Cursor cursor) {
       if(cursor.getCount()==0){
           return null;
       }
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idPlateNumber = cursor.getColumnIndex(DbHelper.COLUMN_PLATE_NUMBER);
        int idResult = cursor.getColumnIndex(DbHelper.COLUMN_RESULT);
        int idHistory = cursor.getColumnIndex(DbHelper.COLUMN_HISTORY_NUMBER);

        String result = cursor.getString(idResult);
        String plateNumber = cursor.getString(idPlateNumber);
        int history = cursor.getInt(idHistory);
        long id = cursor.getLong(idIndex);

        return new DbElement(id, plateNumber, result, history);
    }

    /**
     * Creates a DbElement.
     *
     * @param plateNumber
     * @param result
     * @return DbElement
     */
    public DbElement createDbElement(String plateNumber, String result) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PLATE_NUMBER, plateNumber);
        values.put(DbHelper.COLUMN_RESULT, result);
        values.put(DbHelper.COLUMN_HISTORY_NUMBER, -1);

        long insertId = database.insert(DbHelper.TABLE_ENTRIES, null, values);
        Log.d("Simon",String.valueOf(insertId));
        Cursor cursor = database.query(DbHelper.TABLE_ENTRIES,
                columns, DbHelper.COLUMN_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        DbElement element = cursorToDbElement(cursor);
        cursor.close();

        return element;
    }

    public DbElement getElement(String plate_number) {
        Cursor cursor;
        String [] array = {plate_number};
            cursor = database.query(DbHelper.TABLE_ENTRIES, columns, DbHelper.COLUMN_PLATE_NUMBER
                    + " = ?", array, null, null, null);
            if (cursor.getCount() == 0) {
            cursor.close();
                return null;
            }
                cursor.moveToFirst();
        DbElement element = cursorToDbElement(cursor);
        cursor.close();
            return element;



    }

    public DbElement getElementResult (String result) {

        DbElement element;

        if(result.equalsIgnoreCase("hof")){
            element = getElement("HO");
            return element;
        } else if(result.equalsIgnoreCase("aue")){
            element = getElement("AU");
            return element;
        } else {
            if (result.length() <= 3) {
                return null;
            }
            Cursor cursor;
            String[] array = new String[]{result.substring(0, 1) + result.substring(1).toLowerCase() + "%"};
            cursor = database.query(DbHelper.TABLE_ENTRIES, columns, DbHelper.COLUMN_RESULT + " LIKE ?", array, null, null, null, null);
            if (cursor.getCount() == 0) {
                cursor.close();
                return null;
            }
            cursor.moveToFirst();
             element = cursorToDbElement(cursor);
            cursor.close();
            return element;
        }

    }

    public List<DbElement> getAllDbElementsOrderByHistoryNumber() {
        List<DbElement> DbElementList = new ArrayList<>();
        String [] array = {"-1"};
        Cursor cursor = database.query(DbHelper.TABLE_ENTRIES,
                columns,DbHelper.COLUMN_HISTORY_NUMBER + " > ?",array , null, null, DbHelper.COLUMN_HISTORY_NUMBER + " DESC");


        cursor.moveToFirst();
        DbElement DbElement;

        while (!cursor.isAfterLast()) {
            DbElement = cursorToDbElement(cursor);
            DbElementList.add(DbElement);
            cursor.moveToNext();
        }

        cursor.close();

        return DbElementList;
    }


 /** public void deleteDbElement(DbElement element) {
  long id = element.getId();
  database.delete(DbHelper.TABLE_ENTRIES,
  DbHelper.COLUMN_ID + "=" + id,
  null);
  }*/

  public void update(DbElement element, int history_number){
      ContentValues values = new ContentValues();
      values.put(DbHelper.COLUMN_PLATE_NUMBER, element.getPlate_number());
      values.put(DbHelper.COLUMN_RESULT, element.getResult());
      values.put(DbHelper.COLUMN_HISTORY_NUMBER, history_number);
      String where= DbHelper.COLUMN_PLATE_NUMBER +  " =  ?";
      String [] args = {element.getPlate_number()};
      database.update(DbHelper.TABLE_ENTRIES,values,where, args );
  }

    public void update(DbElement element, String result, int history_number){
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PLATE_NUMBER, element.getPlate_number());
        values.put(DbHelper.COLUMN_RESULT, result);
        values.put(DbHelper.COLUMN_HISTORY_NUMBER, history_number);
        String where= DbHelper.COLUMN_PLATE_NUMBER +  " =  ?";
        String [] args = {element.getPlate_number()};
        database.update(DbHelper.TABLE_ENTRIES,values,where, args );
    }


}

