package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteexample.dao.StudentDao;
import com.example.sqliteexample.database.StudentDatabase;
import com.example.sqliteexample.entity.Student;

public class MainActivity extends AppCompatActivity {

    EditText etStNo, etStName, etStMarks;

    StudentDatabase database;
    StudentDao studentDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etStNo = findViewById(R.id.etStNo);
        etStName = findViewById(R.id.etStName);
        etStMarks = findViewById(R.id.etStMarks);

        database = StudentDatabase.getInstance(this); // getting the reference of Database
        studentDao = database.getStudentDao();

    }

    public void insertData(View view) {
        try { // success
            int stNo = Integer.parseInt(etStNo.getText().toString());
            String stName = etStName.getText().toString();
            int stMarks = Integer.parseInt(etStMarks.getText().toString());
            Student student = new Student();
            student.setStNo(stNo);
            student.setStName(stName);
            student.setStMarks(stMarks);
            studentDao.insert(student);

        } catch (NumberFormatException nfe) {  // failure
            Toast.makeText(this, "Number Format exception occurred", Toast.LENGTH_LONG).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Database related exception occurred", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        etStNo.setText(""); // success or failure
        etStName.setText("");
        etStMarks.setText("");

/*
        long rowId = sqliteHelper.insertDataIntoTable(stNo, stName, stMarks);

        if (rowId > 0) {
            Toast.makeText(this, "Data Insertion Successful", Toast.LENGTH_LONG).show();
            etStNo.setText("");
            etStName.setText("");
            etStMarks.setText("");
        } else {
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_LONG).show();
        }*/
    }

    public void retrieveData(View view) {
        int stNo = Integer.parseInt(etStNo.getText().toString());


        try {
            Student st = studentDao.retrieveByRollNo(stNo);

            int studentNo = st.getStNo();
            String studentName = st.getStName();
            int studentMarks = st.getStMarks();

            etStNo.setText(studentNo + "");
            etStName.setText(studentName);
            etStMarks.setText(studentMarks + "");
        } catch (Exception e) {
            Toast.makeText(this, "retreieve operation failed", Toast.LENGTH_LONG).show();
        }


       /* Cursor cursor = sqliteHelper.retrieveDataFromTable(stNo);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int studentNo = cursor.getInt(1);
            String studentName = cursor.getString(2);
            int studentMarks = cursor.getInt(3);
            etStNo.setText(studentNo + "");
            etStName.setText(studentName);
            etStMarks.setText(studentMarks + "");
        } else {
            Toast.makeText(this, "Data Retrieval Failed", Toast.LENGTH_LONG).show();
        }*/

    }

    public void updateData(View view) {
        int stNo = Integer.parseInt(etStNo.getText().toString());
        String stName = etStName.getText().toString();
        int stMarks = Integer.parseInt(etStMarks.getText().toString());

        Student st = new Student();
        st.setStNo(stNo);
        st.setStName(stName);
        st.setStMarks(stMarks);

        //studentDao.insert(st);  for Conflict((onConflict = OnConflictStrategy.REPLACE))

        studentDao.update(st);

        etStNo.setText("");
        etStName.setText("");
        etStMarks.setText("");

       /* long rowId = sqliteHelper.updateDataIntoTable(stNo, stName, stMarks);

        if (rowId > 0) {
            Toast.makeText(this, "Data Update Successful", Toast.LENGTH_LONG).show();
            etStNo.setText("");
            etStName.setText("");
            etStMarks.setText("");
        } else {
            Toast.makeText(this, "Data Update Failed", Toast.LENGTH_LONG).show();
        }*/
    }

    public void deleteData(View view) {
        int stNo = Integer.parseInt(etStNo.getText().toString());

        studentDao.delete(stNo);


        /*int rowAffected = sqliteHelper.deleteDataFromTable(stNo);
        if (rowAffected > 0) {
            Toast.makeText(this, "Data Deletion Successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data Deletion Failed", Toast.LENGTH_LONG).show();
        }*/
    }
}
