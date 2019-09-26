package com.example.sqliteexample.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sqliteexample.entity.Student;

@Dao
public interface StudentDao {

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Student student);*/

    @Insert
    public void insert(Student student);

    @Query("Select * from Student where Student_Number = :stNum")
    public Student retrieveByRollNo(int stNum);

    @Update
    public void update(Student student);

    @Query("Delete from Student where Student_Number = :stNo")
    public void delete(int stNo);
}
