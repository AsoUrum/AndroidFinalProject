package com.lasalle.exercie.studenthelpproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasalle.exercie.studenthelpproject.model.Student;
import com.lasalle.exercie.studenthelpproject.model.TutorialAssignment;

import java.sql.Date;
import java.util.ArrayList;

public class StudentProfilePage extends AppCompatActivity implements View.OnClickListener, ValueEventListener, ChildEventListener {

    Button btnStudentLogout, btnGetHelp;
    TextView tvStudentName,tvStudentProfile;
    DatabaseReference studentDB,studentChild;
    Student astudent;
    String studentId;

    ListView LvAppointment;
    ArrayList<TutorialAssignment> ListAppointment;
    ArrayAdapter<TutorialAssignment> lvAdapter;
    DatabaseReference TADatabase,ChildDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_page);
        initialize();
    }

    private void initialize() {
        btnStudentLogout = findViewById(R.id.btnStudentLogout);
        btnGetHelp = findViewById(R.id.btnGetHelp);
        tvStudentName = findViewById(R.id.tvStudentName);
        tvStudentProfile = findViewById(R.id.tvStudentsProfile);
        btnStudentLogout.setOnClickListener(this);
        btnGetHelp.setOnClickListener(this);

        Intent i = getIntent();
        studentId = i.getStringExtra("studentid");

        astudent = new Student();
        studentDB = FirebaseDatabase.getInstance().getReference("Student");

        getStudent();

        TADatabase = FirebaseDatabase.getInstance().getReference("TutorialAssignment");
        getAppointment();

        LvAppointment = findViewById(R.id.lvAppointment);
        ListAppointment= new ArrayList<>();
        lvAdapter = new ArrayAdapter<TutorialAssignment>(this, android.R.layout.simple_list_item_1, ListAppointment);
        LvAppointment.setAdapter(lvAdapter);



    }

    private void getAppointment() {
        ChildDatabase = TADatabase.child(studentId);
        ChildDatabase.addChildEventListener(this);
    }

    private void getStudent() {
        studentChild = studentDB.child(studentId);
        studentChild.addValueEventListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.btnStudentLogout:
                toStudentLoginPage();
                break;
            case R.id.btnGetHelp:
                toGetHelp();
    }
}

    private void toGetHelp() {

        Intent i = new Intent(this ,TutorStudentAssignment.class);
        i.putExtra("studentid", studentId);
        startActivity(i);
        this.finish();
    }

    private void toStudentLoginPage() {

        Intent i = new Intent(this ,MainActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        if(snapshot.exists()) {
            astudent.setStudentId(Integer.valueOf( snapshot.child("studentId").getValue().toString()));
            astudent.setFirstName(snapshot.child("firstName").getValue().toString());
            astudent.setLastName(snapshot.child("lastName").getValue().toString());
            astudent.setGender(snapshot.child("gender").getValue().toString());
            astudent.setDateOfBirth(snapshot.child("dateOfBirth").getValue().toString());
            astudent.setEmail(snapshot.child("email").getValue().toString());

           String name = astudent.getFirstName() + " " + astudent.getLastName();

            tvStudentName.setText(name);
            tvStudentProfile.setText(astudent.toString());

        }else{
            Toast.makeText(this,"The document with the id "  + " doesn't exist",Toast.LENGTH_LONG).show();

        }

    }

    ////// appoitnments

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        if (snapshot.exists()){
            String StudentId = snapshot.child("studentId").getValue().toString();
            String TutorId = snapshot.child("tutorId").getValue().toString();
            String TutorialDate = snapshot.child("tutorialDate").getValue().toString();
            String TutorialDescription = snapshot.child("tutorialDescription").getValue().toString();
           // String tid = snapshot.child("tId").getValue().toString();
            TutorialAssignment tutorialAssignment = new TutorialAssignment(Integer.valueOf(StudentId),Integer.valueOf(TutorId), TutorialDate,TutorialDescription);
            ListAppointment.add(tutorialAssignment);
            lvAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(this,"Nothing",Toast.LENGTH_LONG).show();
        }






    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}