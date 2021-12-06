package com.lasalle.exercie.studenthelpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lasalle.exercie.studenthelpproject.model.Student;
import com.lasalle.exercie.studenthelpproject.model.User;

public class StudentRegistration extends AppCompatActivity implements View.OnClickListener {

    EditText edFirstName, edLastName, edGender, edEmail, edDOB,edUsername,edPassword,edStudentId;
    Button btnRegister;

    DatabaseReference studentHelpDatabaseStudent,studentHelpDatabaseUser;
    private static  int count = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        initialize();
    }

    private void initialize() {
        edFirstName =  findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edGender = findViewById(R.id.edGender);
        edEmail = findViewById(R.id.edEmail);
        edDOB = findViewById(R.id.edDOB);
        edUsername = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        edStudentId = findViewById(R.id.edStudentId);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        studentHelpDatabaseStudent = FirebaseDatabase.getInstance().getReference("Student");
        studentHelpDatabaseUser = FirebaseDatabase.getInstance().getReference("Users");
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void register() {
        try{

            String dateOfBirth = edDOB.getText().toString();
            String email = edEmail.getText().toString();
            String firstName = edFirstName.getText().toString();
            String lastName = edLastName.getText().toString();
            String gender = edGender.getText().toString();
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            int studentId = Integer.valueOf(edStudentId.getText().toString());
            int userid = studentId;

            String title = "student";
            Student oneStudent = new Student(studentId,firstName,lastName,email,gender,dateOfBirth);
            User oneUser = new User(userid,username,password,title);
            studentHelpDatabaseStudent.child(String.valueOf(studentId)).setValue(oneStudent);
            studentHelpDatabaseUser.child(username).setValue(oneUser);
            Toast.makeText(this,"Registered successfully!",Toast.LENGTH_LONG).show();
            clearWidgets();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    private void clearWidgets() {
        edFirstName.setText(null);
        edLastName.setText(null);
        edDOB.setText(null);
        edEmail.setText(null);
        edGender.setText(null);
        edUsername.setText(null);
        edPassword.setText(null);
        edStudentId.setText(null);
    }
}