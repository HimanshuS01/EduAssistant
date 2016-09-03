package com.codingblocks.customnavigationdrawer.DBMS;

/**
 * Created by Sachin on 9/3/2016.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.codingblocks.customnavigationdrawer.BatchModel;

import java.util.ArrayList;



public class BatchTable{
    public static final String TABLE_NAME = "batch";

    public static final String ID = "batch_id";

    public static final String NAME = "name";



    public static final String[] PROJECTION = {ID,NAME};

    public static final String CMD_CREATE_TABLE =
            " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                    + ID + " INTEGER PRIMARY KEY , "
                    + NAME + " TEXT"
                    + " );";

    public static ArrayList<BatchModel> getByArg (SQLiteDatabase db) {
        ArrayList<BatchModel> batches = new ArrayList<>();
        Cursor c = db.query(
                TABLE_NAME,
                PROJECTION,
                null,
                null,
                null,
                null,
                null
        );
        //c.moveToFirst();
        while (c.moveToNext()) {
            batches.add(new BatchModel(
                    c.getInt(c.getColumnIndexOrThrow(ID)),

                    c.getString(c.getColumnIndexOrThrow(NAME))
            ));
        }
        c.close();
        return batches;
    }

    public static int deleteById (SQLiteDatabase db,int id) {
        try {
            return db.delete(TABLE_NAME, ID + "=" + id, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long save (SQLiteDatabase db, BatchModel batch) {
        ContentValues cv = new ContentValues();
        cv.put(ID,batch.getId());
        cv.put(NAME,batch.getBatch_name());

        return db.insert(TABLE_NAME, null,cv);
    }
}