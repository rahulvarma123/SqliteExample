package com.example.sqliteexample.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student { //Table name

    @PrimaryKey
    @ColumnInfo(name = "Student_Number")
    int stNo;

    @ColumnInfo(name = "Student_Name")
    String stName;

    @ColumnInfo(name="Student_Marks")
    int stMarks;

    public int getStNo() {
        return stNo;
    }

    public void setStNo(int stNo) {
        this.stNo = stNo;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public int getStMarks() {
        return stMarks;
    }

    public void setStMarks(int stMarks) {
        this.stMarks = stMarks;
    }
}
