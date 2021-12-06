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
import com.lasalle.exercie.studenthelpproject.model.Tutor;
import com.lasalle.exercie.studenthelpproject.model.User;

public class TutorRegistration extends AppCompatActivity implements View.OnClickListener {

    EditText edFirstName, edLastName, edGender, edEmail, edDOB, edSkill, edUsername, edPassword, edTutorId;
    Button btnRegister;

    DatabaseReference tutorHelpDatabase, tutorHelpDatabaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_registration);
        initialize();
    }

    private void initialize() {
        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edGender = findViewById(R.id.edGender);
        edEmail = findViewById(R.id.edEmail);
        edDOB = findViewById(R.id.edDOB);
        edSkill = findViewById(R.id.edSkill);
        btnRegister = findViewById(R.id.btnRegister);
        edUsername = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        edTutorId = findViewById(R.id.edTutorId);
        btnRegister.setOnClickListener(this);

        tutorHelpDatabase = FirebaseDatabase.getInstance().getReference("Tutor");
        tutorHelpDatabaseUser = FirebaseDatabase.getInstance().getReference("Users");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnRegister:
                register();
                break;
        }

    }

    private void register() {

        try {
            String dateOfBirth = edDOB.getText().toString();
            String email = edEmail.getText().toString();
            String firstName = edFirstName.getText().toString();
            String lastName = edLastName.getText().toString();
            String gender = edGender.getText().toString();
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            String skill = edSkill.getText().toString();
            int tutorid = Integer.valueOf(edTutorId.getText().toString());
            int userid = tutorid;
            String title = "tutor";
            Tutor oneTutor = new Tutor(tutorid, firstName, lastName, email, gender, dateOfBirth, skill);
            User oneUser = new User(userid, username, password, title);
            tutorHelpDatabase.child(String.valueOf(oneTutor.getTutorId())).setValue(oneTutor);
            tutorHelpDatabaseUser.child(username).setValue(oneUser);
            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_LONG).show();
            clearWidgets();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clearWidgets() {
        edFirstName.setText(null);
        edLastName.setText(null);
        edDOB.setText(null);
        edSkill.setText(null);
        edEmail.setText(null);
        edGender.setText(null);
        edUsername.setText(null);
        edPassword.setText(null);
        edTutorId.setText(null);
    }

}