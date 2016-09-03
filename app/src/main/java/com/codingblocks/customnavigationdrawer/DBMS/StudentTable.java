package com.codingblocks.customnavigationdrawer.DBMS;

/**
 * Created by Sachin on 9/3/2016.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;

import com.codingblocks.customnavigationdrawer.StudentModel;

import java.util.ArrayList;



/**
 * Created by championswimmer on 30/12/15.
 */
public class StudentTable{
    public static final String TABLE_NAME = "student";

    public static final String ID = "student_id";

    public static final String STUDENT_NAME = "student_name";


    public static final String USER_NAME = "user_name";
    public static final String BATCH_ID = "batch_id";

    public static final String[] PROJECTION =
            {ID,STUDENT_NAME,USER_NAME, BATCH_ID};

    public static final String CMD_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                    + ID + " INTEGER PRIMARY KEY , "

                    + STUDENT_NAME + " TEXT , "
                    + USER_NAME + " TEXT , "
                    + BATCH_ID + " REFERENCES " + BatchTable.TABLE_NAME + " ON DELETE CASCADE ON UPDATE CASCADE"
                    + " );";

//    public static Refuel getById (SQLiteDatabase db, long id) {
//        Cursor c = db.query(
//                RefuelTable.TABLE_NAME + " , " + ExpenseTable.TABLE_NAME,
//                Utils.concat(RefuelTable.PROJECTION, ExpenseTable.PROJECTION),
//                RefuelTable.EXP_ID + " = " + ExpenseTable.ID + " AND " + RefuelTable.ID + " = " +  id,
//                null,
//                null,
//                null,
//                null
//        );
//
//        c.moveToFirst();
//        Refuel ref = new Refuel(
//                c.getInt(c.getColumnIndexOrThrow(ID)),
//                c.getDouble(c.getColumnIndexOrThrow(ODOMETER)),
//                c.getDouble(c.getColumnIndexOrThrow(RATE)),
//                c.getDouble(c.getColumnIndexOrThrow(LITRE)),
//                c.getString(c.getColumnIndexOrThrow(PUMP_NAME)),
//                (c.getInt(c.getColumnIndexOrThrow(FULL_TANK)) != 0),
//                new Expense(
//                        c.getInt(c.getColumnIndexOrThrow(ExpenseTable.ID)),
//                        c.getDouble(c.getColumnIndexOrThrow(ExpenseTable.AMOUNT)),
//                        c.getLong(c.getColumnIndexOrThrow(ExpenseTable.TIMESTAMP)),
//                        c.getString(c.getColumnIndexOrThrow(ExpenseTable.DESC)),
//                        c.getString(c.getColumnIndexOrThrow(ExpenseTable.TYPE))
//                )
//        );
//        c.close();
//        return ref;
//
//    }
//
    public static ArrayList<StudentModel> getByArg ( SQLiteDatabase db, int id, String sortBy ) {
        Cursor c = db.query(
                StudentTable.TABLE_NAME,
                StudentTable.PROJECTION,
                "StudentTable.BATCH_ID=?",
                new String[]{""+id},
                null,
                null,
                null
        );

        ArrayList<StudentModel> students = new ArrayList<>();
        c.moveToFirst();
        while (! c.isAfterLast()) {
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

    public static int deleteById (SQLiteDatabase db,int id) {
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

//    public static int update (SQLiteDatabase db, Refuel rf) {
//        try {
//            rf.getExpense().setDesc("Refuel at " + rf.getPumpName());
//            rf.getExpense().setType("refuel");
//            ExpenseTable.update(db, rf.getExpense());
//
//            ContentValues cv = new ContentValues();
//            cv.put(ODOMETER, rf.getOdometer());
//            cv.put(LITRE, rf.getLitre());
//            cv.put(RATE, rf.getRate());
//            cv.put(PUMP_NAME, rf.getPumpName());
//            cv.put(FULL_TANK, rf.isFullTank());
//            cv.put(EXP_ID, rf.getExpense().getId());
//
//            return db.update(
//                    TABLE_NAME,
//                    cv,
//                    ID + "=" + rf.getId(),
//                    null
//            );
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return 0;
//        }
//
//    }
    public static long save (SQLiteDatabase db, StudentModel student) {
        ContentValues cv = new ContentValues();
        cv.put(ID,student.getId());
        cv.put(STUDENT_NAME, student.getStudent_name());
        cv.put(BATCH_ID,student.getBatch_id());
        cv.put(USER_NAME, student.getUser_name());


      //  cv.put(EXP_ID, ExpenseTable.save(db, rf.getExpense()));

        return db.insert(
                TABLE_NAME,
                null,
                cv
        );
    }

}