package com.lasalle.exercie.studenthelpproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasalle.exercie.studenthelpproject.model.User;

import java.util.ArrayList;


public class StudentLoginPage extends AppCompatActivity implements View.OnClickListener, ValueEventListener {



    Button btnStudentLogin;
    ArrayList<User> listUsers;
    DatabaseReference userDB, userChild;

    EditText edUserName, edPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_page);
        initialize();
    }

    private void initialize() {
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);

        btnStudentLogin =findViewById(R.id.btnStudentLogin);
        btnStudentLogin.setOnClickListener(this);

        listUsers = new ArrayList<User>();
        userDB = FirebaseDatabase.getInstance().getReference("Users");

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.btnStudentLogin:
                validate();

            break;
    }
}

    private void validate() {
        String uname = edUserName.getText().toString();
        userChild = userDB.child(uname);
        userChild.addValueEventListener(this);

    }


    private void toProfilePage(String title, String id) {

        if(title.equals("student"))
        {
            Intent i = new Intent(this ,StudentProfilePage.class);
            i.putExtra("studentid", id);
            startActivity(i);
        }else{
            Intent i = new Intent(this ,tutorProfile.class);
            i.putExtra("tutorid", id);
            startActivity(i);
        }



    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String uname = edUserName.getText().toString();
        String pw= edPassword.getText().toString();

        if(snapshot.exists()) {

            String pword = snapshot.child("password").getValue().toString();
            String title = snapshot.child("title").getValue().toString();
            String uid = snapshot.child("userId").getValue().toString();
            if (pw.equals(pword) ) {

                toProfilePage(title,uid);
            }
        }else{
            Toast.makeText(this,"The document with the id "  + " doesn't exist",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {


    }

}
