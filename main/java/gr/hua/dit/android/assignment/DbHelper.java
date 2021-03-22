package gr.hua.dit.android.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.content.ContentValues.TAG;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SplittableRandom;

public class DbHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "MY_DB";
    public static String TABLE_NAME = "MY_TABLE";
    ArrayList<ContactContract> dbHelperData = new ArrayList<ContactContract>();

    //Database Fields
    public static String id = "id";
    public static String userid = "userid";
    public static String longitude = "longitude";
    public static String latitude = "latitude";
    public static String dt = "timestamp";
    private String SQL_QUERY = " CREATE TABLE " + TABLE_NAME + " ( " + id + " TEXT," + userid + " TEXT," + longitude + " TEXT, " + latitude + " TEXT," + dt + " TEXT ) ";
    ArrayList<ContactContract> myitems_dbHelper = new ArrayList<ContactContract>();


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long CreateEntry(ContactContract contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, contact.getId());
        values.put(userid, contact.getUserid());
        values.put(longitude, contact.getLongitude());
        values.put(latitude, contact.getLatitude());
        values.put(dt, contact.getDt());

        long insertId = db.insert(TABLE_NAME, null, values);


        return insertId;


    }


    public ArrayList<String> spinnerGetData(){ //method which takes the timestamps from db and pass them to arraylist
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<String> r=new ArrayList<>();
        r.add("");
        String[] projection = new String[]{DbHelper.dt};
        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null, null, null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            for(int i=0; i<cursor.getCount();i++){
                r.add(cursor.getString(cursor.getColumnIndex(DbHelper.dt)));
                cursor.moveToNext();
            }


        }
        cursor.close();
        db.close();
        return r;
    }



    public ArrayList<String> readData(String userid, String tms) {
        SQLiteDatabase db = this.getReadableDatabase();// When reading data one should always just get a readable database.
        String[] selectionArgs = new String[]{userid, tms};
        ArrayList<String> r = new ArrayList<>();

        String[] projection = new String[]{   // Define a projection that specifies which columns from the database you will actually use after this query.
                DbHelper.userid,
                DbHelper.dt,
                DbHelper.id,
                DbHelper.latitude,
                DbHelper.longitude,


        };
        String selection = this.userid + "=? and " + this.dt + "=?";

        if (userid != null && tms != null) {
            Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.id)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.userid)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.longitude)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.latitude)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.dt)));
                    cursor.moveToNext();


                }
            }


            cursor.close();
            db.close();

        }
        return r;
    }

    public ArrayList<String> readDataOnlyUserId(String userid) {
        SQLiteDatabase db = this.getReadableDatabase();// When reading data one should always just get a readable database.
        String[] selectionArgs = new String[]{userid};
        ArrayList<String> r = new ArrayList<>();

        String[] projection = new String[]{   // Define a projection that specifies which columns from the database you will actually use after this query.
                DbHelper.userid,
                DbHelper.dt,
                DbHelper.id,
                DbHelper.latitude,
                DbHelper.longitude,


        };
        String selection = this.userid + "=?" ;

        if (userid != null ) {
            Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor.getCount() != 0) {

                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.id)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.userid)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.longitude)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.latitude)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.dt)));
                    cursor.moveToNext();


                }
            }


            cursor.close();
            db.close();

        }
        return r;
    }

    public ArrayList<String> readDataOnlyDt( String tms) {
        SQLiteDatabase db = this.getReadableDatabase();// When reading data one should always just get a readable database.
        String[] selectionArgs = new String[]{tms};
        ArrayList<String> r = new ArrayList<>();

        String[] projection = new String[]{   // Define a projection that specifies which columns from the database you will actually use after this query.
                DbHelper.userid,
                DbHelper.dt,
                DbHelper.id,
                DbHelper.latitude,
                DbHelper.longitude,


        };
        String selection =  this.dt + "=?";

        if (tms != null) {
            Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.id)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.userid)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.longitude)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.latitude)));
                    r.add(cursor.getString(cursor.getColumnIndex(DbHelper.dt)));
                    cursor.moveToNext();


                }
            }


            cursor.close();
            db.close();

        }
        return r;
    }









}

