package com.example.sqliteexample.database;

import android.content.Context;
import android.content.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sqliteexample.dao.StudentDao;
import com.example.sqliteexample.entity.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase database;

    public abstract StudentDao getStudentDao();

    public static StudentDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "University.db").allowMainThreadQueries().build();
        }
        return database;
    }

}
