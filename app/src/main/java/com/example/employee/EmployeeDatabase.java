package com.example.employee;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)

    public abstract class EmployeeDatabase extends RoomDatabase {

    private final static Object LOCK = new Object();
    private static final String DATABASE_NAME = "EmployeeDB";
    private static EmployeeDatabase sInstance;


    public static EmployeeDatabase getInstance(Context context) {

        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        EmployeeDatabase.class,
                        EmployeeDatabase.DATABASE_NAME)
                        .build();

            }
        }
        return sInstance;
    }

    public abstract EmployeeDao employeeDao();
}
