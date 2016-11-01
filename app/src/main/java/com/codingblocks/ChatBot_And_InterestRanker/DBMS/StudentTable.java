package com.codingblocks.ChatBot_And_InterestRanker.DBMS;

/**
 * Created by Sachin on 9/3/2016.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.codingblocks.ChatBot_And_InterestRanker.StudentModel;

import java.util.ArrayList;


/**
 * Created by championswimmer on 30/12/15.
 */
public class StudentTable {
    public static final String TABLE_NAME = "Student";

    public static final String ID = "student_id";

    public static final String STUDENT_NAME = "student_name";

    public static final String USER_NAME = "user_name";

    public static final String BATCH_ID = "batch_id";

    public static final String[] PROJECTION =
            {ID, STUDENT_NAME, USER_NAME, BATCH_ID};

    public static final String CMD_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
                    + STUDENT_NAME + " TEXT , "
                    + USER_NAME + " TEXT , "
                    + BATCH_ID + " INTEGER "
                    + " );";


    public static ArrayList<StudentModel> getByArg(SQLiteDatabase db, int id) {
        Cursor c = db.query(
                true,
                StudentTable.TABLE_NAME,
                StudentTable.PROJECTION,
                BATCH_ID+" = ?",
                new String[]{String.valueOf(id)},
                //null,
                //null,
                null,
                null,
                null,null
        );
        //BATCH_ID+" = ?"
        // new String[]{String.valueOf(id)}

        Log.i("DatabaseContentCount", c.getCount() + "");

        ArrayList<StudentModel> students = new ArrayList<>();
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
            return db.delete(TABLE_NAME, ID + "=" + id, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static long save(SQLiteDatabase db, StudentModel student) {

        ContentValues cv = new ContentValues();
        Log.i("StudentDetails", student.getStudent_name() + " " + student.getUser_name() + " " + student.getBatch_id());

        cv.put(STUDENT_NAME, student.getStudent_name());
        cv.put(USER_NAME, student.getUser_name());
        cv.put(BATCH_ID, student.getBatch_id());

        //Log.i("himanshu", cv.size() + "");

        long result = db.insert(TABLE_NAME, null, cv);

        Log.e("Result After Inserting", "" + result);
        return result;
    }

}