package com.codingblocks.customnavigationdrawer.DBMS;

/**
 * Created by Sachin on 9/3/2016.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;

import com.codingblocks.customnavigationdrawer.StudentModel;

import java.util.ArrayList;


/**
 * Created by championswimmer on 30/12/15.
 */
public class StudentTable {
    public static final String TABLE_NAME = "student";

    public static final String ID = "student_id";

    public static final String STUDENT_NAME = "student_name";


    public static final String USER_NAME = "user_name";
    public static final String BATCH_ID = "batch_id";

    public static final String[] PROJECTION =
            {ID, STUDENT_NAME, USER_NAME, BATCH_ID};

    public static final String CMD_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                    + ID + " INTEGER PRIMARY KEY , "

                    + STUDENT_NAME + " TEXT , "
                    + USER_NAME + " TEXT , "
                    + BATCH_ID + " REFERENCES " + BatchTable.TABLE_NAME + " ON DELETE CASCADE ON UPDATE CASCADE"
                    + " );";


    public static ArrayList<StudentModel> getByArg(SQLiteDatabase db, int id) {
        Cursor c = db.query(
                StudentTable.TABLE_NAME,
                StudentTable.PROJECTION,
                null,
                null,
                null,
                null,
                null
        );
        //BATCH_ID+" = ?"
        // new String[]{String.valueOf(id)}
        Log.i("sahin", c.getCount() + "");

        ArrayList<StudentModel> students = new ArrayList<>();
        //c.moveToFirst();
        while (c.moveToNext()) {
            students.add(
                    new StudentModel(
                            c.getInt(c.getColumnIndexOrThrow(ID)),
                            c.getString(c.getColumnIndexOrThrow(STUDENT_NAME)),
                            c.getString(c.getColumnIndexOrThrow(USER_NAME)),
                            c.getInt(c.getColumnIndexOrThrow(BATCH_ID))
                    )

            );
        }
        c.close();
        return students;

    }

    public static int deleteById(SQLiteDatabase db, int id) {
        /*
        We can just delete the parent expense row.
        The ON DELETE CASCADE clause, will make sure the
        refuel row is also deleted.
         */
        try {
            return db.delete(
                    TABLE_NAME,
                    ID + "=" + id,
                    null
            );
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static long save(SQLiteDatabase db, StudentModel student) {
        ContentValues cv = new ContentValues();
        //Log.i("Details", student.getId() + " " + student.getStudent_name() + " " + student.getBatch_id() + " " + student.getUser_name());
        cv.put(ID, student.getId());
        cv.put(STUDENT_NAME, student.getStudent_name());
        cv.put(BATCH_ID, student.getBatch_id());
        cv.put(USER_NAME, student.getUser_name());
        //Log.i("himand", cv.size() + "");
        long result = db.insert(
                TABLE_NAME,
                null,
                cv
        );
        Log.e("dj", "" + result);
        return result;
    }

}